package com.scorchgames.wumpusworldapp;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageButton;

public class Cave {

    private Room[][] mainRoom;


    private int width;
    private int height;

    private Player player;

    private boolean win;
    private boolean lose;

    private int posX;
    private int posY;

    public Cave(int widthX, int heightY){
        this.width = widthX;
        this.height = heightY;

        mainRoom = new Room[width][height];

        for (int i = 0; i < width; i++) {
            for (int y = 0; y < height; y++) {
                mainRoom[i][y] = new Room();
            }
        }

        player = new Player();

        visited();
        setHero();
        setWumpus();
        setStinky();
        setHole();
        setWindy();
        setGold();
    }

    private void visited(){
        for (int i = 0; i < width; i++) {
            for (int y = 0; y < height; y++) {
                mainRoom[i][y].setVisited(false);
            }
        }
    }

    public void draw(ImageButton[][] buttons){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(mainRoom[i][j].getVisited()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_visitedtile);
                }
                if(mainRoom[i][j].getVisited() == false){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_not_visited_map_tile);
                }
                if(mainRoom[i][j].getHero()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_new_hero_tile);
                }

                if(mainRoom[i][j].getWumpus() && mainRoom[i][j].getVisited()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_new_wumpus_tile);
                }

                if(mainRoom[i][j].getStinky() && mainRoom[i][j].getHero()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_hero_stench);
                }

                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getStinky() && !mainRoom[i][j].getHero()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_newstenchtile);
                }

                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getWindy()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_new_breezetile);
                }

                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getHero() && mainRoom[i][j].getWindy()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_hero_breeze);
                }

                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getHole()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_newholetile);
                }

                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getGold()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_new_gold_tile);
                }

                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getGold() && mainRoom[i][j].getStinky()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_gold_stench);
                }

                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getGold() && mainRoom[i][j].getWindy()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_gold_breeze);
                }

                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getGold() && mainRoom[i][j].getWindy() && mainRoom[i][j].getStinky()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_gold_breeze_stench);
                }

                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getHero() && mainRoom[i][j].getHole()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_hero_hole);
                    lose = true;
                }

                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getHole() && mainRoom[i][j].getWumpus()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_wumpus_hole);
                }

                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getWumpus() && mainRoom[i][j].getHero()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_hero_wumpus);
                    lose = true;
                }

                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getGold() && mainRoom[i][j].getHero()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_hero_gold);
                    win = true;
                }

                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getGold() && mainRoom[i][j].getHero() && mainRoom[i][j].getStinky()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_hero_gold_stench);
                    win = true;
                }

                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getGold() && mainRoom[i][j].getHero() && mainRoom[i][j].getWindy()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_hero_gold_breeze);
                    win = true;
                }

                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getGold() && mainRoom[i][j].getHero() && mainRoom[i][j].getStinky() && mainRoom[i][j].getWindy()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_hero_gold_breeze_stench);
                    win = true;
                }

                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getWindy() && mainRoom[i][j].getStinky()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_stench_breeze);
                }

                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getWindy() && mainRoom[i][j].getWumpus()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_wumpus_breeze);
                }

                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getWindy() && mainRoom[i][j].getWumpus() && mainRoom[i][j].getHero()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_wumpus_breeze_hero);
                    lose = true;
                }

                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getHero() && mainRoom[i][j].getWindy() && mainRoom[i][j].getStinky()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_hero_breeze_stench);
                }
            }
        }
    }

    private void setHero(){
        mainRoom[4][0].setHero(true);
        mainRoom[4][0].setVisited(true);
    }

    public Boolean checkWin(){
        return win;
    }

    public boolean checkLose(){
        return lose;
    }

    public boolean isArrowShot(){
        return player.getIsArrowShot();
    }

    public void setArrowIsShot(boolean is){
        player.setIsArrowShot(is);
    }


    public void setHero(int x, int y){
        if(x == player.getPlayerX()-1 && y == player.getPlayerY() || x == player.getPlayerX()+1 && y == player.getPlayerY()
        || x == player.getPlayerX() && y == player.getPlayerY() + 1 || x == player.getPlayerX() && y == player.getPlayerY() - 1){
            //if(y == player.getPlayerY()-1 || y == player.getPlayerY()+1);
                for(int i = 0; i < width; i++){
                    for(int j = 0; j < height; j++){
                        mainRoom[i][j].setHero(false);
                    }
                }
                mainRoom[x][y].setHero(true);
                player.setPlayerX(x);
                player.setPlayerY(y);
                mainRoom[x][y].setVisited(true);
            //}
        }

    }

    private void setWumpus(){
        posX = (int) ((Math.random() * 3));
        posY = (int) ((Math.random() * 3) + 1);

        if(mainRoom[posX][posY].getHero() == false) {
            mainRoom[posX][posY].setWumpus(true);
        }
    }

    private void setHole(){
        for (int i = 0; i < width; i++) {
            for (int y = 0; y < height; y++) {
                if(Math.random() < 0.20 && mainRoom[i][y].getHero() == false) {
                    mainRoom[i][y].setHole(true);
                }

            }
        }
    }

    public void setGold(){
        int posiX = (int) ((Math.random() * 3));
        int posiY = (int) ((Math.random() * 3) + 1);

        if(mainRoom[posiX][posiY].getHole() == false){
            mainRoom[posiX][posiY].setGold(true);
        }
        else{
            setGold();
        }


    }

    private void setWindy(){
        for (int i = 0; i < width; i++) {
            for (int y = 0; y < height; y++) {
                if(mainRoom[i][y].getHole() == true) {
                    if(i != 0 && mainRoom[i-1][y].getHole() !=true) {
                        mainRoom[i-1][y].setWindy(true);
                    }
                    if(i < width-1 && mainRoom[i+1][y].getHole() !=true) {
                        mainRoom[i+1][y].setWindy(true);
                    }
                    if(y != 0 && mainRoom[i][y-1].getHole() !=true) {
                        mainRoom[i][y-1].setWindy(true);
                    }
                    if(y < height - 1 && mainRoom[i][y+1].getHole() !=true) {
                        mainRoom[i][y+1].setWindy(true);
                    }
                }
            }
        }
    }


    private void setStinky(){
        for (int i = 0; i < width; i++) {
            for (int y = 0; y < height; y++) {
                if(mainRoom[i][y].getWumpus() == true) {
                    if(i != 0 && mainRoom[i-1][y].getWumpus() !=true) {
                        mainRoom[i-1][y].setStinky(true);
                    }
                    if(i < width-1 && mainRoom[i+1][y].getWumpus() !=true) {
                        mainRoom[i+1][y].setStinky(true);
                    }
                    if(y != 0 && mainRoom[i][y-1].getWumpus() !=true) {
                        mainRoom[i][y-1].setStinky(true);
                    }
                    if(y < height - 1 && mainRoom[i][y+1].getWumpus() !=true) {
                        mainRoom[i][y+1].setStinky(true);
                    }
                }
            }
        }
    }


    public boolean didArrowConnect(String dir){
        if(dir.matches("down")){
            if(!player.getIsArrowShot()){
                if(player.getPlayerX() < posX && player.getPlayerY() == posY){
                    player.setIsArrowShot(true);
                    return true;
                }
            }
        }
        else if(dir.matches("up")){
            if(!player.getIsArrowShot()){
                if(player.getPlayerX() > posX && player.getPlayerY() == posY){
                    player.setIsArrowShot(true);
                    return true;
                }
            }
        }
        else if(dir.matches("right")){
            if(!player.getIsArrowShot()){
                if(player.getPlayerY() < posY && player.getPlayerX() == posX){
                    player.setIsArrowShot(true);
                    return true;
                }
            }
        }
        else if(dir.matches("left")){
            if(!player.getIsArrowShot()){
                if(player.getPlayerY() > posY && player.getPlayerX() == posX){
                    player.setIsArrowShot(true);
                    return true;
                }
            }
        }
        return false;
    }

    public void clearWumpus(){
        for (int i = 0; i < width; i++) {
            for (int y = 0; y < height; y++) {
                mainRoom[i][y].setStinky(false);
                mainRoom[i][y].setWumpus(false);
            }
        }
    }

    public void setAllVisited(Boolean variable){
        for (int i = 0; i < width; i++) {
            for (int y = 0; y < height; y++) {
                mainRoom[i][y].setVisited(variable);
            }
        }
    }



}
