import java.util.*;
import java.util.ArrayList;

public class Graphs_01 {

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

    public static void constructGraph() {
        int N = 7;
        ArrayList<Edge>[] graph = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);
        addEdge(graph, 3, 0, 10);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 5, 6, 3);
        addEdge(graph, 6, 4, 8);

        // display
        // display(graph);

        // allPaths
        boolean[] vis = new boolean[7];
        int count = printAllPaths(graph, 0, 6, 0 + "", 0, vis);
        System.out.println(count);

    }

    // basic functions

    // find Edge T : O(E)
    public static int findEdge(ArrayList<Edge>[] graph, int u, int v) {
        ArrayList<Edge> edges = graph[u];
        for (int i = 0; i < edges.size(); i++) {
            int vtx = edges.get(i).v;
            if (vtx == v)
                return i;
        }
        return -1;
    }

    // remove edge (remove from both vtxes) T : O(E)
    public static void removeEdge(ArrayList<Edge>[] graph, int u, int v) {
        // find edge u-v at graph[u];
        int vIdx = findEdge(graph, u, v);
        graph[u].remove(vIdx);

        // find edge v-u at graph[v];
        int uIdx = findEdge(graph, v, u);
        graph[v].remove(uIdx);
    }

    // DFS T : O(E) all edges vis only once (where E = total no of Edges in that
    // particular component)
    public static boolean hasPath(ArrayList<Edge>[] graph, boolean[] vis, int src, int dest) {
        // self check
        if (src == dest) {
            return true;
        }

        // mark
        vis[src] = true;

        // nbrs
        for (Edge e : graph[src]) {
            int nbr = e.v;
            if (vis[nbr] == false) {
                // check path from nbr
                nbr = e.v;
                if (hasPath(graph, vis, nbr, dest))
                    return true;
            }
        }

        return false;

    }

    // hasPath/DFS
    public static boolean hasPath_02(ArrayList<Edge>[] graph, boolean[] vis, int src, int dest) {
        // self
        if (src == dest)
            return true;

        // mark
        vis[src] = true;

        // nbrs
        boolean res = false;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                res = res || hasPath_02(graph, vis, e.v, dest);
        }

        return false;
    }

    // allPaths
    public static int printAllPaths(ArrayList<Edge>[] graph, int src, int dest, String psf, int wsf, boolean[] vis) {
        // self check
        if (src == dest) {
            System.out.println(psf + " " + dest + " @ " + wsf);
            return 1;
        }

        int count = 0;
        // mark
        vis[src] = true;
        // nbrs
        for (Edge edge : graph[src]) {
            int nbr = edge.v;
            // if unvisited
            if (vis[nbr] == false) {
                count += printAllPaths(graph, nbr, dest, psf + nbr, wsf + edge.w, vis);
            }
        }
        // unmark
        vis[src] = false;

        return count;
    }

    // get Connected Components
    // T : O(E+V)

    // single comp
    public static void getSc(ArrayList<Edge>[] graph, int src, ArrayList<Integer> ans, boolean[] vis) {

        ans.add(src);

        vis[src] = true;

        for (Edge edge : graph[src]) {
            int nbr = edge.v;
            // if unvisited
            if (vis[nbr] == false) {
                getSc(graph, nbr, ans, vis);
            }
        }

    }

    // GCC
    public static ArrayList<ArrayList<Integer>> getCC(ArrayList<Edge>[] graph) {

        ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
        boolean[] vis = new boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (vis[i] == false) {
                ArrayList<Integer> singComp = new ArrayList<>();
                getSc(graph, i, singComp, vis);
                comps.add(singComp);
            }
        }

        return comps;
    }

    public static void main(String[] args) {
        constructGraph();
    }

}
