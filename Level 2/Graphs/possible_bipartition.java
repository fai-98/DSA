class Solution {


	// 886. Possible Bipartition

	public class Pair {
		int v;
		int set;

		Pair(int v, int set) {
			this.v = v;
			this.set = set;
		}
	}

	public boolean possibleBipartition(int n, int[][] dislikes) {

		ArrayList < Integer > [] graph = new ArrayList[n];
		construct(graph, dislikes);

		int[] vis = new int[graph.length];
		boolean res = true;

		for (int src = 0; src < graph.length; src++) {
			if (vis[src] == 0) {
				res = res && singleBPT(graph, vis, src);
			}
		}

		return res;
	}

	public boolean singleBPT(ArrayList < Integer > [] graph, int[] vis, int src) {
		Queue < Pair > q = new ArrayDeque < > ();
		Pair p = new Pair(src, 1);

		q.offer(p);

		while (q.size() > 0) {
			int size = q.size();
			while (size-- > 0) {
				//rem
				Pair rp = q.poll();

				//mark*
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

	public void construct(ArrayList < Integer > [] graph, int[][] edges) {
		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList < > ();
		}

		for (int[] edge : edges) {
			addEdge(edge, graph);
		}
	}

	public void addEdge(int[] edge, ArrayList < Integer > [] graph) {
		int u = edge[0] - 1;
		int v = edge[1] - 1;

		graph[u].add(v);
		graph[v].add(u);
	}
}