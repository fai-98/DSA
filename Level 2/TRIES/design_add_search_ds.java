public class design_add_search_ds {
	// Leetcode 208
	public static class Trie {

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
					Node nn = new Node();
					ptr.children[ch - 'a'] = nn;
				}
				ptr = ptr.children[ch - 'a'];
			}
			ptr.isEnd = true;
		}

		public boolean search(String word) {
			return find(root, word, 0);
		}

		public boolean find(Node root, String word , int idx) {
			//base case
			if (idx == word.length()) {
				return root.isEnd;
			}

			char ch = word.charAt(idx);

			if (ch == '.') {
				for (int i = 0; i < 26; i++) {
					Node child = root.children[i];
					if (child != null && find(child, word, idx + 1)) {
						return true;
					}
				}
				return false;
			} else {
				Node child = root.children[ch - 'a'];
				//match
				if (child != null) {
					return find(child, word, idx + 1);
				} else {
					return false;
				}

			}
		} public class Node {
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
					Node nn = new Node();
					ptr.children[ch - 'a'] = nn;
				}
				ptr = ptr.children[ch - 'a'];
			}
			ptr.isEnd = true;
		}

		public boolean search(String word) {
			return find(root, word, 0);
		}

		public boolean find(Node root, String word , int idx) {
			//base case
			if (idx == word.length()) {
				return root.isEnd;
			}

			char ch = word.charAt(idx);

			if (ch == '.') {
				for (int i = 0; i < 26; i++) {
					Node child = root.children[i];
					if (child != null && find(child, word, idx + 1)) {
						return true;
					}
				}
				return false;
			} else {
				Node child = root.children[ch - 'a'];
				//match
				if (child != null) {
					return find(child, word, idx + 1);
				} else {
					return false;
				}

			}

		}
	}
}