import java.util.*;
import java.io.*;

public class numberOfIslands2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] st = br.readLine().split(" ");
		int m = Integer.parseInt(st[0]);
		int n = Integer.parseInt(st[1]);
		int q = Integer.parseInt(st[2]);

		int[][] pos = new int[q][2];
		for (int i = 0; i < q; i++) {
			st = br.readLine().split(" ");
			pos[i][0] = Integer.parseInt(st[0]);
			pos[i][1] = Integer.parseInt(st[1]);
		}

		System.out.println(numIslands2(m, n, pos));
	}
	static int[] par;
	public static List<Integer> numIslands2(int m, int n, int[][] positions) {
		int[][] mat = new int[m][n];

		par = new int[n * m];
		for (int i = 0; i < par.length; i++) {
			par[i] = i;
		}

		int islands = 0;
		List<Integer> res = new ArrayList<>();
		int[][] dir = {{ -1, 0}, {0, -1}, {1, 0}, {0, 1}};

		for (int i = 0; i < positions.length; i++) {
			int[] pos = positions[i];
			int sr = pos[0], sc = pos[1];
			mat[sr][sc] = 1;
			islands++;
			int myPar = findPar(sr * n + sc);
			boolean merge = false;
			for (int d = 0; d < 4; d++) {
				int r = sr + dir[d][0];
				int c = sc + dir[d][1];
				if (r >= 0 && c >= 0 && r < m && c < n) {
					if (mat[r][c] == 1) {
						int p2 = findPar(r * n + c);
						if (myPar != p2) {
							par[p2] = myPar;
							merge = true;
							islands--;
						}
					}
				}
			}

			res.add(islands);
		}

		return res;
	}

	public static int findPar(int u) {
		if (par[u] == u)return u;
		return par[u] = findPar(par[u]);
	}
}