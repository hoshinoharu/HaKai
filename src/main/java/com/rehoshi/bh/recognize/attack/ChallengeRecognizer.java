package com.rehoshi.bh.recognize.attack;

import com.rehoshi.bh.auto.HakaiId;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.StrictRecognizer;

public class ChallengeRecognizer extends StrictRecognizer {

    @HakaiId
    public RecognizeResult findCallUp() {
        return $().targetX(100)
                .targetY(86)
                .inSense(findInScreen("imgs/attack/call_up.png"))
                .desc("武神征召Icon");
    }
}
