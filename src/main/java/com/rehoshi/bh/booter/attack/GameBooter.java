package com.rehoshi.bh.booter.attack;

import com.rehoshi.bh.booter.BhBooter;
import com.rehoshi.bh.controller.CharacterController;
import com.rehoshi.bh.domain.Rect;
import com.rehoshi.bh.recognize.BhRecognizer;

public abstract class GameBooter<R extends BhRecognizer> extends BhBooter<R> {

    protected CharacterController characterController = initCharacterController() ;

    protected abstract CharacterController initCharacterController();

    /**
     * 点击普攻
     */
    public void clickAttack(){
        getDriver().click(new Rect(840, 430, 100, 100), 20, 200) ;
    }

    /**
     * 点击必杀
     */
    public void clickUltimate(){
        getDriver().click(new Rect(840, 430, 100, 100), 20, 200) ;
    }

    /**
     * 点击闪避
     */
    public void clickEvade(){
        getDriver().click(new Rect(840, 430, 100, 100), 20, 200) ;
    }

    @Override
    public int recognizeFrame() {
        //发布命令
        characterController.publish(getDriver());
        return super.recognizeFrame();
    }
}
