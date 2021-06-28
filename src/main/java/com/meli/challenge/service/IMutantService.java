package com.meli.challenge.service;

/**
 * IMutantService defines the contract for services on mutants and humans
 *
 * @author      Camilo Munoz
 * @version     1.0
 * @since       1.0
 */
public interface IMutantService {

  boolean isMutant(String[] dna);

  void saveValidDna(String dnaValid);

}
