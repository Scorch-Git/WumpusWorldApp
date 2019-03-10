package com.scorchgames.wumpusworldapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.scorchgames.wumpusworldapp.ImageButtonExtra;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Cave mainCave;


    private int x = 5;
    private int y = 5;

    private boolean win;
    private boolean lose;
    private boolean isShown;

    private TextView arrowCount;
    private TextView winLose;
    private TextView shoot;

    private TextView winCounter;
    private TextView loseCounter;

    private int winCount;
    private int loseCount;

    private ImageButtonExtra[][] buttons = new ImageButtonExtra[x][y];

    private ImageButton arrow_up;
    private ImageButton arrow_down;
    private ImageButton arrow_right;
    private ImageButton arrow_left;

    private Button reset_button;
    private Button show_tiles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrow_up = findViewById(R.id.up_arrow);
        arrow_down = findViewById(R.id.down_arrow);
        arrow_right = findViewById(R.id.right_arrow);
        arrow_left = findViewById(R.id.left_arrow);
        shoot = findViewById(R.id.shoot_arrow);
        reset_button = findViewById(R.id.reset);
        show_tiles = findViewById(R.id.show_tiles);

        winCounter = findViewById(R.id.win_counter);
        loseCounter = findViewById(R.id.lose_counter);

        winCount = 0;
        loseCount = 0;

        isShown = false;

        mainCave = new Cave(x, y);
        winLose = findViewById(R.id.win_lose);
        arrowCount = findViewById(R.id.arrow_count);

        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
                buttons[i][j].setX(i);
                buttons[i][j].setY(j);
            }
        }
        mainCave.draw(buttons);

        reset_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                resetLevel();
            }
        });

        show_tiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTiles();
            }
        });

        arrow_up.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(mainCave.didArrowConnect("up")){
                    clearWumpus();
                }
                else{
                    mainCave.setArrowIsShot(true);
                }

                arrowCount.setText("Arrows: 0");
            }
        });

        arrow_down.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(mainCave.didArrowConnect("down")){
                    clearWumpus();
                }
                else{
                    mainCave.setArrowIsShot(true);
                }

                arrowCount.setText("Arrows: 0");
            }
        });

        arrow_left.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(mainCave.didArrowConnect("left")){
                    clearWumpus();
                }
                else{
                    mainCave.setArrowIsShot(true);
                }

                arrowCount.setText("Arrows: 0");
            }
        });

        arrow_right.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(mainCave.didArrowConnect("right")){
                    clearWumpus();
                }
                else{
                    mainCave.setArrowIsShot(true);
                }

                arrowCount.setText("Arrows: 0");
            }
        });


    }

    @Override
    public void onClick(View v) {

        if(win || lose){
            if(win){
                ifWin();
            }
            else if(lose){
                ifLose();
            }
        }
        else {
            int indexX = ((ImageButtonExtra) v).getMainX();
            int indexY = ((ImageButtonExtra) v).getMainY();
            mainCave.setHero(indexX, indexY);
            mainCave.draw(buttons);
            if(mainCave.checkWin()){
                win = true;
            }
            else if(mainCave.checkLose()){
                lose = true;
            }
        }

        if(win){
            ifWin();
        }
        else if(lose){
            ifLose();
        }



    }

    private void ifWin() {
        winLose.setText("You Win!");
        winLose.setVisibility(View.VISIBLE);
        show_tiles.setVisibility(View.VISIBLE);
        winCount++;
        winCounter.setText("Wins: " + winCount);
        blankOut();
    }

    private void ifLose(){
        winLose.setText("You Lose!");
        winLose.setVisibility(View.VISIBLE);
        show_tiles.setVisibility(View.VISIBLE);
        loseCount++;
        loseCounter.setText("Losses: " + loseCount);
        blankOut();
    }

    private void blankOut(){
        arrowCount.setVisibility(View.INVISIBLE);
        arrow_up.setVisibility(View.INVISIBLE);
        arrow_down.setVisibility(View.INVISIBLE);
        arrow_left.setVisibility(View.INVISIBLE);
        arrow_right.setVisibility(View.INVISIBLE);
        shoot.setVisibility(View.INVISIBLE);
    }

    private void unBlankOut(){
        arrowCount.setVisibility(View.VISIBLE);
        arrow_up.setVisibility(View.VISIBLE);
        arrow_down.setVisibility(View.VISIBLE);
        arrow_left.setVisibility(View.VISIBLE);
        arrow_right.setVisibility(View.VISIBLE);
        shoot.setVisibility(View.VISIBLE);
        winLose.setVisibility(View.INVISIBLE);
        show_tiles.setVisibility(View.INVISIBLE);
    }

    private void resetLevel(){
        mainCave = new Cave(x, y);
        mainCave.draw(buttons);
        win = false;
        lose = false;
        unBlankOut();
        if(mainCave.isArrowShot()){
            mainCave.setArrowIsShot(false);
        }
        arrowCount.setText("Arrows: 1");
        isShown = false;
    }

    private void clearWumpus(){
        mainCave.clearWumpus();
        mainCave.draw(buttons);
    }

    private void showTiles(){
        if(!isShown){
            mainCave.setAllVisited(true);
        }
        isShown = true;
        mainCave.draw(buttons);
    }
}
