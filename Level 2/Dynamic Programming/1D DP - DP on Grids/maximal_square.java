class Solution {

	// 221. Maximal Square
	public int maximalSquare(char[][] mat) {
		int[][] dp = new int[mat.length + 1][mat[0].length + 1];
		int side = 0;
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				if (mat[i - 1][j - 1] == '1') {
					dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
					side = Math.max(side, dp[i][j]);
				}
			}
		}


		return side * side;
	}


	// 85. Maximal Rectangle
	public int maximalRectangle(char[][] mat) {
		int n = mat.length;
		if (n == 0) return 0;
		int m = mat[0].length;
		int[] heights = new int[m];
		int maxArea = 0;

		for (char[] col : mat) {
			for (int i = 0; i < col.length; i++) {
				if (col[i] == '1')heights[i] += col[i] - '0';
				else heights[i] = 0;
			}
			maxArea = Math.max(maxArea, largestRectangleArea(heights));
		}
		return maxArea;
	}

	public int largestRectangleArea(int[] heights) {
		int[] height = new int[heights.length + 1];
		System.arraycopy(heights, 0, height, 0, heights.length);

		Stack < Integer > st = new Stack < > ();
		st.push(-1);
		st.push(0);
		int maxArea = 0;
		for (int i = 1; i < height.length; i++) {
			while (st.peek() != -1 && height[st.peek()] >= height[i]) {
				int h = height[st.pop()];
				int w = i - st.peek() - 1;
				maxArea = Math.max(maxArea, h * w);
			}
			st.push(i);
		}

		return maxArea;
	}
}
