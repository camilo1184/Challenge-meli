package com.meli.challenge.controller;

import com.meli.challenge.constant.ResourceEndpoint;
import com.meli.challenge.delegate.IStatsDelegate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * StatsController encapsulates resources about mutant and human statistics
 *
 * @author      Camilo Munoz
 * @version     1.0
 * @since       1.0
 */
@RestController
@RequestMapping(value = ResourceEndpoint.STATS)
@Slf4j
public class StatsController {

  IStatsDelegate statsDelegate;

  @Autowired
  public StatsController(IStatsDelegate statsDelegate) {
    this.statsDelegate = statsDelegate;
  }

  @GetMapping
  public ResponseEntity getStats() {
    log.info("Get stats");
    return statsDelegate.getStats();
  }

}
