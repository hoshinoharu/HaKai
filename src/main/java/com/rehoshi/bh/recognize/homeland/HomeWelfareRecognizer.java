package com.rehoshi.bh.recognize.homeland;

import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.StrictRecognizer;

public class HomeWelfareRecognizer extends StrictRecognizer {
    /**
     * 查找金币获取按钮
     * @return
     */
    public RecognizeResult findGoldGetBtn(){
        return $().targetX(562)
                .targetY(349)
                .inSense(findInScreen("imgs/home/land/gold_get_btn.png"))
                .desc("查找金币按钮") ;
    }
}
