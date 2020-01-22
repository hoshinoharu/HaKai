package com.rehoshi.bh.driver;

import com.rehoshi.bh.controller.action.AdbTouchAction;
import com.rehoshi.bh.controller.action.BhTouchAction;
import com.rehoshi.bh.domain.Rect;

import java.io.File;

public class AdbDriver implements BhDriver {

    private String host;
    private int port;

    @Override
    public void connectTarget(String host, int port) throws Exception {
        this.host = host ;
        this.port = port ;
    }

    @Override
    public void startFrame() {

    }

    @Override
    public void endFrame() {

    }

    @Override
    public File getScreenAsFile() {
        return null;
    }

    @Override
    public boolean click(Rect rect) {
        return false;
    }

    @Override
    public boolean click(Rect rect, int times, long delay) {
        return false;
    }

    @Override
    public BhTouchAction newTouch() {
        return new AdbTouchAction(this);
    }

    @Override
    public String getTargetDevices() {
        return String.format("%s:%d", host, port);
    }
}
