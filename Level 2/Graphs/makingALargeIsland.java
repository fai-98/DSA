class makingALargeIsland {
    int[] par;
    int[] size;
    //LeetCode 827
    public int largestIsland(int[][] grid) {
        int max = -(int) 1e9;
        int n = grid.length;
        par = new int[n * n];
        size = new int[n * n];
        boolean hasXero = false;

        //init vals
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0)
                    hasXero = true;

                int idx = i * n + j;
                par[idx] = idx;
                size[idx] = 1;
            }
        }

        int[][] dir = {{ -1, 0}, {0, -1}, {1, 0}, {0, 1}};

        //Union Find by size groups of 1 / islands ;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int myIdx = i * n + j;
                    int p1 = findPar(myIdx);

                    for (int d = 0; d < 4; d++) {
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];

                        if (r >= 0 && c >= 0 && r < n && c < n && grid[r][c] == 1) {
                            int nbrIdx = r * n + c;
                            int p2 = findPar(nbrIdx);

                            if (p1 != p2) {
                                if (size[p1] > size[p2]) {
                                    par[p2] = p1;
                                    size[p1] += size[p2];
                                } else {
                                    par[p1] = p2;
                                    size[p2] += size[p1];
                                }
                            }

                        }

                    }
                }
            }
        }

        //consider every 0 as 1 and add all 4 dir connected islands size
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> visPar = new HashSet<>();
                    int sz = 1;
                    for (int d = 0; d < 4; d++) {
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];

                        if (r >= 0 && c >= 0 && r < n && c < n && grid[r][c] == 1) {
                            int parent = findPar(r * n + c);
                            if (!visPar.contains(parent)) {
                                visPar.add(parent);
                                sz += size[parent];
                            }
                        }
                    }

                    max = Math.max(max, sz );
                }
            }
        }


        return hasXero ? max : n * n;
    }

    public int findPar(int u) {
        if (par[u] == u)
            return u;
        return par[u] = findPar(par[u]);
    }
}