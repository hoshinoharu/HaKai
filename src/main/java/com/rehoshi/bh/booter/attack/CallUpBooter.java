package com.rehoshi.bh.booter.attack;

import com.rehoshi.bh.booter.BhBooter;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.attack.CallUpRecognizer;

public class CallUpBooter extends BhBooter<CallUpRecognizer> {

    @Override
    public int recognizeFrame() {
        RecognizeResult result = $().findEnter();
        if(result.isFound()){
            return toNextSense(result, new GroupMatchBooter()) ;
        }
        return super.recognizeFrame();
    }
}
