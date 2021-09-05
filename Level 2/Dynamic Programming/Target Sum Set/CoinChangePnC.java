import java.util.Arrays;

public class CoinChangePnC {
	// Combination Recursive
	public static int coinChange(int S , int n  , int[] coin) {

		if (S == 0)return 1;
		if (n == 0)return 0;
		if (coin[n - 1] > S)return coinChange(S , n - 1 ,  coin);
		else return coinChange(S - coin[n - 1] , n, coin) + coinChange(S , n - 1 , coin);
	}

	//Combination Memo 2d
	public static int combination_memo(int[] arr, int tar, int n, int[][] dp) {
		if (tar == 0) {
			return dp[n][tar] = 1;
		}

		if (dp[n][tar] != -1)
			return dp[n][tar];
		int count = 0;
		for (int i = n - 1; i >= 0; i--) {
			if (tar - arr[i] >= 0) {
				count += combination_memo(arr, tar - arr[i], i + 1, dp);
			}
		}

		return dp[n][tar] = count;
	}

	//Comb 1d
	public static int change_Tab(int amount, int[] coins) {
		int dp[] = new int[amount + 1];

		dp[0] = 1;
		for (int i = 0; i < coins.length; i++) {
			for (int j = coins[i] ; j <= amount; j++) {
				dp[j] += dp[j - coins[i]];
			}
		}

		return dp[amount];

	}

	//Permutation Recursive
	public static int coinChangeP(int S , int n  , int[] coin) {

		if (S == 0)return 1;
		if (n == 0)return 0;
		int ans = 0;
		for (int i = 0; i < coin.length; i++) {
			if (S - coin[i] >= 0) {
				ans += coinChangeP(S - coin[i], n, coin);
			}
		}

		return ans;
	}

	//Permutations 1d
	public static int permute(int[] coins , int S) {
		int[] dp = new int[S + 1];
		dp[0] = 1;

		for (int i = 1; i < dp.length; i++) {
			for (int j = 0; j < coins.length; j++) {
				if (i - coins[j] >= 0) {
					dp[i] += dp[i - coins[j]];
				}
			}
		}

		return dp[S];
	}

	//322. Coin Change - min no. of coins
	public int coinChange(int[] coins, int amount) {
		int n = amount;
		int[] dp = new int[n + 1];
		Arrays.fill(dp, (int) 1e9);
		dp[0] = 0;

		for (int i = 0; i < coins.length; i++) {
			for (int j = coins[i]; j <= amount; j++) {
				if (j - coins[i] >= 0) {
					dp[j] = Math.min(dp[j], 1 + dp[j - coins[i]]);
				}
			}
		}

		return dp[n] == (int) 1e9 ? -1 : dp[n];
	}

	// 518. Coin Change 2 - Combinations
	public int change_Com(int amount, int[] coins) {
		int[] dp = new int[amount + 1];
		dp[0] = 1;

		for (int i = 0; i < coins.length; i++) {
			for (int j = coins[i]; j <= amount; j++) {
				if (j - coins[i] >= 0) {
					dp[j] += dp[j - coins[i]];
				}
			}
		}

		return dp[amount];
	}

	// Find number of solutions of a linear equation of n variables - GFG
	// https://www.geeksforgeeks.org/find-number-of-solutions-of-a-linear-equation-of-n-variables/

	//Subset Sum - DP 25
	public static Boolean targetSet() {
		int set[] = { 3, 34, 4, 12, 5, 2 };
		int sum = 9;

		return isPos(set, set.length, sum);
	}

	public static boolean isPos(int[] arr , int n , int tar) {

		if (tar == 0 || n == 0) {
			return tar == 0;
		}

		if (tar - arr[n - 1] >= 0) {
			return isPos(arr, n - 1, tar - arr[n - 1]) || isPos(arr, n - 1, tar);
		} else {
			return isPos(arr, n - 1, tar);
		}
	}

	//Tabulation - if ans == 0 false , else true
	public static int subsetSum_Tab(int[] arr , int N , int[][] dp , int Tar) {
		for (int n = 0; n <= N; n++) {
			for (int tar = 0; tar <= Tar; tar++) {
				if (tar == 0) {
					dp[n][tar] = 1;
					continue;
				} else if (n == 0) {
					dp[n][tar] = 0;
					continue;
				}

				if (tar - arr[n - 1] >= 0) {
					dp[n][tar] = dp[n - 1][tar - arr[n - 1]] + dp[n - 1][tar];
				} else {
					dp[n][tar] = dp[n - 1][tar];
				}
			}
		}

		return dp[N][Tar];
	}

	// 0/1 KnapSack
	// dp[n+1][W+1]
	public static int knapSack(int[] wt , int[] val , int N , int W , int[][] dp) {
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= W; j++) {
				if (i == 0 || j == 0) {  //base case
					dp[i][j] = 0;
					continue;
				}

				if (j - wt[i - 1] >= 0) {
					dp[i][j] = Math.max(dp[i - 1][j] , val[i - 1] + dp[i - 1][j - wt[i - 1]]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}

		return dp[N][W];
	}

	//unbounded knapSack
	public static int unbounded_knapSack(int[] wt , int[] val , int N , int W , int[][] dp) {
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= W; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = 0;
					continue;
				}

				if (j - wt[i - 1] >= 0) {
					dp[i][j] = Math.max(dp[i - 1][j] , val[i - 1] + dp[i][j - wt[i - 1]]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}

		return dp[N][W];
	}

	//unbounded knapSack - 1d (like coin change combinations ??)
	public static int unbounded_knapSack(int[] wt , int[] val , int N , int W , int[] dp) {
		dp[0];
		for (i = 1; i <= N; i++) {
			for (int j = wt[i - 1]; j <= W; j++) {
				if (j - wt[i - 1] >= 0) {
					dp[j] = Math.max(dp[j], dp[j - wt[i - 1]]) + val[i - 1];
				}
			}
		}

		return dp[W];
	}

	// 377. Combination Sum IV


	//target Sum - 2 methods

	public static void main(String[] args) {
		// int S = 10;
		// int S = 5;
		// // int coin[] = { 2, 3, 5, 7 };
		// int coin[] = { 1, 2, 5 };
		// int n = coin.length;
		// System.out.println(change_Tab(S,  coin));
		// System.out.println(permute(coin, S));

		System.out.println(targetSet());
	}
}