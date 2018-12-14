package day10;

import util.Pair;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.IntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {

    private static final Pattern LIGHT_PATTERN = Pattern.compile("position=<\\s*([-0-9]+),\\s*([-0-9]+)>\\s+velocity=<\\s*([-0-9]+),\\s*([-0-9]+)>");

    public static void main(String[] args) throws IOException{

        Scanner sysIn = new Scanner(new File("./src/day10/input.txt"));

        List<IntFunction<Pair>> positionFunctions = new LinkedList<>();
        int numerator = 0, denominator = 0;

        while ( sysIn.hasNextLine() ) {
            Matcher lightMatcher = LIGHT_PATTERN.matcher(sysIn.nextLine());
            if ( !lightMatcher.find() ) {
                System.err.println("Invalid input format, exiting...");
                return;
            }
            final int posX = Integer.parseInt(lightMatcher.group(1));
            final int posY = Integer.parseInt(lightMatcher.group(2));
            final int velX = Integer.parseInt(lightMatcher.group(3));
            final int velY = Integer.parseInt(lightMatcher.group(4));
            positionFunctions.add(t -> new Pair(Math.abs(posX - t * velX), Math.abs(posY - t * velY)));
            numerator += velX * posX + velY * posY;
            denominator += velX * velX + velY * velY;
        }
        final int time = numerator / denominator + 1;

        List<Pair> positions = new LinkedList<>();
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        for ( IntFunction<Pair> positionFunction : positionFunctions ) {
            Pair position = positionFunction.apply(time);
            positions.add(position);
            maxX = position.x > maxX ? position.x : maxX;
            minX = position.x < minX ? position.x : minX;
            maxY = position.z > maxY ? position.z : maxY;
            minY = position.z < minY ? position.z : minY;
        }

        Pair min = new Pair(minX, minY);
        boolean[][] display = new boolean[maxX - minX + 1][maxY - minY + 1];
        positions.forEach(p -> display[p.x - min.x][p.z - min.z] = true);

        for ( int i = 0; i < display[0].length; i++ ) {
            for ( int j = 0; j < display.length; j++ ) {
                System.out.print(display[j][i] ? '#' : ' ');
            }
            System.out.println();
        }

        System.out.println(-time);


    }

}
