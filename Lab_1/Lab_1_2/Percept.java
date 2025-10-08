package Lab_1.Lab_1_2;

public class Percept {
    public final String location;
    public final Environment.LocationState state;

    public Percept(String location, Environment.LocationState state) {
        this.location = location;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Percept[" + location + ", " + state + "]";
    }
}

