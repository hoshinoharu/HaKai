package com.rehoshi.bh.controller.fuhua;

import com.rehoshi.bh.controller.CharacterController;

public abstract class FuHua extends CharacterController {
    public void $A(int times){
        addAttack(times);
    }

    public void $B(int times){
        addUltimate(times);
    }

    public void $$A(){
        addHoldAttack(1);
    }

    public void $$B(){
        addHoldUltimate(1);
    }
}
