package com.rehoshi.bh.booter.attack;

import com.rehoshi.bh.booter.BhBooter;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.attack.FollowRecognizer;

public class FollowBooter extends BhBooter<FollowRecognizer> {

    @Override
    public int recognizeFrame() {
        clickCenter();
        RecognizeResult close = $().findClose();
        if(close.isFound()){
            finish();
            return toNextSense(close, new GroupResultBooter()) ;
        }
        return super.recognizeFrame();
    }
}
