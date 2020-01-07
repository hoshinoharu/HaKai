package com.rehoshi.bh.checker;

import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.orc.Ocer;
import org.opencv.core.Mat;

import java.nio.ByteBuffer;

public class TextChecker implements RecognizeChecker {

    private String text;

    public TextChecker(String text) {
        this.text = text;
    }

    @Override
    public boolean check(RecognizeResult $) {
        //识别灰度场景中匹配位置的文字
        Mat sense = $.getInSense().getSenseGary();
        String ocr = Ocer.getInstance().ocrChiness(sense, $.getInSense().getMatchRect());
        System.out.println("ocr结果 " + ocr);
        //替换所有空格消除影响
        boolean matches = ocr.replaceAll("\\s", "").matches(".*?" + text + ".*?");
        return matches;
    }
}
