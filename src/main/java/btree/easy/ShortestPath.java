package btree.easy;// A Java program for Dijkstra's single source shortest path algorithm.
// The program is for adjacency matrix representation of the graph 

class ShortestPath {
    // A utility function to find the vertex with minimum distance value, 
    // from the set of vertices not yet included in shortest path tree 
    static final int V = 9;
    int minDistance(int dist[], boolean sptSet[])
    {
        int min_Distance = Integer.MAX_VALUE;
        int index = -1;
        for(int i=0; i<V; i++){
            if(!sptSet[i] && dist[i] < min_Distance){
                min_Distance = dist[i];
                index = i;
            }
        }
        return index;
    }

    // A utility function to print the constructed distance array 
    void printSolution(int dist[])
    {
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i + " \t\t " + dist[i]);
    }

    // Function that implements Dijkstra's single source shortest path 
    // algorithm for a graph represented using adjacency matrix 
    // representation 
    void dijkstra(int graph[][], int src)
    {
        int[] dist = new int[V];
        boolean[] sptSet = new boolean[V];

        // initiaze the dist and sptSet
        for(int i=0; i<V; i++){
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        for(int count = 0; count < V-1; count ++){
            dist[src] = 0;
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;
            for(int v=0; v<V; v++){
                if(!sptSet[v] && graph[u][v]!=0 && dist[u]+graph[u][v]<dist[v]){
                    dist[v] = dist[u]+graph[u][v];
                }
            }
        }
        printSolution(dist);
    }

    // Driver method 
    public static void main(String[] args)
    {
        /* Let us create the example graph discussed above */
        int graph[][] = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                                      { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                                      { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                                      { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                                      { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                                      { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                                      { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                                      { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                                      { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
        ShortestPath t = new ShortestPath();
        t.dijkstra(graph, 0);
    }
} 