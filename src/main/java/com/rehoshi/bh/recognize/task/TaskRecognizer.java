package com.rehoshi.bh.recognize.task;

import com.rehoshi.bh.auto.HakaiId;
import com.rehoshi.bh.recognize.BhRecognizer;
import com.rehoshi.bh.domain.RecognizeResult;

public class TaskRecognizer extends BhRecognizer {

    @Override
    protected RecognizeResult $() {
        return super.$().foundThreshold(2);//设置默认的精度为误差为2
    }

    @HakaiId
    public RecognizeResult findDailyTaskActive(){//查找每日任务激活按钮
        return $().targetX(0)
                .targetY(130)
                .inSense(findInScreen("imgs/task/daily_task_icon_active.png"))
                .desc("每日任务");
    }

    @HakaiId
    public RecognizeResult findDailyTaskTap(){//查找每日任务点击按钮
        return $().targetX(0)
                .targetY(131)
                .inSense(findInScreen("imgs/task/daily_task_tap.PNG"))
                .desc("每日任务");
    }

    public RecognizeResult findBPTaskActive(){//查找作战任务
        return  $().targetX(0)
                .targetY(195)
                .inSense(findInScreen("imgs/task/bp_task_icon_active.png"))
                .desc("作战任务");
    }
    public RecognizeResult findBPGetBtn(){//查找作战任务领取按钮
        return  $().targetX(180)
                .targetY(198)
                .inSense(findInScreen("imgs/task/bp_task_get_btn.png"))
                .desc("作战奖励按钮");
    }

}
