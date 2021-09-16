public class rem_max_num_of_edges {

	// 1579. Remove Max Number of Edges to Keep Graph Fully Traversable

	public int maxNumEdgesToRemove(int n, int[][] edges) {
		// {type,u,v} , give priority to both alice bob edge

		//1. sort - give preference to 3
		//2. make 2 DSU - for alice and bob
		//3. do union find type 3 - both , 2 - alice , 1 - bob
		//4. if after UF both are fully connected i.e vertices connected
		//5. return removed edges count

		Arrays.sort(edges, (a, b) -> {
			return b[0] - a[0];
		});

		int mergedA = 1;
		int mergedB = 1;
		int remEdge = 0;

		int[] parA = new int[n]; //Alice
		int[] parB = new int[n]; //Bob
		int[] rankA = new int[n];
		int[] rankB = new int[n];

		//initialize
		for (int i = 0; i < n; i++) {
			parA[i] = i;
			parB[i] = i;
			rankA[i] = 1;
			rankB[i] = 1;
		}

		// for n vtces n-1 merge required
		//if edge not merged i.e it can be removed
		for (int[] edge : edges) {
			int type = edge[0];
			int u = edge[1] - 1;
			int v = edge[2] - 1;

			if (type == 1) { //Alice
				boolean isMerged = union(u, v, parA, rankA);
				if (isMerged) {
					mergedA++;
				} else {
					remEdge++;
				}
			} else if (type == 2) { //Bob
				boolean isMerged = union(u, v, parB, rankB);
				if (isMerged) {
					mergedB++;
				} else {
					remEdge++;
				}
			} else { //Both
				boolean isMergedA = union(u, v, parA, rankA);
				boolean isMergedB = union(u, v, parB, rankB);

				if (isMergedA) {
					mergedA++;

				}
				if (isMergedB) {
					mergedB++;
				}

				if (!isMergedA && !isMergedB) {
					remEdge++;
				}

			}
		}

		if (mergedA != n || mergedB != n) {
			return -1;
		}

		return remEdge;
	}

	//union by rank
	public boolean union(int u, int v, int[] par, int[] rank) {
		int p1 = findPar(u, par);
		int p2 = findPar(v, par);

		if (p1 != p2) {
			if (rank[p1] > rank[p2]) {
				par[p2] = p1;
			} else if (rank[p1] < rank[p2]) {
				par[p1] = p2;
			} else {
				par[p1] = p2;
				rank[p2]++;
			}
			return true; //if merging happens
		}
		return false;
	}

	public int findPar(int u, int[] par) {
		if (par[u] == u) return u;
		return par[u] = findPar(par[u], par);
	}
}