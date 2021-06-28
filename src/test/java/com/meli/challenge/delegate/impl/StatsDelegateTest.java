package com.meli.challenge.delegate.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.meli.challenge.delegate.IStatsDelegate;
import com.meli.challenge.dto.StatsDto;
import com.meli.challenge.entity.Stats;
import com.meli.challenge.service.IStatsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class StatsDelegateTest {

  IStatsDelegate statsDelegate;

  @Mock
  IStatsService statsService;

  @BeforeEach
  void setUp(){
    statsDelegate = new StatsDelegate(statsService);
  }

  @Test
  void given_greaterNumberHumans_thenReturnMinusZero(){
    Stats stats = Stats.builder().isMutant(new Long(2)).notMutant(new Long(3)).build();
    when(statsService.getStats()).thenReturn(stats);
    ResponseEntity response = statsDelegate.getStats();
    assertEquals("0,7", ((StatsDto)response.getBody()).getRatio());
  }

  @Test
  void given_greaterNumberHumans_thenReturnMayorZero(){
    Stats stats = Stats.builder().isMutant(new Long(3)).notMutant(new Long(2)).build();
    when(statsService.getStats()).thenReturn(stats);
    ResponseEntity response = statsDelegate.getStats();
    assertEquals("1,5", ((StatsDto)response.getBody()).getRatio());
  }
}