package com.rehoshi.bh.booter.attack;

import com.rehoshi.bh.booter.BhBooter;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.attack.GroupMatchRecognizer;

public class GroupMatchBooter extends BhBooter<GroupMatchRecognizer> {

    @Override
    public int recognizeFrame() {
        RecognizeResult startMatch = $().findStartMatch();
        if (startMatch.isFound()) {
            finish();
            return toNextSense(startMatch, new GroupPrepareBooter());
        }
        return super.recognizeFrame();
    }
}
