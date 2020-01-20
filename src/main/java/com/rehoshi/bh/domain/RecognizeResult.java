package com.rehoshi.bh.domain;


import com.rehoshi.bh.checker.RecognizeChecker;

public class RecognizeResult {
    //识别结果ID
    private Integer id;
    private String desc;
    private Boolean found;
    private Double targetX;
    private Double targetY;
    private MatchResult inSense;
    private Rect intentRect;
    private double foundThreshold = 10;
    private RecognizeChecker checker;
    private Double matchMinVal;

    public RecognizeResult() {

    }

    public RecognizeResult(Double targetX, Double targetY, MatchResult inSense) {
        this.targetX = targetX;
        this.targetY = targetY;
        this.inSense = inSense;
    }

    public RecognizeResult(int targetX, int targetY, MatchResult inSense) {
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

    public MatchResult getInSense() {
        return inSense;
    }

    public Rect getIntentRect() {
        if (intentRect == null) {
            intentRect = getInSense().getMatchRect();
        }
        return intentRect;
    }

    public void setIntentRect(Rect intentRect) {
        this.intentRect = intentRect;
    }

    public RecognizeResult intentRect(Rect intentRect) {
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
        System.out.println("识别区域{"+desc+"}:" + inSense.getMatchRect());
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

    public RecognizeResult targetY(double targetY) {
        setTargetY(targetY);
        return this;
    }

    public void setInSense(MatchResult inSense) {
        this.inSense = inSense;
        matchMinVal(inSense.getMinVal());
    }

    public RecognizeResult inSense(MatchResult inSense) {
        setInSense(inSense);
        return this;
    }

    public RecognizeResult foundThreshold(double foundThreshold) {
        this.foundThreshold = foundThreshold;
        return this;
    }

    public RecognizeChecker getChecker() {
        if (checker == null) {
            checker = RecognizeChecker.DEFAULT_CHECKER;
        }
        return checker;
    }

    public RecognizeResult checker(RecognizeChecker checker) {
        this.checker = checker;
        return this;
    }

    public double getFoundThreshold() {
        return foundThreshold;
    }

    public Double getMatchMinVal() {
        return matchMinVal;
    }

    public RecognizeResult matchMinVal(Double matchMinVal) {
        this.matchMinVal = matchMinVal;
        return this;
    }

    public int getId() {
        return id == null ? -1 : id;
    }

    public RecognizeResult id(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "RecognizeResult{" +
                "id=" + id +
                ", found=" + found +
                ", inSense=" + inSense +
                '}';
    }
}
