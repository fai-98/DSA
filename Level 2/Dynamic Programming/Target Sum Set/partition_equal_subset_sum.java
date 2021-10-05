class Solution {

	// 416. Partition Equal Subset Sum

	public boolean canPartition(int[] nums) {
		int sum = 0;
		for (int i = 0 ; i < nums.length ; i++) {
			sum += nums[i];
		}
		if (sum % 2 != 0)return false;
		else return isPossible(nums, sum / 2);
	}

	public static boolean isPossible(int[] arr, int tar) {
		boolean dp[][] = new boolean[arr.length + 1][tar + 1];

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				if (i == 0 && j == 0) dp[i][j] = true;
				else if (j == 0) dp[i][j] = true;
				else if (i == 0) dp[i][j] = false;
				else {
					if (dp[i - 1][j] == true) dp[i][j] = true;
					else if (arr[i - 1] <= j) {
						dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
					}
				}
			}
		}
		return dp[dp.length - 1][dp[0].length - 1];
	}
}