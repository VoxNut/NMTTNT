package Lab_1.Lab_1_3;

public class TestGridAgent {
    public static void main(String[] args) {
        int rows = 5;
        int cols = 5;
        double dirtRate = 0.2;
        double wallRate = 0.1;

        Agent agent = new Agent(new AgentProgram());
        GridEnvironment env = new GridEnvironment(rows, cols, dirtRate, wallRate, agent);

        env.run(20);
    }
}

