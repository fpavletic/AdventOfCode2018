package day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Task2 {

    public static void main(String[] args) throws FileNotFoundException{

        Set<Integer> visitedFrequencies = new HashSet<>();
        List<Integer> changes = new ArrayList<>();
        int frequency = 0;
        for ( Scanner input = new Scanner(new File("./src/day01/input.txt")); input.hasNext(); ) {
            int change = input.nextInt();
            if ( !visitedFrequencies.add(frequency += change) ) {
                System.out.println(frequency);
                return;
            }
            changes.add(change);
        }
        while ( true ) {
            for ( int change : changes ) {
                if ( !visitedFrequencies.add(frequency += change) ) {
                    System.out.println(frequency);
                    return;
                }
            }
        }

    }
}
