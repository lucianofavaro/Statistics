package com.codechallenge.repository;

import com.codechallenge.model.Result;
import com.codechallenge.utils.UnixTimestampUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * InMemory reference implementation for statistics repository.
 */
@Repository
public class InMemoryStatisticsRepository implements StatisticsRepository {

    private static final int SIXTY_SECONDS = 60;
    public static final int HALF_SECONDS = 500;

    private Map<Long, Result> latestStatistics = new ConcurrentHashMap<>();

    private Result result;

    /**
     * Recalculate the statistics, removing older than 60 seconds from internal map.
     *
     * Map will store last sixth seconds of data, where every map key is a unix second
     * so storage is optimized for in memory accumulation.
     *
     * I need to think a little bit more regarding responsibility since statistics
     * calculation is not part of Repository duties, however it's better optimized when
     * looping through the Map
     */
    @Scheduled(fixedRate = HALF_SECONDS)
    public void recalculate() {
        long currentUnixTimeSeconds = getCurrentTimeStampSeconds();

        Result result = new Result();
        boolean shouldInitializeMinMax = true;

        // remove old items and recalculate statistics
        Iterator<Map.Entry<Long, Result>> it = latestStatistics.entrySet().iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Map.Entry<Long, Result> entry = it.next();
                if (currentUnixTimeSeconds - entry.getKey() > SIXTY_SECONDS) {
                    it.remove();
                } else {
                    if (shouldInitializeMinMax) {
                        result.setMax(Double.MIN_VALUE);
                        result.setMin(Double.MAX_VALUE);

                        shouldInitializeMinMax = false;
                    }

                    Result cached = entry.getValue();
                    result.addSum(cached.getSum());
                    result.addCount(cached.getCount());

                    if (cached.getMax() > result.getMax()) {
                        result.setMax(cached.getMax());
                    }
                    if (cached.getMin() < result.getMin()) {
                        result.setMin(cached.getMin());
                    }
                }
            }
        }

        this.result = result;
    }

    @Override
    public void add(long timeStamp, double value) {
        if (getCurrentTimeStampSeconds() - timeStamp > SIXTY_SECONDS) {
            // discard older than 60 seconds data
            return;
        }

        synchronized (latestStatistics) {
            Result result = latestStatistics.get(timeStamp);

            if (result == null) {
                latestStatistics.put(timeStamp, new Result(1, value, value, value));
            } else {
                result.addCount(1);
                result.addSum(value);
                if (value > result.getMax()) {
                    result.setMax(value);
                }
                if (value < result.getMin()) {
                    result.setMin(value);
                }
                latestStatistics.put(timeStamp, result);
            }
        }
    }

    /**
     * Returns the latest statistics in O(1) since it's precalculated
     * Super fast, not letting the user waiting for calculations.
     * @return
     */
    @Override
    public Result getLatest() {
        return result;
    }

    private long getCurrentTimeStampSeconds() {
        return UnixTimestampUtils.getUnixTimeStampInSeconds(System.currentTimeMillis());
    }
}
