package com.meli.challenge.delegate;

import com.meli.challenge.dto.DnaDto;
import org.springframework.http.ResponseEntity;

/**
 * IMutantDelegate interface that exposes the contract for operations on mutants and humans
 *
 * @author      Camilo Munoz
 * @version     1.0
 * @since       1.0
 */
public interface IMutantDelegate {

  ResponseEntity isMutant(DnaDto dna);

}
