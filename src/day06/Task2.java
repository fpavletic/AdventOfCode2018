package day06;

import util.Pair;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Task2 {


    public static void main(String[] args) throws IOException{

        Scanner sysIn = new Scanner(new File("./src/day06/input.txt"));
        List<Pair> sources = new LinkedList<>();
        int maxHeightOrWeightOfSource = Integer.MIN_VALUE;
        while ( sysIn.hasNextLine() ) {
            Pair newSource = new Pair(sysIn.nextLine());
            maxHeightOrWeightOfSource = Math.max(newSource.x, newSource.z) > maxHeightOrWeightOfSource ?
                    Math.max(newSource.x, newSource.z) : maxHeightOrWeightOfSource;
            sources.add(newSource);
        }

        int locationCount = 0;
        int oldLocationCount = -1;
        int direction = 0;
        int width = 0;
        int height = 0;
        Pair currentPoint = new Pair(0, 0);
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
                        if ( locationCount == oldLocationCount && Math.max(height, width) > maxHeightOrWeightOfSource ) {
                            isOnlyInfLeft = true;
                        } else {
                            oldLocationCount = locationCount;
                        }
                    }
                    break;
            }
            Pair tmp = currentPoint;
            locationCount += sources.stream().mapToInt(p -> getDistance(p, tmp)).sum() < 10000 ? 1 : 0;
            currentPoint = new Pair(currentPoint, direction);
        }
        System.out.println(locationCount);
    }

    private static int getDistance(Pair p1, Pair p2){
        return Math.abs(p1.x - p2.x) + Math.abs(p1.z - p2.z);
    }

}
