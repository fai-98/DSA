import java.io.*;
import java.util.*;

public class PrimsMST {
    static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static class Pair implements Comparable<Pair> {
        int vtx;
        int aqv;
        int wt;

        Pair() {

        }

        Pair(int vtx, int aqv, int wt) {
            this.vtx = vtx;
            this.aqv = aqv;
            this.wt = wt;
        }

        public int compareTo(Pair o) {
            return this.wt - o.wt;
        }
    }

    // static class Edge implements Comparable < Edge > {
    // int v;
    // int wt;

    // Edge(int nbr, int wt) {
    // this.v = nbr;
    // this.wt = wt;
    // }

    // @Override
    // public int compareTo(Edge o) {
    // return this.wt - o.wt;
    // }
    // }

    public static class primsPair {
        int vtx, w;

        primsPair(int vtx, int w) {
            this.vtx = vtx;
            this.w = w;
        }
    }

    public static void prims(ArrayList<Edge>[] graph, int src) {
        int N = graph.length;
        PriorityQueue<primsPair> pq = new PriorityQueue<>((a, b) -> {
            return a.w - b.w;
        });

        int[] dis = new int[N];
        boolean[] vis = new boolean[N];
        Arrays.fill(dis, (int) 1e9);

        dis[src] = 0;
        pq.add(new primsPair(src, 0));
        while (pq.size() != 0) {
            primsPair p = pq.remove();

            if (vis[p.vtx])
                continue;

            vis[p.vtx] = true;
            for (Edge e : graph[p.vtx]) {
                if (vis[e.v] && e.w < dis[e.v]) {
                    dis[e.v] = e.w;
                    pq.add(new primsPair(e.v, e.w));
                }
            }
        }
    }

    public static void minSpanningTree(ArrayList<Edge>[] graph) {
        boolean[] vis = new boolean[graph.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        pq.add(new Pair(0, -1, 0));

        while (pq.size() > 0) {
            // remove
            Pair rem = pq.remove();

            // mark*
            if (vis[rem.vtx] == true) {
                continue;
            }
            vis[rem.vtx] = true;

            // work
            if (rem.aqv != -1)
                System.out.println("[" + rem.vtx + "-" + rem.aqv + "@" + rem.wt + "]");

            // add unvisited nbr
            for (Edge edge : graph[rem.vtx]) {
                int nbr = edge.nbr;

                if (vis[nbr] == false) {
                    pq.add(new Pair(nbr, rem.vtx, edge.wt));
                }
            }
        }
    }

    // Minimum cost to connect all cities
    // Just find MST - wt of whole graph is res
    // MST removes extra edges and gives minCost
    // https://www.geeksforgeeks.org/minimum-cost-connect-cities/

    // Easy Prims ->
    // Add starting idx/src with 0 wt
    // for res node edges will pop in increasing order
    // add first time edges wt in ans
    // add first time nbrs in pq

    public static int minCost(ArrayList<ArrayList<Edge>> graph, int N) {
        int src = 0;
        int ans = 0;
        PriorityQueue<Edge> queue = new PriorityQueue<>();

        queue.add(new Edge(src, 0));

        boolean[] visited = new boolean[vtces];
        while (queue.size() > 0) {
            Edge rem = queue.remove();

            if (visited[rem.v] != false) {
                continue;
            }

            // init 0 wt of 1st dummy Edge is Added
            visited[rem.v] = true;
            ans += rem.wt;

            for (Edge e : graph.get(rem.v)) {
                if (visited[e.v] == false) {
                    queue.add(new Edge(e.v, e.wt));
                }
            }
        }

        return ans;
    }


    // Minimum Wire Required To Connect All Pcs
    public static void MST(ArrayList<Edge>[]graph) {
        boolean[] vis = new boolean[graph.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        pq.add(new Pair(0, -1, 0)); //dummy edge

        while (pq.size() > 0) {
            //rem
            Pair rem = pq.remove();

            //mark*
            if (vis[rem.v] == true)continue;
            vis[rem.v] = true;

            //work
            if (rem.aqv != -1)
                System.out.println("[" + rem.v + "-" + rem.aqv + "@" + rem.wt + "]");

            //add nbr
            for (Edge edge : graph[rem.v]) {
                if (vis[edge.nbr] == false) {
                    pq.add(new Pair(edge.nbr, rem.v, edge.wt));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int vtces = Integer.parseInt(br.readLine());
        ArrayList<Edge>[] graph = new ArrayList[vtces];
        for (int i = 0; i < vtces; i++) {
            graph[i] = new ArrayList<>();
        }

        int edges = Integer.parseInt(br.readLine());
        for (int i = 0; i < edges; i++) {
            String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);
            int wt = Integer.parseInt(parts[2]);
            graph[v1].add(new Edge(v1, v2, wt));
            graph[v2].add(new Edge(v2, v1, wt));
        }

        // write your code here
        minSpanningTree(graph);
    }

}