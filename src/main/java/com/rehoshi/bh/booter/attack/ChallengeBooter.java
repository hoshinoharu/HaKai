package com.rehoshi.bh.booter.attack;

import com.rehoshi.bh.booter.BhBooter;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.attack.ChallengeRecognizer;

public class ChallengeBooter extends BhBooter<ChallengeRecognizer> {

    @Override
    public int recognizeFrame() {
        RecognizeResult result = $().findCallUp() ;
        if(result.isFound()){
            return toNextSense(result, new CallUpBooter()) ;
        }

        return super.recognizeFrame();
    }
}
