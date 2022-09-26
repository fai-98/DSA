public class word_search_ii {
	//LeetCode 212

	// A1. DFS + TRIE
	private class Node {
		Node[] children;
		boolean isEnd;
		int freq;

		Node() {
			this.children = new Node[26];
			this.isEnd = false;
			this.freq = 0;
		}
	}

	public List<String> findWords(char[][] board, String[] words) {
		// make a TRIE and add word in it
		Node root = new Node();
		for (String word : words) {
			insert(word, root);
		}
		// travel in cell and find similar words from dictionary
		boolean[][] vis = new boolean[board.length][board[0].length];
		List<String> res = new ArrayList<>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				StringBuilder str = new StringBuilder();
				travelAndAdd(board, i, j, vis, root, res, str);
			}
		}
		return res;
	}

	private void insert(String word, Node root) {
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (root.children[ch - 'a'] == null) {
				root.children[ch - 'a'] = new Node();
			}
			root = root.children[ch - 'a'];
		}
		root.isEnd = true;
	}

	private int[] xdir = { -1, 0, 1, 0};
	private int[] ydir = {0, -1, 0, 1};

	private void travelAndAdd(char[][] board, int i, int j, boolean[][] vis,
	                          Node root, List<String> res, StringBuilder str) {
		char ch = board[i][j];
		if (root.children[ch - 'a'] == null)
			return;

		str.append(ch);
		vis[i][j] = true;
		root = root.children[ch - 'a'];
		if (root.isEnd == true) {
			res.add(str.toString());
			root.isEnd = false;  //to avoid duplicacy (if same word present twice in grid)
		}
		for (int d = 0; d < 4; d++) {
			int r = i + xdir[d];
			int c = j + ydir[d];

			if (r >= 0 && r < board.length && c >= 0 && c < board[0].length && vis[r][c] == false) {
				travelAndAdd(board, r, c, vis, root, res, str);
			}
		}
		str.deleteCharAt(str.length() - 1);
		vis[i][j] = false;
	}



	// A2. DFS + HashMap (TLE on last TestCase)
	public List < String > findWords(char[][] board, String[] words) {
		int n = board.length, m = board[0].length;
		List < String > res = new ArrayList < > ();
		Map < Character, List < Integer >> map = new HashMap < > ();

		//store all the idx of char in board
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				char ch = board[i][j];
				if (!map.containsKey(ch)) {
					List < Integer > list = new ArrayList < > ();
					map.put(ch, list);
				}
				map.get(ch).add(i * m + j);
			}
		}

		//only start dfs from cell that have the start character
		for (String word : words) {
			char st = word.charAt(0);
			List < Integer > startIdx = map.get(st);

			if (startIdx == null) { //board do not have strt ch , word does not exist
				continue;
			}

			for (int idx : startIdx) {
				int r = idx / m;
				int c = idx % m;

				if (dfs(board, r, c, word, 0) == true) {
					res.add(word);
					break;
					// ex : a word is given only once in arr but it is present twice in
					// grid, then it will give multiple ans
				}
			}
		}

		return res;
	}

	public boolean dfs(char[][] grid, int r, int c, String s, int idx) {
		if (idx == s.length()) {
			return true;
		}
		if (r >= grid.length || r < 0 || c < 0 || c >= grid[0].length || grid[r][c] != s.charAt(idx)) {
			return false;
		}

		grid[r][c] ^= 256;

		boolean T = dfs(grid, r - 1, c, s, idx + 1);
		boolean L = dfs(grid, r, c - 1, s, idx + 1);
		boolean D = dfs(grid, r + 1, c, s, idx + 1);
		boolean R = dfs(grid, r, c + 1, s, idx + 1);

		grid[r][c] ^= 256;

		return T || L || D || R;

	}

	//A3. 242ms
	private class Node {
		Node[] children;
		String str;

		Node() {
			this.children = new Node[26];
			this.str = null;
		}
	}

	public List < String > findWords(char[][] board, String[] words) {
		// make a TRIE and add word in it
		Node root = new Node();
		for (String word : words) {
			insert(word, root);
		}
		// travel in cell and find similar words from dictionary
		List < String > res = new ArrayList < > ();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				dfs(board, i, j, root, res);
			}
		}
		return res;
	}

	private void insert(String word, Node root) {
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (root.children[ch - 'a'] == null) {
				root.children[ch - 'a'] = new Node();
			}
			root = root.children[ch - 'a'];
		}
		root.str = word; // null means not end , if end store word itself
	}

	private int[][] dir = {{ -1, 0}, {0, -1}, {1, 0}, {0, 1}}; //TLDR;

	public void dfs(char[][] grid, int i, int j, Node root, List < String > res) {
		char ch = grid[i][j];

		if (root.children[ch - 'a'] == null || grid[i][j] == '#') {
			return; //curr ch not exist / visited
		}
		// whn we r at root , we look for curr ch in root.children(1 level down)
		// so ex : for "oath" , if we found h in t.children the we move curr ptr
		// to t.children[h-'a'] i.e now node is h node , check if isEnd , it is ans
		Node child = root.children[ch - 'a'];

		if (child.str != null) {
			res.add(child.str); //do not return , maybe more words in the subtree present
			child.str = null; //to avoid dups when 1 word is present multiple times in grid
		}

		//mark as vis
		char orig = grid[i][j];
		grid[i][j] = '#';
		//dfs
		for (int d = 0; d < 4; d++) {
			int r = i + dir[d][0];
			int c = j + dir[d][1];

			if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] != '#') {
				dfs(grid, r, c, child, res);
			}
		}

		grid[i][j] = orig; //unvis
	}

	// A4. WA - redo
	private class Node {
		Node[] children;
		String str;
		int count;  //child count

		Node() {
			this.children = new Node[26];
			this.str = null;
			this.count = 0;
		}
	}

	public List < String > findWords(char[][] board, String[] words) {
		// make a TRIE and add word in it
		Node root = new Node();
		for (String word : words) {
			insert(word, root);
		}
		// travel in cell and find similar words from dictionary
		List < String > res = new ArrayList < > ();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				dfs(board, i, j, root, res);
			}
		}
		return res;
	}

	private void insert(String word, Node root) {
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (root.children[ch - 'a'] == null) {
				root.children[ch - 'a'] = new Node();
				root.count++;
			}
			root = root.children[ch - 'a'];
		}
		root.str = word; // null means not end , if end store word itself
	}

	private int[][] dir = {{ -1, 0}, {0, -1}, {1, 0}, {0, 1}}; //TLDR;

	public void dfs(char[][] grid, int i, int j, Node root, List < String > res) {
		char ch = grid[i][j];

		if (root.children[ch - 'a'] == null || grid[i][j] == '#' || root.count == 0) {
			return; //curr ch not exist / visited
		}
		// whn we r at root , we look for curr ch in root.children(1 level down)
		// so ex : for "oath" , if we found h in t.children the we move curr ptr
		// to t.children[h-'a'] i.e now node is h node , check if isEnd , it is ans
		Node child = root.children[ch - 'a'];

		if (child.str != null) {
			res.add(child.str); //do not return , maybe more words in the subtree present
			child.str = null; //to avoid dups when 1 word is present multiple times in grid
		}

		//mark as vis
		char orig = grid[i][j];
		grid[i][j] = '#';
		//dfs
		for (int d = 0; d < 4; d++) {
			int r = i + dir[d][0];
			int c = j + dir[d][1];

			if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] != '#') {
				dfs(grid, r, c, child, res);
			}
		}

		grid[i][j] = orig; //unvis

		if (child.count == 0) {
			root.count--;
		}
		//if word already made , dont need to go deep into tree again
		//when start char is found in grid , we already removed the word from TRIE
		//virtually
	}
}