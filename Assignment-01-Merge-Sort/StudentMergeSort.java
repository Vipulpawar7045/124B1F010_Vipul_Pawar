import java.io.*;
import java.util.*;

public class StudentMergeSort {

    static class Student {
        int id;
        int age;
        int grade;

        Student(int id, int age, int grade) {
            this.id = id;
            this.age = age;
            this.grade = grade;
        }
    }

    static void merge(Student[] students, int low, int mid, int high) {
        int n1 = mid - low + 1;
        int n2 = high - mid;

        Student[] left = new Student[n1];
        Student[] right = new Student[n2];

        for (int i = 0; i < n1; i++) {
            left[i] = students[low + i];
        }

        for (int i = 0; i < n2; i++) {
            right[i] = students[mid + 1 + i];
        }

        int i = 0;
        int j = 0;
        int k = low;

        while (i < n1 && j < n2) {
            if (left[i].grade <= right[j].grade) {
                students[k] = left[i];
                i++;
            } else {
                students[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            students[k] = left[i];
            i++;
            k++;
        }

        while (j < n2) {
            students[k] = right[j];
            j++;
            k++;
        }
    }

    static void mergeSort(Student[] students, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;

            mergeSort(students, low, mid);
            mergeSort(students, mid + 1, high);

            merge(students, low, mid, high);
        }
    }

    public static void main(String[] args) {
        String fileName = "student-mat.csv";
        ArrayList<Student> studentList = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            br.readLine();

            String line;
            int id = 1;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");

                int age = Integer.parseInt(
                        data[2].replace("\"", "").trim());

                int grade = Integer.parseInt(
                        data[data.length - 1]
                        .replace("\"", "").trim());

                studentList.add(new Student(id, age, grade));
                id++;
            }

            br.close();

            Student[] students =
                    studentList.toArray(new Student[0]);

            System.out.println();
            System.out.println("Students Sorted by Final Grade");
            System.out.println();
            System.out.println("---------------------------------------------");
            System.out.printf("%-15s %-10s %-15s%n",
                    "Student No.", "Age", "Final Grade");
            System.out.println("---------------------------------------------");

            mergeSort(students, 0, students.length - 1);

            for (Student student : students) {
                System.out.printf("%-15d %-10d %-15d%n",
                        student.id,
                        student.age,
                        student.grade);
            }

            System.out.println("---------------------------------------------");

        } catch (FileNotFoundException e) {
            System.out.println("student-mat.csv file not found.");
        } catch (IOException e) {
            System.out.println("Error while reading the dataset.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid data found in dataset.");
        }
    }
}