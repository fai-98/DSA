import java.util.*;
import java.util.ArrayList;

public class GraphAlgorithms {
    public static class Edge {
        int v = 0;
        int w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        Edge e1 = new Edge(v, w);
        Edge e2 = new Edge(u, w);

        graph[u].add(e1);
        graph[v].add(e2);
    }

    // T : O(2E) total 2E edges in graph arr
    public static void display(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            int src = i;
            System.out.print(src);
            for (Edge e : graph[i]) {
                int nbr = e.v;
                int weight = e.w;

                System.out.println(" -> ( " + nbr + ", " + weight + " )");
            }
            System.out.println();
        }
    }

    static int[] par;
    static int[] size;

    public static int findPar(int u) {
        if (par[u] == u)
            return u;
        return par[u] = findPar(par[u]);
    }

    public static void union(int p1, int p2) {
        if (size[p1] > size[p2]) {
            par[p2] = p1;
            size[p1] += size[p2];
        } else {
            par[p1] = p2;
            size[p2] += size[p1];
        }
    }

    // {{u1,v1,w1},{u2,v2,w2}...}
    public static void unionFind_(int[][] edges, ArrayList<Edge>[] graph) {
        int N = graph.length;
        par = new int[N];
        size = new int[N];

        for (int i = 0; i < N; i++) {
            par[i] = i;
            size[i] = 1;
        }

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            int p1 = findPar(u);
            int p2 = findPar(v);

            if (p1 != p2) {
                union(p1, p2);
                addEdge(graph, u, v, w);
            }
        }
    }

    // Kruskal's Algo
    // ******************************************************************************************
    // edges (u,v,wt) , sort on basis of weight
    public static void kruskalAlgo(int[][] edges, int N) {
        Arrays.sort(edges, (a, b) -> {
            return a[2] - b[2];
        });

        ArrayList<Edge>[] graph = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        unionFind_(edges, graph);
    }

    // 1168. Optimize Water Distribution in a Village

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        ArrayList<int[]> allPipes = new ArrayList<>();

        // real pipes
        for (int[] pipe : pipes) {
            allPipes.add(pipe);
        }

        // hypothetical pipes , cost of well represented as .... , 0 = common well
        for (int i = 1; i <= n; i++) {
            int[] pipe = { 0, i, wells[i - 1] };
            allPipes.add(pipe);
        }

        // sort edges by weights
        Collections.sort(allPipes, (a, b) -> {
            return a[2] - b[2];
        });

        par = new int[n + 1];
        int res = 0;

        for (int i = 0; i < n + 1; i++)
            par[i] = i;

        for (int[] edge : allPipes) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            int p1 = findPar(u);
            int p2 = findPar(v);

            if (p1 != p2) {
                par[p2] = p1;
                res += wt;
            }
        }
        return res;
    }

    // Mr. President
    // https://www.hackerearth.com/practice/algorithms/graphs/minimum-spanning-tree/practice-problems/algorithm/mr-president/

    // use kruskals to make MST
    public static int mrPresident(int[][] edges, int N, long K) {
        par = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            par[i] = i;
        }

        Arrays.sort(edges, (a, b) -> {
            return a[2] - b[2];
        });

        ArrayList<Integer> roads = new ArrayList<>();

        int components = N;
        long mcost = 0;
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            int p1 = findPar(u), p2 = findPar(v);
            if (p1 != p2) {
                par[p1] = p2;
                components--;
                mcost += w;
                roads.add(w);
            }
        }

        if (components > 1)
            return -1;

        int superroad = 0;
        for (int i = roads.size() - 1; i >= 0; i--) {
            if (mcost <= K)
                break;
            mcost = mcost - roads.get(i) + 1;
            superroad++;
        }

        return mcost <= K ? superroad : -1;
    }

}
