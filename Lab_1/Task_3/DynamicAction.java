package Lab_1.Task_3;

public class DynamicAction implements Action {
    private final String name;


    public static final DynamicAction SUCK = new DynamicAction("SUCK");
    public static final DynamicAction LEFT = new DynamicAction("LEFT");
    public static final DynamicAction RIGHT = new DynamicAction("RIGHT");
    public static final DynamicAction UP = new DynamicAction("UP");
    public static final DynamicAction DOWN = new DynamicAction("DOWN");

    public DynamicAction(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }


}
