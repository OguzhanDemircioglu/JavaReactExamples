package hackerRank;

import java.util.stream.IntStream;

public class DiagonalDifference {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 30,1},
                {4, 1, 2,1},
                {7, 1, 1,1},
                {7, 8, 10,1}
        };

        System.out.println(
                Math.abs(
                IntStream.range(0, matrix.length)
                        .mapToLong(i -> matrix[i][i] - matrix[i][matrix.length-i-1]).sum())
        );
    }
}
