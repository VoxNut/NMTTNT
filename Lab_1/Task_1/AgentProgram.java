package Lab_1.Task_1;

public class AgentProgram {

    public Action execute(Percept p) {
        if (p.state == Environment.LocationState.DIRTY)
            return DynamicAction.SUCK;
        else if (p.location.equals(Environment.LOCATION_A))
            return DynamicAction.RIGHT;
        else if (p.location.equals(Environment.LOCATION_B))
            return DynamicAction.LEFT;
        else
            return NoOpAction.NO_OP;
    }
}

