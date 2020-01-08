package com.rehoshi.bh.recognize.homeland;

import com.rehoshi.bh.auto.HakaiId;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.BhRecognizer;

public class HomeStaminaRecognizer extends BhRecognizer {

    @HakaiId
    public RecognizeResult findGetBtn(){
        return $().targetX(384)
                .targetY(392)
                .inSense(findInScreen("imgs/home/land/stamina/get_btn.png"))
                .desc("体力领取按钮") ;
    }
}
