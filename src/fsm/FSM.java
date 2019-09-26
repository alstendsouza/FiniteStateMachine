package fsm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class FSM {

    /* Declare the FSM instance variable*/
    private final Transition Transition; // the transition table
    private final int startState; // the start state = 0
    private final Set<Integer> acceptingStates; // the list of final or accepting states
    private static Scanner scanner; // scanner to read user input

    /*Constructor initializes the initial transition table and set the startState to 0 
	 * and acceptingStates as 0,1,2*/
    public FSM(Transition Transition, int startState, Set<Integer> acceptingStates) {
        this.Transition = Objects.requireNonNull(Transition,
                "Transition function is null.");
        this.startState = startState;
        this.acceptingStates = Objects.requireNonNull(acceptingStates,
                "Accepting state set is null.");
    }

    /*This function returns the results based on the current input string*/
    public Integer getRemainder(String text) {
        Integer currentState = startState; // initially 0
        Integer failed = -1; // return -1 if invalid input

        if (text.length() <= 0) {
            return failed;
        } // if empty string return -1

        /* For each character in the input string we lookup the transition table 
    	 * and change the currentState */
        for (char c : text.toCharArray()) {
            currentState = Transition.changeState(currentState, c);
            if (currentState == null) {
                return failed;
            }
        }

        /* If the accepting/final state contains our current state return the currentState as result 
    	 * else return -1 to indicate failure */
        if (acceptingStates.contains(currentState)) {
            return currentState;
        } else {
            return failed;
        }
    }

    public static void main(String[] args) {

        Transition transition = new Transition(); // create new object of class Transition

        /* Define the transition table values */
        transition.setTransition(0, 0, '0');
        transition.setTransition(0, 1, '1');
        transition.setTransition(1, 2, '0');
        transition.setTransition(1, 0, '1');
        transition.setTransition(2, 1, '0');
        transition.setTransition(2, 2, '1');

        /* Define the accepting/final state values */
        Set<Integer> finalStates = new HashSet<>(Arrays.asList(0, 1, 2));

        FSM fsm = new FSM(transition, 0, finalStates);

        System.out.println("Enter your input string . Type 'end' to stop program execution ");
        scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line = line.trim(); // remove excess spaces from both ends
            if (line.equals("end")) {
                break; // ends the program
            }
            int output = fsm.getRemainder(line); //read line
            /* If result not -1 we return the output that contains our result of the current state */
            if (fsm.getRemainder(line) != -1) {
                System.out.println("The output is : " + output);
            } else {
                System.err.println("Invalid input. Please try again"); //input again
            }
        }
    }
}