import java.util.*;

public class University{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] subjects = {"DAA", "AIML", "SPM", "EAC", "MDM-3"};
        int n = subjects.length;

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        System.out.println("Subjects:");
        for (int i = 0; i < n; i++) {
            System.out.println(i + " - " + subjects[i]);
        }

        System.out.print("Enter number of conflicts: ");
        int m = sc.nextInt();

        System.out.println("Enter conflicting pairs (u v):");

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            if (u < 0 || u >= n || v < 0 || v >= n || u == v) {
                System.out.println("Invalid conflict!");
                sc.close();
                return;
            }

            if (!graph.get(u).contains(v)) {
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
        }

        int[] slot = new int[n];
        Arrays.fill(slot, -1);

        for (int u = 0; u < n; u++) {
            boolean[] used = new boolean[n];

            for (int v : graph.get(u)) {
                if (slot[v] != -1) {
                    used[slot[v]] = true;
                }
            }

            int color = 0;

            while (color < n && used[color]) {
                color++;
            }

            slot[u] = color;
        }

        System.out.print("Enter number of rooms: ");
        int roomCount = sc.nextInt();

        if (roomCount <= 0) {
            System.out.println("Number of rooms must be positive.");
            sc.close();
            return;
        }

        int[] capacity = new int[roomCount];

        System.out.println("Enter capacity of each room:");
        for (int i = 0; i < roomCount; i++) {
            capacity[i] = sc.nextInt();
        }

        int[] students = new int[n];

        System.out.println("Enter student count for DAA, AIML, SPM, EAC, MDM-3:");
        for (int i = 0; i < n; i++) {
            students[i] = sc.nextInt();
        }

        int totalSlots = 0;

        for (int c : slot) {
            totalSlots = Math.max(totalSlots, c + 1);
        }

        System.out.println("\n----- EXAM TIMETABLE -----");

        boolean allocationFailed = false;

        for (int t = 0; t < totalSlots; t++) {
            System.out.println("\nSlot " + (t + 1) + ":");

            boolean[] occupied = new boolean[roomCount];

            for (int i = 0; i < n; i++) {
                if (slot[i] != t) {
                    continue;
                }

                int chosen = -1;

                for (int r = 0; r < roomCount; r++) {
                    if (!occupied[r]
                            && capacity[r] >= students[i]
                            && (chosen == -1
                            || capacity[r] < capacity[chosen])) {
                        chosen = r;
                    }
                }

                System.out.print(
                    subjects[i] + " (" + students[i] + " students) - "
                );

                if (chosen == -1) {
                    System.out.println("NO SUITABLE ROOM");
                    allocationFailed = true;
                } else {
                    occupied[chosen] = true;
                    System.out.println("Room " + (chosen + 1));
                }
            }
        }

        System.out.println("\nTotal exam slots: " + totalSlots);

        if (allocationFailed) {
            System.out.println(
                "Warning: Complete room allocation was not possible."
            );
        } else {
            System.out.println("All examinations have suitable rooms.");
        }

        sc.close();
    }
}