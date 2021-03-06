package com.rehoshi.bh.recognize;

import com.rehoshi.bh.auto.HakaiId;
import com.rehoshi.bh.domain.Rect;
import com.rehoshi.bh.domain.RecognizeResult;

public class HomeRecognizer extends BhRecognizer {

    @HakaiId
    public RecognizeResult findHomeLandBtn(){
        return $().targetX(728)
                .targetY(482)
                .foundThreshold(2)
                .inSense(findInScreen("imgs/home/land/home_land_btn.png"))
                .desc("家园按钮") ;
    }

    @HakaiId
    public RecognizeResult findStaminaPotion(){
        double x = 439 ;
        double y = 4 ;
        return new RecognizeResult(x, y, findInScreen("/imgs/home/stamina_potion.PNG")).desc("体力药剂") ;
    }

    public RecognizeResult findAnnouncement(){
        double x = 488 ;
        double y = 34 ;
        return new RecognizeResult(x, y, findInScreen("/imgs/home/notice.PNG"))
                .desc("游戏公告")
                .intentRect(new Rect(875, 55, 10, 10)) ;
    }

    public RecognizeResult findHintAnnouncement(){
        double x = 486 ;
        double y = 36 ;
        return new RecognizeResult(x, y, findInScreen("/imgs/home/notice_hint.png"))
                .desc("游戏公告")
                .intentRect(new Rect(875, 55, 10, 10)) ;
    }

    public RecognizeResult findVersionHot(){
        return new RecognizeResult(420, 485,findInScreen("/imgs/home/version_hot.PNG"))
                .intentRect(new Rect(914, 22, 10, 10))
                .desc("版本热点");
    }

    public RecognizeResult findActivityGetBtn(){
        return new RecognizeResult(450, 472,findInScreen("/imgs/home/activity_get_btn.PNG")).desc("活动领取按钮") ;
    }

    public RecognizeResult findSingInGetBtn(){
        return new RecognizeResult(472, 488,findInScreen("imgs/home/sing_in_get_btn.png")).desc("签到领取按钮") ;
    }

    public RecognizeResult findSingInRewardConfirm(){
        return  new RecognizeResult()
                .targetX(364)
                .targetY(337)
                .inSense(findInScreen("/imgs/home/sing_in_reward_confirm.png"))
                .desc("签到奖励");
    }

    public RecognizeResult findMonthCard(){
        return  new RecognizeResult()
                .targetX(449)
                .targetY(470)
                .inSense(findInScreen("/imgs/home/moon_card_get.png"))
                .desc("领取月卡");
    }

    @HakaiId
    public RecognizeResult findTaskHint(){
        return  $()
                .targetX(8)
                .targetY(63)
                .inSense(findInScreen("/imgs/home/task_hint.png"))
                .desc("每日任务");
    }

    @HakaiId
    public RecognizeResult findAttackBtn() {
        return $().targetX(770)
                .targetY(74)
                .foundThreshold(2)
                .inSense(findInScreen("imgs/attack/attack_btn.png"))
                .desc("主界面出击按钮") ;
    }
}
