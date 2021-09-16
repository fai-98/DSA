public class sliding_puzzle {

	// 773. Sliding Puzzle

	// On an 2 x 3 board, there are five tiles labeled from 1 to 5, and an empty square represented by 0.
	// A move consists of choosing 0 and a 4 - directionally adjacent number and swapping it.
	// The state of the board is solved if and only if the board is [[1, 2, 3], [4, 5, 0]].
	// Given the puzzle board board, return the least number of moves required so that the state of the board is solved.
	// If it is impossible for the state of the board to be solved, return -1.

	public int slidingPuzzle(int[][] board) {
		int n = board.length, m = board[0].length, steps = 0;
		Set < String > set = new HashSet < > ();
		String state = "", finalState = "123450";

		//we always swap only '0' with any other adj ele , bcz given in ques

		int src = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				state += board[i][j];
				if (board[i][j] == 0) {
					src = (i * m + j);
				}
			}
		}

		//all possible dest from every idx
		int[][] dir = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {3, 1, 5}, {2, 4}};

		Queue < String > q = new ArrayDeque < > ();
		q.offer(state);

		while (q.size() > 0) {
			int size = q.size();
			while (size-- > 0) {
				//rem
				String rem = q.poll();

				if (rem.equals(finalState)) {
					return steps;
				}

				//mark**
				if (set.contains(rem)) {
					continue;
				} else set.add(rem);

				int idx = -1;
				for (int i = 0; i < rem.length(); i++) {
					if (rem.charAt(i) == '0') {
						idx = i;
					}
				}

				//consider every possible state from here by swapping all possible indexes
				//add unvis nbrs**
				for (int i = 0; i < dir[idx].length; i++) {
					String nextState = swap(idx, dir[idx][i], rem);
					if (!set.contains(nextState)) q.offer(nextState);
				}

			}

			steps++;
		}

		return -1;
	}

	public String swap(int i, int j, String str) {
		StringBuilder sb = new StringBuilder(str);
		sb.setCharAt(i, str.charAt(j));
		sb.setCharAt(j, str.charAt(i));
		return sb.toString();
	}
}