package com.rehoshi.bh.controller.fuhua;


public class ShadowRider extends FuHua {
    @Override
    public void accumulateSp() {
        $A(2);
        $B(2);
    }

    @Override
    public void branchAttack() {
        $A(3);
        $A(10);
    }
}
