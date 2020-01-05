package com.rehoshi.bh.recognize;

import com.rehoshi.bh.booter.domain.RecognizeResult;

public class LoginRecognizer extends BhRecognizer {
    public RecognizeResult findClickToLogin() {
        double x = 365;
        double y = 376;
        return new RecognizeResult(x, y, findInScreen("/imgs/login/click_to_login.PNG"));
    }
}
