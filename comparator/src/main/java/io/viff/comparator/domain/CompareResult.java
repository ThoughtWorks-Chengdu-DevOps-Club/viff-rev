package io.viff.comparator.domain;


public class CompareResult {
    private double similarity;
    private String resultPath;
    private boolean isSame;

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    public String getResultPath() {
        return resultPath;
    }

    public void setResultPath(String resultPath) {
        this.resultPath = resultPath;
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
                ", resultPath='" + resultPath + '\'' +
                ", isSame=" + isSame +
                '}';
    }
}
