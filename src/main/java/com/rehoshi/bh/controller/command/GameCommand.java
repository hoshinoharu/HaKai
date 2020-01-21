package com.rehoshi.bh.controller.command;

import io.appium.java_client.android.AndroidTouchAction;

public interface GameCommand {
    /**
     * 转变成对应的touch命令
     * @param touchAction
     */
    void attach2TouchAction(AndroidTouchAction touchAction) ;
}
