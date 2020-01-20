package com.rehoshi.bh.recognize.attack;

import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.BhRecognizer;

public class GroupMatchRecognizer extends BhRecognizer {

    public RecognizeResult findStartMatch(){
        return $().targetX(720)
                .targetY(481)
                .inSense(findInScreen("imgs/attack/start_match.png"))
                .desc("一键匹配") ;
    }
}
