public class grid_unique_paths {

	// 62. Unique Paths
	public int uniquePaths(int m, int n) {
		int dp[] = new int[n];
		dp[0] = 1;
		for (int i = 0 ; i < m ; i++) {
			for (int j = 1 ; j < n ; j++) {
				dp[j] = dp[j - 1] + dp[j];

			}
		}

		return dp[n - 1];
	}


	// 63. Unique Paths II
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int n = obstacleGrid.length , m = obstacleGrid[0].length;
		int[][] dp = new int[n][m];
		dp[0][0] = 1;

		boolean flag = false;
		for (int i = 0 ; i < n ; i++) {
			if (flag || obstacleGrid[i][0] == 1) {
				flag = true;
				dp[i][0] = 0;
			} else dp[i][0] = 1;
		}
		flag = false;
		for (int j = 0 ; j < m ; j++) {
			if (flag || obstacleGrid[0][j] == 1) {
				flag = true;
				dp[0][j] = 0;
			} else dp[0][j] = 1;
		}
		for (int i = 1; i < n ; i++) {
			for (int j = 1 ; j < m ; j++) {
				if (obstacleGrid[i][j] == 1)dp[i][j] = 0;
				else dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
			}
		}

		return dp[n - 1][m - 1];
	}
}