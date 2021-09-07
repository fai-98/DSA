public class palin_partition_ii {

    public int minCut(String s) {
        int n = s.length();
        boolean[][] isPal = new boolean[n][n];
        LPS(s, 0, n - 1, isPal); //for pal check query in O(1)
        int[] dp = new int[n];
        Arrays.fill(dp, -1);  //bcz o cuts can be part of ans
        // return min_Cuts(s, 0, isPal, dp);
        return min_Cuts_Tab(s, n - 1, isPal, dp);

    }
    //cut+1 , jaha s cut lgega uske aage ki str virtually pass hogi  , for recursion
    public int min_Cuts(String s, int idx, boolean[][] isPal, int[] dp) {
        if (isPal[idx][s.length() - 1]) {
            return dp[idx] = 0; //if whole string from idx is Pal , 0 cuts needed
        }

        if (dp[idx] != -1) {
            return dp[idx];
        }
        int minAns = (int) 1e9;
        for (int cut = idx; cut < s.length(); cut++) {
            if (isPal[idx][cut]) {
                minAns = Math.min(minAns, min_Cuts(s, cut + 1, isPal, dp) + 1); //why cut+1?
            }
        }
        return dp[idx] = minAns;
    }

    public int min_Cuts_Tab(String s, int IDX, boolean[][] isPal, int[] dp) {
        for (int idx = IDX - 1; idx >= 0; idx--) {
            if (isPal[idx][s.length() - 1]) {
                dp[idx] = 0; //if whole string from idx is Pal , 0 cuts needed
                continue;
            }

            int minAns = (int) 1e9;
            for (int cut = idx; cut < s.length(); cut++) {
                if (isPal[idx][cut]) {
                    minAns = Math.min(minAns, dp[cut + 1] + 1); //why cut+1?
                }
            }

            dp[idx] = minAns;
        }

        return dp[0];
    }

    public void LPS(String s, int SI, int EI, boolean[][] dp) {
        int n = s.length();
        for (int gap = 0; gap < n; gap++) {
            for (int si = 0, ei = gap; ei < n; si++, ei++) {
                if (gap == 0) {
                    dp[si][ei] = true;
                } else if (gap == 1 && s.charAt(si) == s.charAt(ei)) {
                    dp[si][ei] = true;
                } else {
                    if (s.charAt(si) == s.charAt(ei) && dp[si + 1][ei - 1] == true) {
                        dp[si][ei] = true;
                    }
                }
            }
        }
    }
}