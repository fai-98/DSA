import java.io.*;
import java.util.*;
import java.util.ArrayList;

class CheapestFlight {
    public int findCheapestPrice(int n, int[][] flights, int st, int dst, int k) {
        // {v,w}
        ArrayList<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++)
            graph[i] = new ArrayList<>();

        for (int[] edge : flights) {
            int u = edge[0], v = edge[1], w = edge[2];
            graph[u].add(new int[] { v, w });
        }

        int[] dist = new int[n];
        Arrays.fill(dist, (int) 1e9);

        // {vtx,wsf}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        // add vtx , wsf , stop
        pq.add(new int[] { st, 0, -1 });

        dist[st] = 0;

        while (pq.size() > 0) {
            int[] rem = pq.poll();
            int vtx = rem[0], wsf = rem[1], stop = rem[2];

            if (vtx == dst)
                return wsf;

            if (stop < k) {
                for (int[] e : graph[vtx]) {
                    int nbr = e[0], wt = e[1];
                    pq.offer(new int[] { nbr, wt + wsf, stop + 1 });
                }

            }
        }

        return -1;

    }
}