package Lab_1.Task_2;



public class TestFourSquareAgent {
    public static void main(String[] args) {
        Environment env = new Environment(
                Environment.LocationState.DIRTY,
                Environment.LocationState.CLEAN,
                Environment.LocationState.DIRTY,
                Environment.LocationState.CLEAN
        );

        Agent agent = new Agent(new AgentProgram());
        env.addAgent(agent, Environment.LOCATION_A);

        env.run(10);  // simulate 10 steps
    }
}

