package day07;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) throws IOException{

        Scanner sysIn = new Scanner(new File("./src/day07/input.txt"));
        Map<Character, HashSet<Character>> stateToRequirements = new HashMap<>();
        Map<Character, HashSet<Character>> stateToTransitions = new HashMap<>();
        PriorityQueue<Character> states = new PriorityQueue<>(Character::compareTo);
        HashSet<Character> statesWithRequirements = new HashSet<>();
        HashSet<Character> addedStates = new HashSet<>();

        while ( sysIn.hasNextLine() ) {
            String line = sysIn.nextLine();
            Character lhs = line.charAt(5), rhs = line.charAt(36);
            statesWithRequirements.add(rhs);
            if ( addedStates.add(lhs) ) {
                states.add(lhs);
            }
            if ( !stateToRequirements.containsKey(rhs) ) {
                stateToRequirements.put(rhs, new HashSet<>());
            }
            stateToRequirements.get(rhs).add(lhs);

            if ( !stateToTransitions.containsKey(lhs) ) {
                stateToTransitions.put(lhs, new HashSet<>());
            }
            stateToTransitions.get(lhs).add(rhs);
        }
        states.removeAll(statesWithRequirements);

        StringBuilder output = new StringBuilder();
        while ( !states.isEmpty() ) {
            Character state = states.poll();
            output.append(state);

        }
        System.out.println(output);
    }

}
