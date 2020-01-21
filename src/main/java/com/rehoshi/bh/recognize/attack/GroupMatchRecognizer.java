package com.rehoshi.bh.recognize.attack;

import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.BhRecognizer;

public class GroupMatchRecognizer extends BhRecognizer {

    //31 68
    public RecognizeResult findMatchTitle(){
        return $().targetX(31)
                .targetY(68)
                .inSense(findInScreen("imgs/attack/call_up_match_title.png"))
                .desc("征召匹配标题") ;
    }

    public RecognizeResult findStartMatch(){
        return $().targetX(720)
                .targetY(481)
                .inSense(findInScreen("imgs/attack/start_match.png"))
                .desc("一键匹配") ;
    }
}
