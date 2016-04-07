package io.viff.sdk.response;


import io.viff.sdk.domain.Storage;

public class CompareResult {
    private double similarity;
    private Storage diff;
    private boolean isSame;

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    public Storage getDiff() {
        return diff;
    }

    public void setDiff(Storage diff) {
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
