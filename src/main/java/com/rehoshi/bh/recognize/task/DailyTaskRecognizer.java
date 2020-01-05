package com.rehoshi.bh.recognize.task;

import com.rehoshi.bh.booter.domain.RecognizeChecker;
import com.rehoshi.bh.booter.domain.RecognizeResult;

public class DailyTaskRecognizer extends TaskRecognizer {


    /**
     * 每日任务领取按钮
     * @return
     */
    public RecognizeResult findDailyGetBtn() {
        return $().inSense(findInScreen("imgs/task/daily_task_get_btn.png"))
                .checker(RecognizeChecker.xIn(440, 817))//两列按钮 按照x识别
                .desc("每日任务领取按钮");
    }

    /**
     * 前往每日任务
     * @return
     */
    public RecognizeResult findDailyTowardBtn() {
        return $().inSense(findInScreen("imgs/task/daily_task_toward_btn.png"))
                .checker(RecognizeChecker.xIn(440, 817))//两列按钮 按照x识别
                .desc("每日任务前往按钮");
    }
}
