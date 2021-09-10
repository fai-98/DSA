// Java program to demonstrate Multi-source BFS
import java.util.*;
import java.math.*;

class NearestPoliceStations {

	static int N = 1000000;

// This array stores the distances of the vertices
// from the nearest source
	static int dist[] = new int[N];

//This boolean array is true if the current vertex
// is visited otherwise it is false
	static boolean visited[] = new boolean[N];

	static void addEdge(ArrayList<Integer> graph[],
	                    int u, int v) {

		// Function to add 2 edges in an undirected graph
		graph[u].add(v);
		graph[v].add(u);
	}

// Multisource BFS Function


// This function calculates the distance of each
// vertex from nearest source
	public static void BFS(ArrayList<Integer> graph[] , int[] sources ) {
		boolean[] vis = new boolean[graph.length];
		Queue<Integer> q = new ArrayDeque<>();

		//add all sources (t==0) and mark as vis
		for (int src : sources) {
			q.offer(src);
			vis[src] = true;
		}

		//BFS (without cycle variant) (mark*+add simulta...)

		int time = 0;

		while (q.size() > 0) {
			int size = q.size();
			while (size-- > 0) {
				int rem = q.poll();
				System.out.println(rem + " at t = " + time);
				for (int nbr : graph[rem]) {
					if (vis[nbr] == false) {
						vis[nbr] = true;
						q.offer(nbr);
					}
				}
			}
			System.out.println();
			time++;
		}

	}
// Driver code
	public static void main(String args[]) {

		// Number of vertices
		int n = 6;
		@SuppressWarnings("unchecked")
		ArrayList<Integer> graph[] = new ArrayList[N + 1];

		for (int i = 0; i < graph.length; i++)
			graph[i] = new ArrayList<Integer>();

		// Edges
		addEdge(graph, 1, 2);
		addEdge(graph, 1, 6);
		addEdge(graph, 2, 6);
		addEdge(graph, 2, 3);
		addEdge(graph, 3, 6);
		addEdge(graph, 5, 4);
		addEdge(graph, 6, 5);
		addEdge(graph, 3, 4);
		addEdge(graph, 5, 3);

		// Sources
		int sources[] = { 1, 5 };

		int S = sources.length;

		BFS(graph, sources);
	}
}

