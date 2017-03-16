package com.codechallenge.model;

import org.assertj.core.data.Percentage;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {

    private Result result = new Result();

    private final static double SUM = 10;
    private final static double MIN = 8.8;
    private final static double MAX = 9.8;
    private final static int COUNT = 3;
    private final static double AVERAGE = 3.333;
    private final static double DOUBLE_NUMBER = SUM * 2;
    private final static int DOUBLE_COUNT = COUNT * 2;

    @Test
    public void shouldGetSum() throws Exception {
        result.setSum(SUM);
        assertThat(result.getSum()).isEqualTo(SUM);
    }

    @Test
    public void shouldAddSum() throws Exception {
        result.setSum(SUM);
        result.addSum(SUM);
        assertThat(result.getSum()).isEqualTo(DOUBLE_NUMBER);
    }

    @Test
    public void shouldGetAvg() throws Exception {
        result.setSum(SUM);
        result.setCount(COUNT);
        assertThat(result.getAvg()).isCloseTo(AVERAGE, Percentage.withPercentage(0.02));
    }

    @Test
    public void shouldGetMax() throws Exception {
        result.setMax(MAX);
        assertThat(result.getMax()).isEqualTo(MAX);
    }

    @Test
    public void shouldGetMin() throws Exception {
        result.setMin(MIN);
        assertThat(result.getMin()).isEqualTo(MIN);
    }

    @Test
    public void shouldGetCount() throws Exception {
        result.setCount(COUNT);
        assertThat(result.getCount()).isEqualTo(COUNT);
    }

    @Test
    public void shouldAddCount() throws Exception {
        result.setCount(COUNT);
        result.addCount(COUNT);
        assertThat(result.getCount()).isEqualTo(DOUBLE_COUNT);
    }

    @Test
    public void shouldConstructorInitializeCorrectly() {
        Result result = new Result(COUNT, SUM, MAX, MIN);
        assertThat(result.getCount()).isEqualTo(COUNT);
        assertThat(result.getSum()).isEqualTo(SUM);
        assertThat(result.getMax()).isEqualTo(MAX);
        assertThat(result.getMin()).isEqualTo(MIN);assertThat(result.getAvg()).isCloseTo(AVERAGE, Percentage.withPercentage(0.02));
    }
}