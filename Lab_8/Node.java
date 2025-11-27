package Lab_8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Node {

    private String label;
    private int value;
    private List<Node> children = new ArrayList<>();

    // use for non-terminal node
    public Node(String label) {
        super();
        this.label = label;
    }

    // use for terminal node
    public Node(String label, int value) {
        super();
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public List<Node> getChildren() {
        return children;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    // add a child to this node
    public void addChild(Node that) {
        this.children.add(that);
    }

    // check whether this node is terminal or not. The terminal node is assigned a
    // value.
    public boolean isTerminal() {
        return this.children.size() == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(children, label, value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Node other = (Node) obj;
        return Objects.equals(children, other.children) && Objects.equals(label, other.label) && value == other.value;
    }

    @Override
    public String toString() {
        return this.label;
    }

    // Defined comparator which is used for sorting children by alphabetical order
    public static Comparator<Node> LabelComparator = Comparator.comparing(Node::getLabel);
}
