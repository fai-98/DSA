import java.io.*;
import java.util.*;

public class BFS_Types {
    static class Edge {
        int src;
        int nbr;

        Edge(int src, int nbr) {
            this.src = src;
            this.nbr = nbr;
        }
    }

    public static class Pair {
        int v;
        String psf;

        Pair() {
        }

        Pair(int v, String psf) {
            this.v = v;
            this.psf = psf;
        }
    }

    // 08/July/2021******************************************************************************************************

    // Using Pair Class - vertex and pathSoFar
    public static void BFT(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(src, src + ""));

        while (q.size() > 0) {
            // rem
            Pair rem = q.remove();

            // mark*
            if (vis[rem.v] == true) {
                continue;
            } else
                vis[rem.v] = true;

            // work
            System.out.println(rem.v + "@" + rem.psf);

            // add*
            for (Edge edge : graph[rem.v]) {
                int nbr = edge.nbr;
                if (vis[nbr] == false) {
                    q.add(new Pair(nbr, rem.psf + nbr));
                }
            }

        }
    }

    // when cycle detection is not needed S : O(V)
    // all the vtces are add and rem only once from queue
    public static void BFS_Without_Cycle(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        Queue<Integer> q = new ArrayDeque<>();
        int level = 0;

        q.offer(src);
        vis[src] = true;

        while (q.size() > 0) {

            int size = q.size();
            System.out.print("Min No Edges: " + level + " -> ");

            while (size-- > 0) {
                // rem
                int rem = q.poll();
                System.out.print(rem + " , ");

                // mark* & add nbrs
                for (Edge e : graph[rem]) {
                    int nbr = e.nbr;
                    if (vis[nbr] == false) {
                        vis[nbr] = true; // mark* , only unvis nbrs are marked;
                        q.offer(nbr);
                    }
                }
            }
            System.out.println();
            level++;
        }
    }

    // When Cycle detection is needed - > S : O(E)
    public static void BFS_Cycle(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        Queue<Integer> q = new ArrayDeque<>();
        int level = 0;
        boolean isCycle = false;

        q.add(src);

        while (q.size() > 0) {

            int size = q.size();

            while (size-- > 0) {
                // rem
                int rem = q.remove();

                // mark* (mark unmarked , if already vis : continue 'coz shorter path already
                // explored)
                if (vis[rem]) {
                    isCycle = true;
                    continue;
                } else
                    vis[rem] = true;

                // work
                System.out.print(rem + " , ");

                // add* (unvis nbrs)
                for (Edge e : graph[rem]) {
                    if (vis[e.nbr] == false) {
                        q.add(e.nbr);
                    }
                }
            }
            System.out.println();
            level++;
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
            graph[v1].add(new Edge(v1, v2));
            graph[v2].add(new Edge(v2, v1));
        }

        int src = Integer.parseInt(br.readLine());

        // write your code here
        boolean[] vis = new boolean[graph.length];
        // BFT(graph,src,vis);
        // BFS_Cycle(graph,src,vis);
        BFS_Without_Cycle(graph, src, vis);
    }
}
