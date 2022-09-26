class Solution {

	// 122. Best Time to Buy and Sell Stock II

	// State 1.
	// Don't do anything = lastBuy or lastSold
	// (because you're keeping there is no change in the amount of money you have)
	// State 2.
	// Sell them for a profit = lastBuy + current
	// (because you're selling the amount of money you've increases)
	// State 3.
	// Buy new stock = lastSold - current
	// (you need money to buy stock so you sell your profit to buy new stock)

	// T: O(N) , S: O(1)
	public int maxProfit(int[] prices) {  //Using States
		int old_buy_state = -prices[0], old_sell_state = 0;

		for (int i = 1; i < prices.length; i++) {
			int new_buy_state = 0;
			int new_sell_state = 0;

			//new buy on prev sell (max of new buy , prev buy)
			new_buy_state = old_sell_state - prices[i];
			new_buy_state = Math.max(new_buy_state, old_buy_state);

			//new sell on prev buy ( max of new sell, prev sell)
			new_sell_state = old_buy_state + prices[i];
			new_sell_state = Math.max(old_sell_state, new_sell_state);

			old_sell_state = new_sell_state;
			old_buy_state = new_buy_state;
		}

		return old_sell_state;
	}

	public int maxProfit(int[] prices) {

		int profit = 0;

		for (int i = 1; i < prices.length; i++) {
			if (prices[i] > prices[i - 1]) {
				profit += prices[i] - prices[i - 1];
			}
		}
		return profit;
	}

	//buy all the upstrokes
	public int maxProfit(int[] prices) {

		int buy = 0;  //pointers
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
		return profit;
	}

	// dp solution
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