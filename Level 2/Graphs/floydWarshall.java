public class floydWarshall {


	//Algo if a path1 a----b
	//path2 a----via(x)----b
	//take min of path (a to b , a to x + x to b) i.e a to b via x
	public static void floydWarshall(int[][] edges , int n) {
		//convert into matrix
		int[][] mat = new int[n][n];
		//initia..
		for (int[] row : mat) {
			Arrays.fill(row, (int)1e9);
		}

		//directional graph
		for (int[] e : edges) {
			mat[e[0]][e[1]] = e[2]; //for undirected also add mat[e[1]][e[0]] = e[2]
		}
		//src to src dist = 0;
		for (int i = 0; i < n; i++) {
			mat[i][i] = 0;
		}

		//algo
		for (int k = 0; k < n; k++) { 			//intermediate
			for (int i = 0; i < n; i++) { 		//src
				for (int j = 0; j < n; j++) { 	//dest
					mat[i][j] = Math.min(mat[i][j] , mat[i][k] + mat[k][j]);
				}
			}
		}
	}

}
