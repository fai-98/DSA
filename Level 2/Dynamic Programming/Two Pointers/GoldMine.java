public class GoldMine {
    // https://www.geeksforgeeks.org/gold-mine-problem/
    // ******************************GOLDMINE************************************************************

    public static int maxGold(int n, int m, int grid[][]) {
        int[][] dir = {{ -1, 1}, {0, 1}, {1, 1}};
        int[][] dp = new int[n][m];
        for (int[] r : dp)
            Arrays.fill(r, -1);

        int max = -(int)1e9;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, memo(grid, i, 0, dir, dp, n, m));
        }
        return max;
    }

    public static int memo(int[][] grid, int sr , int sc , int[][] dir, int[][] dp, int n, int m) {
        if (sc == m - 1) {
            return dp[sr][sc] = grid[sr][sc];
        }

        if (dp[sr][sc] != -1)
            return dp[sr][sc];

        int profit = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && r < n && c < m ) {//if valid
                profit = Math.max(profit, memo(grid, r, c, dir, dp, n, m));
            }
        }
        return dp[sr][sc] = grid[sr][sc] + profit;
    }

    public static int tab(int grid[][], int n, int m) {
        int[][] dir = {{ -1, 1}, {0, 1}, {1, 1}};
        int[][] dp = new int[n][m];
        for (int[] r : dp)
            Arrays.fill(r, -1);

        //tab
        //col outer loop m row inner loop
        for (int sc = m - 1; sc >= 0; sc--) {
            for (int sr = 0; sr < n; sr++) {
                if (sc == m - 1) {
                    dp[sr][sc] = grid[sr][sc];
                    continue;
                }

                int maxGold = 0;
                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if (r >= 0 && r < n && c < m ) {//if valid
                        maxGold = Math.max(maxGold, dp[r][c]);
                    }
                }

                dp[sr][sc] = grid[sr][sc] + maxGold;
            }
        }

        public static void printPath(int[][] dp) {
            int n = dp.length , m = dp[0].length , R = -1, C = -1;

            int maxGold = 0;
            for (int r = 0, c = 0; r < n; r++) {
                if (dp[r][c] > maxGold) {
                    R = r;
                    C = c;
                }
            }

            int sr = R , sc = C , gold = dp[R][C];
            int[][] dir = {{ -1, 1}, {0, 1}, {1, 1}};

            while (c < m) {
                System.out.println(sr + " , " + sc);
                for (int d = 0; d < 3; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];
                    if (r >= 0 && r < n && c < m) {

                    }
                }
            }

        }


        int max = -(int) 1e9;
        for (int i = 0; i < n; i++) {
            max = Math.max(dp[i][0], max);
        }

        return max;
    }
}
