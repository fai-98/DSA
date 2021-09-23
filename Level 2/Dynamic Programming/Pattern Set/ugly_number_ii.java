class Solution {

	// Leetcode 264. Ugly Number II
	public int nthUglyNumber(int n) {
		int[] dp = new int[n + 1];
		dp[1] = 1;

		int p2 = 1;
		int p3 = 1;
		int p5 = 1;

		//1st is 1 , just next greater ugly num can be min of (num*2 or 5 or 3)
		for (int i = 2; i <= n; i++) {
			int f1 = 2 * dp[p2];
			int f2 = 3 * dp[p3];
			int f3 = 5 * dp[p5];

			int nextNum = Math.min(f1, Math.min(f2, f3));
			dp[i] = nextNum;

			if (nextNum == f1) { // it's mul by 2 , so ptr at 2++;
				p2++;
			}
			if (nextNum == f2) {
				p3++;
			}
			if (nextNum == f3) {
				p5++;
			}
		}

		return dp[n];
	}
}