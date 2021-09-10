import java.util.*;
import java.util.ArrayList;

public class Bipartite {

    public static class Edge {
        int v = 0, w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public class Pair {
        int v;
        int set;

        Pair(int v, int set) {
            this.v = v;
            this.set = set;
        }
    }

    public boolean isBipartite(int[][] graph) {
        int[] vis = new int[graph.length];
        boolean res = true;

        for (int src = 0; src < graph.length; src++) {
            if (vis[src] == 0) {
                res = res && singleBPT(graph, vis, src);
            }
        }

        return res;
    }

    // using pair
    // class***********************************************************************************************
    public boolean singleBPT(int[][] graph, int[] vis, int src) {
        Queue<Pair> q = new ArrayDeque<>();
        Pair p = new Pair(src, 1);

        q.offer(p);

        while (q.size() > 0) {
            int size = q.size();
            while (size-- > 0) {
                // rem
                Pair rp = q.poll();

                // mark*
                if (vis[rp.v] != 0) {
                    if (vis[rp.v] != rp.set)
                        return false;
                    continue;
                }
                vis[rp.v] = rp.set;

                int set = rp.set == 1 ? 2 : 1;
                for (int nbr : graph[rp.v]) {
                    if (vis[nbr] == 0) {
                        Pair np = new Pair(nbr, set);
                        q.offer(np);
                    }
                }

            }
        }
        return true;
    }

    // without pair -> colors {
    // 1,-1}***********************************************************************************************
    public boolean singleBPT(int[][] graph, int src, int[] vis) {

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(src);
        vis[src] = 1;

        while (q.size() > 0) {
            int size = q.size();
            while (size-- > 0) {
                // rem
                int rem = q.poll();

                // add nbrs*
                // 3 cases
                for (int nbr : graph[rem]) {
                    if (vis[nbr] == 0) {
                        q.offer(nbr);
                        vis[nbr] = -vis[rem];
                    } else {
                        if (vis[nbr] == -vis[rem]) {
                            continue;
                        } else
                            return false;
                    }
                }

            }
        }
        return true;
    }

    // General***********************************************************************************************
    // call from all unvis vtces , Arrays.fill(vis,-1);
    public static boolean isBipartite(ArrayList<Edge>[] graph, int src, int[] vis) {
        LinkedList<Integer> que = new LinkedList<>();
        int color = 0; // 0 : red, 1 : green

        que.add(src);
        boolean isCycle = false, isBipartite = true;

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int rvtx = que.removeFirst();
                if (vis[rvtx] != -1) {
                    isCycle = true;
                    if (vis[rvtx] != color) {
                        isBipartite = false;
                        break;
                    }

                    continue;
                }

                vis[rvtx] = color;
                for (Edge e : graph[rvtx]) {
                    if (vis[e.v] == -1) {
                        que.addLast(e.v);
                    }
                }
            }
            color = (color + 1) % 2;
            if (!isBipartite)
                break;
        }

        if (isCycle) {
            if (isBipartite)
                System.out.println("Graph is Bi-Partite it means it has even length cycle");
            else
                System.out.println("Graph is Non Bi-Partite it means it has odd length cycle");

        } else {
            System.out.println("Graph is Bi-Partite");
        }

        return isBipartite;
    }
}