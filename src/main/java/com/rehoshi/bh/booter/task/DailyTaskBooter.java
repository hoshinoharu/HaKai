package com.rehoshi.bh.booter.task;

import com.rehoshi.bh.booter.BhBooter;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.task.DailyTaskRecognizer;

public class DailyTaskBooter extends BhBooter<DailyTaskRecognizer> {
    @Override
    public boolean recognizeSense() {
        return true;
    }

    @Override
    public int recognizeFrame() {
        RecognizeResult result = $h(
                $()::findDailyGetBtn //查找每日任务获取按钮
                , $()::findDailyTowardBtn //查找每日任务前往按钮
                , $()::findTaskConfirm);
        if (result.isFound()) {
            getDriver().click(result.getIntentRect());
            System.out.println("点击" + result.getDesc());
        }
        return super.recognizeFrame();
    }
}
