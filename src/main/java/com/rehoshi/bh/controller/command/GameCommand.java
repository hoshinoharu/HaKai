package com.rehoshi.bh.controller.command;

import com.rehoshi.bh.controller.action.BhTouchAction;
import io.appium.java_client.android.AndroidTouchAction;

public interface GameCommand {
    /**
     * 转变成对应的touch命令
     * @param touchAction
     */
    void attach2TouchAction(BhTouchAction touchAction) ;
}
