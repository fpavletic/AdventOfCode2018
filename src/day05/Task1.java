package day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Task1 {
    public static void main(String[] args) throws IOException{
        char[] input = Files.readAllLines(Paths.get("./src/day05/input.txt")).get(0).toCharArray();
        int count = input.length;
        int head = 0, tail = 1;
        while ( tail != input.length ) {
            if ( input[head] + 32 == input[tail] || input[head] - 32 == input[tail] ) {
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
        System.out.println(count);
    }
}
