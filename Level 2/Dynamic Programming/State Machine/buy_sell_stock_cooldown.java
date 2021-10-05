class Solution {
	public int maxProfit(int[] prices) {

		int old_buy_state = -prices[0], old_sell_state = 0, old_cool_state = 0;

		for (int i = 1; i < prices.length; i++) {
			int new_buy_state = 0, new_sell_state = 0 , new_cool_state = 0;
			//buy only after cooldown
			new_buy_state = old_cool_state - prices[i];
			new_buy_state = Math.max(old_buy_state, new_buy_state);

			//sell after buy
			new_sell_state = old_buy_state + prices[i];
			new_sell_state = Math.max(new_sell_state, old_sell_state);

			//cooldown after sell
			new_cool_state = Math.max(old_sell_state, old_cool_state); //cooldown prev sell , or prev max cooldown

			old_buy_state = new_buy_state;
			old_sell_state = new_sell_state;
			old_cool_state = new_cool_state;
		}

		return old_sell_state;
	}
}