package fsm;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class FSMTest {

    /* Test cases to test for invalid inputs*/
    @Test
    void testInvalidInputs() {
        /* Create transition table*/
        Transition transition = new Transition();
        transition.setTransition(0, 0, '0');
        transition.setTransition(0, 1, '1');
        transition.setTransition(1, 2, '0');
        transition.setTransition(1, 0, '1');
        transition.setTransition(2, 1, '0');
        transition.setTransition(2, 2, '1');

        /* Initialize final states*/
        Set<Integer> finalStates = new HashSet<>(Arrays.asList(0, 1, 2));

        /* Initialize our FSM*/
        FSM fsm = new FSM(transition, 0, finalStates);
        assertEquals(-1, fsm.getRemainder("-2")); // enter a negative number
        assertEquals(-1, fsm.getRemainder("")); // enter an empty string
        assertEquals(-1, fsm.getRemainder("              ")); // enter a string with multiple spaces
        assertEquals(-1, fsm.getRemainder("wrongtext")); // enter text
    }

    /* Test cases to test for valid inputs*/
    @Test
    void testValidInputs() {
        /* Create transition table*/
        Transition transition = new Transition();
        transition.setTransition(0, 0, '0');
        transition.setTransition(0, 1, '1');
        transition.setTransition(1, 2, '0');
        transition.setTransition(1, 0, '1');
        transition.setTransition(2, 1, '0');
        transition.setTransition(2, 2, '1');

        /* Initialize final states*/
        Set<Integer> finalStates = new HashSet<>(Arrays.asList(0, 1, 2));

        /* Initialize our FSM*/
        FSM fsm = new FSM(transition, 0, finalStates);

        /* All legal inputs and returns correct output*/
        assertEquals(0, fsm.getRemainder("110"));
        assertEquals(0, fsm.getRemainder("11"));
        assertEquals(2, fsm.getRemainder("101"));
        assertEquals(1, fsm.getRemainder("1010"));
        assertEquals(0, fsm.getRemainder("0"));
        assertEquals(1, fsm.getRemainder("1"));
    }

}
