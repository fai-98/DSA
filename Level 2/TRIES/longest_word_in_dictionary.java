class Solution {

	// 720. Longest Word in Dictionary
	public class Node {
		Node[] children = new Node[26];
		String str;
	}

	public void insert(Node root, String word) {
		Node ptr = root;

		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (ptr.children[ch - 'a'] == null) {
				ptr.children[ch - 'a'] = new Node();
			}
			ptr = ptr.children[ch - 'a'];
		}

		ptr.str = word; //if it is end - store whole word
	}

	static String maxStr; //travel and change strategy ans initially empty at heap;

	public void dfs(Node root) {
		Node ptr = root;
		for (int i = 0; i < 26; i++) {
			Node child = ptr.children[i];
			if (child != null && child.str != null) { //this node is end of a word
				if (child.str.length() > maxStr.length()) { //update ans
					maxStr = child.str;
				}
				dfs(child); //dfs on subtree
			}
		}
		return;
	}

	//longest word with all prefixes present in dict (as words)
	//longest word with all prefix nodes (isEnd)
	public String longestWord(String[] words) {
		Node root = new Node(); //make root
		maxStr = "";
		//insert all data
		for (String word : words) {
			insert(root, word);
		}
		//dfs
		dfs(root);
		return maxStr;
	}

	// A2.
	// Sort the words alphabetically, therefore shorter words always comes before longer words;
	// Along the sorted list, populate the words that can be built;
	// Any prefix of a word must comes before that word.

	public String longestWord(String[] words) {
		Arrays.sort(words);
		Set<String> built = new HashSet<String>();
		String res = "";
		for (String w : words) {
			if (w.length() == 1 || built.contains(w.substring(0, w.length() - 1))) {
				res = w.length() > res.length() ? w : res;
				built.add(w);
			}
		}
		return res;
	}

	// ["a","banana","app","appl","ap","apply","apple"]
	// Order of add in set = a -> ap -> app -> appl -> apple
	// When we r at apple , we know appl is valid ans so only need to check appl i.e apple.substring(0,n-1)
}