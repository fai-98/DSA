public class number_of_provinces {

	// 547. Number of Provinces
	int[] par;
	int[] rank;
	public int findCircleNum(int[][] grid) {
		//GCC
		int n = grid.length;
		int comps = n;

		par = new int[n];
		rank = new int[n];

		for (int i = 0; i < n; i++) {
			par[i] = i;
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i != j && grid[i][j] == 1) {
					if (union(i, j)) {
						comps--;
					}
				}
			}
		}
		return comps;
	}

	public boolean union(int u, int v) {
		int p1 = findPar(u);
		int p2 = findPar(v);

		if (p1 != p2) {
			if (rank[p1] > rank[p2]) {
				par[p2] = p1;
			} else if (rank[p2] > rank[p1]) {
				par[p1] = p2;
			} else {
				par[p2] = p1;
				rank[p1]++;
			}
			return true;
		}
		return false;
	}

	public int findPar(int u) {
		if (par[u] == u)
			return u;
		return par[u] = findPar(par[u]);
	}
}