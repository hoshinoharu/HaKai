package com.rehoshi.bh.booter;

import com.rehoshi.bh.recognize.BhRecognizer;
import com.rehoshi.bh.recognize.RecogResult;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.util.function.Function;
import java.util.function.Supplier;

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

    protected R $(){
        return getBhRecognizer() ;
    }

    /**
     * 拦截解析
     * @return
     */
    @Override
    public int interceptRecognize(){
        RecogResult recogResult = handleAllRecognizers(
                $()::findError //识别错误对话框
        );
        if(recogResult.isFound()){
            //点击默认意图 继续游戏
            getDriver().click(recogResult.getIntentRect()) ;
            return RecognizeStatus.STAY_CUR_SENSE ;
        }else {
            return RecognizeStatus.NOT_INTERCEPT ;
        }
    }

    public int recognizeFrame() {
        System.out.println("分析当前帧");
        return RecognizeStatus.STAY_CUR_SENSE;
    }

    public Booter getNextBooter() {
        return null;
    }


    public RecogResult handleAllRecognizers(Supplier<RecogResult>... recognizers){
        RecogResult recogResult = null;
        for (Supplier<RecogResult> recog : recognizers){
            recogResult = recog.get();
            if(recogResult.isFound()){
                break;
            }
        }
        return recogResult ;
    }

}
