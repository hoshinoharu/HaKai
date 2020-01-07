package com.rehoshi.bh.domain;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * 匹配结果
 */
public class MatchResult {
    //匹配结果的范围
    private Rect matchRect ;
    //场景图片
    private Mat sense ;
    //查找的目标图片
    private Mat target ;

    private double minVal ;

    public Rect getMatchRect() {
        return matchRect;
    }

    public void setMatchRect(Rect matchRect) {
        this.matchRect = matchRect;
    }

    public Mat getSense() {
        return sense;
    }

    public Mat getSenseGary(){
        Mat mat = new Mat() ;
        Imgproc.cvtColor(sense, mat, Imgproc.COLOR_RGB2GRAY);
        return mat ;
    }

    public void setSense(Mat sense) {
        this.sense = sense;
    }

    public Mat getTarget() {
        return target;
    }

    public void setTarget(Mat target) {
        this.target = target;
    }

    public double getMinVal() {
        return minVal;
    }

    public void setMinVal(double minVal) {
        this.minVal = minVal;
    }
}
