package com.rehoshi.bh.domain;

public class Point extends java.awt.Point.Double {
    public Point(double x, double y) {
        super(x, y);
    }

    public int intX(){
        return (int) x;
    }

    public int intY(){
        return (int) y;
    }
}
