package day07;

import util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) throws FileNotFoundException{
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

        int workersAvailable = 5;
        PriorityQueue<Pair> events = new PriorityQueue<>(Comparator.comparingInt(p -> p.x));
        int lastTime = 0;
        do {
            Pair timeAndState = events.poll();
            if ( timeAndState != null ) {
                lastTime = timeAndState.x;
                Character state = (char) timeAndState.z;
                workersAvailable++;

                if ( stateToTransitions.containsKey(state) ) {
                    for ( Character transitionState : stateToTransitions.get(state) ) {
                        HashSet<Character> transitionStateRequirements = stateToRequirements.get(transitionState);
                        transitionStateRequirements.remove(state);
                        if ( transitionStateRequirements.isEmpty() ) {
                            states.add(transitionState);
                        }
                    }
                }
            }

            while ( workersAvailable > 0 && !states.isEmpty() ) {
                events.add(createEvent(states.poll(), timeAndState == null ? 0 : timeAndState.x));
                workersAvailable--;
            }
        } while ( !events.isEmpty() );
        System.out.println(lastTime);
    }

    private static Pair createEvent(Character state, int startTime){
        return new Pair(startTime + state - 4, state);
    }

}
