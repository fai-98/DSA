class Solution {

	// 931. Minimum Falling Path Sum
	public int minFallingPathSum(int[][] matrix) {
		int n = matrix.length;

		for (int i = n - 2; i >= 0; i--) {
			for (int j = 0; j < n; j++) {
				if (j == 0) {
					matrix[i][j] += Math.min(matrix[i + 1][j], matrix[i + 1][j + 1]);
				} else if (j == n - 1) {
					matrix[i][j] += Math.min(matrix[i + 1][j], matrix[i + 1][j - 1]);
				} else {
					matrix[i][j] += Math.min(Math.min(matrix[i + 1][j],
					                                  matrix[i + 1][j - 1]),
					                         matrix[i + 1][j + 1]);
				}
			}
		}

		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			if (matrix[0][i] < ans) {
				ans = matrix[0][i];
			}
		}

		return ans;
	}

	// 120. Triangle
	public int minimumTotal(List<List<Integer>> triangle) {
		int length = triangle.size();

		for (int i = length - 2; i >= 0; i--) {
			for (int j = 0; j < triangle.get(i).size(); j++) {
				triangle.get(i).set(j, Math.min(triangle.get(i + 1).get(j),
				                                triangle.get(i + 1).get(j + 1)) + triangle.get(i).get(j));
			}
		}

		return triangle.get(0).get(0);
	}

	// 64. Minimum Path Sum
	public int minPathSum(int[][] grid) {
		int[][] dp = new int[grid.length][grid[0].length];
		dp[0][0] = grid[0][0];
		for (int j = 1 ; j < grid[0].length ; j++) {
			dp[0][j] = dp[0][j - 1] + grid[0][j];
		}
		for (int i = 1 ; i < grid.length ; i++) {
			dp[i][0] = grid[i][0] + dp[i - 1][0];
		}
		for (int i = 1 ; i < grid.length ; i++) {
			for (int j = 1 ; j < grid[0].length ; j++) {
				dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
			}
		}

		return dp[dp.length - 1][dp[0].length - 1];
	}
}