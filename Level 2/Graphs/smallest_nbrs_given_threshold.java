class Solution {

	// 1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance

	public int findTheCity(int n, int[][] edges, int distanceThreshold) {
		int[][] mat = new int[n][n];
		for (int[] row : mat)
			Arrays.fill(row, (int) 1e9);

		for (int[] e : edges) {
			int src = e[0], des = e[1], dist = e[2];
			mat[src][des] = dist;
			mat[des][src] = dist;
		}

		for (int i = 0; i < n; i++)
			mat[i][i] = 0;

		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
				}
			}
		}

		//we have all src to all dest min dist

		int min = 101, candidate = 0;

		for (int i = 0; i < n; i++) {
			int ct = 0;
			for (int j = 0; j < n; j++) {
				if (i != j) {
					if (mat[i][j] <= distanceThreshold) {
						ct++;
					}
				}
			}
			if (ct <= min) {
				min = ct;
				candidate = i;
			}
		}

		return candidate;
	}
}