import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class Dijkstra {
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

    public static class Dpair implements Comparable<Dpair> {
        int vtx;
        String psf;
        int wsf;

        Dpair() {

        }

        Dpair(int vtx, String psf, int wsf) {
            this.vtx = vtx;
            this.psf = psf;
            this.wsf = wsf;
        }

        public int compareTo(Dpair o) {
            return this.wsf - o.wsf;
        }
    }

    public class Pair {
        int vtx;
        int wsf;

        Pair() {
        }

        Pair(int vtx, int wsf) {
            this.vtx = vtx;
            this.wsf = wsf;
        }
    }

    public static void dijkstra(ArrayList<Edge>[] graph, int src) {
        boolean[] vis = new boolean[graph.length];

        PriorityQueue<Dpair> pq = new PriorityQueue<>(); // smaller one has higher priority
        pq.add(new Dpair(src, src + "", 0));

        while (pq.size() > 0) {
            // remove
            Dpair rem = pq.remove();

            // mark*
            if (vis[rem.vtx] == true) {
                continue;
            }

            vis[rem.vtx] = true;

            // work
            System.out.println(rem.vtx + " via " + rem.psf + " @ " + rem.wsf);

            // add nbrs
            for (Edge edge : graph[rem.vtx]) {
                if (vis[edge.nbr] == false) {
                    pq.add(new Dpair(edge.nbr, rem.psf + edge.nbr, rem.wsf + edge.wt));
                }
            }
        }

        //Dijkstra  better version
        public static void dijikstra_01(ArrayList<Edge>[] graph, int src) {
            int N = graph.length;
            ArrayList<Edge>[] ngraph = new ArrayList[N];
            for (int i = 0; i < N; i++)
                ngraph[i] = new ArrayList<>();
    
            boolean[] vis = new boolean[N];
            PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> {
                return a.wsf - b.wsf; // for prims : return a.w - b.w;
            });
    
            int[] dis = new int[N];
            int[] par = new int[N];
    
            pq.add(new pair(src, -1, 0, 0));
            while (pq.size() != 0) {
                pair p = pq.remove();
    
                if (vis[p.vtx])
                    continue;
    
                if (p.par != -1)
                    addEdge(ngraph, p.vtx, p.par, p.w);
    
                vis[p.vtx] = true;
    
                dis[p.vtx] = p.wsf;
                par[p.vtx] = p.par;
    
                for (Edge e : graph[p.vtx]) {
                    if (!vis[e.v])
                        pq.add(new pair(e.v, p.vtx, e.w, p.wsf + e.w));
                }
            }
        }
    
        public static void dijikstra_02(ArrayList<Edge>[] graph, int src) {
            int N = graph.length;
            PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> {
                return a.wsf - b.wsf;
            });
    
            int[] dis = new int[N];
            int[] par = new int[N];
            Arrays.fill(dis, (int) 1e9);
            Arrays.fill(par, -1);
    
            pq.add(new pair(src, 0));
            dis[src] = 0;
            while (pq.size() != 0) {
                pair p = pq.remove();
    
                if (p.wsf > dis[p.vtx])
                    continue;
    
                for (Edge e : graph[p.vtx]) {
                    if (p.wsf + e.w < dis[e.v]) {
                        dis[e.v] = p.wsf + e.w;
                        par[e.v] = p.vtx;
                        pq.add(new pair(e.v, p.wsf + e.w));
                    }
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

        int src = Integer.parseInt(br.readLine());
        // write your code here
        dijkstra(graph, src);

    }
}