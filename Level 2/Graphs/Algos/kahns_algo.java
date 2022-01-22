public class kahns_algo {

	// Kahn's Algorithm (Topological Sort)
	// Complexity O(E)+O(V)+O(E+V) = 2(E+V) -> O(E+V) -> O(N) (in graph E+V is N)
	/*************************************************************************************************************/
	public static ArrayList<Integer> kahnsAlgo(ArrayList<Edge>[] graph) {
		int N = graph.length;
		Queue<Integer> q = new ArrayDeque<>();
		ArrayList<Integer> ans = new ArrayList<>();
		int[] indegree = new int[N];

		// indegree init O(E)
		for (int i = 0; i < graph.length; i++) {
			for (Edge e : graph[i]) {
				indegree[e.v]++;
			}
		}

		// add 0 indeg vtces O(V)
		for (int i = 0; i < indegree.length; i++) {
			if (indegree[i] == 0) {
				q.offer(i);
			}
		}

		// O(E+V)
		while (q.size() > 0) {
			// rem , ans.add , nbrs indeg-- , if indeg ==0 -> enque
			int rem = q.poll();
			ans.add(rem);
			// --indeg==0 , first indeg dec by 1 , then checked if 0 or not
			for (Edge e : graph[rem]) {
				if (--indegree[e.v] == 0) {
					q.offer(e.v);
				}
			}
		}

		if (ans.size() != N) {
			ans.clear(); // Topo Sort not possible
		}
		return ans;
	}

	// Exactly Similar Ques are
	// 207 Course Schedule I
	// 210 Course Schedule II
	// Order of COmpilation
}