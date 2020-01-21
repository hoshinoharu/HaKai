package com.rehoshi.bh.controller.command;

import com.rehoshi.bh.domain.Rect;
import com.rehoshi.bh.util.IconLocation;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.offset.PointOption;

/**
 * 单机命令
 */
public abstract class ClickCommand extends TouchCommand {

    public ClickCommand(Rect rect, int times) {
        super(rect.center(), times);
    }

    private static class Attack extends ClickCommand {
        public Attack(int times) {
            super(IconLocation.CHARACTER_ATTACK, times);
        }
    }

    private static class Ultimate extends ClickCommand {
        public Ultimate(int times) {
            super(IconLocation.CHARACTER_ULTIMATE, times);
        }
    }

    private static class Evade extends ClickCommand {
        public Evade(int times) {
            super(IconLocation.CHARACTER_EVADE , times);
        }
    }

    private static class Weapon extends ClickCommand{
        public Weapon(int times) {
            super(IconLocation.CHARACTER_WEAPON, times);
        }
    }

    @Override
    protected void attachPoint2Touch(PointOption point, AndroidTouchAction touchAction) {
        touchAction.tap(point);
    }

    public static ClickCommand attack(int times) {
        return new ClickCommand.Attack(times);
    }

    public static ClickCommand ultimate(int times) {
        return new ClickCommand.Ultimate(times);
    }

    public static ClickCommand evade(int times) {
        return new ClickCommand.Evade(times);
    }

    public static ClickCommand weapon(int times){
        return new ClickCommand.Weapon(times) ;
    }

}
