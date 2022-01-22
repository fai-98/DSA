class Solution {

	// 207. Course Schedule
	public boolean canFinish(int N, int[][] edges) {
		ArrayList < Integer > [] graph = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList < > ();
		}
		for (int[] edge : edges) {
			graph[edge[0]].add(edge[1]);
		}

		return !dfs_topo(graph);
	}
	public boolean dfs_topo(ArrayList < Integer > [] graph) {
		int N = graph.length;
		int[] vis = new int[N];
		Arrays.fill(vis, -1);
		ArrayList < Integer > ans = new ArrayList < > ();

		boolean isCycle = false;

		for (int src = 0; src < graph.length; src++) {
			if (vis[src] == -1) {
				isCycle = isCycle || dfs_topo_isCycle(graph, src, vis, ans);
			}
		}

		if (isCycle) ans.clear();
		return isCycle;
	}

	public boolean dfs_topo_isCycle(ArrayList < Integer > [] graph, int src, int[] vis, ArrayList < Integer > ans) {
		vis[src] = 0;
		boolean res = false;
		for (Integer e : graph[src]) {
			if (vis[e] == -1) {
				res = res || dfs_topo_isCycle(graph, e, vis, ans);
			} else if (vis[e] == 0) {
				return true;
			}
		}

		vis[src] = 1;
		ans.add(src);
		return res;
	}
}