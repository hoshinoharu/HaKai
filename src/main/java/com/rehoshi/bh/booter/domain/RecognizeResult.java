package com.rehoshi.bh.booter.domain;


public class RecognizeResult {

    private String desc;
    private Boolean found;
    private Double targetX;
    private Double targetY;
    private MatchRect inSense;
    private MatchRect intentRect;
    private double foundThreshold = 10;
    private RecognizeChecker checker ;
    private Double matchMinVal ;

    public RecognizeResult(){

    }

    public RecognizeResult(Double targetX, Double targetY, MatchRect inSense) {
        this.targetX = targetX;
        this.targetY = targetY;
        this.inSense = inSense;
    }

    public RecognizeResult(int targetX, int targetY, MatchRect inSense) {
        this((double) targetX, (double) targetY, inSense);
    }

    public boolean isFound() {
        if (found == null) {
            this.found = getChecker().check(this);
        }
        return found;
    }

    public Double getTargetX() {
        return targetX;
    }

    public Double getTargetY() {
        return targetY;
    }

    public MatchRect getInSense() {
        return inSense;
    }

    public MatchRect getIntentRect() {
        if (intentRect == null) {
            intentRect = getInSense();
        }
        return intentRect;
    }

    public void setIntentRect(MatchRect intentRect) {
        this.intentRect = intentRect;
    }

    public RecognizeResult intentRect(MatchRect intentRect) {
        setIntentRect(intentRect);
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public RecognizeResult desc(String desc) {
        setDesc(desc);
        return this;
    }

    public void setTargetX(Double targetX) {
        this.targetX = targetX;
    }

    public void setTargetY(Double targetY) {
        this.targetY = targetY;
    }

    public RecognizeResult targetX(double targetX) {
        setTargetX(targetX);
        return this;
    }

    public RecognizeResult targetY(double targetY){
        setTargetY(targetY);
        return this ;
    }

    public void setInSense(MatchRect inSense) {
        this.inSense = inSense;
        matchMinVal(inSense.getMinVal()) ;
    }
    public RecognizeResult inSense(MatchRect inSense){
        setInSense(inSense);
        return this;
    }

    public RecognizeResult foundThreshold(double foundThreshold) {
        this.foundThreshold = foundThreshold;
        return this ;
    }

    public RecognizeChecker getChecker() {
        if(checker == null){
            checker = RecognizeChecker.DEFAULT_CHECKER ;
        }
        return checker;
    }

    public RecognizeResult checker(RecognizeChecker checker) {
        this.checker = checker;
        return this ;
    }

    public double getFoundThreshold() {
        return foundThreshold;
    }

    public Double getMatchMinVal() {
        return matchMinVal;
    }

    public RecognizeResult matchMinVal(Double matchMinVal) {
        this.matchMinVal = matchMinVal;
        return this ;
    }
}
