class Solution {
	public int mincostTickets(int[] days, int[] costs) {
		Set < Integer > set = new HashSet < > ();
		for (int day : days) set.add(day);
		Integer[] dp = new Integer[397];
		return minCost(set, costs, 1, dp);
	}


	//Similar to coin change - here the intervals are disjoint , but we take continuous length 365
	//only call when curr day = valid day(in arr) , else just skip by 1
	public int minCost(Set < Integer > set, int[] costs, int currDay, Integer[] dp) {
		if (currDay > 365) {
			return dp[currDay] = 0;
		}

		if (dp[currDay] != null) {
			return dp[currDay];
		}

		int myRes = (int) 1e9;
		if (set.contains(currDay)) {
			int one = costs[0] + minCost(set, costs, currDay + 1, dp);
			int seven = costs[1] + minCost(set, costs, currDay + 7, dp);
			int thirty = costs[2] + minCost(set, costs, currDay + 30, dp);

			myRes = Math.min(Math.min(myRes, one), Math.min(seven, thirty));
		} else {
			myRes = minCost(set, costs, currDay + 1, dp);
		}

		return dp[currDay] = myRes;
	}

	//Tabu
	public int minCost_tab(Set < Integer > set, int[] costs, int[] dp) {

		for (int i = 366; i >= 0; i--) {
			if (i == 366) {
				dp[i] = 0;
				continue;
			}

			if (set.contains(i)) {
				int f1 = costs[0] + dp[i + 1];
				int f2 = costs[1] + dp[Math.min(i + 7, 366)];
				int f3 = costs[2] + dp[Math.min(i + 30, 366)];

				dp[i] = Math.min(f1, Math.min(f2, f3));
			} else {
				dp[i] = dp[i + 1];
			}
		}

		return dp[1];
	}

	public int minCost_tab_2(Set < Integer > set, int[] costs, int[] dp) {

		for (int i = 1; i < 366; i++) {
			if (set.contains(i)) {
				dp[i] = Math.min(Math.min(costs[1] + dp[Math.max(i - 7, 0)], costs[2] + dp[Math.max(i - 30, 0)]), costs[0] + dp[i - 1]);
			} else dp[i] = dp[i - 1];
		}

		return dp[365];
	}


	//Space Optimiztion we only need previous 30 cells

}