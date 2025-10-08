package Lab_1.Lab_1_3;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentState {
    private final Map<String, Environment.LocationState> states = new HashMap<>();

    public void setLocationState(String location, Environment.LocationState state) {
        states.put(location, state);
    }

    public Environment.LocationState getLocationState(String location) {
        return states.get(location);
    }

    public boolean isClean() {
        for (Environment.LocationState s : states.values()) {
            if (s == Environment.LocationState.DIRTY)
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return states.toString();
    }
}

