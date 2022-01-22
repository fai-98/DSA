import java.io.*;
import java.util.*;

public class iterative_dfs {
	static class Edge {
		int src;
		int nbr;

		Edge(int src, int nbr) {
			this.src = src;
			this.nbr = nbr;
		}
	}

	public static class Pair {
		int v;
		String psf;
		Pair() {}

		Pair(int v, String psf) {
			this.v = v;
			this.psf = psf;
		}
	}

	public static void iterativeDFS(ArrayList<Edge>[]graph , int src) {
		Stack<Pair> st = new Stack<>();
		boolean[]vis = new boolean[graph.length];
		st.push(new Pair(src, src + ""));

		while (st.size() > 0) {
			//rem
			Pair rem = st.pop();

			//mark*
			if (vis[rem.v] == true)continue;
			vis[rem.v] = true;

			//work
			System.out.println(rem.v + "@" + rem.psf);

			//add nbrs*
			for (Edge edge : graph[rem.v]) {
				if (vis[edge.nbr] == false) {
					st.add(new Pair(edge.nbr, rem.psf + edge.nbr));
				}
			}
		}


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
			graph[v1].add(new Edge(v1, v2));
			graph[v2].add(new Edge(v2, v1));
		}

		int src = Integer.parseInt(br.readLine());

		iterativeDFS(graph, src);
	}
}