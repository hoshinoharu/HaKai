package com.rehoshi.bh.booter;

import com.rehoshi.bh.recognize.LoginRecognizer;
import com.rehoshi.bh.booter.domain.RecognizeResult;

public class LoginBooter extends BhBooter<LoginRecognizer> {

    public LoginBooter() {
    }

    public boolean recognizeSense() {
        try {
            RecognizeResult clickToLogin = getBhRecognizer().findClickToLogin();
            if (clickToLogin.isFound()) {
                System.out.println("点击登录");
                getDriver().click(clickToLogin.getInSense());
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int recognizeFrame() {
        super.recognizeFrame();
        return RecognizeStatus.TO_NEXT_SENSE;
    }

    public Booter getNextBooter() {
        return new HomeBooter();
    }

}
