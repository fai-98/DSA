public class PnC {


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

	public static void main(String[] args) {
		// int S = 10;
		int S = 5;
		// int coin[] = { 2, 3, 5, 7 };
		int coin[] = { 1, 2 };
		int n = coin.length;
		System.out.println(coinChange(S, n,  coin));
		System.out.println(permute(coin, S));
	}
}