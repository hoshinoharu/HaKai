package com.rehoshi.bh.recognize.attack;

import com.rehoshi.bh.auto.HakaiId;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.StrictRecognizer;

public class CallUpRecognizer extends StrictRecognizer {
    @HakaiId
    public RecognizeResult findEnter() {
        return $().targetX(535)
                .targetY(214)
                .inSense(findInScreen("imgs/attack/enter_monday_.png"))
                .desc("征召关卡入口");
    }
}
