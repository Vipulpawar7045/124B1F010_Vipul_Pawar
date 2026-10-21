import java.util.*;

public class Knapsack {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n==============================================");
        System.out.println("     DISASTER RELIEF RESOURCE ALLOCATION");
        System.out.println("           0/1 KNAPSACK PROBLEM");
        System.out.println("==============================================");

        System.out.print("\nEnter number of items: ");
        int n = sc.nextInt();

        String[] itemNames = new String[n];
        int[] weight = new int[n];
        int[] value = new int[n];

        System.out.println("\nEnter the item details:");

        for (int i = 0; i < n; i++) {
            System.out.println("\nItem " + (i + 1));

            System.out.print("Item Name: ");
            itemNames[i] = sc.next();

            System.out.print("Weight (kg): ");
            weight[i] = sc.nextInt();

            System.out.print("Utility Score: ");
            value[i] = sc.nextInt();
        }

        System.out.print("\nEnter truck capacity (kg): ");
        int W = sc.nextInt();

        int[][] dp = new int[n + 1][W + 1];

        // Dynamic Programming
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= W; w++) {

                if (weight[i - 1] <= w) {
                    int include = value[i - 1]
                            + dp[i - 1][w - weight[i - 1]];

                    int exclude = dp[i - 1][w];

                    dp[i][w] = Math.max(include, exclude);

                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Trace back to find selected items
        boolean[] selected = new boolean[n];
        int remainingCapacity = W;
        int totalWeight = 0;

        for (int i = n; i > 0; i--) {

            if (dp[i][remainingCapacity] != dp[i - 1][remainingCapacity]) {
                selected[i - 1] = true;
                totalWeight += weight[i - 1];
                remainingCapacity -= weight[i - 1];
            }
        }

        // Display results
        System.out.println("\n\n==============================================================");
        System.out.println("                 OPTIMIZATION RESULTS");
        System.out.println("==============================================================");

        System.out.printf("%-18s %-12s %-15s %-15s%n",
                "ITEM NAME", "WEIGHT (KG)", "UTILITY", "STATUS");

        System.out.println("--------------------------------------------------------------");

        for (int i = 0; i < n; i++) {

            System.out.printf("%-18s %-12d %-15d %-15s%n",
                    itemNames[i],
                    weight[i],
                    value[i],
                    selected[i] ? "SELECTED" : "NOT SELECTED");
        }

        System.out.println("--------------------------------------------------------------");

        System.out.println("\n                 FINAL SUMMARY");
        System.out.println("--------------------------------------------------------------");
        System.out.println("Truck Capacity       : " + W + " kg");
        System.out.println("Total Weight Loaded  : " + totalWeight + " kg");
        System.out.println("Remaining Capacity   : " + (W - totalWeight) + " kg");
        System.out.println("Maximum Total Utility: " + dp[n][W]);

        System.out.println("\nSelected Relief Items:");

        boolean found = false;

        for (int i = 0; i < n; i++) {
            if (selected[i]) {
                System.out.println("  [+] " + itemNames[i]
                        + " | Weight: " + weight[i]
                        + " kg | Utility: " + value[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("  No items selected.");
        }

        System.out.println("\n==============================================================");
        System.out.println("   Optimal resource allocation completed successfully!");
        System.out.println("==============================================================");

        sc.close();
    }
}