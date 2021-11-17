class Solution {
	// 1031. Maximum Sum of Two Non-Overlapping Subarrays

	public int maxSumTwoNoOverlap(int[] nums, int l1, int l2) {
		int n = nums.length;
		int[] dp1 = new int[n];
		int[] dp2 = new int[n];
		int sum = 0;

		//L to R maxsum of size l1
		for (int i = 0; i < n; i++) {
			if (i < l1) {
				sum += nums[i];
				dp1[i] = sum;
			} else {
				sum += nums[i] - nums[i - l1]; //for maintain size of l1
				dp1[i] = Math.max(dp1[i - 1], sum);
			}
		}

		//R to L max sum of size l2
		sum = 0;
		for (int i = n - 1; i >= 0; i--) {
			if (i >= n - l2) {
				sum += nums[i];
				dp2[i] = sum;
			} else {
				sum += nums[i] - nums[i + l2];
				dp2[i] = Math.max(dp2[i + 1], sum);
			}
		}

		int res = 0;
		for (int i = l1 - 1; i < n - l2; i++) {
			res = Math.max(dp1[i] + dp2[i + 1], res);
		}

		//when l2 size is at left ans l1 size at right
		dp1 = new int[n];
		dp2 = new int[n];
		sum = 0;

		for (int i = 0; i < n; i++) {
			if (i < l2) {
				sum += nums[i];
				dp1[i] = sum;
			} else {
				sum += nums[i] - nums[i - l2]; //for maintain size of l1
				dp1[i] = Math.max(dp1[i - 1], sum);
			}
		}

		sum = 0;
		for (int i = n - 1; i >= 0; i--) {
			if (i >= n - l1) {
				sum += nums[i];
				dp2[i] = sum;
			} else {
				sum += nums[i] - nums[i + l1];
				dp2[i] = Math.max(dp2[i + 1], sum);
			}
		}

		for (int i = l2 - 1; i < n - l1; i++) {
			res = Math.max(dp1[i] + dp2[i + 1], res);
		}

		return res;
	}
}