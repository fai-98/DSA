class Solution {
    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length, res = 0;
        ArrayList < Integer > [] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList < > ();
        }

        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {

                int x1 = bombs[i][0], y1 = bombs[i][1], r1 = bombs[i][2];
                int x2 = bombs[j][0], y2 = bombs[j][1], r2 = bombs[j][2];

                double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

                if (distance <= r1) { //centre 2 is inside or on perim of circle 1
                    graph[i].add(j); //add edge from i to j
                }
                if (distance <= r2) { //overlap
                    graph[j].add(i); //i is in range of j
                }
                //else no overlap, no edge 
            }
        }

        //now simple gcc for graph
        boolean[] vis = new boolean[n];

        //check largest gcc from all src coz its a directed graph
        for (int i = 0; i < n; i++) {
            res = Math.max(res, gcc(i, graph, new boolean[n])); //add 1 for self 
        }

        return res;
    }

    public int gcc(int src, ArrayList < Integer > [] graph, boolean[] vis) {
        //mark
        vis[src] = true;
        //add unvis nbrs
        int count = 1;
        for (int nbr: graph[src]) {
            if (!vis[nbr]) {
                count += gcc(nbr, graph, vis);
            }
        }
        return count;
    }
}



