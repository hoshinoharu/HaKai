package com.rehoshi.bh.booter.domain;

import java.awt.geom.Rectangle2D;

/**
 * 匹配矩形
 */
public class MatchRect extends Rectangle2D.Double {

    private double minVal ;

    public MatchRect(double x, double y, int width, int height) {
        super(x, y, width, height);
    }

    public MatchRect minVal(double minVal) {
        this.minVal = minVal;
        return this ;
    }

    public double getMinVal() {
        return minVal;
    }
}
