package com.rehoshi.bh.booter.attack;

import com.rehoshi.bh.auto.Hakai;
import com.rehoshi.bh.booter.BhBooter;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.attack.GroupResultRecognizer;

public class GroupResultBooter extends BhBooter<GroupResultRecognizer> {
    @Override
    public int recognizeFrame() {
        clickCenter();
        RecognizeResult result = $h($()::findQuit, $()::findCallUpEnter);
        if(result.isFound()){
            switch (result.getId()){
                case Hakai.Id.GroupResultRecognizer.findQuit:
                    //点击退出
                    handleClickIntent(result);

                case Hakai.Id.GroupResultRecognizer.findCallUpEnter:
                    finish();//直接结束
                    break;
            }
        }
        return super.recognizeFrame();
    }
}
