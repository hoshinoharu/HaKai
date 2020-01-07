package com.rehoshi.bh.recognize;

import com.rehoshi.bh.domain.RecognizeResult;

public class StrictRecognizer extends BhRecognizer {
    @Override
    protected RecognizeResult $() {
        return super.$().foundThreshold(2);
    }
}
