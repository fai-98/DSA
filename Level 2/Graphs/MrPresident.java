import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

class MrPresident {
    public static void main(String args[]) throws Exception {

        BufferedReader s = new BufferedReader(new InputStreamReader(System.in));

        String[] line = s.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);
        long K = Integer.parseInt(line[2]);

        int[][] edges = new int[M][3];

        for (int i = 0; i < M; i++) {
            String[] li = s.readLine().split(" ");
            edges[i][0] = Integer.parseInt(li[0]);
            edges[i][1] = Integer.parseInt(li[1]);
            edges[i][2] = Integer.parseInt(li[2]);
        }

        int res = mrPresident(edges, N, K);
        System.out.println(res);

    }
    static int[] par;

    public static int mrPresident(int[][] edges, int N, long K) {
        par = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            par[i] = i;
        }

        Arrays.sort(edges, (a, b) -> {
            return a[2] - b[2];
        });

        ArrayList<Integer> roads = new ArrayList<>();

        int components = N;
        long mcost = 0;
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            int p1 = findPar(u), p2 = findPar(v);
            if (p1 != p2) {
                par[p1] = p2;
                components--;
                mcost += w;
                roads.add(w);
            }
        }

        if (components > 1)
            return -1;

        int superroad = 0;
        for (int i = roads.size() - 1; i >= 0; i--) {
            if (mcost <= K)
                break;
            mcost = mcost - roads.get(i) + 1;
            superroad++;
        }

        return mcost <= K ? superroad : -1;
    }


    public static int findPar(int u) {
        if (par[u] == u)
            return u;
        return par[u] = findPar(par[u]);
    }
}