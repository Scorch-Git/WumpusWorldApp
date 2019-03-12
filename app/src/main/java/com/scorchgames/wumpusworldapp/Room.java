package com.scorchgames.wumpusworldapp;


/**
 * Nicholas Prussen
 * (Code credits to Chase Farrar)
 *
 * Individual rooms in the cave
 */


public class Room {
    private boolean hero;
    private boolean gold;
    private boolean wumpus;
    private boolean windy;
    private boolean hole;
    private boolean stinky;
    private boolean  visited;
    private boolean isTrue = true;

    /**
     * Main Constructor for Room
     */
    public Room(){
        hero = false;
        gold = false;
        wumpus = false;
        windy = false;
        hole = false;
        stinky = false;
        visited = false;

    }

    /**
     * Seconday constructor
     * @param hero
     * @param gold
     * @param wumpus
     * @param windy
     * @param hole
     * @param stinky
     * @param visited
     */
    public Room(boolean hero, boolean gold, boolean wumpus, boolean windy, boolean hole, boolean stinky, boolean visited) {
        this.visited = visited;
        this.hole = hole;
        this.stinky = stinky;
        this.windy = windy;
        this.gold = gold;
        this.hero = hero;

    }

    public boolean getHero(){
        return hero;
    }
    public boolean getGold(){
        return gold;
    }
    public boolean getWumpus(){
        return wumpus;
    }
    public boolean getWindy(){
        return windy;
    }
    public boolean getHole(){
        return hole;
    }
    public boolean getStinky(){
        return stinky;
    }
    public boolean getVisited(){
        return visited;
    }

    public void setHero(boolean isTrue) {
        hero = isTrue;
    }
    public void setGold(boolean isTrue) {
        gold = isTrue;
    }
    public void setWumpus(boolean isTrue) {
        wumpus = isTrue;
    }
    public void setWindy(boolean isTrue) {
        windy = isTrue;
    }
    public void setHole(boolean isTrue) {
        hole = isTrue;
    }
    public void setStinky(boolean isTrue) {
        stinky = isTrue;
    }
    public void setVisited(boolean isTrue) {
        visited = isTrue;
    }
}
