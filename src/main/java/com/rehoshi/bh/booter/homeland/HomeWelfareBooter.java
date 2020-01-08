package com.rehoshi.bh.booter.homeland;

import com.rehoshi.bh.auto.Hakai;
import com.rehoshi.bh.booter.BhBooter;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.domain.Rect;
import com.rehoshi.bh.recognize.homeland.HomeWelfareRecognizer;

public class HomeWelfareBooter extends BhBooter<HomeWelfareRecognizer> {
    @Override
    public boolean recognizeSense() {
        return true;
    }

    @Override
    public int recognizeFrame() {
        RecognizeResult result = $h($()::findStaminaGetBtn, $()::findGoldGetBtn);
        if(result.isFound()){
            handleClickIntent(result);
            if(result.getId() == Hakai.Id.HomeWelfareRecognizer.findStaminaGetBtn){
                return toNextSense(new HomeStaminaBooter()) ;
            }
            return super.recognizeFrame() ;
        }
        return toBack();
    }

    @Override
    public void back() {
        //点击对话框×号
        getDriver().click(new Rect(684, 138, 10, 10)) ;
    }
}
