package day03;

import util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 {

    private static final Pattern LINE_PATTERN = Pattern.compile("#([0-9]+) @ ([0-9]+),([0-9]+): ([0-9]+)x([0-9]+)");

    public static void main(String[] args) throws IOException{

        Map<Integer, Boolean> idToIsOverlapping = new HashMap<>();
        Map<Pair, Integer> coordinatesToId = new HashMap<>();

        List<String> lines = Files.readAllLines(Paths.get("./src/day03/input.txt"));
        for ( String line : lines ) {
            Matcher m = LINE_PATTERN.matcher(line);
            if ( !m.find() ) System.out.println("MatcherFail");
            Integer id = Integer.parseInt(m.group(1));
            boolean isOverlapping = false; //non overlapping by default
            int x = Integer.parseInt(m.group(2)), z = Integer.parseInt(m.group(3));
            int maxI = Integer.parseInt(m.group(4)), maxJ = Integer.parseInt(m.group(5));
            for ( int i = 0; i < maxI; i++ ) {
                for ( int j = 0; j < maxJ; j++ ) { // for every tile claimed
                    Pair coordinates = new Pair(i + x, j + z);
                    Integer mapId = coordinatesToId.merge(coordinates, id, (oldId, newId) -> oldId);
                    if ( !mapId.equals(id) ) { // if tile is already claimed by another elf
                        isOverlapping = true; // is overlapping
                        idToIsOverlapping.put(mapId, true); // and update other elf to be overlapping, too
                    }
                }
            }
            idToIsOverlapping.put(id, isOverlapping); // put this elf into the idToOverlappingMap
        }
        idToIsOverlapping.entrySet().stream().filter(e -> !e.getValue()).forEach(System.out::println);
    }
}
