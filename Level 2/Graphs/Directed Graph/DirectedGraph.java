import java.util.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class DirectedGraph {
    public static class Edge {
        int v = 0;
        int w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(v, w));
    }

    // O(2E)
    public static void display(ArrayList<Edge>[] graph) {
        int N = graph.length;
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + ", " + e.w + ") ");
            }
            System.out.println();
        }
    }

    public static void constructGraph() {
        int N = 7;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);
        addEdge(graph, 3, 0, 10);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 5, 6, 3);
        addEdge(graph, 6, 4, 8);

        display(graph);
    }

    // Topological Sort Recursive only DAG (Directed Acyclic Graph)
    // *******************************************************************************************************

    // call dfs from every unvis src
    public static void topologicalSort(ArrayList<Edge>[] graph) {
        ArrayList<Integer> ans = new ArrayList<>();

        int N = graph.length;
        boolean[] vis = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (!vis[i])
                dfs_topo(graph, i, vis, ans);
        }
    }

    // M-----dfs(unvis nbrs)------S (put in Stack/ArrayList)
    // Topological Sort is Stack (top to bottom)
    // Order of compilation is Reverse
    public static void dfs_topo(ArrayList<Edge>[] graph, int src, boolean[] vis, ArrayList<Integer> ans) {
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                dfs_topo(graph, e.v, vis, ans);
        }

        ans.add(src);
    }



    //Order Of Compilation
    public static void topoSort(ArrayList<Edge>[]graph) {
        boolean[] vis = new boolean[graph.length];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < graph.length; i++) {
            if (vis[i] == false) {
                DFS(graph, i, st, vis);
            }
        }

        while (!st.isEmpty()) {
            System.out.println(st.pop());
        }
    }

    public static void DFS(ArrayList<Edge>[]graph, int src, Stack<Integer> st, boolean[] vis) {

        //mark
        vis[src] = true;

        //nbrs
        for (Edge edge : graph[src]) {
            if (vis[edge.nbr] == false) {
                DFS(graph, edge.nbr, st, vis);
            }
        }

        st.push(src); //while backtracking
    }

    // Kahn's Algorithm (Topological Sort)
    // Complexity O(E)+O(V)+O(E+V) = 2(E+V) -> O(E+V) -> O(N) (in graph E+V is N)
    /*************************************************************************************************************/
    public static ArrayList<Integer> kahnsAlgo(ArrayList<Edge>[] graph) {
        int N = graph.length;
        Queue<Integer> q = new ArrayDeque<>();
        ArrayList<Integer> ans = new ArrayList<>();
        int[] indegree = new int[N];

        // indegree init O(E)
        for (int i = 0; i < graph.length; i++) {
            for (Edge e : graph[i]) {
                indegree[e.v]++;
            }
        }

        // add 0 indeg vtces O(V)
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        // O(E+V)
        while (q.size() > 0) {
            // rem , ans.add , nbrs indeg-- , if indeg ==0 -> enque
            int rem = q.poll();
            ans.add(rem);
            // --indeg==0 , first indeg dec by 1 , then checked if 0 or not
            for (Edge e : graph[rem]) {
                if (--indegree[e.v] == 0) {
                    q.offer(e.v);
                }
            }
        }

        if (ans.size() != N) {
            ans.clear(); // Topo Sort not possible
        }
        return ans;
    }

    // Exactly Similar Ques are
    // 207 Course Schedule I
    // 210 Course Schedule II

    // Cycle in Directed Graph using DFS
    /*********************************************************************************************/

    // using 2 boolean arrays , vis , path , make path==false while backtracking

    // -1-> unvisited , 0-> part of current path , 1-> backtracked (i.e part of prev
    // path)
    // using single array

    public boolean dfs_topo(ArrayList<Edge>[] graph) {
        int N = graph.length;
        int[] vis = new int[N];
        Arrays.fill(vis, -1);
        ArrayList<Integer> ans = new ArrayList<>();

        boolean isCycle = false;

        for (int src = 0; src < graph.length; src++) {
            if (vis[src] == -1) {
                isCycle = isCycle || dfs_topo_isCycle(graph, src, vis, ans);
            }
        }

        if (isCycle)
            ans.clear();
        // return ans;
        return isCycle;
    }

    // -1-> unvisited , 0-> part of current path , 1-> backtracked (i.e part of prev
    // path)
    public boolean dfs_topo_isCycle(ArrayList<Edge>[] graph, int src, int[] vis, ArrayList<Integer> ans) {
        vis[src] = 0;
        boolean res = false;
        for (Edge e : graph[src]) {
            if (vis[e.v] == -1) {
                res = res || dfs_topo_isCycle(graph, e.v, vis, ans);
            } else if (vis[e.v] == 0) {
                return true;
            }
        }

        vis[src] = 1;
        ans.add(src);
        return res;
    }

    public static void main(String[] args) {
        constructGraph();
    }
}