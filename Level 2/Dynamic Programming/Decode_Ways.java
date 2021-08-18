import java.util.Arrays;

public class Decode_Ways {
    // 91. Decode Ways
    public int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, -1);
        // return memo(s, s.length(), dp);
        // return memo(s, 0, dp);
        return tab(s, 0, dp);
    }

    public int memo(String s, int n, int[] dp) {
        if (n == 0) {
            return dp[n] = 1;
        }

        if (dp[n - 1] != -1) {
            return dp[n];
        }

        int count = 0;
        if (s.charAt(n - 1) > '0') {
            count += memo(s, n - 1, dp);
        }

        if (n > 1) {
            int num = (s.charAt(n - 2) - '0') * 10 + (s.charAt(n - 1) - '0');
            if (num <= 26 && num >= 10) {
                count += memo(s, n - 2, dp);
            }
        }

        return dp[n] = count;
    }

    // using index
    public int memo2(String s, int idx, int[] dp) {
        if (idx == s.length()) {
            return dp[idx] = 1; // one ans found
        }

        if (dp[idx] != -1) {
            return dp[idx];
        }

        if (s.charAt(idx) == '0') {
            return dp[idx] = 0;
        }

        int count = 0;

        count += memo2(s, idx + 1, dp); // call for 1 digit

        if (idx < s.length() - 1) {
            int num = (s.charAt(idx) - '0') * 10 + (s.charAt(idx + 1) - '0');
            if (num <= 26) {
                count += memo2(s, idx + 2, dp); // call for 2 dig
            }
        }

        return dp[idx] = count;
    }

    public int tab(String s, int IDX, int[] dp) {
        for (int idx = s.length(); idx >= IDX; idx--) {
            if (idx == s.length()) {
                dp[idx] = 1; // one ans found
                continue;
            }

            if (s.charAt(idx) == '0') {
                dp[idx] = 0;
                continue;
            }

            int count = 0;

            count += dp[idx + 1]; // call for 1 digit

            if (idx < s.length() - 1) {
                int num = (s.charAt(idx) - '0') * 10 + (s.charAt(idx + 1) - '0');
                if (num <= 26) {
                    count += dp[idx + 2]; // call for 2 dig
                }
            }

            dp[idx] = count;
        }

        return dp[IDX];

    }

    // optimized using only 2 variables
    public int optimized(String s, int idx) {
        int n = s.length();
        int one = 1, two = 0, curr = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) != '0') { // 1 dif
                curr += one;
            }

            if (i < n - 1 && s.charAt(i) != '0' && Integer.parseInt(s.substring(i, i + 2)) <= 26) {
                curr += two;
            }

            two = one;
            one = curr;
            curr = 0;
        }

        return one;
    }

    // 639. Decode Ways II
    int M;

    public int numDecodings2(String s) {
        M = 1000000007;
        int n = s.length();
        long[] dp = new long[n + 1];
        Arrays.fill(dp, -1);
        return (int) numDecodings_memo(s, 0, dp);
    }

    public long numDecodings_memo(String s, int idx, long[] dp) {
        if (idx == s.length()) {
            return dp[idx] = 1;
        }

        if (dp[idx] != -1) {
            return dp[idx];
        }

        if (s.charAt(idx) == '0') {
            return dp[idx] = 0;
        }

        long count = 0;
        char curr = s.charAt(idx);
        if (curr == '*') {

            count = (count + 9 * numDecodings_memo(s, idx + 1, dp)) % M;

            if (idx < s.length() - 1) {
                char next = s.charAt(idx + 1);
                if (next == '*') {
                    count = (count + 15 * numDecodings_memo(s, idx + 2, dp)) % M;
                } else if (next >= '0' && next < '7') {
                    count = (count + 2 * numDecodings_memo(s, idx + 2, dp)) % M;
                } else {
                    count = (count + numDecodings_memo(s, idx + 2, dp)) % M;

                }
            }
        } else {

            count = (count + numDecodings_memo(s, idx + 1, dp)) % M;

            if (idx < s.length() - 1) {
                char next = s.charAt(idx + 1);
                if (next == '*') {
                    if (curr == '1') {
                        count = (count + 9 * numDecodings_memo(s, idx + 2, dp)) % M;
                    } else if (curr == '2') {
                        count = (count + 6 * numDecodings_memo(s, idx + 2, dp)) % M;
                    }
                } else {
                    int num = (s.charAt(idx) - '0') * 10 + (s.charAt(idx + 1) - '0');
                    if (num <= 26) {
                        count = (count + 1 * numDecodings_memo(s, idx + 2, dp)) % M;
                    }
                }
            }
        }

        return dp[idx] = count;
    }

    // ********************************************************************TAB*********************************

    public int numDecodings_tabu(String s) {
        M = 1000000007;
        int n = s.length();
        long[] dp = new long[n + 1];
        Arrays.fill(dp, -1);
        return (int) numDecodings_tab(s, 0, dp);
    }

    public long numDecodings_tab(String s, int IDX, long[] dp) {

        for (int idx = s.length(); idx >= 0; idx--) {
            if (idx == s.length()) {
                dp[idx] = 1;
                continue;
            }

            if (s.charAt(idx) == '0') {
                dp[idx] = 0;
                continue;
            }

            long count = 0;
            char curr = s.charAt(idx);
            if (curr == '*') {

                count = (count + 9 * dp[idx + 1]) % M;

                if (idx < s.length() - 1) {
                    char next = s.charAt(idx + 1);
                    if (next == '*') {
                        count = (count + 15 * dp[idx + 2]) % M;
                    } else if (next >= '0' && next < '7') {
                        count = (count + 2 * dp[idx + 2]) % M;
                    } else {
                        count = (count + dp[idx + 2]) % M;

                    }
                }
            } else {

                count = (count + dp[idx + 1]) % M;

                if (idx < s.length() - 1) {
                    char next = s.charAt(idx + 1);
                    if (next == '*') {
                        if (curr == '1') {
                            count = (count + 9 * dp[idx + 2]) % M;
                        } else if (curr == '2') {
                            count = (count + 6 * dp[idx + 2]) % M;
                        }
                    } else {
                        int num = (s.charAt(idx) - '0') * 10 + (s.charAt(idx + 1) - '0');
                        if (num <= 26) {
                            count = (count + 1 * dp[idx + 2]) % M;
                        }
                    }
                }
            }
            dp[idx] = count;
        }

        return dp[IDX];
    }

    // optimized

    // k incrementally , k subsets , friends pairing , mobile keypad , vvi ques ,
    // must reviese
    // combinations maths

}
