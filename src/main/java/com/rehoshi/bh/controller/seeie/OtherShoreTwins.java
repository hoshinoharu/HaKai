package com.rehoshi.bh.controller.seeie;

import com.rehoshi.bh.controller.CharacterController;

public class OtherShoreTwins extends CharacterController {
    @Override
    public void accumulateSp() {
        addAttack(5);
        addEvade(1);
        addUltimate(1);
        addAttack(5);
    }

    @Override
    public void branchAttack() {
        accumulateSp();
    }
}
