package com.rehoshi.bh.booter;

import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.recognize.LoginRecognizer;

public class LoginBooter extends BhBooter<LoginRecognizer> {

    public int recognizeFrame() {

        RecognizeResult clickToLogin = $().findClickToLogin();

        if (clickToLogin.isFound()) {

            System.out.println("点击登录");

            return toNextSense(clickToLogin, new HomeBooter());
        }

        return super.recognizeFrame();
    }
}
