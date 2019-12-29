package com.rehoshi.bh.recognize;

import java.awt.geom.Rectangle2D;

public class LoginRecognizer extends BhRecognizer {



    public RecogResult findClickToLogin() {
        Rectangle2D.Double inScreen = findInScreen("/imgs/login/click_to_login.PNG");
        double x = 365;
        double y = 376;
        return new RecogResult(x, y, inScreen);
    }
}
