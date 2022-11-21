class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {
        int n = maze.length, m = maze[0].length, i = entrance[0], j = entrance[1], min = (int) 1e9;
        
        int steps = 1; //intially 1, first calc. ans then increment 
        
        int[][] dir = { { -1, 0}, {0, -1}, {1, 0}, {0, 1} };
        
        Queue < Integer > q = new ArrayDeque < > ();
        q.offer(i * m + j);
        maze[i][j] = '+'; //mark here otherwise it'll return to start pos.

        while (q.size() > 0) {
            int size = q.size();

            while (size--> 0) {
                int remIdx = q.poll();
                int r = remIdx / m;
                int c = remIdx % m;

                for (int d = 0; d < 4; d++) {
                    int R = r + dir[d][0];
                    int C = c + dir[d][1];
                
                //first we check if the next cell is valid, then we move to that cell, also if valid cell is a border cell minimize the res
                if (R >= 0 && R < n && C >= 0 && C < m && maze[R][C] != '+') {
                        if (R == 0 || R == n - 1 || C == 0 || C == m - 1) {
                            min = Math.min(min, steps);
                        }
                        maze[R][C] = '+';
                        q.offer(R * m + C);
                    }
                }
            }
            steps++;
        }
        return min == (int) 1e9 ? -1 : min;
    }
}