package com.meli.challenge.delegate.impl;

import com.meli.challenge.delegate.IStatsDelegate;
import com.meli.challenge.dto.StatsDto;
import com.meli.challenge.entity.Stats;
import com.meli.challenge.service.IStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * StatsDelegate implements the human and mutant statistics contract
 *
 * @author      Camilo Munoz
 * @version     1.0
 * @since       1.0
 */
@Service
public class StatsDelegate implements IStatsDelegate {

  IStatsService statsService;

  @Autowired
  public StatsDelegate(IStatsService statsService) {
    this.statsService = statsService;
  }

  @Override
  public ResponseEntity getStats() {
    Stats stats = statsService.getStats();
    StatsDto statsDto = StatsDto.builder().ratio("0.0").build();
    if (stats.getNotMutant() > 0) {
      statsDto = StatsDto.builder().countHumanDna(stats.getNotMutant())
          .countMutantDna(stats.getIsMutant())
          .ratio(String.format("%.1f", (double) stats.getIsMutant() / stats.getNotMutant()))
          .build();
    }
    return new ResponseEntity(statsDto, HttpStatus.OK);
  }
}
