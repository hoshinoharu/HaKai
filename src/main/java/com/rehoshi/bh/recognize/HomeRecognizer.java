package com.rehoshi.bh.recognize;

import java.awt.geom.Rectangle2D;

public class HomeRecognizer extends BhRecognizer {

    public RecogResult findStaminaPotion(){
        Rectangle2D.Double inScreen = findInScreen("/imgs/home/stamina_potion.PNG");
        double x = 439 ;
        double y = 4 ;
        return new RecogResult(x, y, inScreen) ;
    }

    public RecogResult findAnnouncement(){
        Rectangle2D.Double inScreen = findInScreen("/imgs/home/notice.PNG");
        double x = 488 ;
        double y = 34 ;
        return new RecogResult(x, y, inScreen) ;
    }
}
