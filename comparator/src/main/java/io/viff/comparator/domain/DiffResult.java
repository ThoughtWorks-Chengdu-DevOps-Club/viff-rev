package io.viff.comparator.domain;

import java.util.List;

public class DiffResult {
    private List<Point> diffPoints;
    private double denominator;

    public double getDenominator() {
        return denominator;
    }

    public void setDenominator(double denominator) {
        this.denominator = denominator;
    }

    public List<Point> getDiffPoints() {
        return diffPoints;
    }

    public void setDiffPoints(List<Point> diffPoints) {
        this.diffPoints = diffPoints;
    }
}
