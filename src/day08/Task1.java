package day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) throws FileNotFoundException{

        Scanner sysIn = new Scanner(new File("./src/day08/input.txt"));
        System.out.println(recursion(sysIn));
    }

    public static long recursion(Scanner sysIn){
        int childCount = sysIn.nextInt();
        int metaDataCount = sysIn.nextInt();
        long sum = 0;
        for ( int i = 0; i < childCount; i++ ) {
            sum += recursion(sysIn);
        }
        for ( int i = 0; i < metaDataCount; i++ ) {
            sum += sysIn.nextInt();
        }
        return sum;
    }

}
