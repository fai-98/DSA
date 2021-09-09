import java.util.Arrays;
class Solution {


	public boolean wordBreak(String s, List < String > wordDict) {
		Set < String > set = new HashSet < > ();
		for (String str : wordDict) {
			set.add(str);
		}
		int[] dp = new int[s.length() + 1];
		Arrays.fill(dp, -1);

		return wordBreak(set, s, dp);
	}



	//MEMO..
	public boolean wordBreak(Set < String > dict, String str, int[] lookup) {
		// `n` stores length of the current substring
		int n = str.length();

		// return true if the end of the string is reached
		if (n == 0) {
			return true;
		}

		// if the subproblem is seen for the first time
		if (lookup[n] == -1) {
			// mark subproblem as seen (0 initially assuming string
			// can't be segmented)
			lookup[n] = 0;

			for (int i = 1; i <= n; i++) {
				// consider all prefixes of the current string
				String prefix = str.substring(0, i);

				// if the prefix is found in the dictionary, then recur for the suffix
				if (dict.contains(prefix) && wordBreak(dict, str.substring(i), lookup)) {
					// return true if the string can be segmented
					lookup[n] = 1;
					return true;
				}
			}
		}

		// return solution to the current subproblem
		return lookup[n] == 1;
	}


	//TABULATION

	// dp = s.len()
	//this code will also give the no. of sentences possible in the given str
	public boolean wb_tab(Set < String > set, String str, int[] dp) {
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j <= i; j++) {
				if (set.contains(str.substring(j, i + 1))) { //prefix exists
					if (j == 0) dp[i] = 1;
					else dp[i] = dp[i] + dp[j - 1];
				}
			}
		}
		return dp[str.length() - 1] > 0;
	}
}