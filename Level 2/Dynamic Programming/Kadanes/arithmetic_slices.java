class Solution {
	// 413. Arithmetic Slices
	//O(n) Space
	public int numberOfArithmeticSlices(int[] nums) {
		int n = nums.length;
		int[] dp = new int[n];
		int sum = 0;

		for (int i = 2; i < n; i++) {
			if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
				dp[i] = 1 + dp[i - 1];
				sum += dp[i];
			}
		}
		// System.out.println(Arrays.toString(dp));
		return sum;
	}

	// O(1) Space
	public int numberOfArithmeticSlices(int[] nums) {
		int n = nums.length;
		int sum = 0 , count = 0;

		for (int i = 2; i < n; i++) {
			if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
				count++;
				sum += count;
			} else count = 0;
		}
		return sum;
	}
}

// if A[i-2], A[i-1], A[i] are arithmetic, then the number of arithmetic slices ending with A[i] (dp[i])
// equals to:
//      the number of arithmetic slices ending with A[i-1] (dp[i-1], all these arithmetic slices appending A[i] are also arithmetic)
//      +
//      A[i-2], A[i-1], A[i] (a brand new arithmetic slice)
// it is how dp[i] = dp[i-1] + 1 comes

// arr = [2, 5, 9, 12, 15, 18, 22, 26, 30, 34, 36, 38, 40, 41];

// dp =  [0, 0, 0, 0, 1, 2, 0, 1, 2, 3, 0, 1, 2, 0];

// ans = 12;