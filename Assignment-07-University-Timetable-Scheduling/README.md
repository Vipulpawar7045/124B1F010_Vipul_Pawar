# Assignment 07: University Timetable Scheduling

## Aim
To design an efficient university examination timetable using Graph Colouring so that examinations with common students do not overlap and the number of examination slots is minimized.

## Problem Statement
A university needs to schedule examinations for students enrolled in overlapping courses. Two examinations cannot take place at the same time if any student is enrolled in both. Graph Colouring is used to assign conflict-free examination slots while attempting to minimize the number of slots.

## Objectives
- Understand Graph Theory and Graph Colouring.
- Implement Greedy Graph Colouring for examination scheduling.
- Consider classroom capacity and availability constraints.

## Theory
Graph Colouring assigns colours to graph vertices such that no two adjacent vertices have the same colour. In examination scheduling, each subject is represented by a vertex, each conflict between subjects is represented by an edge, and each colour represents an examination time slot.

## Algorithms Used
1. Greedy Graph Colouring
2. DSATUR
3. Welsh-Powell

## Algorithm
1. Create a vertex for every subject.
2. Add edges between subjects that have common students.
3. Initialize all subjects as uncoloured.
4. Assign the smallest available colour to each subject.
5. Allocate suitable classrooms according to student count and room capacity.
6. Display the examination timetable and total number of slots.

## Complexity Analysis
- Time Complexity: O(V^2 + E + VR)
- Space Complexity: O(V + E + R)

Where:
- V = Number of subjects
- E = Number of conflict edges
- R = Number of classrooms

## Technologies Used
- Programming Language: Java
- Algorithm: Greedy Graph Colouring
- IDE: Visual Studio Code

## Conclusion
Implemented Greedy Graph Colouring to generate a conflict-free examination timetable and allocate suitable classrooms according to capacity constraints.
