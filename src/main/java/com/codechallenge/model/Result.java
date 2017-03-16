package com.codechallenge.model;

import com.google.common.base.MoreObjects;

public class Result {

    private double sum;
    private double max;
    private double min;
    private long count;

    public Result() {
    }

    public Result(long count, double sum, double max, double min) {
        this.count = count;
        this.sum = sum;
        this.max = max;
        this.min = min;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public void addSum(double sum) {
        this.sum += sum;
    }

    public double getAvg() {
        if (count == 0) {
            return count;
        }

        return sum/count;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void addCount(long count) {
        this.count += count;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("sum", sum)
                .add("avg", getAvg())
                .add("max", max)
                .add("min", min)
                .add("count", count)
                .toString();
    }
}



