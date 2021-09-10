import java.io.*;
import java.util.*;

public class KosaRaju {
  public static void main(String args[]) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] st = br.readLine().split(" ");
    int N = Integer.parseInt(st[0]);
    int edges = Integer.parseInt(st[1]);
    ArrayList<Integer>[] graph = new ArrayList[N];

    for (int i = 0; i < N; i++) {
      graph[i] = new ArrayList<>();
    }

    for (int i = 0; i < edges; i++) {
      st = br.readLine().split(" ");
      int u = Integer.parseInt(st[0]) - 1;
      int v = Integer.parseInt(st[1]) - 1;

      graph[u].add(v);
      graph[v].add(u);
    }

    int SCC = kosaRaju(graph, N);
    System.out.println(SCC);
  }

  public static int kosaRaju(ArrayList<Integer>[] graph, int N) {
    boolean[] vis = new boolean[N];
    Stack<Integer> st = new Stack<>();

    // 1. Random order dfs
    for (int i = 0; i < N; i++) {
      if (!vis[i]) {
        dfs(graph, i, vis, st);
      }
    }

    // 2. reverse edges
    ArrayList<Integer>[] transpose = transpose(graph, N);

    // 3. dfs in order of stack , st from st top
    int SCC = 0;
    vis = new boolean[N];

    while (!st.isEmpty()) {
      int src = st.pop();
      if (!vis[src]) {
        dfs(transpose, src, vis);
        SCC++;
      }
    }

    return SCC;
  }

  public static ArrayList<Integer>[] transpose(ArrayList<Integer>[] graph, int N) {
    ArrayList<Integer>[] transpose = new ArrayList[N];
    for (int i = 0; i < N; i++) {
      transpose[i] = new ArrayList<>();
    }

    for (int i = 0; i < N; i++) {
      for (int nbr : graph[i]) {
        transpose[nbr].add(i);
      }
    }

    return transpose;
  }

  public static void dfs(ArrayList<Integer>[] graph, int src, boolean[] vis, Stack<Integer> st) {
    // mark
    vis[src] = true;

    for (var nbr : graph[src]) {
      if (!vis[nbr]) {
        dfs(graph, nbr, vis, st);
      }
    }
    st.push(src);
  }

  public static void dfs(ArrayList<Integer>[] graph, int src, boolean[] vis) {
    // mark
    vis[src] = true;

    for (var nbr : graph[src]) {
      if (!vis[nbr]) {
        dfs(graph, nbr, vis);
      }
    }
  }

}