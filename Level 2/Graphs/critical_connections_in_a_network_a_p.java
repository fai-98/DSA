class Solution {

	// 1192. Critical Connections in a Network

	int[] par;
	int[] disc;
	int[] low;
	boolean[] vis;
	List < List < Integer >> res;
	int time;
	int dfsCalls;

	public List < List < Integer >> criticalConnections(int n, List < List < Integer >> connections) {
		par = new int[n];
		low = new int[n];
		disc = new int[n];
		vis = new boolean[n];
		par[0] = -1;
		time = 0;
		dfsCalls = 0;
		res = new ArrayList < > ();

		//construct graph
		ArrayList < ArrayList < Integer >> graph = new ArrayList < > ();
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList < > ());
		}

		for (var edge : connections) {
			int u = edge.get(0);
			int v = edge.get(1);

			graph.get(u).add(v);
			graph.get(v).add(u);
		}

		dfs(0, graph);
		return res;
	}

	public void dfs(int src, ArrayList < ArrayList < Integer >> graph) {
		disc[src] = low[src] = time;
		time++;

		vis[src] = true;

		ArrayList < Integer > nbrs = graph.get(src);

		for (int nbr : nbrs) {
			if (par[src] == nbr) {
				continue;
			} else if (vis[nbr]) {
				low[src] = Math.min(low[src], disc[nbr]);
			} else {
				par[nbr] = src;
				dfsCalls++;
				dfs(nbr, graph);
				//check for Edge
				if (disc[src] < low[nbr]) {
					res.add(new ArrayList(Arrays.asList(src, nbr)));
				}

				low[src] = Math.min(low[src], low[nbr]);
			}
		}
	}
}