class Solution {
	public boolean canVisitAllRooms(List < List < Integer >> rooms) {
		int N = rooms.size(), comps = 0;
		boolean[] vis = new boolean[N];

		for (int i = 0; i < N; i++) {
			if (vis[i] == false) {
				comps++;
				getSCC(rooms, i, vis);
			}
		}

		return comps == 1;
	}

	public void getSCC(List < List < Integer >> graph, int src, boolean[] vis) {
		//mark
		vis[src] = true;

		//add nbrs*
		for (int nbr : graph.get(src)) {
			if (vis[nbr] == false) {
				getSCC(graph, nbr, vis);
			}
		}
	}
}