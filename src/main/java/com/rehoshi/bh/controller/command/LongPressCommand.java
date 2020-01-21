package com.rehoshi.bh.controller.command;

import com.rehoshi.bh.domain.Rect;
import com.rehoshi.bh.util.IconLocation;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.offset.PointOption;

/**
 * 长按命令
 */
public class LongPressCommand extends TouchCommand {

    private static class Ultimate extends LongPressCommand{

        public Ultimate(int times) {
            super(IconLocation.CHARACTER_ULTIMATE, times);
        }
    }

    private static class Attack extends LongPressCommand{

        public Attack(int times) {
            super(IconLocation.CHARACTER_ATTACK, times);
        }
    }

    public LongPressCommand(Rect rect, int times) {
        super(rect.center(), times);
    }

    @Override
    protected void attachPoint2Touch(PointOption point, AndroidTouchAction touchAction) {
        touchAction.longPress(point) ;
    }

    public static GameCommand attack(int times){
        return new Attack(times) ;
    }

    public static GameCommand ultimate(int times){
        return new Ultimate(times) ;
    }
}
