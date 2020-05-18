import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

class Solution {
    public static class Graph {

        ArrayList<HashSet<Integer>> nodes;
        int[] distance;
        boolean[] nodeStatus;

        public Graph(int size) {
            this.nodes = new ArrayList<HashSet<Integer>>();
            for (int i = 0; i < size; i++) {
                this.nodes.add(new HashSet<Integer>());
            }
            distance = new int[size];
            Arrays.fill(distance, -1);

            nodeStatus = new boolean[size];
            Arrays.fill(nodeStatus,false);
        }

        public void addEdge(int first, int second) {
            nodes.get(first).add(second);
        }

        public int[] shortestReach(int startId) { // 0 indexed

            distance[startId] = 0;
            nodeStatus[startId] = true;

            LinkedList<Integer> nodeLink = new LinkedList<Integer>();
            nodeLink.add(startId);

            while(nodeLink.size() > 0){
                Integer nodeID = nodeLink.remove();
                for(Integer subNode: nodes.get(nodeID)){
                    if(distance[subNode]==-1 || distance[subNode] > distance[nodeID] + 6){
                        distance[subNode] = distance[nodeID] + 6;
                        nodeLink.add(subNode);
                        nodeStatus[subNode] = true;
                    }
                }
            }
            return distance;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("queries");
        int queries = scanner.nextInt();

        for (int t = 0; t < queries; t++) {

            // Create a graph of size n where each edge weight is 6:
            System.out.println("sizes n");
            Graph graph = new Graph(scanner.nextInt());
            System.out.println("edges m");
            int m = scanner.nextInt();

            // read and set edges
            for (int i = 0; i < m; i++) {
                System.out.println("u and v");
                int u = scanner.nextInt() - 1;
                int v = scanner.nextInt() - 1;

                // add each edge to the graph
                graph.addEdge(u, v);
            }

            // Find shortest reach from node s
            int startId = scanner.nextInt() - 1;
            int[] distances = graph.shortestReach(startId);

            for (int i = 0; i < distances.length; i++) {
                if (i != startId) {
                    System.out.print(distances[i]);
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        scanner.close();
    }
}
