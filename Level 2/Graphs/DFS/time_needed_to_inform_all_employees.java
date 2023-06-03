class Solution {
    int maxDepth;
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        // root node to src node edge with wt = informtime
        // edge from manager[i] to i, root node is node with man = -1
        // add edge only from par to child - uni directional
        //apply BFS till max depth and calc sum inf time or DFS ? 

        //n = 10^5 but n^2 edges cant be there coz its a tree structure so n-1 edges
        maxDepth = 0;
        ArrayList<Edge>[] graph = new ArrayList[n];

        for(int i=0; i<n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<n; i++){
            if(manager[i] != -1){
                graph[manager[i]].add(new Edge(i,informTime[i]));
            }
        }
        //rootNode = headId
        dfs(headID, graph, informTime[headID], new boolean[n]);
        return maxDepth;
    }

    //in reality visited is not needed, as there are no cycles, its a tree
    public void dfs(int src, ArrayList<Edge>[] graph, int depth, boolean[] vis){
        vis[src] = true;;
        
        for(Edge e : graph[src]){
            if(!vis[e.nbr]){
                maxDepth = Math.max(depth + e.wt, maxDepth);
                dfs(e.nbr, graph, depth+e.wt, vis);
            }
        }
        return;
    }

    public class Edge{
        int wt;
        int nbr;

        Edge(){}

        Edge(int nbr, int wt){
            this.wt = wt;
            this.nbr = nbr;
        }
    }
}