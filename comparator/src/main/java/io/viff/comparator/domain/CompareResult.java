package io.viff.comparator.domain;


public class CompareResult {
    private double similarity;
    private Storable diff;
    private boolean isSame;

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    public Storable getDiff() {
        return diff;
    }

    public void setDiff(Storable diff) {
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
