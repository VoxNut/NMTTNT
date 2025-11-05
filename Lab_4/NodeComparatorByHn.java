package Lab_4;

import java.util.Comparator;

public class NodeComparatorByHn implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        int res = Double.compare(o1.getH(), o2.getH());
        return res == 0 ? o1.getLabel().compareTo(o2.getLabel()) : res;
    }
}
