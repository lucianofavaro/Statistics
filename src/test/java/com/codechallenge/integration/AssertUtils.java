package com.codechallenge.integration;

import com.codechallenge.model.Result;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertUtils {

    public static void assertState(Result result, double avg, double max, double min, double sum, long count) {
        assertThat(result.getAvg()).isEqualTo(avg);
        assertThat(result.getMax()).isEqualTo(max);
        assertThat(result.getMin()).isEqualTo(min);
        assertThat(result.getSum()).isEqualTo(sum);
        assertThat(result.getCount()).isEqualTo(count);
    }

    public static void assertInitialState(Result result) {
        assertState(result, 0, 0, 0, 0, 0L);
    }
}
