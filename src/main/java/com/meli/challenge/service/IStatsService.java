package com.meli.challenge.service;

import com.meli.challenge.entity.Stats;

/**
 * IStatsService defines the contract for services on stats
 *
 * @author      Camilo Munoz
 * @version     1.0
 * @since       1.0
 */
public interface IStatsService {

  void saveStats(boolean isMutant);

  Stats getStats();

}
