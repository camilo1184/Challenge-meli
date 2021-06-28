package com.meli.challenge.service.impl;

import com.meli.challenge.entity.DnaVerified;
import com.meli.challenge.repository.DnaVerifiedRepository;
import com.meli.challenge.service.IMutantService;
import com.meli.challenge.util.MatrixOperations;
import com.meli.challenge.util.ValidateMutant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * MutantService implements the contract for services on mutants and humans
 *
 * @author      Camilo Munoz
 * @version     1.0
 * @since       1.0
 */
@Service
public class MutantService implements IMutantService {

  @Value("${dna.mutant}")
  private String dnaMutantValid;

  DnaVerifiedRepository dnaVerifiedRepository;

  @Autowired
  public MutantService(DnaVerifiedRepository dnaVerifiedRepository) {
    this.dnaVerifiedRepository = dnaVerifiedRepository;
  }

  @Override
  public boolean isMutant(String[] dnaInput) {
    List<String> dnaValid = Arrays.asList(dnaMutantValid.split(","));
    if (!ValidateMutant.validateMutantInRow(dnaInput, dnaValid)) {
      return validateMutantInHorizontals(dnaInput, dnaValid);
    }
    return true;
  }

  @Override
  public void saveValidDna(String dnaValid) {
    if (!dnaVerifiedRepository.findDna(dnaValid).isPresent()) {
      dnaVerifiedRepository.save(DnaVerified.builder().dna(dnaValid).build());
    }
  }

  private boolean validateMutantInHorizontals(String[] dnaInput, List<String> dnaValid) {
    List<String[]> matrix = MatrixOperations.buildOriginalMatrix(dnaInput);
    String[] verticals = MatrixOperations.invertMutantMatrixHorizontalToVertical(matrix);
    if (!ValidateMutant.validateMutantInRow(verticals, dnaValid)) {
      if (!ValidateMutant.validateDiagonals(dnaInput, dnaValid)) {
        return validateMatrixInverse(dnaInput, dnaValid);
      }
    }
    return true;
  }

  private boolean validateMatrixInverse(String[] dnaInput, List<String> dnaValid) {
    String[] invertedMatrix = MatrixOperations.invertMatrixOriginal(dnaInput);
    return ValidateMutant.validateDiagonals(invertedMatrix, dnaValid);
  }

}
