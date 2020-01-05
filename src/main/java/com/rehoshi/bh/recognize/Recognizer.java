package com.rehoshi.bh.recognize;

import com.rehoshi.bh.booter.domain.MatchRect;

public interface  Recognizer {

    /**
     * 在场景中查找目标图片位置
     * @param target
     * @param sense
     * @return
     */
    MatchRect findIn(String target, String sense) ;
}
