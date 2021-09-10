class RottenOranges {
	public int orangesRotting(int[][] grid) {
		int n = grid.length, m = grid[0].length, rotten = 0, fresh = 0, time = 0;

		Queue < Integer > q = new ArrayDeque < > ();
		//add rotten/sources to que , and count fresh
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 2) {
					q.offer(i * m + j);
				} else if (grid[i][j] == 1) {
					fresh++;
				}
			}
		}

		if (fresh == 0)return 0;

		//Multisource BFS
		while (q.size() > 0) {
			int size = q.size();
			while (size-- > 0) {
				int remIdx = q.poll();
				int r = remIdx / m;
				int c = remIdx % m;

				//T
				if (r - 1 >= 0 && grid[r - 1][c] == 1) {
					q.offer((r - 1) * m + c);
					grid[r - 1][c] = 2;
					fresh -= 1;
					if (fresh == 0) return time + 1;
				}
				//L
				if (c - 1 >= 0 && grid[r][c - 1] == 1) {
					q.offer(r * m + (c - 1));
					grid[r][c - 1] = 2;
					fresh -= 1;
					if (fresh == 0) return time + 1;
				}
				//D
				if (r + 1 < grid.length && grid[r + 1][c] == 1) {
					q.offer((r + 1) * m + c);
					grid[r + 1][c] = 2;
					fresh -= 1;
					if (fresh == 0) return time + 1;
				}
				//R
				if (c + 1 < grid[0].length && grid[r][c + 1] == 1) {
					q.offer(r * m + (c + 1));
					grid[r][c + 1] = 2;
					fresh -= 1;
					if (fresh == 0) return time + 1;
				}
			}
			time++;
		}

		return -1;

	}

	//Another method**************************************************************************
	public BFS() {
		int[][] dir = { { -1, 0}, {0, -1}, {1, 0}, {0, 1} };

		//Multisource BFS
		while (q.size() > 0) {
			int size = q.size();
			while (size-- > 0) {
				int remIdx = q.poll();
				int r = remIdx / m;
				int c = remIdx % m;

				for (int d = 0; d < 4; d++) {
					int R = r + dir[d][0];
					int C = c + dir[d][1];

					if (R >= 0 && C >= 0 && R < n && C < m && grid[R][C] == 1) {
						grid[R][C] = 2;
						q.offer(R * m + C);
						if (--fresh == 0) return time + 1;
					}
				}
			}
			time++;
		}
	}
}