package io.viff.comparator.domain;


public class CompareResult {
    private double similarity;
    private String diff;
    private boolean isSame;


    public CompareResult() {
    }

    public CompareResult(String diff) {
        this.diff = diff;
    }

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public boolean isSame() {
        return isSame;
    }

    public void setSame(boolean same) {
        isSame = same;
    }

    @Override
    public String toString() {
        return "CompareResult{" +
                "similarity=" + similarity +
                ", diff='" + diff + '\'' +
                ", isSame=" + isSame +
                '}';
    }
}
