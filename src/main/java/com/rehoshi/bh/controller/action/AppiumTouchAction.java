package com.rehoshi.bh.controller.action;

import com.rehoshi.bh.domain.Point;
import com.rehoshi.bh.driver.AppiumDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;

public class AppiumTouchAction implements BhTouchAction<AppiumTouchAction> {

    private AndroidTouchAction androidTouchAction ;

    public AppiumTouchAction(AppiumDriver appiumDriver) {
        this.androidTouchAction = new AndroidTouchAction(appiumDriver.getAppiumDriver());
    }

    @Override
    public boolean perform() {
        this.androidTouchAction.perform() ;
        return true ;
    }

    @Override
    public AppiumTouchAction waitAction(long delay) {
        androidTouchAction.waitAction(WaitOptions.waitOptions(Duration.ofMillis(delay))) ;
        return this;
    }

    @Override
    public AppiumTouchAction tap(Point point) {
        androidTouchAction.tap(PointOption.point((int) point.x, (int) point.y)) ;
        return this;
    }

    @Override
    public AppiumTouchAction longPress(Point point) {
        androidTouchAction.longPress(PointOption.point((int) point.x, (int) point.y)) ;
        return this;
    }
}
