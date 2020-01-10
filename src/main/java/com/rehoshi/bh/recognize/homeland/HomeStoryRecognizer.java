package com.rehoshi.bh.recognize.homeland;

import com.rehoshi.bh.auto.HakaiId;
import com.rehoshi.bh.checker.RecognizeChecker;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.StrictRecognizer;

public class HomeStoryRecognizer extends StrictRecognizer {
    /**
     * 查找远征按钮
     * @return
     */
    @HakaiId
    public RecognizeResult findFinishBtn(){
        return $().targetX(656)
                .checker(RecognizeChecker.X_ONLY)
                .inSense(findInScreen("imgs/story/finish_btn.png"))
                .desc("完成按钮") ;
    }

    @HakaiId
    public RecognizeResult findStartBtn(){
        return $().targetX(655)
                .checker(RecognizeChecker.X_ONLY)
                .inSense(findInScreen("imgs/story/start_btn.png"))
                .desc("开始按钮") ;
    }

    @HakaiId
    public RecognizeResult findRecentIcon(){
        return $().targetX(86)
                .checker(RecognizeChecker.X_ONLY)
                .inSense(findInScreen("imgs/story/start_btn.png"))
                .desc("最近图标按钮") ;
    }

    //499 19 70 23 当前体力


    //478 84 52 24 当前远征体力

}
