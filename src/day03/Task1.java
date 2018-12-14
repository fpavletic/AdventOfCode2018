package day03;

import util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {
    private static final Pattern LINE_PATTERN = Pattern.compile("#[0-9]+ @ ([0-9]+),([0-9]+): ([0-9]+)x([0-9]+)");

    public static void main(String[] args) throws FileNotFoundException{
        Map<Pair, Integer> coordinatesToOccupied = new HashMap<>();

        Scanner input = new Scanner(new File("./src/day03/input.txt"));
        while ( input.hasNextLine() ) {
            String line = input.nextLine();
            Matcher m = LINE_PATTERN.matcher(line);
            m.find();
            int x = Integer.parseInt(m.group(1)), z = Integer.parseInt(m.group(2));
            int maxI = Integer.parseInt(m.group(3)), maxJ = Integer.parseInt(m.group(4));
            for ( int i = 0; i < maxI; i++ ) {
                for ( int j = 0; j < maxJ; j++ ) {
                    coordinatesToOccupied.merge(new Pair(i + x, j + z), 1, (i1, i2) -> i1 + i2);
                }
            }
        }
        System.out.println(coordinatesToOccupied.values().stream().filter((i) -> i > 1).count());
    }
}
