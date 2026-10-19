
import java.util.*;

public class Knapsack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        String[] itemNames = new String[n];
        int[] weight = new int[n];
        int[] value = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter name, weight and utility of item " + (i + 1) + ":");
            itemNames[i] = sc.next();
            weight[i] = sc.nextInt();
            value[i] = sc.nextInt();
        }

        System.out.print("Enter truck capacity: ");
        int W = sc.nextInt();

        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (weight[i - 1] <= w) {
                    dp[i][w] = Math.max(
                        value[i - 1] + dp[i - 1][w - weight[i - 1]],
                        dp[i - 1][w]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println("\nMaximum Total Utility: " + dp[n][W]);

        ArrayList<String> selectedItems = new ArrayList<>();
        int w = W;
        int totalWeight = 0;

        for (int i = n; i > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                selectedItems.add(itemNames[i - 1]);
                totalWeight += weight[i - 1];
                w -= weight[i - 1];
            }
        }

        Collections.reverse(selectedItems);

        System.out.println("Selected Items:");
        for (String item : selectedItems) {
            System.out.println(item);
        }

        System.out.println("Total Weight: " + totalWeight + " kg");

        sc.close();
    }
}
