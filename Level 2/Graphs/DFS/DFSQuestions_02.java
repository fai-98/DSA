import java.util.HashSet;
import java.util.*;
import java.util.ArrayList;

public class DFSQuestions_02 {

    public class Pair {
        String v;
        double wt;

        Pair() {
        }

        Pair(String v, double wt) {
            this.v = v;
            this.wt = wt;
        }
    }

    //Perfect Friends
    public static int perfectFriends(ArrayList<Integer>[]graph) {
        boolean[] vis = new boolean[graph.length];
        ArrayList<ArrayList<Integer>> cc = new ArrayList<>();

        for (int src = 0; src < graph.length; src++) {
            if (vis[src] == false) {
                ArrayList<Integer> scc = new ArrayList<>();
                getSCC(graph, src, scc, vis);
                cc.add(scc);
            }
        }

        int count = 0;

        for (int i = 0; i < cc.size(); i++) {
            for (int j = i + 1; j < cc.size(); j++) {
                int s1 = cc.get(i).size();
                int s2 = cc.get(j).size();

                count += s1 * s2;
            }
        }

        return count;


    }

    public static void getSCC(ArrayList<Integer>[]graph, int src, ArrayList<Integer> scc, boolean[] vis) {
        //self
        scc.add(src);

        //mark
        vis[src] = true;

        //nbrs
        for (int nbr : graph[src]) {
            if (vis[nbr] == false) {
                getSCC(graph, nbr, scc, vis);
            }
        }

        return;
    }



    // 797. All Paths From Source to Target
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        boolean[] vis = new boolean[graph.length];
        dfs(0, graph.length - 1, res, path, graph, vis);
        return res;
    }

    public void dfs(int src, int des, List<List<Integer>> res, List<Integer> path, int[][] graph, boolean[] vis) {

        //self check
        if (src == des) {
            res.add(new ArrayList<>(path));
            return;
        }

        //mark
        vis[src] = true;

        //unvis nbrs
        for (int nbr : graph[src]) {
            if (vis[nbr] == false) {
                path.add(nbr);
                dfs(nbr, des, res, path, graph, vis);
                path.remove(path.size() - 1);
            }
        }

        vis[src] = false;
    }


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

    // 1020. Number of Enclaves (Exactly Similar to 130.)
    public int numEnclaves(int[][] grid) {
        int n = grid.length, m = grid[0].length, enclaves = 0;
        int[][] dir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
                    if (grid[i][j] == 1) {
                        dfs(grid, i, j, dir);
                    }
                }
            }
        }

        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (grid[i][j] == 1)
                    enclaves++;
            }
        }

        return enclaves;
    }

    public void dfs(int[][] grid, int sr, int sc, int[][] dir) {

        grid[sr][sc] = 0;

        for (int d = 0; d < 4; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1) {
                dfs(grid, r, c, dir);
            }
        }
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

        if (r < 0 || c < 0 || r == n || c == m || grid2[r][c] == 0)
            return true;
        if (grid1[r][c] == 0)
            return false;

        grid2[r][c] = 0;

        boolean t = dfs_subIs(grid2, grid1, r - 1, c, n, m);
        boolean l = dfs_subIs(grid2, grid1, r, c - 1, n, m);
        boolean d = dfs_subIs(grid2, grid1, r + 1, c, n, m);
        boolean rt = dfs_subIs(grid2, grid1, r, c + 1, n, m);

        return t && l && d && rt;
    }

    // 399. Evaluate Division

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        int n = values.length;
        Map<String, ArrayList<Pair>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            List<String> eqn = equations.get(i);
            String u = eqn.get(0);
            String v = eqn.get(1);
            double wt = values[i];

            if (!map.containsKey(u)) {
                map.put(u, new ArrayList<>());
            }

            if (!map.containsKey(v)) {
                map.put(v, new ArrayList<>());
            }

            Pair p1 = new Pair(v, wt);
            Pair p2 = new Pair(u, 1.0 / wt);
            map.get(u).add(p1);
            map.get(v).add(p2);

        }

        for (String key : map.keySet()) {
            for (var pair : map.get(key)) {
                System.out.print(key + " - " + pair.v + " - " + pair.wt);
            }
            System.out.println();
        }

        double[] res = new double[queries.size()];
        int idx = 0;
        // solve queries using BFS or DFS
        for (var query : queries) {
            String src = query.get(0);
            String dest = query.get(1);

            if (!map.containsKey(src) || !map.containsKey(dest)) {
                res[idx++] = -1.0;
            } else
                res[idx++] = dfs(map, new HashSet<>(), src, dest, 1);
        }

        return res;
    }

    public double dfs(Map<String, ArrayList<Pair>> graph, Set<String> vis, String src, String des, double r) {
        if (!graph.containsKey(src) || !vis.add(src))
            return -1;

        if (src.equals(des))
            return r;

        for (Pair p : graph.get(src)) {
            double result = dfs(graph, vis, p.v, des, r * p.wt);
            if (result != -1)
                return result;
        }
        return -1;
    }

}
