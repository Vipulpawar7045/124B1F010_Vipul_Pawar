# Assignment 08: Optimizing Delivery Routes for a Logistics Company

## Aim
To implement the Travelling Salesman Problem (TSP) using the Least Cost Branch and Bound technique to find the minimum-cost delivery route that visits every city exactly once and returns to the starting city.

## Problem Statement
A logistics company needs to find the minimum-cost route to deliver packages across multiple cities. The delivery vehicle must visit every city exactly once and return to its starting city. The Least Cost Branch and Bound technique is used to find the optimal route while pruning unnecessary paths.

## Objectives
- Understand the Travelling Salesman Problem and Branch and Bound technique.
- Implement cost matrix reduction and lower-bound calculations.
- Analyze the algorithm's complexity and applications in route optimization.

## Theory
The Travelling Salesman Problem (TSP) is an optimization problem in which a delivery vehicle must visit all cities exactly once and return to the starting city with minimum total travel cost.

Branch and Bound solves the problem by exploring partial routes and calculating lower bounds. Routes that cannot improve the best solution are discarded using pruning.

Least Cost Branch and Bound uses a priority queue to explore the partial route with the smallest lower bound first. Cost matrix reduction helps calculate these lower bounds and reduces unnecessary exploration.

## Algorithm
1. Read the number of cities and the cost matrix.
2. Mark diagonal entries and unavailable roads as infinity.
3. Reduce the cost matrix and calculate the initial lower bound.
4. Insert the initial node into a priority queue.
5. Select the node with the smallest lower bound.
6. Generate child nodes for unvisited cities.
7. Update and reduce each child matrix and calculate its lower bound.
8. Prune branches that cannot improve the best solution.
9. Display the minimum route cost and the optimal route.

## Pseudocode
```text
TSP_LEAST_COST_BRANCH_AND_BOUND(cost, N)

    Create root node
    root.path = [0]
    root.bound = REDUCE_MATRIX(root.matrix)
    Insert root into priority queue

    bestCost = INFINITY

    WHILE priority queue is not empty
        node = Remove node with minimum bound

        IF node.bound >= bestCost
            CONTINUE

        IF all cities have been visited
            Calculate complete tour cost

            IF cost < bestCost
                Update bestCost and bestPath

            CONTINUE

        FOR each unvisited city
            Create child node
            Update path and cost matrix
            Calculate child lower bound

            IF child.bound < bestCost
                Insert child into priority queue

    RETURN bestCost and bestPath
```

## Sample Input
```text
4
0 10 15 20
10 0 35 25
15 35 0 30
20 25 30 0
```

## Sample Output
```text
Minimum cost: 80
Route: 0 -> 2 -> 3 -> 1 -> 0
```

## Complexity Analysis
- Worst-case Time Complexity: O(N² × N!)
- Space Complexity: O(Q × N²), where Q is the number of queued nodes.

## Technologies Used
- Programming Language: Java
- Algorithm: Least Cost Branch and Bound
- Data Structure: Priority Queue
- IDE: Visual Studio Code

## Conclusion
Implemented the Travelling Salesman Problem using Least Cost Branch and Bound to find the minimum-cost delivery route. Cost matrix reduction and pruning help eliminate unnecessary routes and improve the search process.
