package com.codechallenge.repository;

import com.codechallenge.model.Result;

/**
 * Reference to a repository that stores and retrieve statistics.
 *
 * It could be a local repository like InMemoryStatisticsRepository Reference Implementation
 * in this case application would not scale.
 *
 * It can be implemented as a proxy to redis or memcached, so module become stateless and could scale horizontally.
 */
public interface StatisticsRepository {
    /**
     * Add a new statistic to repository
     * @param timeStamp 10 digits Unix timestamp (in seconds)
     * @param value any double value to be part of statistics
     */
    void add(long timeStamp, double value);

    /**
     * Get the latest 60 seconds of statistics
     * @return Result
     */
    Result getLatest();
}
