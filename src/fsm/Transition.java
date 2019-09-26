package fsm;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Transition {

    /* We use a Map to store δ(q,σ)  which includes the startState as the key 
     * and the input character and final state group into another map*/
    private final Map<Integer, Map<Character, Integer>> function = new HashMap<>();

    /* Set the given transitions pertaining to the Finite state automate*/
    public void setTransition(Integer startState, Integer finalState, char input) {
        function.computeIfAbsent(startState, k -> new HashMap<>()).put(input, finalState);
    }

    /* Based on the output change the current state and return the new state*/
    public Integer changeState(Integer startState, char input) {
        return function.getOrDefault(startState, Collections.emptyMap()).get(input);
    }
}
