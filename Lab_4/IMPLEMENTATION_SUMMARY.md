# Lab 4 - A\* Search Algorithm Implementation

## Completed Methods

### Task 2: `execute(Node root, String goal)` ✅

**Purpose:** Performs A\* search from a root node to find the optimal path to a goal node.

**Implementation Details:**

- Uses PriorityQueue ordered by f(n) = g(n) + h(n)
- Maintains explored set to avoid revisiting nodes
- Updates path costs when better paths are found
- Returns the goal node with parent links for path reconstruction

**Example Result:**

- Path from S to G: `[S, B, C, D, F, G]`
- Total cost: 8.0

---

### Task 3: `isAdmissibleH(Node root, String goal)` ✅

**Purpose:** Checks if the heuristic function h(n) is admissible (never overestimates actual cost).

**Implementation Details:**

- Runs Dijkstra's algorithm to find actual shortest path costs
- Compares heuristic values against actual costs
- A heuristic is admissible if h(n) ≤ actual cost to goal for all nodes

**Key Concept:**
An admissible heuristic guarantees that A\* finds the optimal solution. It means the heuristic never overestimates the true cost to reach the goal.

**Example Result:**

- For the test graph with h(S) = 6.0 and actual cost = 8.0
- Result: Admissible (6.0 ≤ 8.0)

---

### Task 5: `execute(Node root, String start, String goal)` ✅

**Purpose:** Performs A\* search from any specified start node to goal node within a graph.

**Implementation Details:**

- First locates the start node in the graph using DFS traversal
- Then runs A\* from that start node to the goal
- Useful when you want to find paths between any two nodes, not just from root

**Example Results:**

- Path from B to G: `[B, C, D, F, G]`, cost: 5.0
- Path from C to G: `[C, D, F, G]`, cost: 4.0

---

## Algorithm Characteristics

### A\* Search Properties:

1. **Complete:** Yes, will find a solution if one exists
2. **Optimal:** Yes, when using an admissible heuristic
3. **Time Complexity:** O(b^d) in worst case, but much better with good heuristics
4. **Space Complexity:** O(b^d) - stores all nodes in memory

### Key Components:

- **g(n):** Actual cost from start to node n
- **h(n):** Heuristic estimated cost from node n to goal
- **f(n) = g(n) + h(n):** Total estimated cost through node n

### Why A\* is Efficient:

- Combines benefits of Dijkstra (optimal) and Greedy Best-First (fast)
- Uses heuristic to guide search toward goal
- Guarantees optimal solution with admissible heuristic
- Avoids exploring unnecessary paths

---

## Test Graph Structure

```
        S (h=6)
       / \
    2 /   \ 3
     /     \
    A(4)   B(4)
     |    / \
   3 |  1/   \3
     | /       \
    C(4)      D(3.5)
     |\         |
   3| |1      2|
     | |        |
    E(1) ---- F(1)
      \         |
     2 \      1|
        \      /
          G(0)
```

**Optimal Path from S to G:** S → B → C → D → F → G (cost: 8.0)

---

## Implementation Notes

### Optimizations:

1. **Tie-breaking:** Uses label comparison when costs are equal
2. **Path update:** Removes and re-adds nodes when better paths found
3. **Efficient search:** Uses HashSet for O(1) explored node lookup

### Potential Improvements:

1. Use a better data structure for frontier (e.g., indexed priority queue)
2. Implement bidirectional A\* for faster searches
3. Add more sophisticated admissibility checking

---

## Usage Example

```java
// Create graph
Node s = new Node("S", 6);
Node g = new Node("G", 0);
// ... add edges ...

AStarSearchAlgo algo = new AStarSearchAlgo();

// Task 2: Search from root
Node result = algo.execute(s, "G");
System.out.println(NodeUtils.printPath(result)); // [S, B, C, D, F, G]

// Task 3: Check admissibility
boolean admissible = algo.isAdmissibleH(s, "G");

// Task 5: Search from any node
Node result2 = algo.execute(s, "B", "G");
System.out.println(NodeUtils.printPath(result2)); // [B, C, D, F, G]
```
