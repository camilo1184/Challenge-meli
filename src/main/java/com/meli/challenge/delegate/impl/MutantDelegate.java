package com.meli.challenge.delegate.impl;

import com.meli.challenge.delegate.IMutantDelegate;
import com.meli.challenge.dto.DnaDto;
import com.meli.challenge.service.IMutantService;
import com.meli.challenge.service.IStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * MutantDelegate implements the contract for operations on mutants and humans
 *
 * @author      Camilo Munoz
 * @version     1.0
 * @since       1.0
 */
@Service
public class MutantDelegate implements IMutantDelegate {

  IMutantService mutantService;
  IStatsService statsService;

  @Autowired
  public MutantDelegate(IMutantService mutantService, IStatsService statsService) {
    this.mutantService = mutantService;
    this.statsService = statsService;
  }

  @Override
  public ResponseEntity isMutant(DnaDto dnaInput) {

    String[] arrayFromList = (String[]) dnaInput.getDna().stream().toArray(String[]::new);
    boolean result = mutantService.isMutant(arrayFromList);
    saveStats(result);
    if (result) {
      mutantService.saveValidDna(String.join(",", dnaInput.getDna()));
      return ResponseEntity.status(HttpStatus.OK).build();
    }
    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
  }

  private void saveStats(boolean isMutant) {
    statsService.saveStats(isMutant);
  }

}
