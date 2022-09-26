class Solution {

	// ==============================INCLUDE EXCLUDE PRINCIPLE=============================

	// 198. House Robber
	public int rob(int[] nums) {
		if (nums.length == 1) {
			return nums[0];
		}
		int include = nums[0];
		int exclude = 0;

		for (int i = 1; i < nums.length; i++) {
			int temp = exclude + nums[i];
			exclude = Math.max(include, exclude);
			include = temp;
		}

		return Math.max(include, exclude);
	}

	//st from 0 idx
	public static int maxSumR(int[] nums, int idx, int[] dp) {
		if (idx >= nums.length) return 0;
		if (dp[idx] != 0) return dp[idx];
		int exclude = 0 + maxSumR(nums, idx + 1, dp);
		int include = nums[idx] + maxSumR(nums, idx + 2, dp);
		int ans = Math.max(exclude, include);
		dp[idx] = ans;
		return ans;
	}

	//last idx of array is passed
	public static int maxSumRecursive(int[] nums, int idx) {
		if (idx == 0) return nums[idx];
		if (idx == 1) return Math.max(nums[0], nums[1]);
		return Math.max(maxSumRecursive(nums, idx - 2) + nums[idx],
		                maxSumRecursive(nums, idx - 1));
	}

	public static int maxSum(int[] nums) {
		int n = nums.length;
		if (n == 1) return nums[0];
		int include = 0, exclude = 0;

		for (int i = 0; i < n; i++) {
			int temp = Math.max(include + nums[i], exclude);
			include = exclude; //jo include hua is bar use nxt me exclude
			exclude = temp;
		}

		return exclude;
	}

	public static int maxSumTab(int[] arr, int n) {
		int dp[] = new int[n + 1];
		dp[0] = 0;
		dp[1] = Math.max(0, arr[0]);

		for (int i = 2; i < dp.length; i++) {
			dp[i] = Math.max(dp[i - 1], arr[i - 1] + dp[i - 2]);
		}

		return dp[dp.length - 1];
	}

	// 213. House Robber II
	public int rob(int[] nums) {
		if (nums.length == 1)return nums[0];
		return Math.max(robHouse(nums, 0, nums.length - 2),
		                robHouse(nums, 1, nums.length - 1));
	}

	public int robHouse(int[] nums, int st, int end) {
		int inc = 0, exc = 0;
		for (int i = st; i <= end; i++) {
			int temp = Math.max(inc + nums[i], exc);
			inc = exc; //jo include hua h wo nxt time exclude krna hai
			exc = temp;
		}

		return exc;
	}



	public static void main(String[] args) throws Exception {

		int arr[] = new int[] {
		    5,
		    5,
		    10,
		    100,
		    10,
		    5
		};
		int n = arr.length;
		int[] dp = new int[n];
		//System.out.println(maxSum(arr));
		System.out.println(maxSumTab(arr, n));
		//System.out.println(maxSumR(arr,0,dp));
		System.out.println(maxSumRecursive(arr, n - 1));
	}

}

//variations
// house robber 1
// house robber 2
// paint house
// paint house 2
// count binary strings

//fibonacci variations
// tiling m*1
// tiling 2*1
// friends pairing
// partition into subsets
// floor tiling 2 - domino tromi
// arrange buildings