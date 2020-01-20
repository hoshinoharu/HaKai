package com.rehoshi.bh.recognize.attack;

import com.rehoshi.bh.auto.HakaiId;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.StrictRecognizer;

public class AttackRecognizer extends StrictRecognizer {

    @HakaiId
    public RecognizeResult findChallengeIcon() {
        return $().targetX(24)
                .targetY(222)
                .inSense(findInScreen("imgs/attack/challenge.png"))
                .desc("挑战图标") ;
    }
    @HakaiId
    public RecognizeResult findChallengeIconNew() {
        return $().targetX(24)
                .targetY(222)
                .inSense(findInScreen("imgs/attack/challenge_new.png"))
                .desc("挑战图标NEW") ;
    }

    @HakaiId
    public RecognizeResult findChallengeBtn() {
        return $().targetX(24)
                .targetY(222)
                .inSense(findInScreen("imgs/attack/challenge_btn.png"))
                .desc("挑战按钮") ;
    }

    @HakaiId
    public RecognizeResult findChallengeBtnNew() {
        return $().targetX(24)
                .targetY(222)
                .inSense(findInScreen("imgs/attack/challenge_new_btn.png"))
                .desc("挑战按钮NEW") ;
    }

}
