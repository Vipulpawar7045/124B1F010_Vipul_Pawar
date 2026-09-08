import java.io.*;
import java.util.*;

public class FoodDeliveryDijkstra {

    static class Edge {
        int destination;
        int weight;

        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class Node implements Comparable<Node> {
        int vertex;
        int distance;

        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    static void dijkstra(ArrayList<Edge>[] graph, int source, int destination) {

        int n = graph.length;
        int[] distance = new int[n];
        int[] parent = new int[n];

        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        distance[source] = 0;
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {

            Node current = pq.poll();
            int u = current.vertex;

            if (current.distance > distance[u]) {
                continue;
            }

            for (Edge edge : graph[u]) {

                int v = edge.destination;
                int newDistance = distance[u] + edge.weight;

                if (newDistance < distance[v]) {
                    distance[v] = newDistance;
                    parent[v] = u;
                    pq.add(new Node(v, newDistance));
                }
            }
        }

        if (distance[destination] == Integer.MAX_VALUE) {
            System.out.println("Customer location is not reachable.");
            return;
        }

        ArrayList<Integer> path = new ArrayList<>();

        int current = destination;

        while (current != -1) {
            path.add(current);
            current = parent[current];
        }

        Collections.reverse(path);

        System.out.println();
        System.out.println("Shortest Travel Time: " + distance[destination] + " minutes");

        System.out.print("Shortest Path: ");

        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));

            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {

        String fileName = "food_delivery_graph.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            String firstLine = br.readLine();
            String[] first = firstLine.trim().split("\\s+");

            int vertices = Integer.parseInt(first[0]);
            int edges = Integer.parseInt(first[1]);

            ArrayList<Edge>[] graph = new ArrayList[vertices];

            for (int i = 0; i < vertices; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < edges; i++) {

                String line = br.readLine();
                String[] data = line.trim().split("\\s+");

                int u = Integer.parseInt(data[0]);
                int v = Integer.parseInt(data[1]);
                int weight = Integer.parseInt(data[2]);

                graph[u].add(new Edge(v, weight));
                graph[v].add(new Edge(u, weight));
            }

            String locationLine = br.readLine();
            String[] locations = locationLine.trim().split("\\s+");

            int source = Integer.parseInt(locations[0]);
            int destination = Integer.parseInt(locations[1]);

            br.close();

            System.out.println();
            System.out.println("Food Delivery Route Optimization");
            System.out.println();

            System.out.println("Number of Locations: " + vertices);
            System.out.println("Number of Roads: " + edges);
            System.out.println("Restaurant Location: " + source);
            System.out.println("Customer Location: " + destination);

            dijkstra(graph, source, destination);

        } catch (FileNotFoundException e) {
            System.out.println("food_delivery_graph.txt file not found.");
        } catch (IOException e) {
            System.out.println("Error while reading the dataset.");
        }
    }
}