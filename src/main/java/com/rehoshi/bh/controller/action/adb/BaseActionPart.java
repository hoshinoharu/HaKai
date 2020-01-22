package com.rehoshi.bh.controller.action.adb;

import com.rehoshi.bh.domain.Point;

public abstract class BaseActionPart implements AdbActionPart {

    protected final String tap(Point point){
        return String.format("adb shell input tap %d %d", point.intX(), point.intY()) ;
    }

    protected final String swipe(Point from, Point to, long duration){
        return String.format("adb shell input swipe %d %d %d %d %d", from.intX(), from.intY(), to.intX(), to.intY(), duration) ;
    }

    protected final String longPress(Point point){
        return swipe(point, point, 550) ;
    }
}
