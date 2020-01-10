package com.rehoshi.bh.booter.homeland;

import com.rehoshi.bh.booter.BhBooter;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.homeland.HomeStoryRecognizer;

public class HomeStoryBooter extends BhBooter<HomeStoryRecognizer> {
    @Override
    public int recognizeFrame() {

        RecognizeResult result = $h($()::findTaskConfirm, $()::findFinishBtn);

        if(result.isFound()){
            //直接点击
            handleClickIntent(result);
        }
        return super.recognizeFrame();
    }
}
