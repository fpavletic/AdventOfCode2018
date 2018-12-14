package day11;

import util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Task1 {

    public static void main(String[] args) throws IOException{

        int gsn = Integer.parseInt(Files.readAllLines(Paths.get("./src/day11/input.txt")).get(0));
        int maxSum = Integer.MIN_VALUE;
        Pair maxSumCoordinates = null;
        for ( int i = 0; i < 299; i++ ) {
            for ( int j = 0; j < 299; j++ ) {
                int sum = 0;
                for ( int m = 0; m < 3; m++ ) {
                    for ( int n = 0; n < 3; n++ ) {
                        sum += getCharge(i + m, j + n, gsn);
                    }
                }
                if ( sum > maxSum ) {
                    maxSum = sum;
                    maxSumCoordinates = new Pair(i, j);
                }

            }
        }
        System.out.println(maxSum + ", " + maxSumCoordinates);
    }

    public static int getCharge(int x, int y, int gsn){
        x += 10;
        return ((x * y) + gsn) * x % 1000 / 100 - 5;
    }
}
