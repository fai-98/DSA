public class cherry_pickup {

	// Leetcode 741. Cherry Pickup
	public int cherryPickup(int[][] grid) {
		int n = grid.length;
		boolean[][] vis = new boolean[n][n];
		return dfs(grid, vis, 0, 0, 0);
	}


	// A1. Backtracking Approach

	static int maxCherry;
	public void cherryPickup_dr(int[][] grid , int r , int c , int n, int csf) {
		if (r < 0 || c < 0 || r >= n || c >= n || grid[r][c] == -1)
			return;

		//for this (0,0) to (n-1,n-1) path , try all (n-1,n-1) to (0,0) paths
		if (r == n - 1 && c == n - 1) {
			cherryPickup_tl(grid, r, c, n, csf);
			return;
		}

		int cherry = grid[r][c]; //0 or 1

		grid[r][c] = 0; //vis
		cherryPickup_dr(grid, r + 1, c, n, csf + cherry);
		cherryPickup_dr(grid, r, c + 1, n, csf + cherry);
		grid[r][c] = cherry; //place cherry back
	}

	//already picked cherries are now 0 so no double count;
	public void cherryPickup_tl(int[][] grid , int r , int c , int n, int csf) {
		if (r < 0 || c < 0 || r >= n || c >= n || grid[r][c] == -1)
			return;

		//for this (0,0) to (n-1,n-1) path , try all (n-1,n-1) to (0,0) paths
		if (r == 0 && c == 0) {
			maxCherry = Math.max(maxCherry, csf);
			return;
		}

		int cherry = grid[r][c]; //0 or 1

		grid[r][c] = 0; //vis
		cherryPickup_tl(grid, r - 1, c, n, csf + cherry);
		cherryPickup_tl(grid, r, c - 1, n, csf + cherry);
		grid[r][c] = cherry; //place cherry back
	}

	//A2.  4d DP

	//return res < 0 ? 0 : res;
	public int cherryPickup_memo(int[][] grid, int r1, int c1, int r2, int c2, int n, Integer[][][][] dp) {
		//-ve base case
		if (r1 >= n || c1 >= n || r2 >= n || c2 >= n || grid[r1][c1] == -1 || grid[r2][c2] == -1) {
			return -(int) 1e9;
		}

		//+ve base case (both person 1 ans person2 whill reach dest at same time)
		if (r1 == n - 1 && c1 == n - 1) {
			return dp[r1][c1][r2][c2] = grid[r1][c1];
		}

		if (dp[r1][c1][r2][c2] != null) {
			return dp[r1][c1][r2][c2];
		}

		int cherries = 0;
		//avoid double counting
		if (r1 == r2 && c1 == c2) {
			cherries += grid[r1][c1];
		} else {
			cherries += grid[r1][c1] + grid[r2][c2];
		}

		int f1 = cherryPickup_memo(grid, r1, c1 + 1, r2, c2 + 1, n, dp); //hh
		int f2 = cherryPickup_memo(grid, r1, c1 + 1, r2 + 1, c2, n, dp); //hv
		int f3 = cherryPickup_memo(grid, r1 + 1, c1, r2, c2 + 1, n, dp); //vh
		int f4 = cherryPickup_memo(grid, r1 + 1, c1, r2 + 1, c2, n, dp); //vv

		return dp[r1][c1][r2][c2] = cherries + Math.max(Math.max(f1, f2), Math.max(f3, f4));

	}

	//Space Optimizations
}