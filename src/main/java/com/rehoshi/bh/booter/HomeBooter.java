package com.rehoshi.bh.booter;

import com.rehoshi.bh.auto.Hakai;
import com.rehoshi.bh.booter.attack.AttackBooter;
import com.rehoshi.bh.booter.homeland.HomeLandBooter;
import com.rehoshi.bh.booter.task.TaskBooter;
import com.rehoshi.bh.recognize.HomeRecognizer;
import com.rehoshi.bh.domain.RecognizeResult;

public class HomeBooter extends BhBooter<HomeRecognizer> {

    private static boolean findCover = false;

    //请求封面超时时间
    private final static long coverTimeOut = 10 * 1000 ;

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
            RecognizeResult staminaPotion = getBhRecognizer().findStaminaPotion();

            if (staminaPotion.isFound()) {
                //识别主界面成功
                System.out.println("识别主界面成功 超时时间" + (timeStamp - startTime));
                return true;
            }
        }
        return false;
    }

    @Override
    public int getMaxSenseRecognizeTimes() {
        return 100;
    }

    @Override
    public int recognizeFrame() {
        RecognizeResult result = $h($()::findAttackBtn,$()::findHomeLandBtn
                , $()::findTaskHint);
            System.out.println(result + " " + Hakai.Id.HomeRecognizer.findAttackBtn);
        if(result.isFound()){
            switch (result.getId()){
                case Hakai.Id.HomeRecognizer.findAttackBtn:
                    return toNextSense(result, new AttackBooter()) ;
                case Hakai.Id.HomeRecognizer.findTaskHint:
                    return toNextSense(result, new TaskBooter()) ;
                case Hakai.Id.HomeRecognizer.findHomeLandBtn:
                    return toNextSense(result, new HomeLandBooter());
            }
        }else {
            handleCover();
        }
        return super.recognizeFrame();
    }

    private boolean handleCover() {

        RecognizeResult recognizeResult = handleAllRecognizers(
                $()::findAnnouncement //查找公告
                ,$()::findHintAnnouncement //查找带提示公告
                , $()::findVersionHot //版本热点
                , $()::findActivityGetBtn // 活动领取
                , $()::findTaskConfirm //任务完成
                , $()::findSingInGetBtn //每日签到领取按钮
                , $()::findSingInRewardConfirm //每日签到奖励领取
                , $()::findMonthCard //月卡
        );

        if (recognizeResult.isFound()) {
            System.out.println("找到" + recognizeResult.getDesc());
            getDriver().click(recognizeResult.getIntentRect());
            return true;
        }
        return false;
    }
}
