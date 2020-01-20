package com.rehoshi.bh.recognize.attack;

import com.rehoshi.bh.auto.HakaiId;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.StrictRecognizer;

public class FollowRecognizer extends StrictRecognizer {

    @HakaiId
    public RecognizeResult findClose() {
        return $().targetX(387)
                .targetY(481)
                .inSense(findInScreen("imgs/attack/follow_close.png"))
                .desc("关闭按钮");
    }
}
