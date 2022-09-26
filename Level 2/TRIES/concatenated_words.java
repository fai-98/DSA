class Solution {
	//Similar
	//Word Break - i
	//Word Break - ii

	//DP Solution
	//Time is O(N * L^3) where L is the word length if we count the time to compute hashcode.
	//Otherwise, let's just call it O(N * L^2)

	// (tab dp)*(N words)*(substring method (longest str.len = L)) = L^2 * N * L = O(N * L^3)
	public List < String > findAllConcatenatedWordsInADict(String[] words) {
		// a word can be concat of only smaller words than itself
		Arrays.sort(words, (a, b) -> {
			return a.length() - b.length();
		});

		List < String > res = new ArrayList < > ();
		Set < String > set = new HashSet < > ();

		for (int i = 0; i < words.length; i++) {
			if (isPossible_Dp(set, words[i])) {
				res.add(words[i]);
			}
			set.add(words[i]);  //now this word can be a part of larger word , so add
		}

		return res;
	}

	public boolean isPossible_Dp(Set < String > set, String str) {
		if (str.isEmpty()) return false;

		int[] dp = new int[str.length()];

		for (int i = 0; i < str.length(); i++) {
			for (int j = 0; j <= i; j++) { //compare all substr(j,i+1) if it exists as a word
				if (set.contains(str.substring(j, i + 1))) { //prefix exists
					if (j == 0) dp[i] = 1; //another word is st of this word
					else dp[i] = dp[i] + dp[j - 1]; //word is in the middle or end
				}
			}
		}

		return dp[str.length() - 1] > 0;
	}

	//TRIE Solution
}