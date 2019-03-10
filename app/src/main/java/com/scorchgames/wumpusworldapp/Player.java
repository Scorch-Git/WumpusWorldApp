package com.scorchgames.wumpusworldapp;

public class Player {

    private boolean isArrowShot;
    private boolean isPlayerAlive;
    private int playerX;
    private int playerY;

    public Player() {
        playerX = 4;
        playerY = 0;
        isPlayerAlive = true;
        isArrowShot = false;
    }

    public Player(boolean isArrowShot, int playerX, int playerY, boolean isPlayerAlive) {
        this.isArrowShot = isArrowShot;
        this.playerX = playerX;
        this.playerY = playerY;
        this.isPlayerAlive = isPlayerAlive;
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

    public boolean getIsPlayerAlive() {
        return isPlayerAlive;
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

    public void setIsPlayerAlive(boolean istrue) {
        isPlayerAlive = istrue;
    }
}
