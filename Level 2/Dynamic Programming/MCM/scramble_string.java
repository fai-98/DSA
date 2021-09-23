public class scramble_string {

	// Leetcode 87. scramble String
	public boolean isscramble(String s1, String s2) {
		int n = s1.length(), m = s2.length();
		return scramble1(s1, s2 );
	}

	// using substring function
	public boolean scramble1(String s, String match) {
		if (s.equals(match)) {
			return true;
		}

		//cuts from si+1 , ei-1 (where ei = n-1);
		for (int cut = 1; cut < s.length(); cut++) {
			//L1L2 match R1R2
			boolean f1 = scramble1(s.substring(0, cut), match.substring(0, cut)) &&
			             scramble1(s.substring(cut), match.substring(cut));
			//L1R2 match R1L2
			boolean f2 = scramble1(s.substring(0, cut), match.substring(match.length() - cut)) &&
			             scramble1(s.substring(cut), match.substring(0, match.length() - cut));

			if (f1 || f2)
				return true;
		}

		return false;
	}

	// using 4 indexes
	public boolean scramble2(String s, String match, int si1 , int ei1 , int si2, int ei2) {
		if (s.substring(si1, ei1 + 1).equals(match.substring(si2, ei2 + 1))) {
			return true;
		}

		//cuts from si+1 , ei-1 (where ei = n-1);
		//here cuts are not the indexes but the length of the cut , so idx = si+cut(len),
		//using indexes results in wrong indexing of substr
		for (int cut = 0; cut < ei1 - si1; cut++) {
			//L1L2 match R1R2
			boolean f1 = scramble2(s, match, si1, si1 + cut, si2, si2 + cut)
			             && scramble2(s, match, si1 + cut + 1, ei1, si2 + cut + 1, ei2);

			//L1R2 match R1L2
			boolean f2 = scramble2(s, match, si1, si1 + cut, ei2 - cut, ei2)
			             && scramble2(s, match, si1 + cut + 1, ei1, si2, ei2 - cut - 1);

			if (f1 || f2)
				return true;
		}

		return false;
	}

	//Using 4D DP
	public boolean scramble3(String s, String match, int si1, int ei1, int si2, int ei2, Boolean[][][][] dp) {
		//base case
		if (s.substring(si1, ei1 + 1).equals(match.substring(si2, ei2 + 1))) {
			return dp[si1][ei1][si2][ei2] = true;
		}

		//lookup
		if (dp[si1][ei1][si2][ei2] != null) {
			return dp[si1][ei1][si2][ei2];
		}

		//cuts from si+1 , ei-1 (where ei = n-1);
		//here cuts are not the indexes but the length of the cut , so idx = si+cut(len),
		//using indexes results in wrong indexing of substr
		for (int cut = 0; cut < ei1 - si1; cut++) {
			//L1L2 match R1R2
			boolean f1 = scramble3(s, match, si1, si1 + cut, si2, si2 + cut, dp) &&
			             scramble3(s, match, si1 + cut + 1, ei1, si2 + cut + 1, ei2, dp);

			//L1R2 match R1L2
			boolean f2 = scramble3(s, match, si1, si1 + cut, ei2 - cut, ei2, dp) &&
			             scramble3(s, match, si1 + cut + 1, ei1, si2, ei2 - cut - 1, dp);

			if (f1 || f2)
				return dp[si1][ei1][si2][ei2] = true;
		}

		return dp[si1][ei1][si2][ei2] = false;
	}

	//Using 3d DP
	//we always pass same len strings , so len is known , instead if ei use ei = i+len
	public boolean scramble4(String s, String match, int si1 , int si2 , int len) {
		if (s.substring(si1, si1 + len).equals(match.substring(si2, si2 + len))) {
			return true;
		}

		//cuts from si+1 , ei-1 (where ei = n-1);
		//here cuts are not the indexes but the length of the cut , so idx = si+cut(len),
		//using indexes results in wrong indexing of substr
		for (int cut = 1; cut < len; cut++) {
			//L1L2 match R1R2
			boolean f1 = scramble4(s, match, si1, si2, cut)
			             && scramble4(s, match, si1 + cut, si2 + cut , len - cut);

			//L1R2 match R1L2
			boolean f2 = scramble4(s, match, si1, si2 + len - cut, cut)
			             && scramble4(s, match, si1 + cut, si2, len - cut);

			if (f1 || f2)
				return true;
		}

		return false;
	}


	//
	public boolean scramble5(String s, String match, int si1, int si2, int len, Boolean[][][] dp) {
		if (s.substring(si1, si1 + len).equals(match.substring(si2, si2 + len))) {
			return dp[si1][si2][len] = true;
		}

		if (dp[si1][si2][len] != null) {
			return dp[si1][si2][len];
		}

		for (int cut = 1; cut < len; cut++) {
			//L1L2 match R1R2
			boolean f1 = scramble5(s, match, si1, si2, cut, dp) &&
			             scramble5(s, match, si1 + cut, si2 + cut, len - cut, dp);

			//L1R2 match R1L2
			boolean f2 = scramble5(s, match, si1, si2 + len - cut, cut, dp) &&
			             scramble5(s, match, si1 + cut, si2, len - cut, dp);

			if (f1 || f2)
				return dp[si1][si2][len] = true;
		}

		return dp[si1][si2][len] = false;
	}


	//3d Tabulation
	// public boolean scramble5(String s, String match, int SI1, int SI2, int len, Boolean[][][] dp) {

	// 	for (int gap = 0; gap < len; gap++) {
	// 		for (int si = 0, ei = gap; ei < len; ei++, si++) {
	// 			if (s.substring(si1, si1 + len).equals(match.substring(si2, si2 + len))) {
	// 				dp[si1][si2][len] = true;
	// 				continue;
	// 			}

	// 			for (int cut = 1; cut < len; cut++) {
	// 				//L1L2 match R1R2
	// 				boolean f1 = dp[si1][si2][cut] && dp[si1 + cut][si2 + cut][len - cut];
	// 				//L1R2 match R1L2
	// 				boolean f2 = dp[si1][si2 + len - cut][cut] && dp[si1 + cut][si2][len - cut];

	// 				if (f1 || f2)
	// 					dp[si1][si2][len] = true;
	// 				else
	// 					dp[si1][si2][len] = false;
	// 			}
	// 		}
	// 	}


	// 	return dp[SI1][SI2][len];
	// }



	public boolean isScramble(String s1, String s2) {
		int n = s1.length(), m = s2.length();
		//some cases
		if (s1.equals(s2)) return true;
		if (n != m) return false;

		//if not anagrams , false
		int[] letters = new int[26];
		for (int i = 0; i < s1.length(); i++) {
			letters[s1.charAt(i) - 'a']++;
			letters[s2.charAt(i) - 'a']--;
		}
		for (int i = 0; i < 26; i++)
			if (letters[i] != 0) return false;

		Boolean[][][][] dp = new Boolean[n][n][m][m];
		return scramble3(s1, s2, 0, n - 1, 0, m - 1, dp);
		// return scramble1(s1,s2);
	}

}