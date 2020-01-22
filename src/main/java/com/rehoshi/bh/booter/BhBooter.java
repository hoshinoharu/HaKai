package com.rehoshi.bh.booter;

import com.rehoshi.bh.domain.RecognizeResult;
import com.rehoshi.bh.domain.Rect;
import com.rehoshi.bh.driver.BhDriver;
import com.rehoshi.bh.recognize.BhRecognizer;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.util.Comparator;
import java.util.function.Supplier;
import java.util.stream.Stream;

public abstract class BhBooter<R extends BhRecognizer> implements Booter {

    private BhDriver driver;

    private R bhRecognizer;

    private Booter nextBooter;

    private boolean finish = false;

    /**
     *识别场景最大次数
     */
    private int curSenseRecognizeTimes  ;

    public void addSenseRecognizeTimes(){
        this.curSenseRecognizeTimes ++ ;
    }

    public int getCurSenseRecognizeTimes() {
        return curSenseRecognizeTimes;
    }

    public int getMaxSenseRecognizeTimes() {
        return 2;
    }

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
        this.curSenseRecognizeTimes = 0 ;
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

    protected R $() {
        return getBhRecognizer();
    }

    protected RecognizeResult $h(Supplier<RecognizeResult>... supplier) {
        return handleAllRecognizers(supplier);
    }

    protected Supplier<RecognizeResult> $s_h(Supplier<RecognizeResult>... supplier) {
        return () -> $s(supplier);
    }

    /**
     * 找到所有识别结果中最匹配的结果
     *
     * @param supplier
     * @return
     */
    protected RecognizeResult $s(Supplier<RecognizeResult>... supplier) {
        RecognizeResult recognizeResult = Stream.of(supplier)
                .map(Supplier::get)
                .sorted(Comparator.comparing(RecognizeResult::getMatchMinVal))
                .findFirst().orElse(null);
        return recognizeResult;
    }

    /**
     * 拦截解析
     *
     * @return
     */
    @Override
    public int interceptRecognize() {
        RecognizeResult recognizeResult = handleAllRecognizers(
                $()::findError //识别错误对话框
        );
        if (recognizeResult.isFound()) {
            //点击默认意图 继续游戏
            getDriver().click(recognizeResult.getIntentRect());
            return RecognizeStatus.STAY_CUR_SENSE;
        } else {
            return RecognizeStatus.NOT_INTERCEPT;
        }
    }

    public int recognizeFrame() {
        return RecognizeStatus.STAY_CUR_SENSE;
    }

    public Booter getNextBooter() {
        return nextBooter;
    }

    public void setNextBooter(Booter nextBooter) {
        this.nextBooter = nextBooter;
    }

    public RecognizeResult handleAllRecognizers(Supplier<RecognizeResult>... recognizers) {
        RecognizeResult recognizeResult = null;
        for (Supplier<RecognizeResult> recog : recognizers) {
            recognizeResult = recog.get();
            if (recognizeResult.isFound()) {
                break;
            }
        }
        return recognizeResult;
    }

    protected int toNextSense(Booter booter) {
        setNextBooter(booter);
        return RecognizeStatus.TO_NEXT_SENSE;
    }

    protected int toNextSense(RecognizeResult result, Booter booter) {
        handleClickIntent(result);
        return toNextSense(booter);
    }

    public int toBack() {
        back();
        return RecognizeStatus.TO_PRE_SENSE;
    }

    public void back() {
        //默认点击左上角返回按钮
        getDriver().click(new Rect(0, 8, 90, 38));
    }

    public void handleClickIntent(RecognizeResult result) {
        getDriver().click(result.getIntentRect());
    }

    public void finish(){
        this.finish = true ;
    }

    public boolean isFinish() {
        return finish;
    }

    protected void clickCenter(){
        getDriver().click(new Rect(0, 0, 960, 540)) ;
    }

    @Override
    public boolean recognizeSenseTimeout() {
        return getCurSenseRecognizeTimes() > getMaxSenseRecognizeTimes();
    }

    @Override
    public void onRecognizeSenseFinish() {
        addSenseRecognizeTimes();
    }
}
