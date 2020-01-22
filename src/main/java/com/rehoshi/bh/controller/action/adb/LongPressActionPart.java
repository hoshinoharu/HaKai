package com.rehoshi.bh.controller.action.adb;

import com.rehoshi.bh.controller.action.AdbTouchAction;
import com.rehoshi.bh.domain.Point;

public class LongPressActionPart extends BaseActionPart {

    private Point point ;

    public LongPressActionPart(Point point) {
        this.point = point;
    }

    @Override
    public void execute(AdbTouchAction touchAction) {
        String s = longPress(point);
        touchAction.addCommand(s);
    }
}
