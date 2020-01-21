package com.rehoshi.bh.booter.attack.callup;

import com.rehoshi.bh.booter.BhBooter;
import com.rehoshi.bh.booter.attack.GroupMatchBooter;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.attack.CallUpRecognizer;

public class CallUpBooter extends BhBooter<CallUpRecognizer> {

    @Override
    public int recognizeFrame() {
        RecognizeResult result = $().findCallUpTitle();
        if(result.isFound()){
            return toNextSense(result, new GroupMatchBooter()) ;
        }
        return super.recognizeFrame();
    }
}
