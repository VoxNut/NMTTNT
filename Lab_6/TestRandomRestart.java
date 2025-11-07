package Lab_6;

/**
 * Test class for Hill Climbing Search with Random Restart
 */
public class TestRandomRestart {

    public static void main(String[] args) {
        System.out.println("Testing Hill Climbing Search with Random Restart");
        System.out.println("================================================\n");

        // Test 1: Random restart with board size 8 and max 10 restarts
        System.out.println("TEST 1: 8-Queens with 10 restarts");
        HillClimbingSearch hc1 = new HillClimbingSearch(1000);
        Queen result1 = hc1.executeWithRandomRestart(8, 10);

        System.out.println("\nFinal Result:");
        System.out.println(result1);
        System.out.println("\n" + "=".repeat(60) + "\n");

        // Test 2: Random restart with custom initial state
        System.out.println("TEST 2: 8-Queens with custom initial state and 20 restarts");
        HillClimbingSearch hc2 = new HillClimbingSearch(1000);
        Queen initialState = new Queen(8);
        System.out.println("Custom initial state:");
        System.out.println(initialState);
        System.out.println();

        Queen result2 = hc2.executeWithRandomRestart(initialState, 20);

        System.out.println("\nFinal Result:");
        System.out.println(result2);
        System.out.println("\n" + "=".repeat(60) + "\n");

        // Test 3: Larger board (10-Queens) with 15 restarts
        System.out.println("TEST 3: 10-Queens with 15 restarts");
        HillClimbingSearch hc3 = new HillClimbingSearch(1000);
        Queen result3 = hc3.executeWithRandomRestart(10, 15);

        System.out.println("\nFinal Result:");
        System.out.println(result3);
    }
}
