# Assignment-05: Multistage Graph

## Aim

To implement the Multistage Graph problem using Java and Dynamic Programming to find the minimum-cost path from a source node to a destination node.

## Objectives

1. To understand the concept of a Multistage Graph and use Dynamic Programming to calculate the minimum-cost path.
2. To implement the Multistage Graph algorithm in Java and determine the optimal path with updated edge costs.

## Theory

A **Multistage Graph** is a directed and weighted graph in which the vertices are divided into multiple stages. The edges generally connect nodes from an earlier stage to nodes in a later stage. Each edge has a cost associated with it, such as distance, time, or money.

The main objective is to find the path from the source node to the destination node with the **minimum total cost**.

### Dynamic Programming

Dynamic Programming is used to solve the Multistage Graph problem by dividing it into smaller subproblems. The algorithm works **backward**, starting from the destination stage and calculating the minimum cost for every node.

The recurrence relation is:

**C(v) = min [ cost(v,u) + C(u) ]**

Where:

- `C(v)` = minimum cost from node `v` to the destination.
- `cost(v,u)` = cost of the edge from `v` to `u`.
- `C(u)` = minimum cost from node `u` to the destination.

The minimum cost of nodes in the last stage is initialized to `0` because the destination has already been reached. The algorithm then calculates the minimum cost of nodes in the previous stages.

### Graph Representation

The graph is represented using an **Adjacency List**. Each node stores its connected nodes along with the corresponding edge costs.

An additional array is used to store the next node in the minimum-cost path. This allows the complete optimal path to be reconstructed after calculating the minimum costs.

### Edge Cost Update

The program also supports updating the cost of an edge using a multiplier.

**Current Cost = Base Cost × Multiplier**

After an edge cost is updated, the program recalculates the minimum costs of the affected nodes and their predecessor nodes.

### Applications

Multistage Graphs can be used in:

- Transportation and route optimization
- Logistics and delivery planning
- Communication networks
- Project planning
- Resource allocation
- Cost optimization

### Complexity

For the initial Dynamic Programming calculation:

**Time Complexity:** `O(V + E)`

**Space Complexity:** `O(V + E)`

where `V` represents the number of vertices and `E` represents the number of edges.

## Algorithm

1. Start the program.
2. Read the number of stages.
3. Read the number of nodes in each stage.
4. Read the number of edges and their costs.
5. Store the graph using an adjacency list.
6. Initialize the minimum cost of all nodes to infinity.
7. Set the minimum cost of the last-stage nodes to zero.
8. Process the stages from the second-last stage toward the first stage.
9. For every node, calculate the cost of all possible outgoing paths.
10. Select the path having the minimum cost.
11. Store the next node of the selected path.
12. Display the minimum costs of the source-stage nodes.
13. Take a source node and display its minimum-cost path.
14. If an edge cost is updated, modify its current cost.
15. Recalculate the affected nodes and their predecessors.
16. Display the updated minimum costs.
17. Stop.

## Pseudocode

```text
START

Read number of stages S
Read number of nodes in each stage

Read number of edges M

FOR each edge
    Read source, destination and cost
    Store edge in adjacency list
END FOR

Initialize bestCost of all nodes to INFINITY
Initialize nextNode of all nodes to -1

FOR each node in the last stage
    bestCost[node] = 0
END FOR

FOR stage = S-2 down to 0
    FOR each node u in current stage
        FOR each edge from u to v
            candidate = edgeCost + bestCost[v]

            IF candidate < bestCost[u]
                bestCost[u] = candidate
                nextNode[u] = v
            END IF
        END FOR
    END FOR
END FOR

Display minimum costs

Read source node

Follow nextNode from source
Display optimal path and total cost

Read number of edge updates

FOR each update
    Update edge cost
    Recalculate affected nodes
END FOR

Display updated minimum costs

STOP
