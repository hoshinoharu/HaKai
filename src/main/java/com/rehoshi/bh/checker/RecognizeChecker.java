package com.rehoshi.bh.checker;

import com.rehoshi.bh.domain.RecognizeResult;

public interface RecognizeChecker {

    /**
     * 默认的检查器 判断识别结果的xy误差总和不大于设定的阈值
     */
    RecognizeChecker DEFAULT_CHECKER = result ->
            Math.abs(result.getInSense().getMatchRect().x - result.getTargetX())
                    + Math.abs(result.getInSense().getMatchRect().y - result.getTargetY())
                    <= result.getFoundThreshold();

    /**
     * 只检查x坐标的检查器
     */
    RecognizeChecker X_ONLY = result ->
            Math.abs(result.getInSense().getMatchRect().x - result.getTargetX()) <= result.getFoundThreshold();

    boolean check(RecognizeResult $);

    /**
     * x坐标满足其中一个就返回true
     * @param xArr
     * @return
     */
    static RecognizeChecker xIn(double... xArr) {
        return result -> {
            boolean flag = false;
            if (xArr != null) {
                for (int i = 0; i < xArr.length && !flag; i++){
                    flag = Math.abs(result.getInSense().getMatchRect().x - xArr[i]) <= result.getFoundThreshold() ;
                }
            }
            return flag;
        };
    }

    /**
     * 识别链
     * 链中每一个checker检查成功，才代表整条链检查成功，否则直接失败
     * @param checkers
     * @return
     */
    static RecognizeChecker chain(RecognizeChecker... checkers){
        return RecognizeCheckerChain.of(checkers) ;
    }

    static RecognizeChecker textEqual(String text){
        return new TextChecker(text) ;
    }
}
