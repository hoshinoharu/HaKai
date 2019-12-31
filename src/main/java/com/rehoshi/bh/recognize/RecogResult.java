package com.rehoshi.bh.recognize;

import java.awt.geom.Rectangle2D;

public class RecogResult {
    private String desc;
    private Boolean found;
    private Double targetX;
    private Double targetY;
    private Rectangle2D.Double inSense;
    private Rectangle2D.Double intentRect;

    public RecogResult(){

    }

    public RecogResult(Double targetX, Double targetY, Rectangle2D.Double inSense) {
        this.targetX = targetX;
        this.targetY = targetY;
        this.inSense = inSense;
    }

    public RecogResult(int targetX, int targetY, Rectangle2D.Double inSense) {
        this((double) targetX, (double) targetY, inSense);
    }

    public boolean isFound() {
        if (found == null) {
            this.found = Math.abs(inSense.x - targetX) + Math.abs(inSense.y - targetY) <= 10;
        }
        return found;
    }

    public Double getTargetX() {
        return targetX;
    }

    public Double getTargetY() {
        return targetY;
    }

    public Rectangle2D.Double getInSense() {
        return inSense;
    }

    public Rectangle2D.Double getIntentRect() {
        if (intentRect == null) {
            intentRect = getInSense();
        }
        return intentRect;
    }

    public void setIntentRect(Rectangle2D.Double intentRect) {
        this.intentRect = intentRect;
    }

    public RecogResult intentRect(Rectangle2D.Double intentRect) {
        setIntentRect(intentRect);
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public RecogResult desc(String desc) {
        setDesc(desc);
        return this;
    }

    public void setTargetX(Double targetX) {
        this.targetX = targetX;
    }

    public void setTargetY(Double targetY) {
        this.targetY = targetY;
    }

    public RecogResult targetX(double targetX) {
        setTargetX(targetX);
        return this;
    }

    public RecogResult targetY(double targetY){
        setTargetY(targetY);
        return this ;
    }

    public void setInSense(Rectangle2D.Double inSense) {
        this.inSense = inSense;
    }
    public RecogResult inSense(Rectangle2D.Double inSense){
        setInSense(inSense);
        return this;
    }
}
