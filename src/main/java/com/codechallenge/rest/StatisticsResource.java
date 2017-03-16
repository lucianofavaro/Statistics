package com.codechallenge.rest;

import com.codechallenge.model.Request;
import com.codechallenge.model.Result;
import com.codechallenge.service.Accumulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@EnableAutoConfiguration
@RequestMapping("/statistics")
public class StatisticsResource {

    private Accumulator accumulator;

    @Autowired
    public StatisticsResource(Accumulator accumulator) {
        this.accumulator = accumulator;
    }

    @RequestMapping(method= RequestMethod.GET)
    public @ResponseBody Result getStatistics() {
        return accumulator.latest();
    }

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity process(@RequestBody @Valid Request request) {
        if (request == null) {
            return ResponseEntity.badRequest().build();
        } else {
            accumulator.process(request);
            return ResponseEntity.ok().build();
        }
    }

}
