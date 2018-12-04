package day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task1 {
    public static void main(String[] args) throws IOException{

        List<String> input = Files.readAllLines(Paths.get("./src/day04/input.txt"));
        Collections.sort(input);

        Integer lastId = -1;
        int lastFellAsleep = -1;
        Map<Integer, Integer> guardToTotalSlept = new HashMap<>();
        Map<Integer, int[]> guardToPrefixArray = new HashMap<>();
        for ( String line : input ) {
            String[] lineSplit = line.split(" ", 5);
            int minute = Integer.parseInt(lineSplit[1].substring(3, 5));
            switch ( lineSplit[2].charAt(0) ) {
                case 'G':
                    lastId = Integer.parseInt(lineSplit[3].substring(1));
                    break;
                case 'f':
                    lastFellAsleep = minute;
                    break;
                case 'w':
                    guardToTotalSlept.merge(lastId, minute - lastFellAsleep, Integer::sum);
                    int[] prefixArray = guardToPrefixArray.get(lastId);
                    if ( prefixArray == null ) {
                        prefixArray = new int[60];
                        guardToPrefixArray.put(lastId, prefixArray);
                    }
                    prefixArray[lastFellAsleep]++;
                    prefixArray[minute]--;
                    break;
            }
        }

        Integer maxSleptId = guardToTotalSlept.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).get().getKey();
        int maxSleptMinuteIndex = -1;
        int maxSleptMinute = 0;
        int[] maxSleptPrefixArray = guardToPrefixArray.get(maxSleptId);
        for ( int i = 0, j = 0; i < maxSleptPrefixArray.length; i++ ) {
            j += maxSleptPrefixArray[i];
            if ( j > maxSleptMinute ) {
                maxSleptMinute = j;
                maxSleptMinuteIndex = i;
            }
        }
        System.out.println(maxSleptMinuteIndex * maxSleptId);


    }
}
