class Solution {

	// 743. Network Delay Time
	public int networkDelayTime(int[][] times, int n, int k) {
		Map < Integer, List < int[] >> graph = new HashMap < > ();

		// <u , [v,w] >
		for (int[] edge : times) {
			int u = edge[0];
			int v = edge[1];
			int w = edge[2];

			graph.putIfAbsent(u, new ArrayList < > ());
			int[] nbr = new int[] {
			    v,
			    w
			};
			graph.get(u).add(nbr);
		}

		int[] dist = new int[n + 1];
		Arrays.fill(dist, (int) 1e9);

		PriorityQueue < int[] > pq = new PriorityQueue < > ((a, b) -> {
			return a[1] - b[1];
		});

		pq.add(new int[] {
		           k,
		           0
		       });
		dist[k] = 0;

		while (pq.size() > 0) {
			int[] rem = pq.poll();

			int src = rem[0], wsf = rem[1];

			//we reach same vtx with greater dist, skip it
			if (wsf > dist[src]) continue;

			if (graph.containsKey(src)) {
				//add only nbrs with less dist than prev
				for (int[] nbrs : graph.get(src)) {
					int nbr = nbrs[0], wt = nbrs[1];
					if (wsf + wt < dist[nbr]) {
						//update dist arr
						dist[nbr] = wsf + wt;
						pq.offer(new int[] {
						             nbr,
						             wsf + wt
						         });
					}
				}
			}
		}

		int max = 0;
		for (int i = 1; i <= n; i++) {
			if (dist[i] == (int) 1e9) return -1;
			max = Math.max(max, dist[i]);
		}

		return max;
	}
}