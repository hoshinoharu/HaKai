package com.rehoshi.bh.controller.action.adb;

import com.rehoshi.bh.controller.action.AdbTouchAction;
import com.rehoshi.bh.domain.Point;

public class TapActionPart extends BaseActionPart {

    private Point point ;

    public TapActionPart(Point point) {
        this.point = point;
    }

    @Override
    public void execute(AdbTouchAction touchAction) {
        String s = tap(point);
        touchAction.addCommand(s);
    }
}
