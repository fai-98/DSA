import java.io.*;
import java.util.*;

public class bipartite {
	static class Edge {
		int src;
		int nbr;
		int wt;

		Edge(int src, int nbr, int wt) {
			this.src = src;
			this.nbr = nbr;
			this.wt = wt;
		}
	}

	public static class Pair {
		int v ;
		int set ;

		Pair() {}

		Pair(int v , int set) {
			this.v = v;
			this.set = set;
		}
	}

	public static boolean isBPT(ArrayList<Edge>[] graph) {
		int[] vis = new int[graph.length];
		for (int i = 0; i < graph.length; i++) {
			if (vis[i] == 0) {
				boolean ans = singleBPT(graph, i, vis);
				if (ans == false)return false;
			}
		}

		return true;
	}

	public static boolean singleBPT(ArrayList<Edge>[]graph, int src, int[] vis) {
		Queue<Pair> q = new ArrayDeque<>();
		q.add(new Pair(src, 1));

		while (q.size() > 0) {
			//rem
			Pair rem = q.remove();

			//mark*
			if (vis[rem.v] != 0) {
				if (vis[rem.v] != rem.set) {
					return false;
				}
				continue;
			}

			vis[rem.v] = rem.set;

			//add nbrs*
			for (Edge edge : graph[rem.v]) {
				int nbr = edge.nbr;
				if (vis[nbr] == 0) {
					q.add(new Pair(nbr, rem.set == 1 ? 2 : 1));
				}
			}
		}

		return true;

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int vtces = Integer.parseInt(br.readLine());
		ArrayList<Edge>[] graph = new ArrayList[vtces];
		for (int i = 0; i < vtces; i++) {
			graph[i] = new ArrayList<>();
		}

		int edges = Integer.parseInt(br.readLine());
		for (int i = 0; i < edges; i++) {
			String[] parts = br.readLine().split(" ");
			int v1 = Integer.parseInt(parts[0]);
			int v2 = Integer.parseInt(parts[1]);
			int wt = Integer.parseInt(parts[2]);
			graph[v1].add(new Edge(v1, v2, wt));
			graph[v2].add(new Edge(v2, v1, wt));
		}

		System.out.println(isBPT(graph));
	}
}