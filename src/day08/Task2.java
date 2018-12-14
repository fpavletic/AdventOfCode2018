package day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task2 {


    public static void main(String[] args) throws FileNotFoundException{

        Scanner sysIn = new Scanner(new File("./src/day08/input.txt"));
        System.out.println(recursion(sysIn));
    }

    public static long recursion(Scanner sysIn){
        int childCount = sysIn.nextInt();
        int metadataCount = sysIn.nextInt();
        long[] dp = new long[childCount];

        long sum = 0;
        for ( int i = 0; i < childCount; i++ ) {
            dp[i] = recursion(sysIn);
        }

        if ( childCount == 0 ) {
            return metadataSum(sysIn, metadataCount);
        } else {
            for ( int i = 0; i < metadataCount; i++ ) {
                int index = sysIn.nextInt() - 1;
                sum += index < childCount ? dp[index] : 0;
            }
        }
        return sum;
    }

    private static long metadataSum(Scanner sysIn, int metadataCount){
        long sum = 0;
        for ( int i = 0; i < metadataCount; i++ ) {
            sum += sysIn.nextInt();
        }
        return sum;
    }

}
