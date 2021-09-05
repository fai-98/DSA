public class best_Team {

	// 1626. Best Team With No Conflicts
	public int bestTeamScore(int[] scores, int[] ages) {
		int n = scores.length, maxScore = 0;
		int[][] pair = new int[n][2]; //{age,score}
		for (int i = 0; i < n; i++) {
			pair[i][0] = ages[i];
			pair[i][1] = scores[i];
		}

		// sort on age , if age equal , sort on scores
		Arrays.sort(pair, (a, b) -> {
			return a[0] - b[0] != 0 ? a[0] - b[0] : a[1] - b[1];
		});

		int[] dp = new int[n];
		//max Sum inc SSQ , update dp on maxSum
		// LIS condition is score[j]<=score[i] , age aready less/equal , no violation
		for (int i = 0; i < n; i++) {
			dp[i] = pair[i][1];
			for (int j = i - 1; j >= 0; j--) {
				if (pair[j][1] <= pair[i][1]) {
					dp[i] = Math.max(dp[i], dp[j] + pair[i][1]);
				}
			}
			maxScore = Math.max(maxScore, dp[i]);
		}

		return maxScore;
	}

}