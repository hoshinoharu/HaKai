package com.rehoshi.bh.booter;

public interface Booter {
    interface RecognizeStatus {

        int NOT_INTERCEPT = -1;

        //去上一个场景
        int TO_PRE_SENSE = 0;

        //留在当前场景
        int STAY_CUR_SENSE = 1;

        //去下一个场景
        int TO_NEXT_SENSE = 2;

        int NO_ACTION = 1001;

    }

    /**
     * 绑定driver
     *
     * @param driver
     */
    void bindDriver(BhDriver driver);

    /**
     * 识别当前场景
     * 只做场景识别用于初始化操作
     */
    default boolean recognizeSense() {
        return true;
    }

    /**
     * 拦截识别
     *
     * @return
     */
    int interceptRecognize();

    /**
     * 识别当前帧
     * 用于判断当前界面状态
     */
    int recognizeFrame();

    /**
     * 获取下一个Booter
     *
     * @return
     */
    Booter getNextBooter();

}
