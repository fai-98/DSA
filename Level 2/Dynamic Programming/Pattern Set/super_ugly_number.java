class Solution {

	// Leetcode 313. Super Ugly Number

	public int nthSuperUglyNumber(int n, int[] primes) {
		int len = primes.length;
		//initialize pointers
		int[] ptr = new int[len];
		Arrays.fill(ptr, 1);

		int[] dp = new int[n + 1];
		dp[1] = 1; //base case

		for (int j = 2; j <= n; j++) {

			double min = (int) 1e18;

			//Step 1. find min of all factors
			for (int i = 0; i < len; i++) {
				double factor = dp[ptr[i]] * primes[i];
				min = Math.min(min, factor);
			}

			//Step 2. make ans
			dp[j] = (int) min;

			//Step 3. increment pointers which led to this ans
			for (int i = 0; i < len; i++) {
				int factor = dp[ptr[i]] * primes[i];
				if (factor == min)
					ptr[i]++;
			}
		}

		return dp[n];
	}
}