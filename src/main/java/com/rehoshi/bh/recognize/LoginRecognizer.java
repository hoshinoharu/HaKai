package com.rehoshi.bh.recognize;

import com.rehoshi.bh.auto.HakaiId;
import com.rehoshi.bh.domain.RecognizeResult;

public class LoginRecognizer extends BhRecognizer {
    public RecognizeResult findClickToLogin() {
        double x = 365;
        double y = 376;
        return $().targetY(y)
                .targetX(x)
                .inSense(findInScreen("/imgs/login/click_to_login.PNG"));
    }
}
