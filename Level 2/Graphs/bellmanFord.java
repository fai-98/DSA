import java.util.ArrayList;
import java.util.Arrays;

class bellmanFord {

	// time complexity of Bellman-Ford is O(VE)
	// using only 1 array
	public void bellmanFordAlgo(int n, int[][] edges, int src) {
		// n vertices
		int[] path = new int[n];
		Arrays.fill(path, (int) 1e9);
		path[src] = 0;

		// (V-1) times
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < edges.length; j++) { // for E edges
				int u = edges[j][0];
				int v = edges[j][1];
				int w = edges[j][2];

				if (path[u] == (int) 1e9) {
					continue;
				} else if (path[u] + w < path[v]) {
					path[v] = path[u] + w;
				}
			}
		}
		// now all the idx of path have the shortest path for vtx == idx , if INF -> no
		// path exists
		// negative wt cycle detection , run same loop for 1 more time i.e Vth time
		boolean negativeCycle = false;

		for (int j = 0; j < edges.length; j++) { // for E edges
			int u = edges[j][0];
			int v = edges[j][1];
			int w = edges[j][2];

			if (path[u] == (int) 1e9) {
				continue;
			} else if (path[u] + w < path[v]) {
				negativeCycle = true; // path still getting updated -> path with V edges possible
			}
		}

	}

	// using 2 arrays , using 2 arrays , for some ques prev method dont work , ex
	// cheapest flights
	public static void bellmanFordAlgo_(int N, int[][] edges, int src) {
		int[] prev = new int[N];

		Arrays.fill(prev, (int) 1e9);

		prev[src] = 0;
		boolean negativeCycle = false;
		for (int i = 1; i <= N; i++) {
			int[] curr = new int[N];
			for (int j = 0; j < N; j++)
				curr[j] = prev[j];

			boolean anyUpdate = false;
			for (int[] e : edges) {
				int u = e[0], v = e[1], w = e[2];
				if (prev[u] != (int) 1e9 && prev[u] + w < curr[v]) {
					curr[v] = prev[u] + w;
					anyUpdate = true;
					if (i == N) {
						negativeCycle = true;
						break;
					}
				}
			}

			if (!anyUpdate)
				break;

			prev = curr;
		}

		System.out.println("Negative Cycle: " + negativeCycle);

	}
}
