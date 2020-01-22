package com.rehoshi.bh.controller.action;


import com.rehoshi.bh.domain.Point;

import java.util.function.Consumer;

public interface BhTouchAction<A extends BhTouchAction<A>> {

    /**
     * 执行操作
     */
    boolean perform();

    /**
     * 异步执行操作
     */
    default void performAsync(){
        performAsync(null);
    }

    /**
     * 默认启动一个线程 调用perform方法
     * @param callback
     */
    default void performAsync(Consumer<Boolean> callback){
        new Thread(()->{
            boolean perform = perform();
            if(callback != null){
                callback.accept(perform);
            }

        }).start();
    }

    /**
     * 等待操作
     * @param delay
     */
    A waitAction(long delay);


    /**
     * 点击操作
     * @param point
     */
    A tap(Point point);

    /**
     * 长按操作
     */
    A longPress(Point point);
}
