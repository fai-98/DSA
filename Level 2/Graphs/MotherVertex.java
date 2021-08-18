import java.io.*;
import java.util.*;

public class MotherVertex {
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] st = br.readLine().split(" ");
        int n = Integer.parseInt(st[0]);
        int m = Integer.parseInt(st[1]);

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = br.readLine().split(" ");
            int u = Integer.parseInt(st[0]) - 1;
            int v = Integer.parseInt(st[1]) - 1;
            graph.get(u).add(v);
        }

        System.out.println(findMotherVertex(n, graph));
    }

    // Mother Vertex is not defined for disconnected graphs
    public static int findMotherVertex(int N, ArrayList<ArrayList<Integer>> adj) {
        Stack<Integer> st = new Stack<>();
        boolean[] vis = new boolean[N];

        // fill stack
        for (int i = 0; i < N; i++) {
            if (!vis[i]) {
                dfs(adj, i, st, vis);
            }
        }

        // If there exist mother vertex (or vertices), then one of the mother vertices
        // is the last finished vertex in DFS. (Or a mother vertex has the maximum
        // finish time in DFS traversal).
        // A vertex is said to be finished in DFS if a recursive call for its DFS is
        // over, i.e., all descendants of the vertex have been visited.

        int posAns = st.peek();
        vis = new boolean[N];

        dfs(adj, posAns, vis);

        // if couldnt reach to any vtx , no mother vtx exist
        for (int i = 0; i < N; i++) {
            if (vis[i] == false) {
                return -1;
            }
        }

        return posAns + 1; // we prev converted vtx to 0 indexed
    }

    public static void dfs(ArrayList<ArrayList<Integer>> adj, int src, Stack<Integer> st, boolean[] vis) {
        vis[src] = true;
        for (int nbr : adj.get(src)) {
            if (!vis[nbr]) {
                dfs(adj, nbr, st, vis);
            }
        }
        st.push(src);
    }

    public static void dfs(ArrayList<ArrayList<Integer>> adj, int src, boolean[] vis) {
        vis[src] = true;
        for (int nbr : adj.get(src)) {
            if (!vis[nbr]) {
                dfs(adj, nbr, vis);
            }
        }
    }
}