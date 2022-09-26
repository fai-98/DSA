public class design_add_search_ds {
	// Leetcode 211
	public class Node {
		Node[] children;
		boolean isEnd;

		Node() {
			this.children = new Node[26];
			this.isEnd = false;
		}
	}

	private Node root = null;

	public WordDictionary() {
		root = new Node();
	}

	public void addWord(String word) {
		Node ptr = root;

		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);

			if (ptr.children[ch - 'a'] == null) {
				ptr.children[ch - 'a'] = new Node();
			}

			ptr = ptr.children[ch - 'a'];
		}

		ptr.isEnd = true;
	}

	public boolean search(String word) {
		return find(root, word, 0);
	}

	public boolean find(Node root, String word, int idx) {
		if (idx == word.length()) {
			return root.isEnd; //maybe it exists but just as a prefix then false
		}
		//when we are at node we are comparing with children
		//so the last char is checked when we are at its parent
		//when we have last char node , we just check is it is end or not
		char ch = word.charAt(idx);

		if (ch == '.') { //if . atleast 1 char shd be present to be matched
			for (int i = 0; i < 26; i++) {
				Node child = root.children[i];
				if (child != null && find(child, word, idx + 1)) {
					//if matched , match rest of str with subtree
					return true;
				}
			}
			return false;
		} else {
			Node child = root.children[ch - 'a']; //matched
			if (child != null) {
				return find(child, word, idx + 1);
			} else {
				return false;
			}
		}
	}
}