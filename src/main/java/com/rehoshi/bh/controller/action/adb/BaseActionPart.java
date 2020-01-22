package com.rehoshi.bh.controller.action.adb;

import com.rehoshi.bh.domain.Point;

public abstract class BaseActionPart implements AdbActionPart {

    protected final String tap(Point point){
        return String.format("adb shell tap %d %d", point.intX(), point.intY()) ;
    }

    protected final String swipe(Point from, Point to, long duration){
        if(duration <= 500){
            duration = 550 ;
        }

        return String.format("adb shell swipe %d %d %d %d %d", from.intX(), from.intY(), to.intX(), to.intY(), duration) ;
    }

    protected final String longPress(Point point, long duration){
        return swipe(point, point, duration) ;
    }
}
