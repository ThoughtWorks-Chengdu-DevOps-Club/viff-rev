package io.viff.comparator.domain;


public class CompareResult {
    private double similarity;
    private Storable result;
    private boolean isSame;

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    public Storable getResult() {
        return result;
    }

    public void setResult(Storable result) {
        this.result = result;
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
                ", result='" + result + '\'' +
                ", isSame=" + isSame +
                '}';
    }
}
