
import java.util.*;

public class Main {

    static class Edge {
        int to;
        double baseCost;
        double curCost;

        Edge(int to, double cost) {
            this.to = to;
            this.baseCost = cost;
            this.curCost = cost;
        }
    }

    static final double INF = 1e30;

    static ArrayList<ArrayList<Edge>> adj;
    static ArrayList<ArrayList<Integer>> revAdj;
    static double[] bestCost;
    static int[] nextNode;

    // Recalculate the minimum cost of a node
    static double recomputeNode(int node) {
        double best = INF;
        int bestV = -1;

        for (Edge e : adj.get(node)) {
            if (bestCost[e.to] >= INF / 2)
                continue;

            double candidate = e.curCost + bestCost[e.to];

            if (candidate < best) {
                best = candidate;
                bestV = e.to;
            }
        }

        nextNode[node] = bestV;
        return best;
    }

    // Display minimum costs of source-stage nodes
    static void displayCosts(int[] stageStart, int[] stageCount) {
        System.out.println("\nBest costs from Stage-0 nodes:");

        for (int k = 0; k < stageCount[0]; k++) {
            int node = stageStart[0] + k;

            if (bestCost[node] >= INF / 2)
                System.out.println("Node " + node + ": unreachable");
            else
                System.out.printf(
                    "Node %d: cost = %.6f%n",
                    node, bestCost[node]
                );
        }
    }

    // Display the optimal path from a source node
    static void printPath(int src) {
        if (bestCost[src] >= INF / 2) {
            System.out.println("No route from " + src);
            return;
        }

        System.out.print("Path from " + src + " : ");

        int cur = src;
        double total = 0.0;

        while (cur != -1) {
            System.out.print(cur);

            int nxt = nextNode[cur];

            if (nxt == -1)
                break;

            double edgeCost = INF;

            for (Edge e : adj.get(cur)) {
                if (e.to == nxt && e.curCost < edgeCost) {
                    edgeCost = e.curCost;
                }
            }

            if (edgeCost == INF)
                break;

            total += edgeCost;
            System.out.print(" -> ");
            cur = nxt;
        }

        System.out.printf(
            "%nTotal route cost (sum edges): %.6f%n", total
        );
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input stages
        System.out.print("Enter number of stages: ");
        int S = sc.nextInt();

        if (S <= 0) {
            System.out.println("Invalid number of stages");
            sc.close();
            return;
        }

        int[] stageCount = new int[S];
        int[] stageStart = new int[S];
        int N = 0;

        System.out.print(
            "Enter number of nodes in each stage (" + S + " values): "
        );

        for (int i = 0; i < S; i++) {
            stageCount[i] = sc.nextInt();

            if (stageCount[i] <= 0) {
                System.out.println("Each stage must have at least one node");
                sc.close();
                return;
            }

            stageStart[i] = N;
            N += stageCount[i];
        }

        // Create adjacency lists
        adj = new ArrayList<>();
        revAdj = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
            revAdj.add(new ArrayList<>());
        }

        System.out.print("Enter number of edges: ");
        int M = sc.nextInt();

        System.out.println("Enter each edge as: u v cost");

        for (int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            double cost = sc.nextDouble();

            if (u < 0 || u >= N || v < 0 || v >= N) {
                System.out.println("Invalid edge node ID");
                sc.close();
                return;
            }

            adj.get(u).add(new Edge(v, cost));
            revAdj.get(v).add(u);
        }

        // Dynamic Programming initialization
        bestCost = new double[N];
        nextNode = new int[N];

        Arrays.fill(bestCost, INF);
        Arrays.fill(nextNode, -1);

        // Destination-stage nodes have cost zero
        int lastStage = S - 1;

        for (int k = 0; k < stageCount[lastStage]; k++) {
            int node = stageStart[lastStage] + k;
            bestCost[node] = 0.0;
        }

        // Calculate minimum costs backward
        for (int st = S - 2; st >= 0; st--) {
            for (int k = 0; k < stageCount[st]; k++) {
                int u = stageStart[st] + k;
                bestCost[u] = recomputeNode(u);
            }
        }

        displayCosts(stageStart, stageCount);

        // Print path
        System.out.print(
            "\nEnter a source node ID (in stage 0) to print path, or -1 to skip: "
        );

        int src = sc.nextInt();

        if (src >= stageStart[0] &&
            src < stageStart[0] + stageCount[0]) {
            printPath(src);
        } else if (src != -1) {
            System.out.println("Invalid source node for Stage 0");
        }

        // Real-time edge cost updates
        System.out.print(
            "\nEnter number of live updates to edge costs (0 to finish): "
        );

        int Q = sc.nextInt();

        while (Q-- > 0) {
            System.out.print("Enter edge update (u v multiplier): ");

            int u = sc.nextInt();
            int v = sc.nextInt();
            double multiplier = sc.nextDouble();

            if (u < 0 || u >= N || v < 0 || v >= N) {
                System.out.println("Invalid edge node ID");
                continue;
            }

            boolean found = false;

            for (Edge e : adj.get(u)) {
                if (e.to == v) {
                    e.curCost = e.baseCost * multiplier;
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Edge not found");
                continue;
            }

            // Recalculate the updated node and its predecessors
            Queue<Integer> queue = new ArrayDeque<>();

            double newCost = recomputeNode(u);

            if (Math.abs(newCost - bestCost[u]) > 1e-9) {
                bestCost[u] = newCost;
                queue.offer(u);
            }

            while (!queue.isEmpty()) {
                int node = queue.poll();

                for (int pred : revAdj.get(node)) {
                    double updatedCost = recomputeNode(pred);

                    if (Math.abs(updatedCost - bestCost[pred]) > 1e-9) {
                        bestCost[pred] = updatedCost;
                        queue.offer(pred);
                    }
                }
            }
        }

        System.out.println("\nAfter updates:");
        displayCosts(stageStart, stageCount);

        sc.close();
    }
}
