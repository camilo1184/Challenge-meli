package com.meli.challenge.service.impl;

import com.meli.challenge.entity.Stats;
import com.meli.challenge.repository.StatsRepository;
import com.meli.challenge.service.IStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * StatsService implements the contract for services the stats
 *
 * @author      Camilo Munoz
 * @version     1.0
 * @since       1.0
 */
@Service
public class StatsService implements IStatsService {

  StatsRepository statsRepository;

  @Autowired
  public StatsService(StatsRepository statsRepository) {
    this.statsRepository = statsRepository;
  }

  @Override
  @Transactional
  public void saveStats(boolean isMutant) {
    if (isMutant) {
      statsRepository.saveStatics(1, 0);
    } else {
      statsRepository.saveStatics(0, 1);
    }
  }

  @Override
  public Stats getStats() {
    return statsRepository.findAll().get(0);
  }
}
