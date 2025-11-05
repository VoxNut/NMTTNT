# Algorithm Comparison for 8-Puzzle Problem

## Test Configuration

- **Initial State:**

  ```
  1 5 3
  4 0 8
  7 2 6
  ```

- **Goal State:**
  ```
  1 2 3
  4 5 6
  7 8 0
  ```

## Results Summary

| Algorithm             | Success | Time (ms) | Iterations      | Path Cost | Notes                                 |
| --------------------- | ------- | --------- | --------------- | --------- | ------------------------------------- |
| **BFS**               | ❌ NO   | 145       | 50,000 (limit)  | -         | Reached limit, high memory usage      |
| **DFS**               | ❌ NO   | 93        | 50,000 (limit)  | -         | Reached limit, may explore deep paths |
| **Hill Climbing**     | ❌ NO   | 5         | 2               | -         | Stuck at local minimum (h=5)          |
| **Greedy Best-First** | ❌ NO   | 562       | 200,000 (limit) | -         | Reached limit, explores many states   |
| **A\***               | ✅ YES  | 336       | 99,758          | 16 steps  | **Found optimal solution!**           |

## Detailed Analysis

### 1. Breadth-First Search (BFS)

- **Strategy:** Explores all nodes at depth d before exploring nodes at depth d+1
- **Completeness:** Complete (will find solution if exists)
- **Optimality:** Optimal (finds shortest path)
- **Result:** Did not find solution within 50,000 iterations
- **Memory Usage:** Very high - must store all nodes at current level
- **Problem:** For this puzzle configuration, the solution is deep in the search tree

### 2. Depth-First Search (DFS)

- **Strategy:** Explores as deep as possible before backtracking
- **Completeness:** Not complete in infinite spaces
- **Optimality:** Not optimal (may find long path)
- **Result:** Did not find solution within 50,000 iterations
- **Memory Usage:** Lower than BFS (only stores path)
- **Problem:** Can get stuck exploring very deep branches

### 3. Hill Climbing

- **Strategy:** Always moves to the best neighbor (lowest h-value)
- **Completeness:** Not complete
- **Optimality:** Not optimal
- **Result:** Stuck at local minimum after only 2 iterations
- **Performance:** Very fast (5ms) but ineffective
- **Problem:** Gets trapped in local minima, h=5 means 5 misplaced tiles but can't improve

### 4. Greedy Best-First Search

- **Strategy:** Expands node with lowest heuristic value h(n)
- **Completeness:** Not complete
- **Optimality:** Not optimal
- **Result:** Did not find solution within 200,000 iterations
- **Problem:** Only considers heuristic, ignores path cost, can cycle or explore inefficiently

### 5. A\* Search ⭐ WINNER

- **Strategy:** Expands node with lowest f(n) = g(n) + h(n)
- **Completeness:** Complete
- **Optimality:** Optimal (with admissible heuristic)
- **Result:** ✅ Found solution in 99,758 iterations
- **Path Cost:** 16 steps (optimal solution)
- **Performance:** 336ms
- **Why it works:** Balances actual cost g(n) and heuristic h(n), preventing both excessive exploration and getting stuck

## Key Insights

### Why A\* Succeeded

1. **Balanced Approach:** Considers both path cost and heuristic
2. **Admissible Heuristic:** Manhattan distance never overestimates actual cost
3. **Optimal Path Finding:** f(n) = g(n) + h(n) ensures optimal solution
4. **Prevents Cycles:** gScores map tracks best path to each state

### Why Others Failed

**BFS/DFS:**

- This puzzle requires exploring many states before finding the goal
- The solution path is 16 steps deep
- Without heuristic guidance, they explore inefficiently

**Hill Climbing:**

- Gets stuck immediately at local minimum
- No mechanism to escape poor local configurations
- Too greedy - doesn't explore alternatives

**Greedy Best-First:**

- Only uses heuristic, ignores actual path cost
- Can explore states multiple times via different paths
- May prefer states that look good but lead nowhere

## Recommendations

For 8-Puzzle problems:

1. **Best Choice:** **A\* Search** - Guarantees optimal solution with reasonable performance
2. **Alternative:** BFS if enough memory and guaranteed to find solution eventually
3. **Avoid:** Hill Climbing (gets stuck), DFS (too many branches), Greedy Best-First (inefficient)

## Implementation Notes

All algorithms use:

- **HashSet for explored states:** O(1) lookup
- **HashSet for frontier tracking:** O(1) contains check (avoiding slow PriorityQueue.contains)
- **Manhattan Distance heuristic:** Sum of distances of tiles from goal positions
- **Iteration limits:** Prevent infinite loops and memory issues

The Manhattan distance heuristic is **admissible** (never overestimates) and **consistent**, making A\* optimal.
