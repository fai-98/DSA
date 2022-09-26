class Solution {

	// 714. Best Time to Buy and Sell Stock with Transaction Fee

	public int maxProfit(int[] prices, int fee) {
		int old_buy_state = -prices[0], old_sell_state = 0;

		for (int i = 1; i < prices.length; i++) {
			int new_buy_state = 0;
			int new_sell_state = 0;

			//new buy on prev sell (max of new buy , prev buy)
			new_buy_state = old_sell_state - prices[i];
			new_buy_state = Math.max(new_buy_state, old_buy_state);

			//new sell on prev buy ( max of new sell, prev sell) when sell fee also applied
			new_sell_state = old_buy_state + prices[i] - fee;
			new_sell_state = Math.max(old_sell_state, new_sell_state);

			old_sell_state = new_sell_state;
			old_buy_state = new_buy_state;
		}

		return old_sell_state;
	}

	public int maxProfit_(int[] prices, int fee) {
		int cash = 0, hold = -prices[0];
		for (int i = 1; i < prices.length; i++) {
			cash = Math.max(cash, hold + prices[i] - fee);
			hold = Math.max(hold, cash - prices[i]);
		}
		return cash;
	}
}

//new sell on prev buy ( max of new sell, prev sell)
//new buy on prev sell (max of new buy , prev buy)
//valid transact = no. buy = no. sell
//in buy state we have 1 stock extra , which is loaned