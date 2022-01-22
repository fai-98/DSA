class Solution {

	// 329. Longest Increasing Path in a Matrix
	public int longestIncreasingPath(int[][] matrix) {
		int n = matrix.length, m = matrix[0].length;
		int[][] dir = {{0, 1}, {0, -1}, {1, 0}, { -1, 0}};
		int[][] indegree = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {

				for (int d = 0; d < 4; d++) {
					int r = i + dir[d][0];
					int c = j + dir[d][1];

					if (r >= 0 && c >= 0 && r < n && c < m) {
						if (matrix[i][j] > matrix[r][c]) {
							indegree[i][j]++;
						}
					}
				}
			}
		}

		Queue < Integer > q = new ArrayDeque < > ();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (indegree[i][j] == 0) {
					q.offer(i * m + j);
				}
			}
		}

		int level = 0;
		while (q.size() > 0) {
			int size = q.size();
			while (size-- > 0) {
				int remIdx = q.poll();

				int sr = remIdx / m;
				int sc = remIdx % m;

				for (int d = 0; d < 4; d++) {
					int r = sr + dir[d][0];
					int c = sc + dir[d][1];

					if (r >= 0 && c >= 0 && r < n && c < m) {
						if (matrix[sr][sc] < matrix[r][c]) {
							if (--indegree[r][c] == 0) q.offer(r * m + c);
						}
					}
				}

			}
			level++;
		}

		return level;
	}

	//Tags
	//topo Sort, bfs , dp
}