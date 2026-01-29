package Lab_1.Task_3;

import java.util.HashMap;
import java.util.Map;

public class Environment {

    public enum LocationState { CLEAN, DIRTY }

    public static final String LOCATION_A = "A";
    public static final String LOCATION_B = "B";
    public static final String LOCATION_C = "C";
    public static final String LOCATION_D = "D";

    private final EnvironmentState envState = new EnvironmentState();
    private Agent agent;
    private int score = 0;

    // adjacency map for 4-square grid
    private final Map<String, Map<String, String>> moves = new HashMap<>();

    public Environment(LocationState a, LocationState b,
                       LocationState c, LocationState d) {
        envState.setLocationState(LOCATION_A, a);
        envState.setLocationState(LOCATION_B, b);
        envState.setLocationState(LOCATION_C, c);
        envState.setLocationState(LOCATION_D, d);

        // adjacency relationships (A–B–C–D)
        moves.put(LOCATION_A, Map.of(
                "RIGHT", LOCATION_B,
                "DOWN", LOCATION_C
        ));
        moves.put(LOCATION_B, Map.of(
                "LEFT", LOCATION_A,
                "DOWN", LOCATION_D
        ));
        moves.put(LOCATION_C, Map.of(
                "UP", LOCATION_A,
                "RIGHT", LOCATION_D
        ));
        moves.put(LOCATION_D, Map.of(
                "UP", LOCATION_B,
                "LEFT", LOCATION_C
        ));
    }

    public void addAgent(Agent agent, String location) {
        this.agent = agent;
        agent.setLocation(location);
    }

    public EnvironmentState executeAction(Action action) {
        String loc = agent.getLocation();
        String act = action.getName();

        switch (act) {
            case "SUCK":
                envState.setLocationState(loc, LocationState.CLEAN);
                score += 500;
                break;

            case "LEFT":
            case "RIGHT":
            case "UP":
            case "DOWN":
                String newLoc = null;
                if (moves.containsKey(loc) && moves.get(loc).containsKey(act))
                    newLoc = moves.get(loc).get(act);

                if (newLoc != null) {
                    agent.setLocation(newLoc);
                    score -= 10;
                } else {
                    score -= 100; // invalid move
                }
                break;

            default:
                break;
        }
        return envState;
    }

    public Percept getPerceptSeenBy() {
        String loc = agent.getLocation();
        LocationState state = envState.getLocationState(loc);
        return new Percept(loc, state);
    }

    public void run(int steps) {
        for (int i = 0; i < steps; i++) {
            Percept p = getPerceptSeenBy();
            Action a = agent.execute(p);
            executeAction(a);
        }
    }
}


