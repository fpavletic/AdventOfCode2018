package day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner sysIn = new Scanner(new File("./src/day09/input.txt"));
        Task1.printWinnerScore(sysIn.nextInt(), sysIn.nextInt() * 100);
    }
}
