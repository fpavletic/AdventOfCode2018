package day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) throws FileNotFoundException{

        int output = 0;
        for ( Scanner input = new Scanner(new File("./src/day01/input.txt")); input.hasNext(); output += input.nextInt() )
            ;
        System.out.println(output);
    }


}
