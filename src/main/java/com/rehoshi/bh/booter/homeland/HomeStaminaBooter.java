package com.rehoshi.bh.booter.homeland;

import com.rehoshi.bh.booter.BhBooter;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.homeland.HomeStaminaRecognizer;

public class HomeStaminaBooter extends BhBooter<HomeStaminaRecognizer> {
    @Override
    public int recognizeFrame() {
        RecognizeResult getBtn = $().findGetBtn();
        if(getBtn.isFound()){
            handleClickIntent(getBtn);
            return toBack() ;
        }
        return super.recognizeFrame();
    }
}
