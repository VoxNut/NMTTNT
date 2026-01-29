package Lab_1.Task_1;

public class Environment {

    public enum LocationState { CLEAN, DIRTY }

    public static final String LOCATION_A = "A";
    public static final String LOCATION_B = "B";
    public static final String LOCATION_C = "C";
    public static final String LOCATION_D = "D";

    private final EnvironmentState envState = new EnvironmentState();
    private Agent agent;

    public Environment(LocationState a, LocationState b) {
        envState.setLocationState(LOCATION_A, a);
        envState.setLocationState(LOCATION_B, b);
    }

    public void addAgent(Agent agent, String location) {
        this.agent = agent;
        agent.setLocation(location);
    }

    public EnvironmentState executeAction(Action action) {
        String loc = agent.getLocation();
        switch (action.getName()) {
            case "SUCK":
                envState.setLocationState(loc, LocationState.CLEAN);
                break;
            case "LEFT":
                agent.setLocation(LOCATION_A);
                break;
            case "RIGHT":
                agent.setLocation(LOCATION_B);
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

    public void run() {
        while (!envState.isClean()) {
            Percept p = getPerceptSeenBy();
            Action a = agent.execute(p);
            executeAction(a);
            System.out.println("Environment state: " + envState);
            System.out.println("Agent Loc.: " + agent.getLocation() + " Action: " + a);
        }
    }
}
