package com.rehoshi.bh.domain;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * 匹配矩形
 */
public class Rect extends Rectangle2D.Double {

    private double minVal;

    public Rect(double x, double y, int width, int height) {
        super(x, y, width, height);
    }

    public Rect minVal(double minVal) {
        this.minVal = minVal;
        return this;
    }

    public double getMinVal() {
        return minVal;
    }

    public Rectangle toAwtRectangle() {
        return new Rectangle((int) x, (int) y, (int) width, (int) height);
    }
}
