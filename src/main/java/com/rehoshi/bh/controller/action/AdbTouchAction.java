package com.rehoshi.bh.controller.action;

import com.rehoshi.bh.domain.Point;
import com.rehoshi.bh.driver.AdbDriver;
import com.rehoshi.bh.log.Log;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class AdbTouchAction implements BhTouchAction<AdbTouchAction> {

    private List<String> commandCache = new Vector<>() ;

    private AdbDriver adbDriver ;

    public AdbTouchAction(AdbDriver adbDriver) {
        this.adbDriver = adbDriver;
    }

    @Override
    public boolean perform() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process exec = runtime.exec(commandCache.toArray(new String[commandCache.size()]));
            Log.log(exec);
        } catch (IOException e) {
            e.printStackTrace();
            return false ;
        }
        return true ;
    }

    @Override
    public AdbTouchAction waitAction(long delay) {
        return null;
    }

    @Override
    public AdbTouchAction tap(Point point) {
        return null;
    }

    @Override
    public AdbTouchAction longPress(Point point) {
        return null;
    }
}
