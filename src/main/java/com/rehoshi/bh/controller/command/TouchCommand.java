package com.rehoshi.bh.controller.command;

import com.rehoshi.bh.controller.action.BhTouchAction;
import com.rehoshi.bh.domain.Point;

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
    public void attach2TouchAction(BhTouchAction touchAction) {
        for (int i= 0; i < times; i ++){
            if(i > 0){
                //按键间隔
                touchAction.waitAction(200) ;
            }
            attachPoint2Touch(touchPoint, touchAction);
        }
    }

    protected abstract void attachPoint2Touch(Point point, BhTouchAction touchAction);
}
