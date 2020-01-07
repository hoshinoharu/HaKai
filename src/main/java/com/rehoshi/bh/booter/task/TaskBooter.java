package com.rehoshi.bh.booter.task;

import com.rehoshi.bh.booter.BhBooter;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.task.TaskRecognizer;

public class TaskBooter extends BhBooter<TaskRecognizer> {

    @Override
    public boolean recognizeSense() {
        return true;
    }

    @Override
    public int recognizeFrame() {
        RecognizeResult recognizeResult = $h($()::findDailyTaskActive
                , $()::findDailyTaskTap);
        if (recognizeResult.isFound()) {
            getDriver().click(recognizeResult.getIntentRect());
            System.out.println("跳转每日任务界面");
            return toNextSense(new DailyTaskBooter());
        }
        return super.recognizeFrame();
    }
}
