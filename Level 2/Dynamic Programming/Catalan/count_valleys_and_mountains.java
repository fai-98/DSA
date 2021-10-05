public class count_valleys_and_mountains {


	//number of downstrokes at any point will never exceed upstrokes
	public static int nth_catalan(int n) {
		int[] dp = new int[n + 2]; //catalan 0...n , n+2 size to avoid idx out of bounds
		dp[0] = 1;
		dp[1] = 1;

		for (int i = 2; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				dp[i] += dp[j] * dp[i - j - 1];
			}
		}

		return dp[n];
	}

	//same ques
	//Combinations of Balanced Parentheses Dynamic Programming
	// no of ) never exceed (
}