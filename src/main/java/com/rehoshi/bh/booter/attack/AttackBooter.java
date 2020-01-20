package com.rehoshi.bh.booter.attack;

import com.rehoshi.bh.auto.Hakai;
import com.rehoshi.bh.booter.BhBooter;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.attack.AttackRecognizer;

public class AttackBooter extends BhBooter<AttackRecognizer> {

    @Override
    public int recognizeFrame() {
        RecognizeResult result = $h($()::findChallengeIcon
                , $()::findChallengeIconNew
                , $()::findChallengeBtn
                , $()::findChallengeBtnNew);
        if (result.isFound()) {
            switch (result.getId()){
                case Hakai.Id.AttackRecognizer.findChallengeBtn:
                case Hakai.Id.AttackRecognizer.findChallengeBtnNew:
                    return toNextSense(result, new ChallengeBooter()) ;
                case Hakai.Id.AttackRecognizer.findChallengeIcon:
                case Hakai.Id.AttackRecognizer.findChallengeIconNew:
                    return toNextSense(new ChallengeBooter()) ;

            }
        }
        return super.recognizeFrame();
    }

}
