class Solution {

	// 37. Sudoku Solver
	public void solveSudoku(char[][] board) {
		solve(board);
	}

	public boolean solve(char[][] board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.') {
					for (char ch = '1'; ch <= '9'; ch++) {
						if (isValid(board, i, j, ch)) {
							board[i][j] = ch;
							if (solve(board)) return true; //it received true -> board solved
							board[i][j] = '.'; //false receive , some prev choice was wrong/ undo it
						}
					}
					return false; //1-9 no num could be put so return false -> backtrack
				}
			}
		}
		return true; //we reach here bcz there was no empty space we return that info to solve()
	}

	public boolean isValid(char[][] board, int r, int c, char n) {
		int block_row = (r / 3) * 3, block_col = (c / 3) * 3;
		// Block no. is i/3, first element is i/3*3

		for (int i = 0; i < 9; i++) {
			if (board[i][c] == n) return false; //row check
			if (board[r][i] == n) return false; //col check
			if (board[block_row + i / 3][block_col + i % 3] == n) return false; //block check
		}
		return true;
	}

	//================================== Void Type ====================================================
	public void solveSudoku(char[][] board) {
		solve(board, 0, 0);
	}

	public void display(char[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void solve(char[][] board, int r, int c) {
		if (r == 9) {
			display(board);
			return;
		}

		int R = 0;
		int C = 0;
		//next cell
		if (c == board[0].length - 1) { //go to nxt row
			R = r + 1;
			C = 0;
		} else {
			R = r;
			C = c + 1;
		}

		if (board[r][c] != '.') {
			solve(board, R, C); //put nothing solve next cell
		} else {
			for (char num = '1'; num <= '9'; num++) {
				if (isValid(board, r, c, num) == true) {
					board[r][c] = num; //place     place/unplace on curr r/c , recur on next r/c
					solve(board, R, C); //recur
					board[r][c] = '.'; //backtrack
				}
			}
		}
		return;
	}

	public boolean isValid(char[][] board, int r, int c, char n) {
		int block_row = (r / 3) * 3, block_col = (c / 3) * 3;
		// Block no. is i/3, first element is i/3*3

		for (int i = 0; i < 9; i++) {
			if (board[i][c] == n) return false; //row check
			if (board[r][i] == n) return false; //col check
			if (board[block_row + i / 3][block_col + i % 3] == n) return false; //block check
		}
		return true;
	}
}