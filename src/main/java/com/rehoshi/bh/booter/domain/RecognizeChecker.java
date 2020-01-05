package com.rehoshi.bh.booter.domain;

public interface RecognizeChecker {

    /**
     * 默认的检查器 判断识别结果的xy误差总和不大于设定的阈值
     */
    RecognizeChecker DEFAULT_CHECKER = result ->
            Math.abs(result.getInSense().x - result.getTargetX())
                    + Math.abs(result.getInSense().y - result.getTargetY())
                    <= result.getFoundThreshold();

    /**
     * 只检查x坐标的检查器
     */
    RecognizeChecker X_ONLY = result ->
            Math.abs(result.getInSense().x - result.getTargetX()) <= result.getFoundThreshold();

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
                    flag = Math.abs(result.getInSense().x - xArr[i]) <= result.getFoundThreshold() ;
                }
            }
            return flag;
        };
    }
}
