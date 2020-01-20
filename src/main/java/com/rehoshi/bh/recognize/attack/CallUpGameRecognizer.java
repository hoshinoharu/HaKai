package com.rehoshi.bh.recognize.attack;

import com.rehoshi.bh.auto.HakaiId;
import com.rehoshi.bh.checker.RecognizeChecker;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.StrictRecognizer;

public class CallUpGameRecognizer extends StrictRecognizer {

    @HakaiId
    public RecognizeResult findAttackBtn(){
        return $().targetX(840)
                .targetY(430)
                .inSense(findInScreen("imgs/attack/btn_a.png"))
                .desc("普通攻击") ;
    }

    @HakaiId
    public RecognizeResult findFollow(){
        return $().targetX(462)
                .targetY(369)
                .checker(RecognizeChecker.Y_ONLY)
                .inSense(findInScreen("imgs/attack/follow.png"))
                .desc("点赞按钮") ;
    }
}
