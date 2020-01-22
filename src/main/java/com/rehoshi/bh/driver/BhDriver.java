package com.rehoshi.bh.driver;

import com.rehoshi.bh.controller.action.BhTouchAction;
import com.rehoshi.bh.domain.Rect;

import java.io.File;

public interface BhDriver {

    String APP_PACKAGE = "com.miHoYo.enterprise.NGHSoD" ;
    String APP_ACTIVITY = "com.miHoYo.overridenativeactivity.OverrideNativeActivity" ;

    void connectTarget(String host, int port) throws Exception;

    void startFrame();

    void endFrame();

    File getScreenAsFile();

    default boolean click(Rect rect) {
        BhTouchAction touchAction = newTouch();
        touchAction.tap(rect.center())
                .perform();
        return true;
    }

    default boolean click(Rect rect, int times, long interval) {
        BhTouchAction touchAction = newTouch();
        for (int i = 0; i < times; i++) {
            if (i > 0 && interval > 0) {
                touchAction.waitAction(interval);
            }
            touchAction.tap(rect.center());
        }
        touchAction.perform();
        return true;
    }


    BhTouchAction newTouch();

    String getTargetDevices() ;

}
