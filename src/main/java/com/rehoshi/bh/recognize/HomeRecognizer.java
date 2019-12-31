package com.rehoshi.bh.recognize;

import java.awt.geom.Rectangle2D;

public class HomeRecognizer extends BhRecognizer {

    public RecogResult findStaminaPotion(){
        Rectangle2D.Double inScreen = findInScreen("/imgs/home/stamina_potion.PNG");
        double x = 439 ;
        double y = 4 ;
        return new RecogResult(x, y, inScreen) ;
    }

    public RecogResult findAnnouncement(){
        Rectangle2D.Double inScreen = findInScreen("/imgs/home/notice.PNG");
        double x = 488 ;
        double y = 34 ;
        return new RecogResult(x, y, inScreen)
                .desc("游戏公告")
                .intentRect(new Rectangle2D.Double(875, 55, 10, 10)) ;
    }

    public RecogResult findVersionHot(){
        Rectangle2D.Double inScreen = findInScreen("/imgs/home/version_hot.PNG");
        return new RecogResult(420, 485,inScreen).desc("版本热点");
    }

    public RecogResult findActivityGetBtn(){
        Rectangle2D.Double inScreen = findInScreen("/imgs/home/activity_get_btn.PNG");
        return new RecogResult(450, 472,inScreen).desc("活动领取按钮") ;
    }

    public RecogResult findSingInGetBtn(){
        Rectangle2D.Double inScreen = findInScreen("imgs/home/sing_in_get_btn.png");
        return new RecogResult(472, 488,inScreen).desc("签到领取按钮") ;
    }

    public RecogResult findSingInRewardConfirm(){
        return  new RecogResult()
                .targetX(364)
                .targetY(337)
                .inSense(findInScreen("/imgs/home/sing_in_reward_confirm.png"))
                .desc("签到奖励");
    }

    public RecogResult findMoonCard(){
        return  new RecogResult()
                .targetX(449)
                .targetY(470)
                .inSense(findInScreen("/imgs/home/moon_card_get.png"))
                .desc("领取月卡");
    }
}
