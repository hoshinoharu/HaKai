package com.rehoshi.bh.controller.action.adb;

import com.rehoshi.bh.controller.action.AdbTouchAction;

public class WaitActionPart implements AdbActionPart {
    private long duration ;

    public WaitActionPart(long duration) {
        this.duration = duration;
    }

    @Override
    public void execute(AdbTouchAction touchAction) {
        try {
            Thread.sleep(this.duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
