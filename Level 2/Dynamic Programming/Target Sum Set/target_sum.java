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


	// Memoization
	public int findTargetSumWays(int[] nums, int target) {
		int n = nums.length;
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		//range possible -INF..-Sum .......+Sum..+INF = +Sum-(-Sum)+1 = 2*Sum +1
		//we need to shift values by adding maximum possible val
		// idx = currsum + sum; , if curr = -sum , idx = -sum+sum = 0

		Integer[][] dp = new Integer[n + 1][2 * sum + 1];

		return ways(nums, nums.length, 0, 0, target, sum, dp);
	}

	public int ways(int[] nums, int n, int idx, int sum, int tar, int shift, Integer[][] dp) {

		if (idx == n) {
			return dp[idx][sum + shift] = tar == sum ? 1 : 0;
		}

		if (dp[idx][sum + shift] != null) {
			return dp[idx][sum + shift];
		}

		int add = ways(nums, n, idx + 1, sum + nums[idx], tar, shift, dp);
		int subtract = ways(nums, n, idx + 1, sum - nums[idx], tar, shift, dp);

		return dp[idx][sum + shift] = add + subtract;
	}

	//A3. Based on derivation / subset sum given difference

	// The original problem statement is equivalent to:
	// Find a subset of nums that need to be positive, and the rest of them negative,
	// such that the sum is equal to target

	// Fails [0,0,0,0,1]
	public int findTargetSumWays(int[] nums, int target) {
		int sum = 0, n = nums.length;
		for (int i = 0; i < n; i++) {
			sum += nums[i];
		}
		if (sum < Math.abs(target) || (sum + target) % 2 == 1) return 0;
		// S(P)*2 = target + sum(arr) , cant be odd sum+tar
		int new_tar = (sum + target) / 2;

		int[][] dp = new int[n + 1][new_tar + 1];
		return subset_sum(nums, dp, new_tar);
	}

	//if tar = 0 , it'll also gives ans , ex: [0,0,0,0,1]
	//we don't use continue when j == 0 , bcz we not only need true/false but also count
	//so start your calc from dp[1][0] i.e 1st row col = 0 means when target sum = 0

	public int subset_sum(int[] arr, int[][] dp, int tar) {
		int n = arr.length;
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= tar; j++) {
				if (i == 0 && j == 0) {
					dp[i][j] = 1;
				} else if (i == 0) { //elements = 0 , sum > 0 not possible
					dp[i][j] = 0;
				} else {
					if (j - arr[i - 1] >= 0) { //selection possible
						dp[i][j] = dp[i - 1][j] + dp[i - 1][j - arr[i - 1]];
					} else {
						dp[i][j] = dp[i - 1][j];
					}
				}
			}
		}

		return dp[n][tar];
	}
}