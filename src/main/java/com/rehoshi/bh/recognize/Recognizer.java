package com.rehoshi.bh.recognize;

import java.awt.geom.Rectangle2D;

public interface Recognizer {

    /**
     * 在场景中查找目标图片位置
     * @param target
     * @param sense
     * @return
     */
    Rectangle2D.Double findIn(String target, String sense) ;
}
