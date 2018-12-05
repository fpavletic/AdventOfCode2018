package day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Task2 {
    public static void main(String[] args) throws IOException{
        char[] originalInput = Files.readAllLines(Paths.get("./src/day05/input.txt")).get(0).toCharArray();

        int minCount = Integer.MAX_VALUE;
        for ( char c = 'A'; c <= 'Z'; c++ ) {
            char[] input = Arrays.copyOf(originalInput, originalInput.length);
            int head = 0, tail = 1;
            int count = input.length;

            while ( input[head] == c || input[head] == c + 32 ) {
                input[(head = tail++) - 1] = 0;
                count--;
            }

            while ( tail != input.length ) {

                if ( input[tail] == c || input[tail] == c + 32 ) {
                    input[tail++] = 0;
                    count--;
                } else if ( input[head] + 32 == input[tail] || input[head] - 32 == input[tail] ) {
                    input[head--] = 0;
                    input[tail++] = 0;
                    count -= 2;
                    while ( head >= 0 && input[head] == 0 ) {
                        head--;
                    }
                    if ( head < 0 ) {
                        head = tail++;
                    }
                } else {
                    head = tail++;
                }
            }
            if ( count < minCount ) {
                minCount = count;
            }
        }
        System.out.println(minCount);
    }
}
