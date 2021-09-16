public class shortest_bridge {


	//Method 1
	//Flood fill 1 island
	//Multisource BFS from 2nd island
	public int shortestBridge(int[][] grid) {
		int n = grid.length, m = grid[0].length, dist = 0;

		//color 1 island diff than other
		boolean flag = false;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 1 && !flag) {
					floodFill(grid, i, j, 2);
					flag = true;
				}
			}
		}

		//apply bfs from island of 1's to island of 2's
		Queue < Integer > q = new ArrayDeque < > ();

		//Multisource
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 1) {
					q.offer(i * m + j);
				}
			}
		}

		int[][] dir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };


		while (q.size() > 0) {
			int size = q.size();
			while (size-- > 0) {
				int rIdx = q.poll();
				int sr = rIdx / m;
				int sc = rIdx % m;

				// calls
				for (int d = 0; d < 4; d++) {
					int r = sr + dir[d][0];
					int c = sc + dir[d][1];

					if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 2) {
						return dist;
					}

					if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 0) {
						grid[r][c] = -1;
						q.offer(r * m + c);
					}
				}
			}
			dist++;
		}

		return dist;
	}
	public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {

		boolean[][] vis = new boolean[image.length][image[0].length];
		dfs(image, sr, sc, image[sr][sc], newColor, vis);
		return image;
	}

	public void dfs(int[][] arr, int sr, int sc, int orig, int color, boolean[][] vis) {
		if (sr < 0 || sc < 0 || sr >= arr.length || sc >= arr[0].length || vis[sr][sc] == true || arr[sr][sc] != orig) return;

		vis[sr][sc] = true;
		arr[sr][sc] = color;
		dfs(arr, sr - 1, sc, orig, color, vis);
		dfs(arr, sr, sc - 1, orig, color, vis);
		dfs(arr, sr + 1, sc, orig, color, vis);
		dfs(arr, sr, sc + 1, orig, color, vis);
		vis[sr][sc] = false;
	}


	//Another Approach
	//run dfs from any 1 , enque all the ones and mark vis , run BFS from all sources in que
	//can use same boolean matrix
}