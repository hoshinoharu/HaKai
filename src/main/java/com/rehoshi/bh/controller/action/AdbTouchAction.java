package com.rehoshi.bh.controller.action;

import com.rehoshi.bh.controller.action.adb.AdbActionPart;
import com.rehoshi.bh.controller.action.adb.LongPressActionPart;
import com.rehoshi.bh.controller.action.adb.TapActionPart;
import com.rehoshi.bh.controller.action.adb.WaitActionPart;
import com.rehoshi.bh.domain.Point;
import com.rehoshi.bh.driver.AdbDriver;
import com.rehoshi.bh.log.Log;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class AdbTouchAction implements BhTouchAction<AdbTouchAction> {

    private List<String> commandCache = new Vector<>() ;
    private List<AdbActionPart> actionPartList = new Vector<>() ;

    private AdbDriver adbDriver ;

    public AdbTouchAction(AdbDriver adbDriver) {
        this.adbDriver = adbDriver;
    }

    @Override
    public boolean perform() {
        this.actionPartList.forEach(adbActionPart -> adbActionPart.execute(this));
        this.adbDriver.executeAdbCommand(commandCache);
        commandCache.clear();
        actionPartList.clear();
        return true ;
    }

    public void addCommand(String command){
        this.commandCache.add(command) ;
    }

    @Override
    public AdbTouchAction waitAction(long delay) {
        this.actionPartList.add(new WaitActionPart(delay)) ;
        return this;
    }

    @Override
    public AdbTouchAction tap(Point point) {
        this.actionPartList.add(new TapActionPart(point)) ;
        return this;
    }

    @Override
    public AdbTouchAction longPress(Point point) {
        this.actionPartList.add(new LongPressActionPart(point)) ;
        return this;
    }
}
