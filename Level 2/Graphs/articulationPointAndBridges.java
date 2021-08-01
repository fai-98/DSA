import java.util.*;
import java.io.*;
public class articulationPointAndBridges {

	static int[] parent;
	static int[] disc;
	static int[] low;
	static boolean[] vis;
	static boolean[] ap;
	static int time;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int v = sc.nextInt();
		int e = sc.nextInt();

		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < v; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < e; i++) {
			int u = sc.nextInt() - 1;
			int v1 = sc.nextInt() - 1;

			graph.get(u).add(v1);
			graph.get(v1).add(u);
		}

		parent = new int[v];
		disc = new int[v];
		low = new int[v];
		vis = new boolean[v];
		ap = new boolean[v];
		time = 0;

		//call dfs with src = 0;
		// src has no par so par[src] = -1;
		parent[0] = -1;
		dfs(0, graph);

		int res = 0;
		for (int i = 0; i < v; i++) {
			if (ap[i] == true)res++;
		}
		System.out.println(res);
	}

	public static void dfs(int src , ArrayList<ArrayList<Integer>> graph) {
		// initially low = disc , low updated while backtracking
		disc[src] = low[src] = time;
		time++;
		int dfsCount = 0;
		//mark
		vis[src] = true;

		//get nbrs
		ArrayList<Integer> nbrs = graph.get(src);

		//3 cases for nbrs
		for (int nbr : nbrs) {
			//par+vis
			if (parent[src] == nbr) {
				continue;
			} else if (vis[nbr] == true) { //vis
				low[src] = Math.min(low[src], disc[nbr]);
			} else {
				//set parent
				parent[nbr] = src;
				dfsCount++;
				dfs(nbr, graph);
				//during backtracking update low
				low[src] = Math.min(low[src], low[nbr]);
				//check for AP
				if (parent[src] == -1) { //actual source
					if (dfsCount >= 2) {
						ap[src] = true;
					}
				} else {
					if (low[nbr] >= disc[src]) {
						ap[src] = true;
					}
				}
			}
		}
	}
}