package com.rehoshi.bh.booter;

import com.rehoshi.bh.recognize.HomeRecognizer;
import com.rehoshi.bh.recognize.RecogResult;

public class HomeBooter extends BhBooter<HomeRecognizer> {

    private static boolean findCover = false;

    //请求封面超时时间
    private final static long coverTimeOut = 6 * 1000 ;

    private long startTime = -1 ;

    public boolean recognizeSense() {

        long timeStamp = System.currentTimeMillis() ;
        if(startTime == -1){
            startTime = timeStamp ;
        }

        //如果没有找到封面 就一直找
        boolean handled = handleCover();
        if(handled){
            findCover = true ;
        }else if(findCover || timeStamp - startTime >= coverTimeOut){
            //如果没找到cover 并且找到过 cover 开始识别主界面 或者 识别cover超时

            //识别主界面
            RecogResult staminaPotion = getBhRecognizer().findStaminaPotion();

            if (staminaPotion.isFound()) {
                //识别主界面成功
                System.out.println("识别主界面成功");
                return true;
            }
        }
        return false;
    }

    @Override
    public int recognizeFrame() {

        return super.recognizeFrame();
    }

    private boolean handleCover() {

        RecogResult recogResult = handleAllRecognizers(
                $()::findAnnouncement //查找公告
                , $()::findVersionHot //版本热点
                , $()::findActivityGetBtn // 活动领取
                , $()::findTaskConfirm //任务完成
                , $()::findSingInGetBtn //每日签到领取按钮
                , $()::findSingInRewardConfirm //每日签到奖励领取
                , $()::findMoonCard //月卡
        );

        if (recogResult.isFound()) {
            getDriver().click(recogResult.getIntentRect());
            return true;
        }
        return false;
    }
}
