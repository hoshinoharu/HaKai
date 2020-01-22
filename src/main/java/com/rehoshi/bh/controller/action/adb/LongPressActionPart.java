package com.rehoshi.bh.controller.action.adb;

import com.rehoshi.bh.controller.action.AdbTouchAction;
import com.rehoshi.bh.domain.Point;

public class LongPressActionPart extends BaseActionPart {

    private Point point ;

    private long duration ;

    public LongPressActionPart(Point point, long duration) {
        this.point = point;
        this.duration = duration;
    }

    @Override
    public void execute(AdbTouchAction touchAction) {
        String s = longPress(point, duration);
    }
}
