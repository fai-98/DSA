public class Solution {

	// 121. Best Time to Buy and Sell Stock  k = 1

	// The idea is to buy when the stock is cheapest
	// and sell when it is the most expensive.
	public int maxProfit(int prices[]) {
		int minprice = Integer.MAX_VALUE;
		int maxprofit = 0;
		for (int i = 0; i < prices.length; i++) {
			if (prices[i] < minprice)
				minprice = prices[i];
			else if (prices[i] - minprice > maxprofit)
				maxprofit = prices[i] - minprice;
		}
		return maxprofit;
	}

	public int maxProfit(int[] prices) {
		//if we find min and max only we will stuck when max day is < min day
		int minPrice = (int) 1e9, maxProfit = 0;
		//we always subtract min from curr idx or idx after minIdx so sell after buy only..
		for (int i = 0; i < prices.length; i++) {
			minPrice = Math.min(minPrice, prices[i]);
			maxProfit = Math.max(maxProfit, prices[i] - minPrice);
		}
		return maxProfit;
	}


	// 123. Best Time to Buy and Sell Stock III    k = 2

	// states  buy1 -> sell1 -> buy2 -> sell2 (only 4 transactions)
	// this dependency is not circular (not infinite transactions )
	// so buy1 is not dependent on sell2
	public int maxProfit(int[] prices) {
		int firstBuy = -prices[0], firstSell = 0, secondBuy = -(int) 1e9, secondSell = 0;

		for (int i = 1; i < prices.length; i++) {
			firstBuy = Math.max(firstBuy, - prices[i]);
			firstSell = Math.max(firstSell, firstBuy + prices[i]);
			secondBuy = Math.max(secondBuy, firstSell - prices[i]);
			secondSell = Math.max(secondSell, secondBuy + prices[i]);
		}

		return secondSell;
	}

	// A2.
	public int maxProfit(int[] prices) {
		int n = prices.length;

		int[] sell = new int[n];
		int[] buy = new int[n];

		//R-L dp[i] = max profit if sold upto today (maybe sell today or any day prev)
		//max profit = prices[i] - min price in prev days
		int minPrice = prices[0];
		for (int i = 1; i < n; i++) {
			sell[i] = Math.max(sell[i - 1] , prices[i] - minPrice);
			minPrice = Math.min(prices[i] , minPrice);
		}

		//L-R dp[i] = max profit possible if i buy today on any later date
		//max pro = max price on days after d - prices[i]
		int maxPrice = prices[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			buy[i] = Math.max(buy[i + 1] , maxPrice - prices[i]);
			maxPrice = Math.max(prices[i] , maxPrice);
		}

		int res = 0;
		for (int i = 0; i < n; i++) {
			res = Math.max(res, buy[i] + sell[i]);
		}

		return res;
	}


	// 188. Best Time to Buy and Sell Stock IV k = arbitrary
	public int maxProfit(int k, int[] prices) {
		int n = prices.length;
		if (n <= 1 || k == 0) return 0;

		if (k >= n / 2) {
			return infinite_(prices);
		}

		int[] buy = new int[k];
		Arrays.fill(buy, -(int) 1e9);

		int[] sell = new int[k];

		// any i,j means max profit doing j transactions in i days
		// after n times loop , sell[k-1] contains max profit in k trans.. in n days
		for (int i = 0; i < n; i++) {
			//if i do only 1 transaction i.e buy here or sell here
			buy[0] = Math.max(buy[0], -prices[i]);
			sell[0] = Math.max(sell[0], buy[0] + prices[i]);

			// j <= i/2 bcz you cannot do more than floor(i/2) transactions in i days
			for (int j = 1; j < k && j <= i / 2; j++) {
				buy[j] = Math.max(buy[j], sell[j - 1] - prices[i]);
				sell[j] = Math.max(sell[j], buy[j] + prices[i]);
			}
		}

		return sell[k - 1];
	}

	// at max we can have floor(n/2) transactions , if k>=n/2 it is basically infinite
	// so add all upstrokes in ans , same as problem #122
	public static int infinite_(int[] prices) {
		int profit = 0;

		for (int i = 1; i < prices.length; i++) {
			if (prices[i] > prices[i - 1]) {
				profit += prices[i] - prices[i - 1];
			}
		}
		return profit;
	}


	//A2. O(1) space


	// 122. Best Time to Buy and Sell Stock II  k = infinity

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


	// 714. Best Time to Buy and Sell Stock with Transaction Fee (k = infinity)

	// A1.
	public int maxProfit_(int[] prices, int fee) {
		int cash = 0, hold = -prices[0];
		for (int i = 1; i < prices.length; i++) {
			cash = Math.max(cash, hold + prices[i] - fee);
			hold = Math.max(hold, cash - prices[i]);
		}
		return cash;
	}

	// A2.
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

	//new sell on prev buy ( max of new sell, prev sell)
	//new buy on prev sell (max of new buy , prev buy)
	//valid transact = no. buy = no. sell
	//in buy state we have 1 stock extra , which is loaned



	// 309. Best Time to Buy and Sell Stock with Cooldown k = infinity

	public int maxProfit(int[] prices) {

		int old_buy_state = -prices[0], old_sell_state = 0, old_cool_state = 0; //cool state is sell[i-2]

		for (int i = 1; i < prices.length; i++) {
			int new_buy_state = 0, new_sell_state = 0 , new_cool_state = 0;
			//buy only after cooldown
			new_buy_state = old_cool_state - prices[i];
			new_buy_state = Math.max(old_buy_state, new_buy_state);

			//sell after buy
			new_sell_state = old_buy_state + prices[i];
			new_sell_state = Math.max(new_sell_state, old_sell_state);

			//after sell , only option is to cooldown
			new_cool_state = old_sell_state;

			old_buy_state = new_buy_state;
			old_sell_state = new_sell_state;
			old_cool_state = new_cool_state;
		}

		return old_sell_state;
	}
}


