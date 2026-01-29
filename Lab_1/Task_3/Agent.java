package Lab_1.Task_3;

public class Agent {
    private final AgentProgram program;
    private String location;

    public Agent(AgentProgram program) {
        this.program = program;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public Action execute(Percept p) {
        return program.execute(p);
    }
}

