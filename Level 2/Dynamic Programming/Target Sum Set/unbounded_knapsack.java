public class unbounded_knapsack {

	//unbounded knapSack
	public static int unbounded_knapSack(int[] wt , int[] val , int N , int W , int[][] dp) {
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= W; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = 0;
					continue;
				}

				if (j - wt[i - 1] >= 0) {
					dp[i][j] = Math.max(dp[i - 1][j] , val[i - 1] + dp[i][j - wt[i - 1]]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}

		return dp[N][W];
	}

	//unbounded knapSack - 1d (like coin change combinations ??)
	public static int unbounded_knapSack(int[] wt , int[] val , int N , int W , int[] dp) {
		dp[0];
		for (i = 1; i <= N; i++) {
			for (int j = wt[i - 1]; j <= W; j++) {
				if (j - wt[i - 1] >= 0) {
					dp[j] = Math.max(dp[j], dp[j - wt[i - 1]]) + val[i - 1];
				}
			}
		}

		return dp[W];
	}
}