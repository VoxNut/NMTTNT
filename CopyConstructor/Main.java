package CopyConstructor;

public class Main {
    static class Engine {
        private String model;

        public Engine(String model) {
            this.model = model;
        }

        public Engine(Engine another) {
            this.model = another.model; // Deep copy of Engine
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getModel() {
            return model;
        }
    }

    static class Car {
        private String make;
        private Engine engine;

        public Car(String make, Engine engine) {
            this.make = make;
            this.engine = engine;
        }

        // Deep copy constructor
        public Car(Car another) {
            this.make = another.make;
            this.engine = new Engine(another.engine); // Deep copy of Engine
        }

        public Engine getEngine() {
            return engine;
        }
    }

    public static class DeepCopyDemo {
        public static void main(String[] args) {
            Engine engine = new Engine("V8");
            Car original = new Car("Ford", engine);
            Car copy = new Car(original);

            copy.getEngine().setModel("V6");

            System.out.println("Original Engine: " + original.getEngine().getModel());
            System.out.println("Copied Engine: " + copy.getEngine().getModel());
        }
    }
}
