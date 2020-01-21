package com.rehoshi.bh.controller;

import com.rehoshi.bh.booter.BhDriver;
import com.rehoshi.bh.controller.command.ClickCommand;
import com.rehoshi.bh.controller.command.GameCommand;
import com.rehoshi.bh.controller.command.LongPressCommand;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class CharacterController implements GameController {

    private List<GameCommand> commandCache = new ArrayList<>();

    /**
     * 积攒sp
     */
    public abstract void accumulateSp();

    /**
     * 分支攻击
     */
    public abstract void branchAttack() ;

    /**
     * 终极技能
     */
    public void finalSkill(){
        addHoldUltimate(1);
    }

    @Override
    public void publish(BhDriver bhDriver) {
        AndroidTouchAction androidTouchAction = bhDriver.newTouch();
        boolean first = true ;
        Iterator<GameCommand> iterator = commandCache.iterator();
        while (iterator.hasNext()){
            GameCommand next = iterator.next();
            if(!first){
                //多个命令间隔
                androidTouchAction.waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))) ;
            }else {
                first = false ;
            }
            next.attach2TouchAction(androidTouchAction);
        }
        try {
            new Thread(androidTouchAction::perform).start();
        }catch (Exception e){
        }
        commandCache.clear();
    }

    public void addCommand(GameCommand gameCommand) {
        this.commandCache.add(gameCommand);
    }

    public void addAttack(int times) {
        this.addCommand(ClickCommand.attack(times));
    }

    public void addUltimate(int times) {
        this.addCommand(ClickCommand.ultimate(times));
    }

    public void addEvade(int times) {
        this.addCommand(ClickCommand.evade(times));
    }

    public void addWeapon(int times) {
        this.addCommand(ClickCommand.weapon(times));
    }

    public void addHoldUltimate(int times) {
        this.addCommand(LongPressCommand.ultimate(times));
    }

    public void addHoldAttack(int times) {
        this.addCommand(LongPressCommand.attack(times));
    }
}
