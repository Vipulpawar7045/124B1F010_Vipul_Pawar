import java.util.*;

public class TSPBranchAndBound {

    static final int INF = 1000000000;

    static class Node {
        int[][] matrix;
        ArrayList<Integer> path;
        int bound;

        Node(int[][] matrix, ArrayList<Integer> path, int bound) {
            this.matrix = matrix;
            this.path = path;
            this.bound = bound;
        }
    }

    static int reduceMatrix(int[][] matrix) {
        int n = matrix.length;
        int reduction = 0;

        for (int i = 0; i < n; i++) {
            int min = INF;

            for (int j = 0; j < n; j++) {
                min = Math.min(min, matrix[i][j]);
            }

            if (min == INF) {
                continue;
            }

            reduction += min;

            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != INF) {
                    matrix[i][j] -= min;
                }
            }
        }

        for (int j = 0; j < n; j++) {
            int min = INF;

            for (int i = 0; i < n; i++) {
                min = Math.min(min, matrix[i][j]);
            }

            if (min == INF) {
                continue;
            }

            reduction += min;

            for (int i = 0; i < n; i++) {
                if (matrix[i][j] != INF) {
                    matrix[i][j] -= min;
                }
            }
        }

        return reduction;
    }

    static int[][] copyMatrix(int[][] matrix) {
        int n = matrix.length;
        int[][] copy = new int[n][n];

        for (int i = 0; i < n; i++) {
            copy[i] = matrix[i].clone();
        }

        return copy;
    }

    static int calculateCost(int[][] cost, ArrayList<Integer> path) {
        int total = 0;

        for (int i = 1; i < path.size(); i++) {
            total += cost[path.get(i - 1)][path.get(i)];
        }

        total += cost[path.get(path.size() - 1)][0];

        return total;
    }

    static void solveTSP(int[][] cost) {
        int n = cost.length;

        int[][] rootMatrix = copyMatrix(cost);
        int rootBound = reduceMatrix(rootMatrix);

        ArrayList<Integer> rootPath = new ArrayList<>();
        rootPath.add(0);

        Node root = new Node(rootMatrix, rootPath, rootBound);

        PriorityQueue<Node> pq =
                new PriorityQueue<>(Comparator.comparingInt(node -> node.bound));

        pq.add(root);

        int bestCost = INF;
        ArrayList<Integer> bestPath = null;

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.bound >= bestCost) {
                continue;
            }

            int u = current.path.get(current.path.size() - 1);

            if (current.path.size() == n) {
                if (cost[u][0] == INF) {
                    continue;
                }

                int total = calculateCost(cost, current.path);

                if (total < bestCost) {
                    bestCost = total;
                    bestPath = new ArrayList<>(current.path);
                }

                continue;
            }

            for (int v = 1; v < n; v++) {
                if (current.path.contains(v)) {
                    continue;
                }

                if (current.matrix[u][v] == INF) {
                    continue;
                }

                Node child = new Node(
                        copyMatrix(current.matrix),
                        new ArrayList<>(current.path),
                        current.bound
                );

                child.path.add(v);

                child.bound += child.matrix[u][v];

                for (int j = 0; j < n; j++) {
                    child.matrix[u][j] = INF;
                }

                for (int i = 0; i < n; i++) {
                    child.matrix[i][v] = INF;
                }

                if (child.path.size() < n) {
                    child.matrix[v][0] = INF;
                }

                child.bound += reduceMatrix(child.matrix);

                if (child.bound < bestCost) {
                    pq.add(child);
                }
            }
        }

        if (bestPath == null) {
            System.out.println("No possible tour");
        } else {
            System.out.println("\nMinimum cost: " + bestCost);
            System.out.print("Route: ");

            for (int city : bestPath) {
                System.out.print(city + " -> ");
            }

            System.out.println("0");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of cities: ");
        int n = sc.nextInt();

        if (n < 2) {
            System.out.println("Number of cities must be at least 2.");
            sc.close();
            return;
        }

        int[][] cost = new int[n][n];

        System.out.println("Enter cost matrix (0 for no road):");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int value = sc.nextInt();

                if (i == j || value <= 0) {
                    cost[i][j] = INF;
                } else {
                    cost[i][j] = value;
                }
            }
        }

        solveTSP(cost);
        sc.close();
    }
}