import java.util.Arrays;
public class SCS {

    public String shortestCommonSupersequence(String str1, String str2) {
        int n = str1.length(), m = str2.length();
        int[][] dp = new int[n + 1][m + 1];
        int LCS_len = LCS(str1, n, str2, m, dp);
        // int SCS_len = n + m - LCS_len; //write common chars only once
        String lcs = getLCS(str1, n, str2, m, dp);
        String SCS = SCS(str1, str2, lcs);
        return SCS;
    }

    public int LCS(String s, int N, String p, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }

                if (s.charAt(n - 1) == p.charAt(m - 1)) {
                    dp[n][m] = 1 + dp[n - 1][m - 1];
                } else {
                    dp[n][m] = Math.max(dp[n - 1][m], dp[n][m - 1]);
                }
            }
        }
        return dp[N][M];
    }

    public String getLCS(String s, int n, String p, int m, int[][] dp) {
        StringBuilder sb = new StringBuilder();
        while (n > 0 && m > 0) {
            if (s.charAt(n - 1) == p.charAt(m - 1)) {
                sb.append(s.charAt(n - 1));
                n--;
                m--;
            } else if (dp[n - 1][m] > dp[n][m - 1]) {
                n--;
            } else {
                m--;
            }
        }
        return sb.reverse().toString();
    }

    public String SCS(String s, String p, String LCS) {
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;
        for (char ch : LCS.toCharArray()) {
            while (s.charAt(i) != ch) {  //reach till LCS char , till then add your chars
                sb.append(s.charAt(i));
                i++;
            }
            while (p.charAt(j) != ch) { //same from str2
                sb.append(p.charAt(j));
                j++;
            }
            sb.append(ch);  // when u reach LCS char /common char on both strs , add only once
            i++;            // and move 3 pointers , i++,j++ and for loop
            j++;
        }
        sb.append(s.substring(i) + p.substring(j));
        return sb.toString();
    }
}

