public class optimal_strategy_for_a_game {

	// https://www.geeksforgeeks.org/optimal-strategy-for-a-game-dp-31/

	public static void optimalStrategy(int[] arr) {
		int n = arr.length;
		int[][] dp = new int[n][n];
		int ans = fun(arr, 0, arr.length - 1, dp);
		System.out.println(ans);

	}

	public static int fun(int[] arr , int i, int j, int[][] dp) {
		if (i == j) {
			return dp[i][j] = arr[i];
		}

		if (i + 1 == j) {
			return dp[i][j] = Math.max(arr[i], arr[j]);
		}

		if (dp[i][j] != 0) {
			return dp[i][j];
		}

		int f1 = arr[i] + Math.min(fun(arr, i + 2, j, dp), fun(arr, i + 1, j - 1, dp));
		int f2 = arr[j] + Math.min(fun(arr, i + 1, j - 1, dp), fun(arr, i, j - 2, dp));
		int ans = Math.max(f1, f2);

		return dp[i][j] = ans;
	}

	//Tabulation
	public static int opt_tab(int[] arr) {
		int n = arr.length;
		int[][] dp = new int[n][n];

		for (int gap = 0; gap < n; gap++) {
			for (int si = 0, ei = gap; ei < n; si++, ei++) {
				if (gap == 0) {
					dp[si][ei] = arr[si];
				} else if (gap == 1) {
					dp[si][ei] = Math.max(arr[si], arr[ei]);
				} else {
					dp[si][ei] = Math.max(arr[si] + Math.min(dp[si + 2][ei], dp[si + 1][ei - 1]),
					                      arr[ei] + Math.min(dp[si + 1][ei - 1], dp[si][ei - 2]));
				}
			}
		}

		return dp[0][n - 1];
	}


	// f1- player choose i, f2 - player choose j
	//opponent will alaways play optimally and he will make the choice which will lead to us
	//getting the minimum number of amount

	//Maximize the min
	//when you choose - choose the best (max)
	//when opponent chooses - expect the worst (min)

	// if p1 choose i , p2 choose i+1 or jth
	// p2 choose i+1 , nxt call i+2,j , p2 choose jth nxt call i+1,j-1
	// min of (call1 , call2)
}