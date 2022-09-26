class Solution {

    // Leetcode 688. Knight Probability in Chessboard

    static int[][] dir = {{ -2, -1}, { -2, 1}, { -1, 2}, {1, 2}, {2, -1}, {2, 1}, { -1, -2}, {1, -2}};

    public double knightProbability(int n, int k, int row, int column) {

        double[][][] dp = new double[n][n][k + 1];
        for (double[][] arr : dp) {
            for (double[] r : arr) {
                Arrays.fill(r, -1);
            }
        }
        return probab_(n, row, column, k, dp);
    }

    public double probab_(int n, int sr, int sc, int k , double[][][] dp) {
        if (k == 0) {
            return dp[sr][sc][k] = 1;
        }

        if (dp[sr][sc][k] != -1) {
            return dp[sr][sc][k];
        }

        double prob = 0;
        for (int d = 0; d < 8; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && r < n && c >= 0 && c < n) {
                prob += 0.125 * probab_(n, r, c, k - 1, dp);
            }
        }

        return dp[sr][sc][k] = prob;
    }
}