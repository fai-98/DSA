public class largestDivisibleSubset {

	// 368. Largest Divisible Subset
	public List < Integer > largestDivisibleSubset(int[] nums) {
		Arrays.sort(nums);
		int n = nums.length, maxLen = 1;
		List < Integer > res = new ArrayList < > ();
		int[] dp = new int[n];
		Arrays.fill(dp, 1);

		for (int i = 1; i < n; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (nums[i] % nums[j] == 0) {
					dp[i] = Math.max(dp[i], 1 + dp[j]);
				}
			}
			maxLen = Math.max(maxLen, dp[i]);
		}
		// System.out.println(Arrays.toString(dp));
		//reverse engg on dp - start from maxlen
		//where dp[i] = max -1 && condition satisfies , add to ans
		int len = 0, val = 0;
		boolean flag = false;
		for (int i = n - 1; i >= 0; i--) {
			if (dp[i] == maxLen && !flag) {
				res.add(nums[i]);
				len = maxLen - 1;
				val = nums[i];
				flag = true;
			} else if (dp[i] == len && val % nums[i] == 0) {
				val = nums[i];
				res.add(nums[i]);
				len--;
			}
		}
		Collections.reverse(res);
		return res;

		//Recursive reverse-engg ??

	}