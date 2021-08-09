//Leetcode 490
//https://github.com/cheonhyangzhang/leetcode-solutions/blob/master/solutions-451-500/490-the-maze.md
public class TheMaze {
	public boolean hasPath(int[][] maze, int[] start, int[] destination) {
		int n = maze.length , m = maze[0].length , sr = start[0] , sc = start[1], dr = destination[0] , dc = destination[1];
		Queue<Integer> q = new ArrayDeque<>();
		boolean[][] vis = new boolean[n][m];
		int[][] dir = {{ -1, 0}, {0, -1}, {1, 0}, {0, 1}};

		q.offer(sr * m + sc);
		vis[sr][sc] = true;

		int len = Math.max(n, m); //max radius possible

		while (q.size() > 0) {
			int size = q.size();
			while (size-- > 0) {
				int idx = q.poll();
				int i = idx / m , j = idx % m;

				//dir
				for (int d = 0; d < 4; d++) {
					int r = i , c = j;
					//go in depth in that dir
					while (r >= 0 && c >= 0 && r < n && c < m && maze[r][c] == 0) {
						//move 1 step fwd in that dir
						r += dir[d][0];
						c += dir[d][1];
					}
					//adjust
					r -= dir[d][0];
					c -= dir[d][1];

					if (vis[r][c])
						continue;
					//mark
					vis[r][c] = true;
					q.offer(r * m + c);
					if (r == dr && c == dc) //its valid dest i.e ball will stop here
						return true;
				}
			}
		}

		return false;
	}

	//505. The Maze II
	//https://www.lintcode.com/problem/the-maze-ii/note

	public int shortestDistance(int[][] maze, int[] start, int[] destination) {
		class Pair implements Comparable<Pair> {
			int r;
			int c;
			int dis;

			Pair(int r , int c , int dis) {
				this.r = r;
				this.c = c;
				this.dis = dis;
			}

			public int compareTo(Pair o) {
				return this.dis - o.dis;
			}
		}

		int n = maze.length , m = maze[0].length , sr = start[0] ;
		int sc = start[1], dr = destination[0] , dc = destination[1];

		int[][] distance = new int[n][m];
		for (int[] row : distance)
			Arrays.fill(row, (int)1e9);

		int[][] dir = {{ -1, 0}, {0, -1}, {1, 0}, {0, 1}};
		PriorityQueue<Pair> pq = new PriorityQueue<>();

		pq.offer(new Pair(sr, sc, 0));
		distance[sr][sc] = 0;
		int len = Math.max(n, m);

		while (pq.size() > 0) {
			int size = pq.size();
			while (size-- > 0) {
				Pair rem = pq.poll();

				// 4 dir
				for (int d = 0; d < 4; d++) {
					int r = rem.r , c = rem.c , dis = rem.dis;

					//first go to that pos , then check if valid , thats why we
					//adjust r,c after while loop
					while (r >= 0 && c >= 0 && r < n && c < m && maze[r][c] == 0) {
						r += dir[d][0];
						c += dir[d][1];
						dis++;
					}
					r -= dir[d][0];
					c -= dir[d][1];
					dis--;

					if (distance[r][c] <= dis)
						continue;
					else {
						distance[r][c] = dis;
						pq.offer(new Pair(r, c, dis));
					}

					// if (r == dr && c == dc)
					// 	return dis;
				}

			}
		}
		if (distance[dr][dc] != (int)1e9)return distance[dr][dc];
		return -1;

	}

	//499. The Maze III

}