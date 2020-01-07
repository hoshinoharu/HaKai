package com.rehoshi.bh.booter.homeland;

import com.rehoshi.bh.booter.BhBooter;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.homeland.HomeLandRecognizer;

public class HomeLandBooter extends BhBooter<HomeLandRecognizer> {
    @Override
    public boolean recognizeSense() {
        return true;
    }

    @Override
    public int recognizeFrame() {
        //查找福利箱是否满了
        RecognizeResult welfareMax = $().findWelfareMax();
        if(welfareMax.isFound()){
            //跳转福利界面
            return toNextSense(welfareMax, new HomeWelfareBooter()) ;
        }
        return super.recognizeFrame();
    }
}
