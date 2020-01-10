package com.rehoshi.bh.booter.homeland;

import com.rehoshi.bh.auto.Hakai;
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
        RecognizeResult result = $h($()::findWelfareMax
                , $()::findStorySweep);
        if(result.isFound()){
            switch (result.getId()){
                case Hakai.Id.HomeLandRecognizer.findWelfareMax:
                    return toNextSense(result, new HomeWelfareBooter()) ;
                case Hakai.Id.HomeLandRecognizer.findStorySweep:
                    return toNextSense(result, new HomeStoryBooter()) ;
            }
        }
        return super.recognizeFrame();
    }
}
