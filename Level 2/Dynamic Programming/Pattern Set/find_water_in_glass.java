public class find_water_in_glass {
	// https://www.geeksforgeeks.org/find-water-in-a-glass/

	public static double solution(int k, int r, int c) {
		double water = k , spare = 0;
		double[][] dp = new double[k][k];

		dp[0][0] = water;
		for (int i = 0; i <= r; i++) {
			for (int j = 0; j <= i; j++) {

				if (dp[i][j] > 1.0) {
					spare = dp[i][j] - 1.0;
					dp[i][j] = 1.0;
					dp[i + 1][j] += spare / 2.0; //left
					dp[i + 1][j + 1] += spare / 2.0; //right
				}

			}
		}

		return dp[r][c];
	}
}