class Solution {
	public int findTargetSumWays(int[] nums, int target) {
		int n = nums.length;
		// int[][] dp = new int[n+1][target+1];
		// for (int[] row: dp) Arrays.fill(row, -1);
		return ways(nums, nums.length, 0, 0, target );
	}

	public int ways(int[] nums, int n, int idx, int sum, int tar ) {

		if (idx == n) {
			return tar == sum ? 1 : 0;
		}

		int add = ways(nums, n, idx + 1, sum + nums[idx], tar);
		int subtract = ways(nums, n, idx + 1, sum - nums[idx], tar);

		return add + subtract;
	}


	//Tabulation / Memo ?
}