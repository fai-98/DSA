import java.util.HashSet;

public class DFSQuestions_02 {

    // #200numberOfIsland
    public int numIslands(char[][] grid) {
        int islands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    islands++;
                    dfs_01(grid, i, j);
                }
            }
        }
        return islands;
    }

    // dfs method 1
    // T : (E x V) where E.V == nXm
    public void dfs_01(char[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == '0')
            return;

        // mark
        grid[r][c] = '0';
        // TLDR;
        dfs_01(grid, r - 1, c);
        dfs_01(grid, r, c - 1);
        dfs_01(grid, r + 1, c);
        dfs_01(grid, r, c + 1);
    }

    // dfs method 2 , using directions
    // int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}} ;
    public void dfs_dir(char[][] grid, int i, int j, int[][] dir) {
        // mark
        grid[i][j] = '0';

        int n = grid.length, m = grid[0].length;
        for (int d = 0; d < 4; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            // proactive call
            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == '1') {
                dfs_dir(grid, r, c, dir);
            }
        }

    }

    // 695. Max Area of Island
    // 1-> using static var , 2-> using size (same logic as in trees)

    public int maxAreaOfIsland(int[][] grid) {
        int max = -(int) 1e9;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(max, dfs(grid, i, j));
                }
            }
        }
        return max == -(int) 1e9 ? 0 : max;
    }

    public int dfs(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0)
            return 0;

        // increase area
        int size = 0;
        // mark
        grid[r][c] = 0;
        // TLDR;
        size += dfs(grid, r - 1, c);
        size += dfs(grid, r, c - 1);
        size += dfs(grid, r + 1, c);
        size += dfs(grid, r, c + 1);

        return size + 1;
    }

    // 463. Island Perimeter

    // using DFS
    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    perimeter = dfs(grid, i, j);
                    return perimeter;
                }
            }
        }
        return perimeter;
    }

    public int dfs_peri(int[][] grid, int r, int c) {

        if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] == -1)
            return 0;

        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0) {
            return 1;
        }
        int res = 0;
        // mark
        grid[r][c] = -1;

        // TLDR;
        res += dfs_peri(grid, r - 1, c);
        res += dfs_peri(grid, r, c - 1);
        res += dfs_peri(grid, r + 1, c);
        res += dfs_peri(grid, r, c + 1);

        return res;
    }

    // iterative - math
    public int islandPerimeter_01(int[][] grid) {
        int land = 0, nbrs = 0, n = grid.length, m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    land++;
                    if (i < n - 1 && grid[i + 1][j] == 1)
                        nbrs++;
                    if (j < m - 1 && grid[i][j + 1] == 1)
                        nbrs++;
                }
            }
        }

        return land * 4 - nbrs * 2;
    }

    // 130. Surrounded Regions

    public void solve(char[][] board) {
        int n = board.length, m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                    if (board[i][j] == 'O')
                        dfs_01(board, i, j);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '#')
                    board[i][j] = 'O';
                else if (board[i][j] == 'O')
                    board[i][j] = 'X';
            }
        }
    }

    public void dfs_surr(char[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 'X' || grid[r][c] == '#')
            return;

        // mark
        grid[r][c] = '#';
        // TLDR;
        dfs_surr(grid, r - 1, c);
        dfs_surr(grid, r, c - 1);
        dfs_surr(grid, r + 1, c);
        dfs_surr(grid, r, c + 1);
    }

    // 08/July/2021******************************************************************************************************

    // 694. Number of Distinct Islands

    public int numDistinctIslands(int[][] grid) {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    sb = new StringBuilder();
                    dfs_02(grid, i, j);
                    set.add(sb.toString());
                }
            }
        }

        return set.size();
    }

    static StringBuilder sb;

    public void dfs_02(int[][] grid, int r, int c) {

        // TLDR;
        if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1) {

            grid[r][c] = 0;

            sb.append("t");
            dfs_02(grid, r - 1, c);

            sb.append("l");
            dfs_02(grid, r, c - 1);

            sb.append("d");
            dfs_02(grid, r + 1, c);

            sb.append("r");
            dfs_02(grid, r, c + 1);

        }
        sb.append("b");

    }

    // 1905. Count Sub Islands
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int n = grid1.length, m = grid1[0].length, count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid2[i][j] == 1) {
                    count += dfs_subIs(grid2, grid1, i, j, n, m) ? 1 : 0;
                }
            }
        }
        return count;
    }

    public boolean dfs_subIs(int[][] grid2, int[][] grid1, int r, int c, int n, int m) {

        if (r < 0 || c < 0 || r == n || c == m || grid2[r][c] == 0) return true;
        if (grid1[r][c] == 0) return false;

        grid2[r][c] = 0;

        boolean t = dfs_subIs(grid2, grid1, r - 1, c, n, m);
        boolean l = dfs_subIs(grid2, grid1, r, c - 1, n, m);
        boolean d = dfs_subIs(grid2, grid1, r + 1, c, n, m);
        boolean rt = dfs_subIs(grid2, grid1, r, c + 1, n, m);

        return t && l && d && rt;
    }

}
