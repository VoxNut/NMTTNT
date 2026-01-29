package Lab_9.Task_1_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Node {
	private List<Integer> data = new ArrayList<>();
	private List<Node> children = new ArrayList<>();

	public void add(Integer val) {
		this.data.add(val);
	}

	public void addAll(List<Integer> data) {
		this.data.addAll(data);
	}

	// Get children of the current nodes
	//Task_2
	public List<Node> getSuccessors() {
	    List<Node> successors = new ArrayList<>();

	    for (int val : data) {
	        List<List<Integer>> listDivide = devideData(val);

	        for (List<Integer> split : listDivide) {
	            List<Integer> newData = new ArrayList<>(this.data);
	            newData.remove(Integer.valueOf(val));
	            newData.addAll(split);

	            Collections.sort(newData, DESCOMPARATOR);

	            Node child = new Node();
	            child.setData(newData);

	            boolean isDuplicate = false;
	            for (Node existing : successors) {
	                List<Integer> existingData = new ArrayList<>(existing.getData());
	                Collections.sort(existingData, DESCOMPARATOR);
	                if (existingData.equals(newData)) {
	                    isDuplicate = true;
	                    break;
	                }
	            }

	            if (!isDuplicate) {
	                successors.add(child);
	            }
	        }
	    }

	    this.children = successors;
	    for(Node n : successors) {
	    	System.out.println(n.toString());
	    }
	    return successors;
	}

	// Check whether a node is terminal or not
	// Task_1
	public boolean isTerminal() {
		for (int i : data) {
			if (i <= 2) {
				return true;
			}
		}
		return false;
	}

	public static final Comparator<Integer> DESCOMPARATOR = (o1, o2) -> o2.compareTo(o1);

	@Override
	public String toString() {
		Collections.sort(this.data, DESCOMPARATOR);
		return this.data.toString();
	}

	public List<Integer> getData() {
		return data;
	}

	public void setData(List<Integer> data) {
		this.data = data;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

	public static List<List<Integer>> devideData(int data){
		List<Integer> part;
		List<List<Integer>> result = new ArrayList<>();
		for(int i = 1; i <= data/2; i++) {
			part = new ArrayList<>();
			part.add(i);
			part.add(data - i);
			result.add(part);
		}
		return result;
	}
}
