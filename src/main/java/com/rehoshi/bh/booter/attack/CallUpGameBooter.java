package com.rehoshi.bh.booter.attack;

import com.rehoshi.bh.auto.Hakai;
import com.rehoshi.bh.booter.BhBooter;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.attack.CallUpGameRecognizer;

public class CallUpGameBooter extends BhBooter<CallUpGameRecognizer> {
    @Override
    public int recognizeFrame() {
        clickCenter();
        RecognizeResult result = $h($()::findAttackBtn
                , $()::findFollow);
        if(result.isFound()){
            switch (result.getId()){
                case Hakai.Id.CallUpGameRecognizer.findAttackBtn:
                    getDriver().click(result.getIntentRect(), 20, 200) ;
                    break;
                case Hakai.Id.CallUpGameRecognizer.findFollow:
                    finish();
                    return toNextSense(result, new FollowBooter()) ;
            }
        }
        return super.recognizeFrame();
    }
}
