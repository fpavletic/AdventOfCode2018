package day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task1 {


    public static void main(String[] args) throws FileNotFoundException{
        int doubleCount = 0, tripleCount = 0;
        Scanner input = new Scanner(new File("./src/day02/input.txt"));

        while ( input.hasNextLine() ) {
            boolean doubleIncremented = false, tripleIncremented = false;
            int[] count = new int[27];
            for ( char c : input.nextLine().toCharArray() ) {
                ++count[c - 97];
            }
            for ( int i : count ) {
                switch ( i ) {
                    case 2:
                        if ( !doubleIncremented ) {
                            doubleIncremented = true;
                            doubleCount++;
                        }
                        break;
                    case 3:
                        if ( !tripleIncremented ) {
                            tripleIncremented = true;
                            tripleCount++;
                        }
                        break;
                }
                if ( tripleIncremented && doubleIncremented ) {
                    break;
                }
            }
        }
        System.out.println(doubleCount * tripleCount);

    }

}
