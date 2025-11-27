package Lab_8;

public class TestAlphaBetaSearchAlgo {

    public static void main(String[] args) {
        // // Build the tree as in the alpha-beta example diagram
        // Node root = new Node("A");
        // Node B = new Node("B");
        // Node C = new Node("C");
        // Node E = new Node("E");

        // // Left subtree (B)
        // Node F = new Node("F");
        // Node G = new Node("G", -5);
        // Node N = new Node("N", 4);
        // Node O = new Node("O");
        // Node W = new Node("W", -3);
        // Node X = new Node("X", -5);
        // // Middle subtree (C)
        // Node H = new Node("H", 3);
        // Node I = new Node("I", 8);
        // Node J = new Node("J");
        // Node P = new Node("P", 9);
        // Node Q = new Node("Q", -6);
        // Node R = new Node("R", 0);
        // // Right subtree (E)
        // Node K = new Node("K");
        // Node L = new Node("L", 2);
        // Node M = new Node("M");
        // Node S = new Node("S", 3);
        // Node T = new Node("T", 5);
        // Node U = new Node("U", -7);
        // Node V = new Node("V", -9);
        // // Attach nodes
        // root.addChild(B);
        // root.addChild(C);
        // root.addChild(E);
        // // B children
        // B.addChild(F);
        // B.addChild(G);
        // // F children
        // F.addChild(N);
        // F.addChild(O);
        // // O children
        // O.addChild(W);
        // O.addChild(X);
        // // C children
        // C.addChild(H);
        // C.addChild(I);
        // C.addChild(J);
        // // J children
        // J.addChild(P);
        // J.addChild(Q);
        // J.addChild(R);
        // // E children
        // E.addChild(K);
        // E.addChild(L);
        // E.addChild(M);
        // // K children
        // K.addChild(S);
        // K.addChild(T);
        // // M children
        // M.addChild(U);
        // M.addChild(V);
        Node root = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");

        AlphaBetaSearchAlgo algo = new AlphaBetaSearchAlgo();

        algo.execute(root);
    }
}
