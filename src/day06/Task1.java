package day06;

import util.Pair;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Task1 {

    public static void main(String[] args) throws IOException{

        Scanner sysIn = new Scanner(new File("./src/day06/input.txt"));
        List<Pair> sources = new LinkedList<>();
        Map<Integer, Integer> sourceIdToCount = new HashMap<>();
        int maxHeightOrWeightOfSource = Integer.MIN_VALUE;
        while ( sysIn.hasNextLine() ) {
            Pair newSource = new Pair(sysIn.nextLine());
            maxHeightOrWeightOfSource = Math.max(newSource.x, newSource.z) > maxHeightOrWeightOfSource ?
                    Math.max(newSource.x, newSource.z) : maxHeightOrWeightOfSource;
            sources.add(newSource);
        }

        int direction = 0;
        int width = 0;
        int height = 0;
        Pair currentPoint = new Pair(0, 0);
        Set<Integer> expanded = new HashSet<>();
        int lastExpandedSize = -1;
        boolean isOnlyInfLeft = false;

        while ( !isOnlyInfLeft ) {
            switch ( direction ) {
                case 0:
                    if ( currentPoint.x < -width ) {
                        direction = 1;
                    }
                    break;
                case 1:
                    if ( currentPoint.z < -height ) {
                        direction = 2;
                    }
                    break;
                case 2:
                    if ( currentPoint.x > width ) {
                        direction = 3;
                        width = currentPoint.x;
                    }
                    break;
                case 3:
                    if ( currentPoint.z > height ) {
                        height = currentPoint.z;
                        direction = 0;
                        if ( expanded.size() == lastExpandedSize && Math.max(height, width) > maxHeightOrWeightOfSource ) {
                            isOnlyInfLeft = true;
                        } else {
                            lastExpandedSize = expanded.size();
                            expanded.clear();
                        }
                    }
                    break;
            }
            Pair tmp = currentPoint;
            Pair idAndDistance = sources.stream().map(p -> new Pair(p.id, getDistance(p, tmp))).min(Comparator.comparingInt(p -> p.z)).get();
            expanded.add(idAndDistance.x);
            sourceIdToCount.merge(idAndDistance.x, 1, Integer::sum);
            currentPoint = new Pair(currentPoint, direction);
        }
        sourceIdToCount.keySet().removeAll(expanded);
        System.out.println(sourceIdToCount.values().stream().max(Integer::compareTo).get());
    }

    private static int getDistance(Pair p1, Pair p2){
        return Math.abs(p1.x - p2.x) + Math.abs(p1.z - p2.z);
    }

}
