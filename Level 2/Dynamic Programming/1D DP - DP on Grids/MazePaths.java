public class MazePaths {
    // ******************************MAZE-PATHS************************************************************/
    // 1 step allowed , h , v , d
    // Faith : add total paths from h , v , d to get our ans
    public static int MazePaths_memo(int sr, int sc, int dr, int dc, int[][] dp) {
        if (sr > dr || sc > dc) {
            return 0;
        }
        if (sr == dr && sc == dc) {
            return dp[sr][sc] = 1; // this indicates that i have found 1 path
        }

        if (dp[sr][sc] != 0) {
            return dp[sr][sc];
        }
        // h
        int hans = MazePaths_memo(sr, sc + 1, dr, dc, dp);
        // v
        int vans = MazePaths_memo(sr + 1, sc, dr, dc, dp);
        // d
        int dans = MazePaths_memo(sr + 1, sc + 1, dr, dc, dp);

        return dp[sr][sc] = hans + vans + dans;
    }

    // using dir method
    public static int MazePaths_memo(int sr, int sc, int dr, int dc, int[][] dir, int[][] dp) {

        if (sr == dr && sc == dc) {
            return dp[sr][sc] = 1; // this indicates that i have found 1 path
        }

        if (dp[sr][sc] != 0) {
            return dp[sr][sc];
        }

        int count = 0;
        for (int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];

            if (r >= 0 && c >= 0 && r <= dr && c <= dc) {
                count += MazePaths_memo(r, c, dr, dc, dir, dp);
            }
        }

        return dp[sr][sc] = count;
    }

    public static int MazePaths_tab(int SR, int SC, int dr, int dc, int[][] dir, int[][] dp) {
        for (int sr = dr; sr >= SR; sr--) {
            for (int sc = dc; sc >= SC; sc--) {
                if (sr == dr && sc == dc) {
                    dp[sr][sc] = 1;
                    continue; // base case
                }

                int count = 0;
                for (int[] d : dir) {
                    int r = sr + d[0];
                    int c = sc + d[1];

                    if (r <= dr && c <= dc) {
                        count += dp[r][c];
                    }
                }

            }
        }

        return dp[SR][SC];
    }

    // infinite step jump allowed
    public static int MazePathsJump_tab(int SR, int SC, int dr, int dc, int[][] dir, int[][] dp) {
        for (int sr = dr; sr >= SR; sr--) {
            for (int sc = dc; sc >= SC; sc--) {
                if (sr == dr && sc == dc) {
                    dp[sr][sc] = 1;
                    continue; // base case
                }

                int count = 0;
                for (int[] d : dir) {
                    int r = sr + d[0];
                    int c = sc + d[1];
                    while (r <= dr && c <= dc) { // for jumps
                        count += dp[r][c];
                        r += d[0];
                        c += d[1];
                    }

                }
                dp[sr][sc] = count;
            }
        }

        return dp[SR][SC];

    }

}
