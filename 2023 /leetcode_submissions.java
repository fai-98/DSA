public class leetcode_submissions{
    // 1557. Minimum Number of Vertices to Reach All Nodes
    // https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes/description/

    // A1. Indegree Approach
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        //all the vtces with no incoming edge (indegree 0) must be counted
        //if a vtx have even 1 incoming edge, that means form the indeg 0 vtces
        //there is an incoming edge directly or indirectly
        List<Integer> res = new ArrayList<>();
        int[] indegree = new int[n];

        for(List<Integer> edge : edges){
            indegree[edge.get(1)]++;
        }

        for(int i=0; i<n; i++){
            if(indegree[i] == 0) res.add(i);
        }
        return res;
    }

    // A2.


    // https://leetcode.com/problems/count-sorted-vowel-strings/description/
    
    public int countVowelStrings(int n) {
        int a = 1, e = 1, i = 1, o = 1, u = 1; //for n = 1
        while(n-->1){
            a = (a + e + i + o + u);  //can be appended to all
            e = (e + i + o + u);   //can be app to str from e,i,o,u
            i = (i + o + u);
            o = (o + u);
            u = (u);
        }
        return (a + e + i + o + u);
    }

    //using dp array ?




    // https://leetcode.com/problems/shortest-bridge/description/

    // Why visited is not needed in BFS sometimes ??

    // Flood Fill (DFS) color one island different color
    // Multisource BFS to find shortest dist from A -> B
    // When we reach island B the first time, it is shortest bcz, BFS runs radially and simultaneously from all sources 


    int[][] dir = { {-1,0}, {0,-1}, {1,0}, {0,1} };
    public int shortestBridge(int[][] grid) {
        int n = grid.length;
        boolean[][] vis = new boolean[n][n];

        boolean flag = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !flag) {
                    grid[i][j] = 2;
                    floodFill(grid, i, j, vis, 2, 1);
                    flag = true;
                }
                if (flag) break;
            }
            if (flag) break;
        }

        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++) {
        //        System.out.print(grid[i][j] + " - ");
        //     }
        //        System.out.println();
        // }

        // island A : all 2's, island B all 1's

        Queue < Integer > q = new ArrayDeque < > ();
        //add all sources of island a to queue
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    q.offer(i * n + j);
                }
            }
        }

        int dist = 0;
        while (q.size() > 0) {
            int size = q.size();
            while (size--> 0) {
                int remIdx = q.poll();
                int r = remIdx / n;
                int c = remIdx % n;

                for (int d = 0; d < 4; d++) {
                    int R = r + dir[d][0];
                    int C = c + dir[d][1];

                    if (R >= 0 && C >= 0 && R < n && C < n && grid[R][C] == 2) {
                        return dist;
                    }

                    if (R >= 0 && C >= 0 && R < n && C < n && grid[R][C] == 0) {
                        grid[R][C] = -1;  //mark else it'll come back to it
                        q.offer(R * n + C);
                    }
                }
            }
            dist++;
        }
        return 0;
    }

    public void floodFill(int[][] grid, int i, int j, boolean[][] vis, int color, int orig) {

        for (int d = 0; d < 4; d++) {
            int R = i + dir[d][0];
            int C = j + dir[d][1];

            if (R < grid.length && C < grid[0].length && R >= 0 && C >= 0 && grid[R][C] == orig && !vis[R][C]) {
                grid[R][C] = color;
                vis[R][C] = true;
                floodFill(grid, R, C, vis, color, orig);
                vis[R][C] = false;
            }
        }
        return;
    }


    //both islands have 1's, so we have to flood fill 1 island to another number
    //then put all sources of island 1 to queue
    //apply multisource BFS, use visited arr and use cells of matrix to store dist or not ?
    //whenever we reach a 1, update minimum 



    // https://leetcode.com/problems/map-of-highest-peak/description/

    //Similar - nothing just Multisource BFS 
    //1's are water but height is 0, max abs diff for nbrs is 1 
    //so add all water i.e ht = 0 to BFS queue, start multisource BFS 
    //adj cells will get filled by ht+1 and so on, this is the most we can maximize 
    public int[][] highestPeak(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] dir = { {-1,0}, {0,-1}, {1,0}, {0,1} };

        Queue < Integer > q = new ArrayDeque < > ();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    q.offer(i * m + j);
                } else {
                    grid[i][j] = -1; //we set water = 1 to 0 (height), so change 0 to other
                }
            }
        }

        int height = 1;

        while (q.size() > 0) {
            int size = q.size();
            while (size--> 0) {
                int remIdx = q.poll();
                int r = remIdx / m;
                int c = remIdx % m;

                for (int d = 0; d < 4; d++) {
                    int R = r + dir[d][0];
                    int C = c + dir[d][1];

                    if (R >= 0 && C >= 0 && R < n && C < m && grid[R][C] == -1) {
                        grid[R][C] = height; //store height
                        q.offer(R * m + C);
                    }
                }
            }
            height++;
        }

        return grid;
    }



    // https://leetcode.com/studyplan/dynamic-programming/ 
    // Leetcode DP patterns remaining questions. 

    // https://leetcode.com/problems/delete-and-earn/description/
    //House Robber , Fibonacci , Include - Exclude pattern
    public int deleteAndEarn(int[] nums) {
        int[] freq = new int[10001], dp = new int[10001];;
        int maxRes = -(int) 1e9;

        for (int i = 0; i < nums.length; i++) {
            freq[nums[i]]++;
        }
        //base case
        dp[1] = freq[1];
        for (int i = 2; i < 10001; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + freq[i] * i);  //Max of include, exclude
        }
        return dp[10000];
    }


    //LIS Pattern 

    // https://leetcode.com/problems/longest-increasing-subsequence/description/?envType=study-plan-v2&id=dynamic-programming
    public int lengthOfLIS(int[] nums) {
        int lis = 1;
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);

        //consider all end pts which are less than nums[i]
        //max len of that subseq + inc nums[i] = new max len at this idx 

        //we have a choice at every idx - inc prev elements or start our own ssq
        for(int i=1; i<nums.length; i++){
        	for(int j=i-1; j>=0; j--){
        		if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1); 
        		}
        	}
        	lis = Math.max(lis,dp[i]);
        }

        return lis;
    }

    //arithmetic 2 questions 

}