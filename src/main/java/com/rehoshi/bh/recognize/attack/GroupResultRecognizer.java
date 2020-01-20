package com.rehoshi.bh.recognize.attack;

import com.rehoshi.bh.auto.HakaiId;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.StrictRecognizer;

public class GroupResultRecognizer extends StrictRecognizer {

    @HakaiId
    public RecognizeResult findQuit(){
        return $().targetX(531)
                .targetY(470)
                .inSense(findInScreen("imgs/attack/quit_group.png"))
                .desc("退出小队") ;
    }

    @HakaiId
    public RecognizeResult findCallUpEnter(){
        return $().targetX(535)
                .targetY(214)
                .inSense(findInScreen("imgs/attack/enter_monday_.png"))
                .desc("征召关卡入口");
    }
}
