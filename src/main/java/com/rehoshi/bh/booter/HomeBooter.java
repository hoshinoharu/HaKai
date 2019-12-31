package com.rehoshi.bh.booter;

import com.rehoshi.bh.recognize.HomeRecognizer;
import com.rehoshi.bh.recognize.RecogResult;

import java.awt.geom.Rectangle2D;

public class HomeBooter extends BhBooter<HomeRecognizer> {


    public boolean recognizeSense() {
        RecogResult staminaPotion = getBhRecognizer().findStaminaPotion();
        if (staminaPotion.isFound()) {
            //识别主界面成功
            System.out.println("识别主界面成功");
            return true;
        }else {
            handleCover();
        }
        return false;
    }

    @Override
    public int recognizeFrame() {
        handleCover();
        return super.recognizeFrame();
    }

    private void handleCover() {

        RecogResult recogResult = handleAllRecognizers(
                $()::findAnnouncement //查找公告
                ,$()::findVersionHot //版本热点
                ,$()::findActivityGetBtn // 活动领取
                ,$()::findTaskConfirm //任务完成
                ,$()::findSingInGetBtn //每日签到领取按钮
                ,$()::findSingInRewardConfirm //每日签到奖励领取
                ,$()::findMoonCard //月卡
        );
        if(recogResult.isFound()){
            getDriver().click(recogResult.getIntentRect()) ;
        }
    }
}
