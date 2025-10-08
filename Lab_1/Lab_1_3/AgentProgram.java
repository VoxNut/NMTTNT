package Lab_1.Lab_1_3;

import java.util.List;
import java.util.Random;

public class AgentProgram {
    private final Random rand = new Random();

    public Action execute(Percept p) {
        if (p.state == Environment.LocationState.DIRTY)
            return DynamicAction.SUCK;

        // otherwise pick a random move
        List<DynamicAction> moves = List.of(
                DynamicAction.UP,
                DynamicAction.DOWN,
                DynamicAction.LEFT,
                DynamicAction.RIGHT
        );
        return moves.get(rand.nextInt(moves.size()));
    }
}



