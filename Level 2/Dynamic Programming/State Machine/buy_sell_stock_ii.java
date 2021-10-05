class Solution {

	// 122. Best Time to Buy and Sell Stock II

	public int maxProfit(int[] prices) {

		int buy = 0;
		int sell = 0;
		int profit = 0;

		for (int i = 1; i < prices.length; i++) {
			if (prices[i] >= prices[i - 1]) {
				sell++;
			} else {
				profit += prices[sell] - prices[buy];
				buy = sell = i;
			}
		}
		profit += prices[sell] - prices[buy];
		return maxProfit_(prices);
	}

	public int maxProfit_(int[] prices) {
		int n = prices.length;
		int[] dp = new int[n];
		dp[0] = 0;

		int prev = prices[0];
		for (int i = 1; i < n; i++) {
			if (prices[i] > prev) {
				dp[i] += prices[i] - prev + dp[i - 1];
			} else {
				dp[i] = dp[i - 1];
			}
			prev = prices[i];
		}

		return dp[n - 1];
	}
}