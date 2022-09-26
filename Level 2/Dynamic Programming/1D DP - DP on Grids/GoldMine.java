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

    // https://www.codingninjas.com/codestudio/problems/ninja-s-training_3621003?leftPanelTab=1
    //================================== NINJAS TRAINING ======================================


    // dp[day][t2] = i have to perform task t on this day , what is the max profit i can make
    // if task t , prev day task can only be t2 or t3
    public static int ninjaTraining(int n, int arr[][]) {
        int[][] dp = new int[n][3];
        dp[0][0] = arr[0][0];
        dp[0][1] = arr[0][1];
        dp[0][2] = arr[0][2];

        for (int i = 1; i < n; i++) {
            dp[i][0] = arr[i][0] + Math.max(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = arr[i][1] + Math.max(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = arr[i][2] + Math.max(dp[i - 1][1], dp[i - 1][0]);
        }

        int ans = Math.max(dp[n - 1][0], Math.max(dp[n - 1][1], dp[n - 1][2]));
        return ans;
    }
}
