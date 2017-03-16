package com.codechallenge.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestTest {

    private static final double AMOUNT = 10;
    private static final long TIMESTAMP = 10;

    private final Request request = new Request();

    @Test
    public void shouldGetAmount() throws Exception {
        request.setAmount(AMOUNT);
        assertThat(request.getAmount()).isEqualTo(AMOUNT);
    }

    @Test
    public void shouldGetTimestamp() throws Exception {
        request.setTimestamp(TIMESTAMP);
        assertThat(request.getTimestamp()).isEqualTo(TIMESTAMP);
    }
}