public class interleaving_string {
	// Leetcode 97. Interleaving String


	//Memoization
	// k = always i+j
	public boolean interleave1(String s1, int i, String s2, int j, String s3, Boolean[][] dp) {
		//both strs consumed -> s3 is interleaving
		if (i == s1.length() && j == s2.length()) {
			return dp[i][j] = true;
		}

		if (dp[i][j] != null) {
			return dp[i][j];
		}

		// s1[i] == s3[k]
		boolean res = false;
		if (i < s1.length() && s1.charAt(i) == s3.charAt(i + j)) {
			res = res || interleave1(s1, i + 1, s2, j, s3, dp);
			if (res) return dp[i][j] = res;
		}

		// s2[j] == s3[k]
		if (j < s2.length() && s2.charAt(j) == s3.charAt(i + j)) {
			res = res || interleave1(s1, i, s2, j + 1, s3, dp);
			if (res) return dp[i][j] = res;
		}
		// s1[i] = s3[k] && s2[j] = s3[k] also handled above
		return dp[i][j] = res;
	}


	//Tabulation


	public static void main(String[] args) {
		String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
		System.out.println(interleave1(s1, 0, s2, 0, s3));
	}
}