package com.meli.challenge.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.meli.challenge.entity.Stats;
import com.meli.challenge.repository.StatsRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StatsServiceTest {

  private StatsService StatsService;

  @Mock
  private StatsRepository statsRepository;

  @BeforeEach
  void setUp() {
    StatsService = new StatsService(statsRepository);
  }

  @Test
  void given_ifIsMutantOrNot_thenSaveStats() {
    doNothing().when(statsRepository).saveStatics(1, 0);
    StatsService.saveStats(true);
    verify(statsRepository).saveStatics(1, 0);

    doNothing().when(statsRepository).saveStatics(0, 1);
    StatsService.saveStats(false);
    verify(statsRepository).saveStatics(0, 1);
  }

  @Test
  void given_stats_then_returnObjectStats() {
    List<Stats> stats = new ArrayList<>();
    stats.add(new Stats());
    when(statsRepository.findAll()).thenReturn(stats);
    assertNotNull(StatsService.getStats());
  }
}