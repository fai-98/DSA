public class reconstruct_itinerary {

	// 332. Reconstruct Itinerary
	Map < String, PriorityQueue < String >> graph;
	LinkedList < String > res;

	public List < String > findItinerary(List < List < String >> tickets) {
		graph = new HashMap < > ();
		res = new LinkedList < > (); //addFirst of O(1) , add while backtracking

		//priority queue will give lexico smaller nbr first
		for (List < String > ticket : tickets) {
			String src = ticket.get(0);
			String nbr = ticket.get(1);
			PriorityQueue nbrs = graph.getOrDefault(src, new PriorityQueue < > ());
			nbrs.offer(nbr);       //nbrs pq
			graph.put(src, nbrs);  // add src - pq of nbrs
		}

		dfs("JFK");
		return res;
	}

	public void dfs(String src) {
		//mark*  - no need to mark edge vis , coz we are removing edge from pq
		//work
		//add unvis nbrs
		PriorityQueue < String > nbrs = graph.get(src);
		while (nbrs != null && nbrs.size() > 0) {
			String nbr = nbrs.poll();
			dfs(nbr);
		}
		res.addFirst(src);
	}

	// Eulerian Path is a path in graph that visits every edge exactly once.
	// Eulerian Circuit is an Eulerian Path which starts and ends on the same vertex.

	// Find Eulerian Path in Directed Graph *with given condition ( lexico smaller path )
}