public class knapSack {

	// 0/1 KnapSack
	// dp[n+1][W+1]
	public static int knapSack(int[] wt , int[] val , int N , int W , int[][] dp) {
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= W; j++) {
				if (i == 0 || j == 0) {  //base case
					dp[i][j] = 0;
					continue;
				}

				if (j - wt[i - 1] >= 0) {
					dp[i][j] = Math.max(dp[i - 1][j] , val[i - 1] + dp[i - 1][j - wt[i - 1]]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}

		return dp[N][W];
	}
}