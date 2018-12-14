package day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) throws FileNotFoundException{

        Scanner sysIn = new Scanner(new File("./src/day09/input.txt"));
        int playerCount = sysIn.nextInt();
        int maxValue = sysIn.nextInt() + 1;
        printWinnerScore(playerCount, maxValue);
    }

    public static void printWinnerScore(int playerCount, int maxValue){
        LinkedList circle = new LinkedList();
        long[] playerToScore = new long[playerCount];
        for ( int value = 1; value < maxValue; value++ ) {
            if ( value % 23 == 0 ) {
                playerToScore[value % playerCount] += value + circle.remove();
            } else {
                circle.insert(value);
            }
        }
        System.out.println(Arrays.stream(playerToScore).parallel().max().getAsLong());
    }


    private static class LinkedList {
        private Node current;

        public LinkedList(){
            current = new Node(0);
            current.previous = current;
            current.next = current;
        }

        public void insert(int value){
            Node oneCw = current.next;
            oneCw.next = oneCw.next.previous = new Node(oneCw, oneCw.next, value);
            current = oneCw.next;
        }

        public long remove(){
            Node sevenLeft = current.previous.previous.previous.previous.previous.previous.previous;
            current = sevenLeft.previous.next = sevenLeft.next;
            sevenLeft.next.previous = sevenLeft.previous;
            return sevenLeft.value;
        }
    }

    private static class Node {
        private Node next, previous;
        private int value;

        public Node(Node previous, Node next, int value){
            this.next = next;
            this.previous = previous;
            this.value = value;
        }

        public Node(int value){
            this.value = value;
        }
    }
}
