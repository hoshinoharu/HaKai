package com.rehoshi.bh.controller.command;

import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.awt.*;
import java.time.Duration;

/**
 * 触摸命令
 */
public abstract class TouchCommand implements GameCommand {

    private Point touchPoint ;

    private int times ;

    public TouchCommand(Point touchPoint, int times) {
        this.touchPoint = touchPoint;
        this.times = times;
    }

    @Override
    public void attach2TouchAction(AndroidTouchAction touchAction) {
        PointOption point = PointOption.point(touchPoint.x, touchPoint.y);
        for (int i= 0; i < times; i ++){
            if(i > 0){
                //按键间隔
                touchAction.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300))) ;
            }
            attachPoint2Touch(point, touchAction);
        }
    }

    protected abstract void attachPoint2Touch(PointOption point, AndroidTouchAction touchAction);
}
