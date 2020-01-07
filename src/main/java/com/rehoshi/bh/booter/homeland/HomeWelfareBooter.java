package com.rehoshi.bh.booter.homeland;

import com.rehoshi.bh.booter.BhBooter;
import com.rehoshi.bh.domain.Rect;
import com.rehoshi.bh.recognize.homeland.HomeWelfareRecognizer;

public class HomeWelfareBooter extends BhBooter<HomeWelfareRecognizer> {
    @Override
    public boolean recognizeSense() {
        return false;
    }

    @Override
    public int recognizeFrame() {
        return super.recognizeFrame();
    }

    @Override
    public void back() {
        //点击对话框×号
        getDriver().click(new Rect(684, 138, 10, 10)) ;
    }
}
