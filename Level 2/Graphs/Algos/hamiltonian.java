import java.io.*;
import java.util.*;

public class hamiltonian {
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

	public static void HPC(ArrayList<Edge>[]graph, int src, String psf, boolean[]vis, int oSrc) {

		if (psf.length() == graph.length) {
			System.out.print(psf);
			//cycle?
			for (Edge edge : graph[oSrc]) {
				int nbr = edge.nbr;
				if (nbr == src) {
					System.out.println("*");
					return;
				}
			}

			//no cyce
			System.out.println(".");
			return;

		}

		vis[src] = true;

		for (Edge edge : graph[src]) {
			int nbr = edge.nbr;
			if (vis[nbr] == false) {
				HPC(graph, nbr, psf + nbr, vis, oSrc);
			}
		}

		vis[src] = false;

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

		int src = Integer.parseInt(br.readLine());
		boolean[] vis = new boolean[graph.length];
		HPC(graph, src, src + "", vis, src);

	}


}