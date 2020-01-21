package com.rehoshi.bh.booter.attack;

import com.rehoshi.bh.auto.Hakai;
import com.rehoshi.bh.booter.BhBooter;
import com.rehoshi.bh.booter.attack.callup.CallUpGameBooter;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.attack.GroupPrepareRecognizer;

public class GroupPrepareBooter extends BhBooter<GroupPrepareRecognizer> {

    @Override
    public int recognizeFrame() {
        RecognizeResult result = $h($()::findStartBtn, $()::findPrepareBtn);
        if(result.isFound()){
            switch (result.getId()){
                case Hakai.Id.GroupPrepareRecognizer.findStartBtn://开始按钮
                    finish();
                    return toNextSense(result, new CallUpGameBooter()) ;
                case Hakai.Id.GroupPrepareRecognizer.findPrepareBtn://准备按钮
                    finish();
                    return toNextSense(result, new CallUpGameBooter()) ;

            }
        }
        return super.recognizeFrame();
    }
}
