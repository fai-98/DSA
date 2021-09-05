public class max_Length_Of_Pair_Chain {
	// 646. Maximum Length of Pair Chain
	public int findLongestChain(int[][] pairs) {
		Arrays.sort(pairs, (a, b) -> {
			return a[0] - b[0];
		});

		int n = pairs.length, maxLen = 1;
		int[] dp = new int[n];
		Arrays.fill(dp, 1);

		for (int i = 1; i < n; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (pairs[j][1] < pairs[i][0]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			maxLen = Math.max(maxLen, dp[i]);
		}

		return maxLen;
	}
}