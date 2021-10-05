class Solution {
	// Leetcode 1293. Shortest Path in a Grid with Obstacles Elimination
	public class Pair {
		int flip;
		int idx;

		Pair() {}

		Pair(int idx, int flip) {
			this.idx = idx;
			this.flip = flip;
		}
	}

	public int shortestPath(int[][] grid, int k) {
		//src = (0,0) , des = (n-1,m-1)
		int n = grid.length, m = grid[0].length, dist = 0;

		//use 3d vis , coz same idx can be part of another path with less flips
		// ex [1][1][0] , [1][1][1]
		boolean[][][] vis = new boolean[n][m][k + 1];

		int[][] dir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

		Queue < Pair > q = new ArrayDeque < > ();
		q.offer(new Pair(0, 0));
		vis[0][0][0] = true;

		while (q.size() > 0) {
			int size = q.size();
			while (size-- > 0) {
				Pair rp = q.poll();
				int idx = rp.idx;
				int flip = rp.flip;
				int sr = idx / m;
				int sc = idx % m;

				if (sr == n - 1 && sc == m - 1) {
					return dist;
				}

				for (int d = 0; d < 4; d++) {
					int r = sr + dir[d][0];
					int c = sc + dir[d][1];

					if (r >= 0 && c >= 0 && r < n && c < m) {
						//if flip>allowed , continue without adding and marking
						int newFlips = flip + grid[r][c];
						if (newFlips > k || vis[r][c][newFlips]) {
							continue;
						}
						//mark unvis
						vis[r][c][newFlips] = true;
						q.offer(new Pair(r * m + c, newFlips));
					}
				}
			}
			dist++;
		}

		return -1;
	}
}