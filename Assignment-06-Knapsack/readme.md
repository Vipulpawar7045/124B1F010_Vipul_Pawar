# Assignment 06: Disaster Relief Resource Allocation

## Aim
To implement the 0/1 Knapsack problem using Dynamic Programming to maximize the utility of disaster relief supplies without exceeding the truck's weight capacity.

## Problem Statement
A disaster relief organization has a truck with limited carrying capacity and several essential supplies, each having a specific weight and utility value. The objective is to select supplies that maximize total utility while keeping the total weight within the truck's capacity.

## Algorithm Used
- 0/1 Knapsack
- Dynamic Programming

## Objectives
- Understand the 0/1 Knapsack problem.
- Implement Dynamic Programming to find the optimal solution.
- Analyze the time and space complexity of different approaches.

## Algorithmic Approaches
1. **Brute Force:** Evaluates all possible combinations of items.
2. **Dynamic Programming:** Uses a DP table to calculate maximum utility efficiently.
3. **Greedy Approach:** Selects items based on their utility-to-weight ratio. It is optimal for Fractional Knapsack but not always for 0/1 Knapsack.

## Working
1. Initialize a DP table with zeros.
2. Iterate through each item and possible truck capacity.
3. Compare the utility obtained by including and excluding each item.
4. Store the maximum utility in the DP table.
5. Trace back through the table to identify the selected items.

## Complexity Analysis
- **Time Complexity:** O(N × W)
- **Space Complexity:** O(N × W)

Where:
- N = Number of items
- W = Truck capacity

## Technologies Used
- Programming Language: Java
- Algorithm Technique: Dynamic Programming
- IDE: Visual Studio Code / IntelliJ IDEA / Eclipse

## Conclusion
Implemented the 0/1 Knapsack problem using Dynamic Programming to select essential disaster relief supplies with maximum utility while satisfying the truck's weight constraint.
