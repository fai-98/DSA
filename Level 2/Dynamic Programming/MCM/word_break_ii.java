class Solution {

	// 140. Word Break II

	public List < String > wordBreak(String s, List < String > wordDict) {
		Set < String > set = new HashSet < > ();
		for (String str : wordDict) {
			set.add(str);
		}
		return dfs(s, set);
	}

	public List < String > dfs(String s, Set < String > set) {

		if (s.length() == 0) {
			List < String > base = new ArrayList < > ();
			base.add("");
			return base;
		}

		List < String > myRes = new ArrayList < > ();
		for (int cut = 1; cut <= s.length(); cut++) {
			String word = s.substring(0, cut);
			if (set.contains(word)) {
				List < String > rres = dfs(s.substring(cut), set);
				for (String sentence : rres) {
					//if sent.. is empty - base case when str.len()==0
					//else its from intermediate level , add space b/w words
					myRes.add(word + (sentence.isEmpty() ? "" : " ") + sentence);
				}
			}
		}
		return myRes;
	}
}

// Memoization using hashMap ??


// nxt ques ..  472. Concatenated Words