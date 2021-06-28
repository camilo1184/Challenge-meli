package com.meli.challenge.util;

import org.apache.commons.lang3.StringUtils;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MatrixOperations {

  public static String[] invertMatrixOriginal(String[] entry) {
    final String[] newString = new String[entry.length];
    IntStream.range(0, entry.length).forEach(i -> newString[i] = StringUtils.reverse(entry[i]));
    return newString;
  }

  public static String getDiagonalMatrix(List<String[]> matrix) {
    final String[] diagonal = {""};
    IntStream.range(0, matrix.size())
        .forEach(x -> diagonal[0] += matrix.get(x)[x]);
    return diagonal[0];
  }

  public static String[] getSubMatrix(List<String[]> matrix, int index, int sizeX, int sizeY,
      int positionRest, int initX, int iniyY) {
    String[] diagonalMatrix = new String[matrix.size() - index];
    Arrays.fill(diagonalMatrix, "");
    for (int x = initX; x < sizeX; x++) {
      for (int y = iniyY; y < sizeY; y++) {
        diagonalMatrix[x - positionRest] += matrix.get(x)[y];
      }
    }
    return diagonalMatrix;
  }

  public static List<String[]> buildOriginalMatrix(String[] entry) {
    return Arrays.asList(entry)
        .stream()
        .map(row -> row.split(""))
        .collect(Collectors.toList());
  }

  public static String[] invertMutantMatrixHorizontalToVertical(List<String[]> mutant) {
    String[] inverseMatrix = new String[mutant.size()];
    Arrays.fill(inverseMatrix, "");
    IntStream.range(0, mutant.size())
        .forEach(x -> IntStream.range(0, mutant.size())
            .forEach(y -> inverseMatrix[x] += mutant.get(y)[x]));
    return inverseMatrix;
  }

}
