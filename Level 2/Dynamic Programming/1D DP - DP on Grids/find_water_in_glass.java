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
					dp[i][j] = 1.0;  //make it as one after removing excess
					dp[i + 1][j] += spare / 2.0; //left
					dp[i + 1][j + 1] += spare / 2.0; //right
				}

			}
		}

		return dp[r][c];
	}

	// 799. Champagne Tower
	public double champagneTower(int water, int row, int col) {
		//GFG Find water in glass
		// obs - every glass is responsible for its below 2 glasses
		// shift indexes for easy implementation
		// dp[i][j] and dp[i][j+1] depend on dp[i-1][j]

		//SIMULATE THE WHOLE PROCESS
		//in 100th row 5050 glass possible , contraint only 100,100

		double[][] dp = new double[102][102];  //excess water can also be there
		dp[0][0] = water;

		for (int i = 0; i <= row; i++) {
			for (int j = 0; j <= row; j++) { //in 1st row 2 glass , ..3..4 so on
				if (dp[i][j] > 1) {
					double spare = dp[i][j] - 1.0;
					dp[i][j] = 1.0; //update it's ans
					dp[i + 1][j] += spare / 2.0; //standing at ith row , we fill i+1 th row
					dp[i + 1][j + 1] += spare / 2.0;
				}
			}
		}

		return dp[row][col];
	}
}