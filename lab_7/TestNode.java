package lab_7;

public class TestNode {
    // Simple assertion helper

    private static void assertTrue(boolean cond, String msg) {
        if (!cond) {
            throw new AssertionError(msg);
        }
    }

    public static void main(String[] args) {
        try {
            // Test 1: generateBoard produces valid rows/columns
            Node n1 = new Node();
            int N = Node.N;
            for (int i = 0; i < N; i++) {
                int row = n1.getRow(i);
                assertTrue(row >= 0 && row < N, "Row out of bounds: " + row);
            }

            // Test 2: generateAllCandidates returns 64 (N*N) candidates
            int candidates = n1.generateAllCandidates().size();
            assertTrue(candidates == N * N, "Expected " + (N * N) + " candidates, got " + candidates);

            // Test 3: getBestCandidate heuristic is no worse than original
            int hOrig = n1.getH();
            Node bestNeighbor = n1.getBestCandidate();
            int hBest = bestNeighbor.getH();
            assertTrue(hBest <= hOrig, "Best neighbor heuristic worse than original: " + hBest + " > " + hOrig);

            // Test 4: getH non-negative and known solution has h == 0
            assertTrue(hOrig >= 0, "Heuristic negative: " + hOrig);
            // known solution for 8-queens (rows indexed by column): {0,4,7,5,2,6,1,3}
            Queen[] sol = new Queen[N];
            int[] rows = {0, 4, 7, 5, 2, 6, 1, 3};
            for (int c = 0; c < N; c++) {
                sol[c] = new Queen(rows[c], c);
            }
            Node solved = new Node(sol);
            assertTrue(solved.getH() == 0, "Known solution should have h==0 but got " + solved.getH());

            System.out.println("All tests passed.");
            System.exit(0);
        } catch (Throwable t) {
            System.err.println("Test failed: " + t.getMessage());
            t.printStackTrace(System.err);
            System.exit(2);
        }
    }
}
