package com.rehoshi.bh.booter;

import com.rehoshi.bh.recognize.HomeRecognizer;
import com.rehoshi.bh.recognize.RecogResult;

import java.awt.geom.Rectangle2D;

public class HomeBotter extends BhBooter<HomeRecognizer> {

    private boolean hasNotice = true;

    private int searchNoticeCount = 0 ;

    public boolean recognizeSense() {
        if (hasNotice) {
            searchNoticeCount ++ ;
            //查找公告
            RecogResult announcement = getBhRecognizer().findAnnouncement();
            if (announcement.isFinded()) {
                //关闭公告
                getDriver().click(new Rectangle2D.Double(875, 55, 10, 10));
                System.out.println("关闭公告");
                hasNotice = true ;
            }else if(searchNoticeCount == 3){
                System.out.println("没有找到公告");
                return true ;
        }
        }else {
            RecogResult staminaPotion = getBhRecognizer().findStaminaPotion();
            if(staminaPotion.isFinded()){
                //识别主界面成功
                System.out.println("识别主界面成功");
                return true ;
            }
        }
        return false;
    }

    private void handleCover(){

    }
}
