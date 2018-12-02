package day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner input = new Scanner(new File("./src/day02/input.txt"));
        List<char[]> ids = new ArrayList<>();
        while ( input.hasNextLine() ) {
            char[] newId = input.nextLine().toCharArray();
            for ( char[] id : ids ) {
                int index = isSimilar(newId, id);
                if ( index != -1 ) {
                    String commons = new String(newId);
                    System.out.println(commons.substring(0, index) + commons.substring(index + 1));
                    return;
                }
            }
            ids.add(newId);
        }
    }

    private static int isSimilar(char[] c1, char[] c2){
        int indexDifference = -1;
        for ( int i = 0; i < c1.length; i++ ) {
            if ( indexDifference == -1 ) {
                indexDifference = c1[i] == c2[i] ? -1 : i;
            } else if ( c1[i] != c2[i] ) {
                return -1;
            }
        }
        return indexDifference;
    }
}
