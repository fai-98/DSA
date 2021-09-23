class Solution {

	// Leetcode 377. Combination Sum IV
	//same as coinchage permutation

	public int combinationSum4(int[] nums, int target) {
		int[] dp = new int[target + 1];
		dp[0] = 1;

		for (int tar = 0; tar <= target; tar++) {
			for (int i = 0; i < nums.length; i++) {
				int val = nums[i];
				if (val <= tar) {
					dp[tar] += dp[tar - val];
				}
			}
		}

		return dp[target];
	}
}