public class fibonacci_variations {

	//Count Binary Strings
	public static int count(int n) {
		if (n == 0)return 0;
		if (n == 1)return 2;

		int zero = 1;
		int one = 1;

		for (int i = 2; i <= n; i++) {
			int temp = zero + one;
			zero = one;
			one = temp;
		}

		return zero + one;

	}

	// Arrange Buildings

	public static void arrange_buildings(int n) {
		long[] dp0 = new long[n + 1];
		long[] dp1 = new long[n + 1];

		dp0[1] = 1;
		dp1[1] = 1;

		for (int i = 2; i < dp1.length; i++) {
			dp0[i] = dp1[i - 1];
			dp1[i] = dp1[i - 1] + dp0[i - 1];

		}

		long ans = dp0[n] + dp1[n];

		System.out.println(ans * ans);
	}

	// Tiling With 2 * 1 Tiles

	public static void tiling(int n) {
		int[] DP = new int[n + 1];
		DP[0] = 1;
		DP[1] = 1;


		for (int i = 2; i < n + 1; i++) {
			DP[i] = DP[i - 1] + DP[i - 2];
		}

		int ans = DP[n];
		System.out.println(ans);
	}

	// Tiling With M * 1 Tiles

	public static void tiling(int n, int m) {
		int[] DP = new int[n + 1];
		DP[0] = 1;
		DP[1] = 1;


		for (int i = 2; i < n + 1; i++) {
			if (i < m) {
				DP[i] = DP[i - 1];
			} else {
				DP[i] = DP[i - 1] + DP[i - m];
			}
		}

		int ans = DP[n];
		System.out.println(ans);
	}

	// Friends Pairing
	public static void friends_pairing(int n) {

		int[] dp = new int[n + 1];
		dp[1] = 1;
		dp[2] = 2;

		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + (i - 1) * dp[i - 2];
		}

		System.out.println(dp[n]);
	}

	// 790. Domino and Tromino Tiling
	public int numTilings(int N) {
		int M = (int)1e9 + 7;
		int[] dp = new int[N + 1];
		dp[0] = dp[1] = 1;
		if (N >= 2)dp[2] = 2;

		for (int i = 3; i <= N; i++) {
			dp[i] = 2 * dp[i - 1] % M + dp[i - 3] % M;
			dp[i] %= M;
		}

		return dp[N];
	}
}