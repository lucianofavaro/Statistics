package com.codechallenge.integration;

import com.codechallenge.model.Request;
import com.codechallenge.model.Result;
import com.codechallenge.service.Accumulator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jvnet.hk2.config.Units;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccumulatorParallelIntegrationTest {

    private static final double AMOUNT = 10;
    private static final int TIMES_TO_EXECUTE = 10;
    private static final int TIME_REQUIRED_TO_ZEROING_ACCUMULATOR_IN_MILLISECONDS = 62000;
    private static final int TIME_FOR_NEXT_CALCULATION_MILLISECONDS = 501;
    public static final int TIMEOUT_FOR_THREADS_TO_RUN = 100;

    @Autowired
    private Accumulator accumulator;

    @Test
    public void shouldAccumulateAndGiveAccurateResultsInParallel() throws InterruptedException {
        Result result = accumulator.latest();
        AssertUtils.assertInitialState(result);

        CountDownLatch latch = new CountDownLatch(TIMES_TO_EXECUTE);

        Executor executor = Executors.newFixedThreadPool(TIMES_TO_EXECUTE);

        for (int i=0; i<TIMES_TO_EXECUTE; i++) {
            executor.execute(new StatisticsGenerator(latch));
        }

        latch.await(TIMEOUT_FOR_THREADS_TO_RUN, TimeUnit.MILLISECONDS);

        // Wait for next calculation
        Thread.sleep(TIME_FOR_NEXT_CALCULATION_MILLISECONDS);

        result = accumulator.latest();
        AssertUtils.assertState(result, (AMOUNT * TIMES_TO_EXECUTE) / TIMES_TO_EXECUTE, AMOUNT, AMOUNT, AMOUNT * TIMES_TO_EXECUTE, TIMES_TO_EXECUTE);

        // Wait for cleanup
        Thread.sleep(TIME_REQUIRED_TO_ZEROING_ACCUMULATOR_IN_MILLISECONDS);

        result = accumulator.latest();
        AssertUtils.assertInitialState(result);
    }

    private class StatisticsGenerator implements Runnable {

        private final CountDownLatch latch;

        public StatisticsGenerator(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            Request request = new Request(AMOUNT, System.currentTimeMillis());
            accumulator.process(request);
            latch.countDown();
        }
    }
}
