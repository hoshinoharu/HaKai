package com.rehoshi.bh.recognize;

import java.awt.geom.Rectangle2D;

public class RecogResult {
    private boolean finded = false ;
    private Double targetX ;
    private Double targetY ;
    private Rectangle2D.Double inSense ;

    public RecogResult(Double targetX, Double targetY, Rectangle2D.Double inSense) {
        this.targetX = targetX;
        this.targetY = targetY;
        this.inSense = inSense;
        this.finded = Math.abs(inSense.x - targetX) + Math.abs(inSense.y - targetY) <= 10;
    }

    public boolean isFinded() {
        return finded;
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
}
