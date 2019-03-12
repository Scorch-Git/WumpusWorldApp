package com.scorchgames.wumpusworldapp;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageButton;

/**
 * Nicholas Prussen
 * (Code credits to Chase Farrar)
 *
 * This creates a double array of Rooms
 */
public class Cave {

    //Main double array
    private Room[][] mainRoom;

    //Size of array
    private int width;
    private int height;

    //Calls class Player
    private Player player;

    //track win/loss
    private boolean win;
    private boolean lose;

    //layout of rooms
    private int posX;
    private int posY;

    /**
     * Main constructor, intializes
     * @param widthX x size
     * @param heightY y size
     */
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

        //Call all initialization methods
        visited();
        setHero();
        setWumpus();
        setStinky();
        setHole();
        setWindy();
        setGold();
    }

    /**
     * Intializes every room as not visited
     */
    private void visited(){
        for (int i = 0; i < width; i++) {
            for (int y = 0; y < height; y++) {
                mainRoom[i][y].setVisited(false);
            }
        }
    }

    /**
     * Initializes hero at (4,0)
     */
    private void setHero(){
        mainRoom[4][0].setHero(true);
        mainRoom[4][0].setVisited(true);
    }

    /**
     * Checks for a win
     * @return returns win/loss
     */
    public Boolean checkWin(){
        return win;
    }

    /**
     * Checks loss
     * @return returns whether loss or no
     */
    public boolean checkLose(){
        return lose;
    }

    /**
     * Checks whether arrow is shot
     * @return returns yes or no
     */
    public boolean isArrowShot(){
        return player.getIsArrowShot();
    }

    /**
     * Resets arrowCount
     * @param is boolean to reset arrowShot
     */
    public void setArrowIsShot(boolean is){
        player.setIsArrowShot(is);
    }


    /**
     * This checks the whether a button press is a valid move or not
     * @param x location of the tile x
     * @param y location of the tile y
     */
    public void setHero(int x, int y){
        if(x == player.getPlayerX()-1 && y == player.getPlayerY() || x == player.getPlayerX()+1 && y == player.getPlayerY()
        || x == player.getPlayerX() && y == player.getPlayerY() + 1 || x == player.getPlayerX() && y == player.getPlayerY() - 1){
                for(int i = 0; i < width; i++){
                    for(int j = 0; j < height; j++){
                        mainRoom[i][j].setHero(false);
                    }
                }
                mainRoom[x][y].setHero(true);
                player.setPlayerX(x);
                player.setPlayerY(y);
                mainRoom[x][y].setVisited(true);
        }

    }

    /**
     * Initializes Wumpus position
     */
    private void setWumpus(){
        posX = (int) ((Math.random() * 3));
        posY = (int) ((Math.random() * 3) + 1);

        if(mainRoom[posX][posY].getHero() == false) {
            mainRoom[posX][posY].setWumpus(true);
        }
    }

    /**
     * 20% change of setting a tile to a hole
     */
    private void setHole(){
        for (int i = 0; i < width; i++) {
            for (int y = 0; y < height; y++) {
                if(Math.random() < 0.20 && mainRoom[i][y].getHero() == false) {
                    mainRoom[i][y].setHole(true);
                }

            }
        }
    }

    /**
     * Sets one instance of gold in the map not on a hole
     */
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

    /**
     * Sets tiles windy around holes
     */
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


    /**
     * Sets tiles stinky around Wumpus
     */
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


    /**
     * takes in string direction, determines hit or not
     * @param dir direction of arrow
     */
    public void didArrowConnect(String dir){
        if(dir.matches("down")){
            if(!player.getIsArrowShot()){
                if(player.getPlayerX() < posX && player.getPlayerY() == posY){
                    player.setIsArrowShot(true);
                    clearWumpus();
                }
            }
        }
        else if(dir.matches("up")){
            if(!player.getIsArrowShot()){
                if(player.getPlayerX() > posX && player.getPlayerY() == posY){
                    player.setIsArrowShot(true);
                    clearWumpus();
                }
            }
        }
        else if(dir.matches("right")){
            if(!player.getIsArrowShot()){
                if(player.getPlayerY() < posY && player.getPlayerX() == posX){
                    player.setIsArrowShot(true);
                    clearWumpus();
                }
            }
        }
        else if(dir.matches("left")){
            if(!player.getIsArrowShot()){
                if(player.getPlayerY() > posY && player.getPlayerX() == posX){
                    player.setIsArrowShot(true);
                    clearWumpus();
                }
            }
        }
    }

    /**
     * Clears wumpus if player arrow connected
     */
    private void clearWumpus(){
        for (int i = 0; i < width; i++) {
            for (int y = 0; y < height; y++) {
                mainRoom[i][y].setStinky(false);
                mainRoom[i][y].setWumpus(false);
            }
        }
    }

    /**
     * This is called when showTiles button is pushed
     * @param variable variable for setting all tiles
     */
    public void setAllVisited(Boolean variable){
        for (int i = 0; i < width; i++) {
            for (int y = 0; y < height; y++) {
                mainRoom[i][y].setVisited(variable);
            }
        }
    }



    /**
     * This is the only hardcoding, I know no better way to do this. This applies image assets
     * when the boolean states of tiles change. THE ORDER OF THESE DOES MATTER
     * @param buttons this is the array of ImageButtonExtras
     */
    public void draw(ImageButton[][] buttons){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){

                //This sets an empty visited tile
                if(mainRoom[i][j].getVisited()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_visitedtile);
                }

                //This set a not visited tile
                if(mainRoom[i][j].getVisited() == false){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_not_visited_map_tile);
                }

                //This sets a hero + visited tile
                if(mainRoom[i][j].getHero()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_new_hero_tile);
                }

                //This sets a wumpus + visited tile
                if(mainRoom[i][j].getWumpus() && mainRoom[i][j].getVisited()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_new_wumpus_tile);
                }

                //This sets a hero + stench tile
                if(mainRoom[i][j].getStinky() && mainRoom[i][j].getHero()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_hero_stench);
                }

                //This sets a visited + stench tile
                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getStinky() && !mainRoom[i][j].getHero()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_newstenchtile);
                }

                //This sets a visited + breeze tile
                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getWindy()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_new_breezetile);
                }

                //This sets a hero + breeze tile
                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getHero() && mainRoom[i][j].getWindy()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_hero_breeze);
                }

                //This sets a normal hero tile
                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getHole()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_newholetile);
                }

                //This sets an empty gold tile
                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getGold()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_new_gold_tile);
                }

                //This sets a gold + stench tile
                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getGold() && mainRoom[i][j].getStinky()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_gold_stench);
                }

                //This sets a gold + breeze tile
                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getGold() && mainRoom[i][j].getWindy()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_gold_breeze);
                }

                //This sets a gold + breeze + stench tile
                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getGold() && mainRoom[i][j].getWindy() && mainRoom[i][j].getStinky()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_gold_breeze_stench);
                }

                //This sets a hero + hole tile
                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getHero() && mainRoom[i][j].getHole()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_hero_hole);
                    lose = true;
                }

                //This sets a wumpus + hole tile
                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getHole() && mainRoom[i][j].getWumpus()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_wumpus_hole);
                }

                //This sets a wumpus + hero tile
                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getWumpus() && mainRoom[i][j].getHero()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_hero_wumpus);
                    lose = true;
                }

                //This sets a hero + gold tile
                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getGold() && mainRoom[i][j].getHero()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_hero_gold);
                    win = true;
                }

                //This sets a hero + gold + stinky tile
                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getGold() && mainRoom[i][j].getHero() && mainRoom[i][j].getStinky()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_hero_gold_stench);
                    win = true;
                }

                //This sets a hero + gold + windy tile
                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getGold() && mainRoom[i][j].getHero() && mainRoom[i][j].getWindy()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_hero_gold_breeze);
                    win = true;
                }

                //This sets a hero + gold + stinky + windy tile
                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getGold() && mainRoom[i][j].getHero() && mainRoom[i][j].getStinky() && mainRoom[i][j].getWindy()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_hero_gold_breeze_stench);
                    win = true;
                }

                //This sets a windy + stinky tile
                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getWindy() && mainRoom[i][j].getStinky()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_stench_breeze);
                }

                //This sets a wumpus + breeze tile
                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getWindy() && mainRoom[i][j].getWumpus()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_wumpus_breeze);
                }

                //This sets a windy + wumpus + hero tile
                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getWindy() && mainRoom[i][j].getWumpus() && mainRoom[i][j].getHero()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_wumpus_breeze_hero);
                    lose = true;
                }

                //This sets a hero + windy + stinky tile
                if(mainRoom[i][j].getVisited() && mainRoom[i][j].getHero() && mainRoom[i][j].getWindy() && mainRoom[i][j].getStinky()){
                    buttons[i][j].setBackgroundResource(R.drawable.ic_hero_breeze_stench);
                }
            }
        }
    }



}
