public class subset_sum_gfg {
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
}