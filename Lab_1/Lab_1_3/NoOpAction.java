package Lab_1.Lab_1_3;

public class NoOpAction implements Action {
    public static final NoOpAction NO_OP = new NoOpAction();

    private NoOpAction() {}

    @Override
    public String getName() {
        return "NO_OP";
    }

    @Override
    public String toString() {
        return getName();
    }
}

