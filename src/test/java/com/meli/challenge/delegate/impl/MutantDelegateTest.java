package com.meli.challenge.delegate.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.meli.challenge.delegate.IMutantDelegate;
import com.meli.challenge.dto.DnaDto;
import com.meli.challenge.service.IMutantService;
import com.meli.challenge.service.IStatsService;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class MutantDelegateTest {

  private IMutantDelegate mutantDelegate;

  @Mock
  private IStatsService statsService;

  @Mock
  private IMutantService mutantService;

  DnaDto dnaInput;

  @BeforeEach
  void setUp() {
    mutantDelegate = new MutantDelegate(mutantService, statsService);
    dnaInput = DnaDto.builder().dna(Arrays.asList("AAAA", "CCCC", "TTTT", "GGGG")).build();
  }

  @Test
  void given_listValidDnaInput_then_returnValidMutant() {
    when(mutantService.isMutant(any())).thenReturn(true);
    assertEquals(HttpStatus.OK.value(), mutantDelegate.isMutant(dnaInput).getStatusCodeValue());
  }

  @Test
  void given_listInvalidDnaInput_then_returnValidMutant() {
    when(mutantService.isMutant(any())).thenReturn(false);
    assertEquals(HttpStatus.FORBIDDEN.value(),
        mutantDelegate.isMutant(dnaInput).getStatusCodeValue());
  }

}