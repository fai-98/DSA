class Solution {


	// 1039. Minimum Score Triangulation of Polygon
	public int minScoreTriangulation(int[] values) {
		int n = values.length;
		int[][] dp = new int[n][n];
		for (int[] r : dp) Arrays.fill(r, -1);
		return min_score(values, 0, n - 1, dp);
	}


	// consider si....ei , as base , cut n-2 times from si<cut<ei

	public int min_score(int[] vtx, int si, int ei, int[][] dp) {
		if (ei - si < 2) {
			return dp[si][ei] = 0;
		}

		if (dp[si][ei] != -1) {
			return dp[si][ei];
		}

		int myRes = (int) 1e9;
		for (int cut = si + 1; cut < ei; cut++) {

			int lres = min_score(vtx, si, cut, dp);
			int rres = min_score(vtx, cut, ei, dp);

			int rec_res = lres + (vtx[si] * vtx[cut] * vtx[ei]) + rres;
			myRes = Math.min(myRes, rec_res);
		}

		return dp[si][ei] = myRes;
	}
}