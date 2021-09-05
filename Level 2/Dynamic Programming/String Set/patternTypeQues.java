
import java.util.Arrays;
public class patternTypeQues {

	// 940. Distinct Subsequences II*********************************************************************

	// if char repeat dp[i] = 2*dp[i-1] - dp[char just before the prev occurence of this char]
	//ex for abab , we are at idx 3 b -> dp[i] = 2*dp[i-1] - dp[a at idx 0]
	public int distinctSubseqII(String s) {
		int n = s.length(), MOD = 1000000007;
		int[] dp = new int[n + 1];
		int[] lastIdx = new int[256];
		Arrays.fill(lastIdx, -1);

		dp[0] = 1; //empty string
		for (int i = 0; i < n; i++) {
			char ch = s.charAt(i);
			dp[i + 1] = (dp[i] * 2) % MOD;

			int idx = lastIdx[ch];
			if (idx != -1) {
				dp[i + 1] = (dp[i + 1] % MOD - dp[idx] % MOD) % MOD;
			}
			lastIdx[ch] = i;
		}

		dp[n]--;  //substract " "
		return dp[n] < 0 ? dp[n] + MOD : dp[n]; //fix -ve mod
	}

	// Using O(1) Space , T: O(N)



	// Number of subsequences of the form a^i b^j c^k*********************************************
	// https://www.geeksforgeeks.org/number-subsequences-form-ai-bj-ck/
	// Using O(1) Space , T: O(N)

	public static int count(String s) {
		int MOD = 1000000007;
		int emptyString = 1;
		int aCount = 0;
		int bCount = 0;
		int cCount = 0;

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == 'a') {
				aCount = ((2 * aCount) % MOD + emptyString % MOD) % MOD;
			} else if (ch == 'b') {
				bCount = ((2 * bCount) % MOD + aCount % MOD) % MOD;
			} else if (ch == 'c') {
				cCount = ((2 * cCount) % MOD + bCount % MOD) % MOD;
			}
		}

		return cCount;
	}


	// Count All Palindromic Subsequence in a given String*****************************************
	// https://www.geeksforgeeks.org/count-palindromic-subsequence-given-string/

	long countPS(String str) {
		int n = str.length();
		long[][] dp = new long[n][n];
		return countPS_memo(str, 0, n - 1, dp);
	}

	public static long countPS_memo(String str , int i , int j , long[][] dp) {

		if (i > j) {
			return dp[i][j] = 0;
		}

		if (i == j) {
			return dp[i][j] = 1;
		}

		if (dp[i][j] != 0) {
			return dp[i][j];
		}

		int MOD = 1000000007;
		long include_Both = countPS_memo(str, i + 1, j - 1, dp);
		long incl_First = countPS_memo(str, i, j - 1, dp);
		long incl_Last = countPS_memo(str, i + 1, j, dp);

		if (str.charAt(i) == str.charAt(j)) {
			dp[i][j] = (incl_Last + incl_First + 1) % MOD;
		} else {
			dp[i][j] = (incl_Last + incl_First - include_Both + MOD) % MOD;
		}

		return dp[i][j];
	}

	//Tabulation
	public static long countPS_Tab(String s , int SI , int EI , long[][] dp) {
		int n = s.length();
		for (int gap = 0; gap < n; gap++) {
			for (int si = 0, ei = gap; ei < n; si++, ei++) {
				if (si == ei) {
					dp[si][ei] = 1;
					continue;
				}

				if (s.charAt(si) == s.charAt(ei)) {
					dp[si][ei] = dp[si][ei - 1] + dp[si + 1][ei] + 1;
				} else {
					dp[si][si] = dp[si][ei - 1] + dp[si + 1][ei] - dp[si + 1][ei - 1];
				}
			}
		}

		return dp[SI][EI];
	}

	public static void main(String[] args) throws Exception {
		System.out.println(count("abbc"));
	}
}