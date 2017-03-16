package com.codechallenge.service;

import com.codechallenge.model.Request;
import com.codechallenge.model.Result;
import com.codechallenge.repository.StatisticsRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.omg.CORBA.PRIVATE_MEMBER;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class AccumulatorTest {

    public static final long TIMESTAMP = 1000L;
    public static final long TIMESTAMP_INSECONDS = TIMESTAMP / 1000L;
    @Mock
    private StatisticsRepository statisticsRepository;

    private Accumulator accumulator;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.accumulator = new Accumulator(statisticsRepository);
    }

    @Test
    public void shouldGetLatest() throws Exception {
        accumulator.latest();
        verify(statisticsRepository).getLatest();
    }

    @Test
    public void shouldProcess() throws Exception {
        Request request = new Request();
        request.setAmount(1);
        request.setTimestamp(TIMESTAMP);
        accumulator.process(request);
        verify(statisticsRepository).add(TIMESTAMP_INSECONDS, request.getAmount());
    }
}