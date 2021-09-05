public class numberOfLIS {

	// 673. Number of Longest Increasing Subsequence

	public int findNumberOfLIS(int[] nums) {
		int n = nums.length;
		int[] dp = new int[n];
		int[] count = new int[n];

		int maxLen = 0, maxCount = 0;
		for (int i = 0; i < n; i++) {
			dp[i] = 1;
			count[i] = 1;
			for (int j = i - 1; j >= 0; j--) {
				if (nums[j] < nums[i]) {
					if (dp[i] < dp[j] + 1) {
						dp[i] = dp[j] + 1;
						count[i] = count[j];
					} else if (dp[i] == dp[j] + 1) {
						count[i] += count[j];
					}
				}
			}

			if (dp[i] > maxLen) {
				maxLen = dp[i];
				maxCount = count[i];
			} else if (maxLen == dp[i]) {
				maxCount += count[i];
			}
		}

		return maxCount;
	}
}