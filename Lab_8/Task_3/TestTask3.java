package Lab_8.Task_3;

import Lab_8.Node;
import Lab_8.Task_2_4.AlphaBetaSearchAlgo;

public class TestTask3 {

    public static void main(String[] args) {
        // Construct the tree
		Node a = new Node("A");
		Node b = new Node("B"); Node c = new Node("C"); Node d = new Node("D");
		Node e = new Node("E"); Node f = new Node("F");
		Node g = new Node("G"); Node h = new Node("H");
		Node i = new Node("I"); Node j = new Node("J");
		
		Node k = new Node("K"); Node l = new Node("L");
		Node m = new Node("M"); Node n = new Node("N");
		Node o = new Node("O");
		Node p = new Node("P"); Node q = new Node("Q");
		Node r = new Node("R"); Node s = new Node("S");
		Node t = new Node("T"); Node u = new Node("U");

		// Level 1
		a.addChild(b); a.addChild(c); a.addChild(d);
		
		// Level 2
		b.addChild(e); b.addChild(f);
		c.addChild(g); c.addChild(h);
		d.addChild(i); d.addChild(j);
		
		// Level 3
		e.addChild(k); e.addChild(l);
		f.addChild(m); f.addChild(n);
		g.addChild(o);
		h.addChild(p); h.addChild(q);
		i.addChild(r); i.addChild(s);
		j.addChild(t); j.addChild(u);
		
		// Level 4
		k.addChild(new Node("K1", 4)); k.addChild(new Node("K2", 9));
		l.addChild(new Node("L1", 2));
		m.addChild(new Node("M1", 1));
		n.addChild(new Node("N1", 10)); n.addChild(new Node("N2", 0));
		o.addChild(new Node("O1", 7));
		p.addChild(new Node("P1", 4));
		q.addChild(new Node("Q1", 2));
		r.addChild(new Node("R1", 1)); r.addChild(new Node("R2", 8));
		s.addChild(new Node("S1", 3));
		t.addChild(new Node("T1", 7)); t.addChild(new Node("T2", 4));
		u.addChild(new Node("U1", 3)); u.addChild(new Node("U2", 1));

		AlphaBetaRightToLeftSearchAlgo algo = new AlphaBetaRightToLeftSearchAlgo();
		algo.execute(a);    
    
    }
}
