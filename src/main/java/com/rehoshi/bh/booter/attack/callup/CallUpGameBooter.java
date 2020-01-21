package com.rehoshi.bh.booter.attack.callup;

import com.rehoshi.bh.auto.Hakai;
import com.rehoshi.bh.booter.attack.FollowBooter;
import com.rehoshi.bh.booter.attack.GameBooter;
import com.rehoshi.bh.controller.CharacterController;
import com.rehoshi.bh.controller.seeie.OtherShoreTwins;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.attack.CallUpGameRecognizer;

public class CallUpGameBooter extends GameBooter<CallUpGameRecognizer> {

    int times = 0 ;
    @Override
    public int recognizeFrame() {
        clickCenter();
        RecognizeResult result = $h($()::findFollow);
        if(result.isFound()){
            switch (result.getId()){
                case Hakai.Id.CallUpGameRecognizer.findFollow:
                    finish();
                    return toNextSense(result, new FollowBooter()) ;
            }
        }else {//如果没有找到 表示还在游戏中 执行游戏逻辑
            if(times < 100){
                characterController.accumulateSp();
            }else if(times == 100){
                characterController.finalSkill();
            }else {
                characterController.branchAttack();
            }
            times ++ ;
        }
        return super.recognizeFrame();
    }

    @Override
    protected CharacterController initCharacterController() {
        return new OtherShoreTwins();
    }
}
