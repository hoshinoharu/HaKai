package com.rehoshi.bh.booter;

import com.rehoshi.bh.recognize.BhRecognizer;

import java.io.File;
import java.lang.reflect.ParameterizedType;

public abstract class BhBooter<R extends BhRecognizer> implements Booter {

    private BhDriver driver;

    private R bhRecognizer;

    public void bindDriver(BhDriver driver) {
        this.driver = driver;
        if (this.bhRecognizer == null) {
            try {
                ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
                Class type = (Class) parameterizedType.getActualTypeArguments()[0];
                this.bhRecognizer = (R) type.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.bhRecognizer.bindDriver(driver);
    }

    public BhDriver getDriver() {
        return driver;
    }

    public File getScreenShotFile() {
        return driver.getScreenAsFile();
    }

    public R getBhRecognizer() {
        return bhRecognizer;
    }

    public int recognizeFrame() {
        System.out.println("分析当前帧");
        return RecognizeStatus.STAY_CUR_SENSE;
    }

    public Booter getNextBooter() {
        return null;
    }
}
