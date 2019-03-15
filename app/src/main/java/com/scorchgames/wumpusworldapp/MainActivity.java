package com.scorchgames.wumpusworldapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Nicholas Prussen
 * This is the main driver for the Wumpus World game. This initializes the cave class and controls all top level
 * textView and buttons
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Initializes Cave class
    private Cave mainCave;

    //Size of game layout
    private int x = 5;
    private int y = 5;

    //Tracks win/loss
    private boolean win;
    private boolean lose;

    //Tracks whether tiles are currently being shown
    private boolean isShown;

    //Tracks whether credits are being shown
    private Boolean isCredits;

    //For Displaying info on the screen for the user
    private TextView arrowCount;
    private TextView winLose;
    private TextView shoot;
    private TextView winCounter;
    private TextView loseCounter;

    //Credit text when Credit button clicked
    private TextView codeCred;
    private TextView assCred;

    //Tracks win/loss count
    private int winCount;
    private int loseCount;

    //This initiates a double array of buttons for the tiles
    private ImageButtonExtra[][] buttons = new ImageButtonExtra[x][y];


    //Tracks arrows for shooting arrow
    private ImageButton arrow_up;
    private ImageButton arrow_down;
    private ImageButton arrow_right;
    private ImageButton arrow_left;

    //Extra buttons for the user
    private Button reset_button;
    private Button credit_button;
    private Button show_tiles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assigns ids to arrow buttons
        arrow_up = findViewById(R.id.up_arrow);
        arrow_down = findViewById(R.id.down_arrow);
        arrow_right = findViewById(R.id.right_arrow);
        arrow_left = findViewById(R.id.left_arrow);

        //Extra button assignments
        shoot = findViewById(R.id.shoot_arrow);
        reset_button = findViewById(R.id.reset);
        show_tiles = findViewById(R.id.show_tiles);
        credit_button = findViewById(R.id.credits);

        //Assign credit texts
        codeCred = findViewById(R.id.code_cred);
        assCred = findViewById(R.id.asset_cred);

        //Assign win/loss counters
        winCounter = findViewById(R.id.win_counter);
        loseCounter = findViewById(R.id.lose_counter);

        //Initialize win/loss
        winCount = 0;
        loseCount = 0;

        isShown = false;
        isCredits = false;

        //Initialize main inner game
        mainCave = new Cave(x, y);

        //Extra text Elements
        winLose = findViewById(R.id.win_lose);
        arrowCount = findViewById(R.id.arrow_count);

        //Assigns button id and ties in x,y
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
        //first method draw
        mainCave.draw(buttons);

        /**
         * Called when reset clicked
         */
        reset_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                resetLevel();
            }
        });

        /**
         * Called when show tiles clicked
         */
        show_tiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTiles();
            }
        });

        /**
         * Called when credit button clicked
         */
        credit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCredits){
                    if(lose || win){
                        winLose.setVisibility(View.VISIBLE);
                        assCred.setVisibility(View.INVISIBLE);
                        codeCred.setVisibility(View.INVISIBLE);
                    }
                    else{
                        unBlankOut();
                    }
                    isCredits = false;
                }
                else{
                    showCredits();
                    isCredits = true;
                }
            }
        });

        /**
         *Called when up arrow clicked
         */
        arrow_up.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mainCave.didArrowConnect("up");
                mainCave.setArrowIsShot(true);
                arrowCount.setText("Arrows: 0");
                mainCave.draw(buttons);
            }
        });

        /**
         *Called when down arrow clicked
         */
        arrow_down.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mainCave.didArrowConnect("down");
                mainCave.setArrowIsShot(true);
                arrowCount.setText("Arrows: 0");
                mainCave.draw(buttons);
            }
        });

        /**
         *Called when left arrow clicked
         */
        arrow_left.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mainCave.didArrowConnect("left");
                mainCave.setArrowIsShot(true);
                arrowCount.setText("Arrows: 0");
                mainCave.draw(buttons);
            }
        });

        /**
         *Called when right arrow clicked
         */
        arrow_right.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mainCave.didArrowConnect("right");
                mainCave.setArrowIsShot(true);
                arrowCount.setText("Arrows: 0");
                mainCave.draw(buttons);
            }
        });


    }


    /**
     * Called when any tile is clicked
     * @param v button clicked
     */
    @Override
    public void onClick(View v) {

        //Disables credit text if game still going
        if(isCredits){
            assCred.setVisibility(View.INVISIBLE);
            codeCred.setVisibility(View.INVISIBLE);
        }

        //Checks for win
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
                ifWin();
            }
            else if(mainCave.checkLose()){
                lose = true;
                ifLose();
            }
        }

        //Second check for win
        //if(win){
          //  ifWin();
        //}
        //else if(lose){
        //    ifLose();
        //}



    }

    /**
     * Called when show credits is not shown and button is clicked
     */
    private void showCredits(){
        blankOut();
        winLose.setVisibility(View.INVISIBLE);
        codeCred.setVisibility(View.VISIBLE);
        assCred.setVisibility(View.VISIBLE);
    }

    /**
     * Called when win boolean is true
     */
    private void ifWin() {
        winLose.setText("You Win!");
        winLose.setVisibility(View.VISIBLE);
        show_tiles.setVisibility(View.VISIBLE);
        winCount++;
        winCounter.setText("Wins: " + winCount);
        blankOut();
    }

    /**
     * Called when lose boolean is true
     */
    private void ifLose(){
        winLose.setText("You Lose!");
        winLose.setVisibility(View.VISIBLE);
        show_tiles.setVisibility(View.VISIBLE);
        loseCount++;
        loseCounter.setText("Losses: " + loseCount);
        blankOut();
    }

    /**
     * Helper method to reduce reused code
     * Blanks out all content on win/loss
     */
    private void blankOut(){
        arrowCount.setVisibility(View.INVISIBLE);
        arrow_up.setVisibility(View.INVISIBLE);
        arrow_down.setVisibility(View.INVISIBLE);
        arrow_left.setVisibility(View.INVISIBLE);
        arrow_right.setVisibility(View.INVISIBLE);
        shoot.setVisibility(View.INVISIBLE);
    }

    /**
     * Helper method to reduce reused code
     * Unblanks out content on reset
     */
    private void unBlankOut(){
        arrowCount.setVisibility(View.VISIBLE);
        arrow_up.setVisibility(View.VISIBLE);
        arrow_down.setVisibility(View.VISIBLE);
        arrow_left.setVisibility(View.VISIBLE);
        arrow_right.setVisibility(View.VISIBLE);
        shoot.setVisibility(View.VISIBLE);
        winLose.setVisibility(View.INVISIBLE);
        show_tiles.setVisibility(View.INVISIBLE);
        assCred.setVisibility(View.INVISIBLE);
        codeCred.setVisibility(View.INVISIBLE);
    }

    /**
     * Called when reset button is clicked
     */
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


    /**
     * Called when show tiles button is clicked
     */
    private void showTiles(){
        if(!isShown){
            mainCave.setAllVisited(true);
        }
        isShown = true;
        mainCave.draw(buttons);
    }
}
