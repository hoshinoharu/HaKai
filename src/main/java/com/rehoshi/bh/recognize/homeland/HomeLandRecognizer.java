package com.rehoshi.bh.recognize.homeland;

import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.StrictRecognizer;

public class HomeLandRecognizer extends StrictRecognizer {

    public RecognizeResult findWelfareMax(){
        return $().targetX(699)
                .targetY(382)
                .inSense(findInScreen("imgs/home/land/welfare_max.png"))
                .desc("家园福利箱") ;
    }
}
