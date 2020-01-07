package com.rehoshi.bh.recognize.task;

import com.rehoshi.bh.auto.HakaiId;
import com.rehoshi.bh.checker.RecognizeChecker;
import com.rehoshi.bh.domain.RecognizeResult;
import org.slf4j.ILoggerFactory;

public class DailyTaskRecognizer extends TaskRecognizer {
    /**
     * 每日任务领取按钮
     * @return
     */
    public RecognizeResult findDailyGetBtn() {
        return $().inSense(findInScreen("imgs/task/daily_task_get_btn.png", "taskget"))
                .checker(RecognizeChecker.chain(
                        RecognizeChecker.xIn(440, 817)
                        ,RecognizeChecker.textEqual("领取")//识别文字 为领取
                ))//两列按钮 按照x识别
                .desc("每日任务领取按钮");
    }

    /**
     * 前往每日任务
     * @return
     */
    @HakaiId
    public RecognizeResult findDailyTowardBtn() {
        return $().inSense(findInScreen("imgs/task/daily_task_toward_btn.png", "task toward"))
                .checker(RecognizeChecker.xIn(440, 817))//两列按钮 按照x识别
                .desc("每日任务前往按钮");
    }
}
