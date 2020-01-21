package com.rehoshi.bh.recognize.attack;

import com.rehoshi.bh.auto.HakaiId;
import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.domain.Rect;
import com.rehoshi.bh.recognize.StrictRecognizer;

import java.util.Calendar;

public class CallUpRecognizer extends StrictRecognizer {
    @HakaiId
    public RecognizeResult findEnterMonday() {
        return $().targetX(535)
                .targetY(214)
                .inSense(findInScreen("imgs/attack/enter_monday_.png"))
                .desc("征召关卡周一入口");
    }

    @HakaiId
    public RecognizeResult findEnterTuesday(){
        return $().targetX(533)
                .targetY(225)
                .inSense(findInScreen("imgs/attack/enter_tuesday.png"))
                .desc("征召关卡周二入口");
    }

    public RecognizeResult findEnter(){
        Calendar calendar = Calendar.getInstance() ;
        int i = calendar.get(Calendar.DAY_OF_WEEK);
        RecognizeResult result = null ;
        switch (i){
            case Calendar.SUNDAY:
                break;
            case Calendar.MONDAY:
                result = findEnterMonday() ;
                break;
            case Calendar.TUESDAY:
                result = findEnterTuesday() ;
                break;
        }
        return result ;

    }

    @HakaiId
    public RecognizeResult findCallUpTitle(){
        return $().targetX(8)
                .targetY(60)
                .inSense(findInScreen("imgs/attack/call_up_title_ex.png"))
                .intentRect(new Rect(537, 229, 124, 114))//设置点击区域
                .desc("征召关卡标题");
    }
}
