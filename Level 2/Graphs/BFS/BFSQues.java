import java.util.*;
import java.util.ArrayList;

public class BFSQues {
    public static class Edge {
        int v = 0;
        int w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    // COMMON IDEA FOR MULTIPLE PROBLEMS
    /*
     * Instead of invoking BFS for each land cell to see how far we can get away
     * from that source, we flip the problem. The flipped problem is to start from
     * target (sea) and to figure our the closest source (land) This allows us to
     * run a single BFS search that emerges from different places (all the targets
     * aka all the zero cells) in the grid Add all the targets (all zero cells) into
     * the queue. While you're at it, also mark those targets as visited (add to a
     * visited set) Run a single BFS on the pre-processed queue and investigate
     * neighbours. if neighbiour cell has not been visited --> then it must be a
     * land cell (since all the sea cells have been marked visited): append the
     * neighbour cell into the queue and mutate the gird (also mark the land cell as
     * vis (i.e store the dist , or use vis arr etc.))
     */


    //Spread Of Infection
    public static int infection(ArrayList<Edge>[]graph, int src, int t) {
        int count = 0;
        boolean[] vis = new boolean[graph.length];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(src);


        while (q.size() > 0 && t > 0) {


            int size = q.size();
            for (int i = 0; i < size; i++) {

                //rem
                int rem = q.remove();

                //mark*
                if (vis[rem] == true) {
                    continue;
                } else {
                    vis[rem] = true;
                    count++;
                }

                for (Edge edge : graph[rem]) {
                    int nbr = edge.nbr;
                    if (vis[nbr] == false) {
                        q.add(nbr);

                    }
                }
            }

            t--;
        }

        return count;
    }

    // 08/July/2021******************************************************************************************************
    // 994. Rotting Oranges
    public int orangesRotting(int[][] grid) {
        int n = grid.length, m = grid[0].length, rotten = 0, fresh = 0, time = 0;

        Queue<Integer> q = new ArrayDeque<>();
        // add rotten/sources to que , and count fresh
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    q.offer(i * m + j);
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        if (fresh == 0)
            return 0;

        // Multisource BFS
        while (q.size() > 0) {
            int size = q.size();
            while (size-- > 0) {
                int remIdx = q.poll();
                int r = remIdx / m;
                int c = remIdx % m;

                // T
                if (r - 1 >= 0 && grid[r - 1][c] == 1) {
                    q.offer((r - 1) * m + c);
                    grid[r - 1][c] = 2;
                    fresh -= 1;
                    if (fresh == 0)
                        return time + 1;
                }
                // L
                if (c - 1 >= 0 && grid[r][c - 1] == 1) {
                    q.offer(r * m + (c - 1));
                    grid[r][c - 1] = 2;
                    fresh -= 1;
                    if (fresh == 0)
                        return time + 1;
                }
                // D
                if (r + 1 < grid.length && grid[r + 1][c] == 1) {
                    q.offer((r + 1) * m + c);
                    grid[r + 1][c] = 2;
                    fresh -= 1;
                    if (fresh == 0)
                        return time + 1;
                }
                // R
                if (c + 1 < grid[0].length && grid[r][c + 1] == 1) {
                    q.offer(r * m + (c + 1));
                    grid[r][c + 1] = 2;
                    fresh -= 1;
                    if (fresh == 0)
                        return time + 1;
                }
            }
            time++;
        }

        return -1;

    }

    // 1091. Shortest Path in Binary Matrix
    public int shortestPathBinaryMatrix(int[][] grid) {

        int[][] dir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 }, { 1, 1 } };

        int n = grid.length, m = grid[0].length, path = 1;

        if (grid[0][0] == 1 || grid[n - 1][m - 1] == 1)
            return -1;

