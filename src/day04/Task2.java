package day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task2 {
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

        int maxSleptMinuteGlobal = 0;
        int maxSleptMinuteIndexGlobal = -1;
        Integer maxSleptMinuteIdGlobal = null;
        for ( Map.Entry<Integer, int[]> entry : guardToPrefixArray.entrySet() ) {
            int maxSleptMinuteIndex = -1;
            int maxSleptMinute = 0;
            for ( int i = 0, j = 0; i < entry.getValue().length; i++ ) {
                j += entry.getValue()[i];
                if ( j > maxSleptMinute ) {
                    maxSleptMinute = j;
                    maxSleptMinuteIndex = i;
                }
            }
            if ( maxSleptMinute > maxSleptMinuteGlobal ) {
                maxSleptMinuteGlobal = maxSleptMinute;
                maxSleptMinuteIndexGlobal = maxSleptMinuteIndex;
                maxSleptMinuteIdGlobal = entry.getKey();
            }
        }
        System.out.println(maxSleptMinuteIdGlobal * maxSleptMinuteIndexGlobal);


    }
}
