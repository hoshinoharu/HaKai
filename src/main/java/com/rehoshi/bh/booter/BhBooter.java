package com.rehoshi.bh.booter;

import com.rehoshi.bh.booter.domain.RecognizeResult;
import com.rehoshi.bh.recognize.BhRecognizer;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.util.Comparator;
import java.util.function.Supplier;
import java.util.stream.Stream;

public abstract class BhBooter<R extends BhRecognizer> implements Booter {

    private BhDriver driver;

    private R bhRecognizer;

    private Booter nextBooter ;

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

    protected RecognizeResult $h(Supplier<RecognizeResult>... supplier){
        return handleAllRecognizers(supplier) ;
    }

    protected Supplier<RecognizeResult> $s_h(Supplier<RecognizeResult>... supplier){
        return () -> $s(supplier);
    }


    /**找到所有识别结果中最匹配的结果
     * @param supplier
     * @return
     */
    protected RecognizeResult $s(Supplier<RecognizeResult>... supplier){
        RecognizeResult recognizeResult = Stream.of(supplier)
                .map(Supplier::get)
                .sorted(Comparator.comparing(RecognizeResult::getMatchMinVal))
                .findFirst().orElse(null);
        return recognizeResult ;
    }

    /**
     * 拦截解析
     * @return
     */
    @Override
    public int interceptRecognize(){
        RecognizeResult recognizeResult = handleAllRecognizers(
                $()::findError //识别错误对话框
        );
        if(recognizeResult.isFound()){
            //点击默认意图 继续游戏
            getDriver().click(recognizeResult.getIntentRect()) ;
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
        return nextBooter;
    }

    public void setNextBooter(Booter nextBooter) {
        this.nextBooter = nextBooter;
    }

    public RecognizeResult handleAllRecognizers(Supplier<RecognizeResult>... recognizers){
        RecognizeResult recognizeResult = null;
        for (Supplier<RecognizeResult> recog : recognizers){
            recognizeResult = recog.get();
            if(recognizeResult.isFound()){
                break;
            }
        }
        return recognizeResult;
    }

    protected int toNextSense(Booter booter){
        setNextBooter(booter);
        return RecognizeStatus.TO_NEXT_SENSE ;
    }

}
