package com.rehoshi.bh.recognize.homeland;

import com.rehoshi.bh.auto.HakaiId;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.StrictRecognizer;

public class HomeWelfareRecognizer extends StrictRecognizer {
    /**
     * 查找金币获取按钮
     * @return
     */
    @HakaiId
    public RecognizeResult findGoldGetBtn(){
        return $().targetX(562)
                .targetY(349)
                .inSense(findInScreen("imgs/home/land/gold_get_btn.png"))
                .desc("金币获取按钮") ;
    }

    /**
     * 查找体力取出按钮
     * @return
     */
    @HakaiId
    public RecognizeResult findStaminaGetBtn(){
        return $().targetX(562)
                .targetY(237)
                .inSense(findInScreen("imgs/home/land/stamina_get_btn.png"))
                .desc("取出体力按钮") ;
    }


}
