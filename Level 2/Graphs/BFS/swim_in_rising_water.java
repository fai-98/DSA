public class swim_in_rising_water {

	// 778. Swim in Rising Water
	public int swimInWater(int[][] grid) {
		int n = grid.length, m = grid[0].length;
		boolean[][] vis = new boolean[n][m];

		int[][] dir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

		PriorityQueue < int[] > q = new PriorityQueue < > ((a, b) -> {
			return a[1] - b[1];
		});

		q.offer(new int[] {
		            0,
		            grid[0][0]
		        });


		while (q.size() > 0) {
			int size = q.size();
			while (size-- > 0) {
				int[] rp = q.poll();

				int idx = rp[0];
				int t = rp[1];
				int sr = idx / m;
				int sc = idx % m;

				//reached dest
				if (sr == n - 1 && sc == m - 1) {
					return t;
				}


				for (int d = 0; d < 4; d++) {
					int r = sr + dir[d][0];
					int c = sc + dir[d][1];

					if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]) {
						int[] p = new int[2];
						int i = r * m + c;
						vis[r][c] = true;

						if (t >= grid[r][c]) {
							p[1] = t;
						} else {
							p[1] = grid[r][c];
						}
						p[0] = i;
						grid[r][c] = p[1];
						q.offer(p);
					}
				}
			}
		}

		return grid[n - 1][m - 1];

	}
}