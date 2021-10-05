class Solution {
	public int superEggDrop(int k, int n) {
		return eggSolve(k, n);
		// return eggSolver(n, k);
	}


	//recursive
	public int eggSolver(int floor, int eggs) {
		if (floor == 1 || floor == 0) return floor;
		if (eggs == 1) return floor;

		int myRes = (int) 1e9;

		for (int f = 1; f <= floor; f++) {
			int brk = eggSolver(f - 1, eggs - 1);
			int not_brk = eggSolver(floor - f, eggs);

			int res = Math.max(brk, not_brk); //worst res
			myRes = Math.min(res, myRes);
		}

		return myRes + 1; // 1 for attempt
	}

	//Tabulation : TLE 8,1000
	//eggs,floor

	// O(KN^2) linear search of i=1...i=n every time
	public int eggSolve(int k, int n) {
		int[][] dp = new int[n + 1][k + 1]; //floors,eggs

		//base case 1 floor = 1 , attempts = 1
		for (int j = 1; j <= k; j++)
			dp[1][j] = 1;
		//base case 2 : egg=1 , worst attempts = n
		for (int i = 1; i <= n; i++)
			dp[i][1] = i;

		//from 2,2
		for (int i = 2; i <= n; i++) {
			for (int j = 2; j <= k; j++) {
				// min of max of all k
				dp[i][j] = (int) 1e9;
				//drom from every floor 1..i
				for (int f = 1; f <= i; f++) {
					int res = Math.max(dp[i - f][j - 1], dp[f - 1][j]) + 1;
					dp[i][j] = Math.min(dp[i][j], res);
				}
			}
		}
		return dp[n][k];
	}

	//Optimization
	public int eggSolve_(int k, int n) {
		int[][] dp = new int[n + 1][k + 1]; //floors,eggs

		//base case 1 floor = 1 , attempts = 1
		for (int j = 1; j <= k; j++)
			dp[1][j] = 1;
		//base case 2 : egg=1 , worst attempts = n
		for (int i = 1; i <= n; i++)
			dp[i][1] = i;

		//from 2,2
		for (int i = 2; i <= n; i++) {
			for (int j = 2; j <= k; j++) {
				// min of max of all k
				dp[i][j] = (int) 1e9;

				//drop with binary search
				int lo = 1, hi = i;
				while (lo <= hi) {
					int mid = lo + (hi - lo) / 2;
					int factor1 = dp[mid - 1][j - 1]; //break
					int factor2 = dp[i - mid][j]; //not break

					dp[i][j] = Math.min(dp[i][j], Math.max(factor1, factor2) + 1);

					if (factor1 == factor2) {
						break;
					} else if (factor1 < factor2) {
						lo = mid + 1;
					} else {
						hi = mid - 1;
					}
				}
			}
		}
		return dp[n][k];
	}
}