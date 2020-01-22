package com.rehoshi.bh.controller;


import com.rehoshi.bh.driver.BhDriver;

public interface GameController {
    /**
     * 发布命令
     */
    void publish(BhDriver bhDriver);
}
