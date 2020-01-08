package com.rehoshi.bh.recognize.homeland;

import com.rehoshi.bh.auto.HakaiId;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.StrictRecognizer;

public class HomeLandRecognizer extends StrictRecognizer {

    @HakaiId
    public RecognizeResult findWelfareMax(){
        return $().targetX(669)
                .targetY(382)
                .inSense(findInScreen("imgs/home/land/welfare_max.png"))
                .desc("家园福利箱") ;
    }

    /**
     * 查找远征按钮
     * @return
     */
    @HakaiId
    public RecognizeResult findStorySweep(){
        return $().targetX(584)
                .targetY(472)
                .inSense(findInScreen("imgs/home/story_sweep.png"))
                .desc("远征按钮") ;
    }

}
