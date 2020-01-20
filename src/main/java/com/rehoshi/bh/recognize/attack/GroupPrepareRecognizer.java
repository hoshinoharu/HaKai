package com.rehoshi.bh.recognize.attack;

import com.rehoshi.bh.auto.HakaiId;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.StrictRecognizer;

public class GroupPrepareRecognizer extends StrictRecognizer {

    @HakaiId
    public RecognizeResult findStartBtn(){//队长开始按钮
        return $().targetX(359)
                .targetY(476)
                .inSense(findInScreen("imgs/attack/group_start.png"))
                .desc("小队开始") ;
    }

    @HakaiId
    public RecognizeResult findPrepareBtn(){//队员准备按钮
        return $().targetX(359)
                .targetY(476)
                .inSense(findInScreen("imgs/attack/group_prepare.png"))
                .desc("小队准备") ;
    }
}
