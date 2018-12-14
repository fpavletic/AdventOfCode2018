package day11;

import util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Task2 {
    public static void main(String[] args) throws IOException{

        int[][][] xySizeDp = new int[300][300][301];

        int gsn = Integer.parseInt(Files.readAllLines(Paths.get("./src/day11/input.txt")).get(0));
        int maxSum = Integer.MIN_VALUE;
        Pair maxSumCoordinates = null;
        int maxSumSize = 0;
        for ( int k = 1; k <= 300; k++ ) {
            for ( int i = 0; i < 300 - k + 1; i++ ) {
                for ( int j = 0; j < 300 - k + 1; j++ ) {
                    int sum = xySizeDp[i][j][k - 1];
                    for ( int m = 0; m < k; m++ ) {
                        sum += getCharge(i + k - 1, j + m, gsn);
                        sum += getCharge(i + m, j + k - 1, gsn);
                    }
                    sum -= getCharge(i + k - 1, j + k - 1, gsn);
                    xySizeDp[i][j][k] = sum;
                    if ( sum > maxSum ) {
                        maxSum = sum;
                        maxSumCoordinates = new Pair(i, j);
                        maxSumSize = k;
                    }
                }
            }
        }
        System.out.println(maxSum + ", " + maxSumCoordinates + ", " + maxSumSize);
    }

    public static int getCharge(int x, int y, int gsn){
        x += 10;
        return ((x * y) + gsn) * x % 1000 / 100 - 5;
    }
}
