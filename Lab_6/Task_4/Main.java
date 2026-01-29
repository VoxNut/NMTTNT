package Lab_6.Task_4;

import Lab_6.Task_2_3.Node;

public class Main {
    public static void main(String[] args) {
        Node initial = new Node();
        initial = initial.generateBoard();


        HillClimbingSearchNQueen hc = new HillClimbingSearchNQueen();
        Node resHC = hc.executeHillClimbingWithRandomRestart(initial);
        System.out.println("H: " + resHC.getH());
        System.out.println("step: " + hc.getStepClimbed());
        System.out.println("total steps: " + hc.getStepClimbedAfterRandomRestart());
        System.out.println("random restarts: " + hc.getRandomRestarts());

        SA_SearchAlgo sa = new SA_SearchAlgo();
        Node resSA = sa.execute(initial);
        System.out.println("H: " + resSA.getH());
    }
}
