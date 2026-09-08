# Merge Sort - Student Performance

## Problem Statement

Design and implement a sorting algorithm using Merge Sort to arrange student final grades in ascending order. The Student Performance dataset is used for this implementation. The final grade (G3) of the students is taken as the value for sorting.

## Dataset

The dataset used is the Student Performance dataset from the UCI Machine Learning Repository.

Dataset file used:

student-mat.csv

The G3 column represents the final grade of the student. The grades are sorted in ascending order using Merge Sort.

## Algorithm

Merge Sort is a sorting algorithm based on the Divide and Conquer approach.

The algorithm works in three steps:

1. Divide the array into two halves.
2. Recursively sort both halves.
3. Merge the sorted halves.

## Time Complexity

Best Case: O(n log n)

Average Case: O(n log n)

Worst Case: O(n log n)

## Space Complexity

O(N)

## Files

StudentMergeSort.java - Java implementation of Merge Sort.

output.txt - Output of the Java program.

README.md - Information about the project.

## How to Run

Keep student-mat.csv in the same folder as StudentMergeSort.java.

Compile the program:

javac StudentMergeSort.java

Run the program:

java StudentMergeSort

## Output

The program displays the number of student records and the final grades before and after sorting.

## Dataset Source

UCI Machine Learning Repository - Student Performance Dataset

https://archive.ics.uci.edu/dataset/320/student%2Bperformance
