package com.scorchgames.wumpusworldapp;

/**
 * Nicholas Prussen
 * (Code credits to Chase Farrar)
 * This is the constructor for the player in the game
 */
public class Player {

    private boolean isArrowShot;
    private int playerX;
    private int playerY;

    /**
     * Constructor for Player
     * Initialize variables
     */
    public Player() {
        playerX = 4;
        playerY = 0;
        isArrowShot = false;
    }

    /**
     * Also constructor
     * @param isArrowShot
     * @param playerX
     * @param playerY
     */
    public Player(boolean isArrowShot, int playerX, int playerY) {
        this.isArrowShot = isArrowShot;
        this.playerX = playerX;
        this.playerY = playerY;
    }

    public boolean getIsArrowShot() {
        return isArrowShot;
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setIsArrowShot(boolean istrue) {
        isArrowShot = istrue;
    }

    public void setPlayerX(int newPlayerX) {
        playerX = newPlayerX;
    }

    public void setPlayerY(int newPlayerY) {
        playerY = newPlayerY;
    }
}
