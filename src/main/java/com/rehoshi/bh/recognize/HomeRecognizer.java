package com.rehoshi.bh.recognize;

import com.rehoshi.bh.booter.domain.MatchRect;
import com.rehoshi.bh.booter.domain.RecognizeResult;

public class HomeRecognizer extends BhRecognizer {

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
                .intentRect(new MatchRect(875, 55, 10, 10)) ;
    }

    public RecognizeResult findHintAnnouncement(){
        double x = 486 ;
        double y = 36 ;
        return new RecognizeResult(x, y, findInScreen("/imgs/home/notice_hint.png"))
                .desc("游戏公告")
                .intentRect(new MatchRect(875, 55, 10, 10)) ;
    }

    public RecognizeResult findVersionHot(){
        return new RecognizeResult(420, 485,findInScreen("/imgs/home/version_hot.PNG"))
                .intentRect(new MatchRect(914, 22, 10, 10))
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

    public RecognizeResult findTaskHint(){
        return  new RecognizeResult()
                .targetX(8)
                .targetY(63)
                .inSense(findInScreen("/imgs/home/task_hint.png"))
                .desc("每日任务");
    }
}