        if (n == 1 && m == 1 && grid[0][0] == 0)
            return 1;

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0 * m + 0);
        while (q.size() > 0) {
            int size = q.size();
            while (size-- > 0) {
                int remIdx = q.poll();
                int r = remIdx / m;
                int c = remIdx % m;

                if (r == n - 1 && c == m - 1)
                    return path;

                for (int d = 0; d < 8; d++) {
                    int R = r + dir[d][0];
                    int C = c + dir[d][1];

                    if (R >= 0 && C >= 0 && R < n && C < m && grid[R][C] == 0) {
                        grid[R][C] = 2;
                        q.offer(R * m + C);

                    }
                }
            }
            path++;
        }

        return -1;
    }

    // 542. 01 Matrix
    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length, m = mat[0].length, dist = 1, INF = (int) 1e9;
        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    q.offer(i * m + j);
                } else {
                    mat[i][j] = INF;
                }
            }
        }

        int[][] dir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

        while (q.size() > 0) {
            int size = q.size();
            while (size-- > 0) {
                int rIdx = q.poll();
                int sr = rIdx / m;
                int sc = rIdx % m;

                // calls
                for (int d = 0; d < 4; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    // add* + mark* -> we only travel on INF (i.e) unvis path -> then add to que
                    // and mark it as vis
                    if (r >= 0 && c >= 0 && r < n && c < m && mat[r][c] == INF) {
                        mat[r][c] = dist;
                        q.offer(r * m + c);
                    }
                }
            }
            dist++;
        }

        return mat;
    }

    // 1162. As Far from Land as Possible (Exactly Same as 542. 01 Matrix )

    public int maxDistance(int[][] grid) {
        int n = grid.length, m = grid[0].length, dist = 1, maxDist = -(int) 1e9;
        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    q.offer(i * m + j);
                }
            }
        }

        int[][] dir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

        while (q.size() > 0) {
            int size = q.size();
            while (size-- > 0) {

                // remIdx (st point land)
                // dist from land to land is 0
                int rIdx = q.poll();
                int sr = rIdx / m;
                int sc = rIdx % m;

                for (int d = 0; d < 4; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    // (mark* & add ) -> here mark* means store dist , any val other than 0
                    // helps us to mark the cell as vis , only 0 cells are added to que
                    if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 0) {
                        grid[r][c] = dist;
                        maxDist = Math.max(dist, maxDist);
                        q.offer(r * m + c);
                    }
                }
            }
            dist++;
        }

        return maxDist == -(int) 1e9 ? -1 : maxDist;

    }

    // 286. Walls And Gates
    public void wallsAndGates(int[][] rooms) {
        int INF = 2147483647;
        int n = rooms.length, m = rooms[0].length;
        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (rooms[i][j] == 0) {
                    q.offer(i * m + j);
                    // mark
                }
            }
        }

        int dist = 1;
        int[][] dir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

        while (q.size() > 0) {
            int size = q.size();
            while (size-- > 0) {
                // rem
                int remIdx = q.poll();
                int sr = remIdx / m;
                int sc = remIdx % m;

                for (int d = 0; d < 4; d++) {
                    int R = sr + dir[d][0];
                    int C = sc + dir[d][1];

                    // mark* + add* (INF means unvis -> empty space , store shortest dist in cell
                    // and add*)
                    if (R >= 0 && C >= 0 && R < n && C < m && rooms[R][C] == INF) {
                        rooms[R][C] = dist;
                        q.offer(R * m + C);
                    }
                }
            }
            dist++;
        }
    }

    // 815. Bus Routes

    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target)
            return 0;
        // bus No. to stop mapping given in routes
        // make busStop to busNo mapping
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        // i = busNumber , j = busStop mapping <j,i>/<stop,busNo>
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                int busStop = routes[i][j];
                map.putIfAbsent(busStop, new ArrayList<>());
                map.get(busStop).add(i);
            }
        }

        boolean[] busStopVis = new boolean[(int) 1e6];
        boolean[] busNumberVis = new boolean[routes.length];

        LinkedList<Integer> q = new LinkedList<>();
        // queue contains vertices i.e busStops not busNo
        q.addLast(source);
        busStopVis[source] = true;
        int level = 0;
        while (q.size() > 0) {
            int size = q.size();
            while (size-- > 0) {
                int remStop = q.removeFirst();
                // now the busNums we can take from here
                ArrayList<Integer> busNums = map.get(remStop);

                // mark* + add*
                for (int bus : busNums) {
                    if (busNumberVis[bus])
                        continue;
                    // getStops
                    for (int nextStop : routes[bus]) {

                        if (!busStopVis[nextStop]) {
                            q.addLast(nextStop);
                            busStopVis[nextStop] = true;
                            // ans found
                            if (nextStop == target)
                                return level + 1;
                        }
                    }
                    busNumberVis[bus] = true; // this bus and all related stops are processed
                }
            }

            level++;
        }

        return -1;

    }

}
