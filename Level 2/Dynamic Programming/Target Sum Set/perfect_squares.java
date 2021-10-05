class Solution {

	// 279. Perfect Squares

	public int numSquares(int n) {
		int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE - 1);
		dp[0] = 0;

		for (int i = 1; i * i <= n; i++) {
			for (int j = i * i; j < dp.length; j++) {
				dp[j] = Math.min(1 + dp[j - i * i], dp[j]);
			}
		}

		return dp[n];
	}
}