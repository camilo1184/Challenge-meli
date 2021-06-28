package com.meli.challenge.util;

import java.util.Arrays;
import java.util.List;

public class ValidateMutant {

  public static boolean validateDiagonals(String[] entry, List<String> dna) {
    List<String[]> matrix = MatrixOperations.buildOriginalMatrix(entry);

    //Right
    for (int i = 0; i < 3; i++) {
      String[] list = MatrixOperations
          .getSubMatrix(matrix, i, matrix.size() - i, matrix.size(), 0, 0, i);
        if (validateDiagonalsSubMatrix(dna, list)) {
            return true;
        }
    }

    //Left
    for (int i = 1; i < 3; i++) {
      String[] list = MatrixOperations
          .getSubMatrix(matrix, i, matrix.size(), matrix.size() - i, i, i, 0);
        if (validateDiagonalsSubMatrix(dna, list)) {
            return true;
        }
    }

    return false;
  }

  public static boolean validateDiagonalsSubMatrix(List<String> dna, String[] list) {
    List<String[]> subMatrix = MatrixOperations.buildOriginalMatrix(list);
    String diagonal = MatrixOperations.getDiagonalMatrix(subMatrix);
    if (dna.stream().anyMatch(x -> diagonal.contains(x))) {
      return true;
    }
    return false;
  }

  public static boolean validateMutantInRow(String[] entry, List<String> dna) {
    return Arrays.asList(entry)
        .stream()
        .filter(a -> dna.stream().anyMatch(dnaMutant -> a.contains(dnaMutant))).findAny()
        .isPresent();
  }

}
