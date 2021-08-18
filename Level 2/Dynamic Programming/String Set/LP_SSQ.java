import java.util.Arrays;
public class LP_SSQ {

    // length
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        return LPS_memo(s, 0, n - 1, dp);
    }

    //
    public int LPS_memo(String s, int st, int end, int[][] dp) {
        if (st == end) {
            return dp[st][end] = 1;
        }

        if (st > end) {
            return dp[st][end] = 0;
        }

        if (dp[st][end] != 0) {
            return dp[st][end];
        }
        if (s.charAt(st) == s.charAt(end)) {
            dp[st][end] = 2 + LPS_memo(s, st + 1, end - 1, dp);
        } else {
            dp[st][end] = Math.max(LPS_memo(s, st + 1, end, dp), LPS_memo(s, st, end - 1, dp));
        }

        return dp[st][end];
    }

    // tabulation - gap strategy (SI=0, EI=n-1)
    public int LPS_tab(String s, int SI, int EI, int[][] dp) {
        int n = s.length();
        for (int gap = 0; gap < n; gap++) {
            for (int st = 0, end = gap; end < n; st++, end++) {
                if (st == end) {
                    dp[st][end] = 1;
                    continue;
                }

                if (st > end) {
                    dp[st][end] = 0;
                    continue;
                }

                if (s.charAt(st) == s.charAt(end)) {
                    dp[st][end] = 2 + dp[st + 1][end - 1];
                } else {
                    dp[st][end] = Math.max(dp[st + 1][end], dp[st][end - 1]);
                }
            }
        }

        return dp[SI][EI];
    }

    // another one
    public static int LPSTab(String s, int n , int[][] dp) {

        for (int diag = 0; diag < n; diag++) {

            for (int i = 0, j = diag; j < n; j++, i++) {
                if (i == j)
                    dp[i][j] = 1;

                else if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }

        return dp[0][n - 1];
    }

    // Minimum number of deletions to make a string palindrome**************************************
    // https://www.geeksforgeeks.org/minimum-number-deletions-make-string-palindrome/

    // -- Min no. of deletions = Length of String - Length of longestPalindromeSubseq (LPSq);

    // 1312. Minimum Insertion Steps to Make a String Palindrome********************************
    // no of insertions = no. of deletions (exactly same ques)
    // -> we delete unpaired chars
    // -> or we can add same no. of chars to pair them up and make palindrome


    // Print LPS using reverse-engineering**********************************************************

    //does'nt work for odd length
    // public static void print_LP_Subseq(String s , int[][] dp) {
    //     int n = dp.length , m = dp[0].length;
    //     int si = 0 , ei = m - 1;
    //     StringBuilder sb = new StringBuilder();

    //     while (si <= ei) {
    //         if (s.charAt(si) == s.charAt(ei)) {
    //             sb.append(s.charAt(si));
    //             si = si + 1;
    //             ei = ei - 1;
    //         } else if (dp[si][ei - 1] > dp[si + 1][ei]) {
    //             ei = ei - 1;
    //         } else {
    //             si = si + 1;
    //         }
    //     }

    //     String a = sb.toString();
    //     String b = sb.reverse().toString();
    //     System.out.println(a + b);
    // }

    //Recursive
    public static String lpss_ReverseEngi(String s, int si, int ei, int[][] dp) {
        if (si >= ei) {
            return si == ei ? (s.charAt(si) + "") : "";
        }

        if (s.charAt(si) == s.charAt(ei))
            return s.charAt(si) + lpss_ReverseEngi(s, si + 1, ei - 1, dp) + s.charAt(si);
        else if (dp[si + 1][ei] > dp[si][ei - 1])
            return lpss_ReverseEngi(s, si + 1, ei, dp);
        else
            return lpss_ReverseEngi(s, si, ei - 1, dp);
    }


    //Longest Common Subsequence 1143*******************************************************
    public static int LCS_memo(String s , int n , String p , int m , int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = 0;
        }

        if (dp[n][m] != -1) {
            return dp[n][m];
        }

        if (s.charAt(n - 1) == p.charAt(m - 1)) {
            return dp[n][m] = 1 + LCS_memo(s, n - 1, p, m - 1, dp);
        } else {
            return dp[n][m] = Math.max(LCS_memo(s, n - 1, p, m, dp), LCS_memo(s, n, p, m - 1, dp));
        }
    }

    public static int LCS_Tab(String s , int N , String p , int M , int[][] dp) {
        for (int n = 0; n <= N; n++ ) {
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

    //using String DP , DP[n][m] = LCS string
    public static String LCS_String(String s, int N, String p, int M, String[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = "";
                    continue;
                }

                if (s.charAt(n - 1) == p.charAt(m - 1)) {
                    dp[n][m] =  dp[n - 1][m - 1] + s.charAt(n - 1) ;
                } else {
                    dp[n][m] = dp[n - 1][m].length() > dp[n][m - 1].length() ? dp[n - 1][m] : dp[n][m - 1];
                }
            }
        }
        return dp[N][M];
    }

    //Print LCS
    public static void print_LCS(String s, int n, String p, int m, int[][] dp) {
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

        System.out.println(sb.reverse().toString());
    }

    //Longest Palindromic Substring  gap strategy
    //gap = 0 - 1 len string
    //gap = 1 - 2 len string
    public static String LP_Substr(String s) {
        int n = s.length() , start = 0 , end = 0;
        boolean[][] dp = new boolean[n][n];

        for (int gap = 0; gap < n; gap++) {
            for (int si = 0, ei = gap; ei < n; ei++, si++) {
                if (gap == 0) {
                    dp[si][ei] = true; //1 len str i==j
                } else if (gap == 1 && s.charAt(si) == s.charAt(ei)) { // 2 len str
                    dp[si][ei] = true;
                } else { //if ei = ei , check for middle string pal or not
                    if (s.charAt(si) == s.charAt(ei) && dp[si + 1][ei - 1] == true) {
                        dp[si][ei] = true;
                    }
                }

                if (dp[si][ei] == true) {
                    start = si;
                    end = ei;
                }
            }
        }

        return s.substring(start, end + 1);
    }

    //Longest Common Substring - Recursive - memo  int[][] dp = new int[n+1][m+1];
    public static int LC_Substr(String s , int n , String p , int m , int len ) {
        if (n == 0 || m == 0) {
            return  len;
        }

        if (s.charAt(n - 1) == p.charAt(m - 1)) {
            len =  LC_Substr(s, n - 1, p, m - 1, len + 1 );
        } else
            len = Math.max(len, Math.max(LC_Substr(s, n - 1, p, m, 0), LC_Substr(s, n, p, m - 1, 0)));

        return len;
    }


    //Tabulation ?
    // if s[n-1] = p[m-1] - dp[n-1][m-1] + 1 , prev char equal or not
    // else start fresh dp[n][m] = 0;
    // take max at every point

    public static int LC_Substr_Tab(String s, int N , String p , int M) {
        int maxLen = 0;
        int[][] dp = new int[N + 1][M + 1];

        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }

                if (s.charAt(n - 1) == p.charAt(m - 1)) {
                    dp[n][m] = 1 + dp[n - 1][m - 1];
                } else {
                    dp[n][m] = 0;
                }

                maxLen = Math.max(maxLen, dp[n][m]);
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        String s = "ABCDGH";
        String p = "ACDGHR";
        // String s = "abcde";
        // String p = "ace";
        int n = s.length();
        int m = p.length();
        // int[][] dp = new int[n][n];
        // int[][] dp = new int[n + 1][m + 1];
        // String[][] dp = new String[n + 1][m + 1];
        // LPSTab(s, n, dp);
        // for (int[]row : dp)Arrays.fill(row, -1);
        System.out.println(LCS_String(s, n, p, m, dp));
        // print_LCS(s, n, p, m, dp);
        // System.out.println(LC_Substr(s, n, p, m, 0));
        // display2D(dp);
        // print_LP_Subseq(s, dp);
        // System.out.println(lpss_ReverseEngi(s, 0, n - 1, dp));
        // System.out.println(LP_Substr("forgeeksskeegfor"));

    }

    public static void display2D(int[][] arr) {
        for (int[] a : arr) {
            System.out.println(Arrays.toString(a));
        }

    }
}

//variations
// 15
// ->print LCS - Y
// ->longest common substring - Y
// ->shortest common supersequence - Y
// ->print SCS - Y
// ->longest palindromic subsequence - Y
// ->largest palindromic substring - Y
// ->longest repeating subsequence -Y
// ->sequence pattern matching - Y
// ->minimum insertion deletions a->b  -Y
// ->largest repeating subsequence - Y
// ->length of largest ssq which is substring ... -Y
// ->count how many times a appear as subsequence
// ->count of palindromic substring
// ->min # of deletion  -Y
// ->min # of insertions...  -Y