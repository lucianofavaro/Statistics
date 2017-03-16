package com.codechallenge.service;

import com.codechallenge.model.Request;
import com.codechallenge.model.Result;
import com.codechallenge.repository.StatisticsRepository;
import com.codechallenge.utils.UnixTimestampUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Accumulator {

    private StatisticsRepository statisticsRepository;

    @Autowired
    public Accumulator(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    public Result latest() {
        return statisticsRepository.getLatest();
    }

    public void process(Request request) {
        statisticsRepository.add(UnixTimestampUtils.getUnixTimeStampInSeconds(request.getTimestamp()), request.getAmount());
    }
}
