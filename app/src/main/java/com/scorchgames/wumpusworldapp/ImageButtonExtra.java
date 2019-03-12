package com.scorchgames.wumpusworldapp;

import android.content.Context;
import android.util.AttributeSet;

/**
 * This was created to enable x/y tracking of ImageButtons in a double array
 */
public class ImageButtonExtra extends android.support.v7.widget.AppCompatImageButton {

    private int mainX;
    private int mainY;

    public ImageButtonExtra(Context context, int x, int y) {
        super(context);
        this.mainX = x;
        this.mainY = y;
    }
    public ImageButtonExtra(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageButtonExtra(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setX(int x){
        mainX= x;
    }

    public void setY(int y){
        mainY = y;
    }

    public int getMainX(){
        return mainX;
    }

    public int getMainY(){
        return mainY;
    }


}
