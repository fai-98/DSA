import java.io.*;
import java.util.*;

public class MrPresident {

    static long mod = (long) (1e9 + 7);

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int N = sc.ni();
        int M = sc.ni();
        long K = sc.nl();

        int[][] edges = new int[M][3];

        for (int i = 0; i < M; i++) {
            edges[i][0] = sc.ni();
            edges[i][1] = sc.ni();
            edges[i][2] = sc.ni();
        }

        int res = mrPresident(edges, N, K);
        out.print(res);
        out.close();
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

    static class Scanner {

        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int ni() throws IOException {
            return Integer.parseInt(next());
        }

        public long nl() throws IOException {
            return Long.parseLong(next());
        }

        public int[] nia(int n) throws IOException {
            int a[] = new int[n];
            String sa[] = br.readLine().split(" ");
            for (int i = 0; i < n; i++)
                a[i] = Integer.parseInt(sa[i]);
            return a;
        }

        public long[] nla(int n) throws IOException {
            long a[] = new long[n];
            String sa[] = br.readLine().split(" ");
            for (int i = 0; i < n; i++)
                a[i] = Long.parseLong(sa[i]);
            return a;
        }

        public int[] sort(int[] a) {
            ArrayList<Integer> l = new ArrayList<>();
            for (int v : a)
                l.add(v);
            Collections.sort(l);
            for (int i = 0; i < a.length; i++)
                a[i] = l.get(i);
            return a;
        }

        public long[] sort(long[] a) {
            ArrayList<Long> l = new ArrayList<>();
            for (long v : a)
                l.add(v);
            Collections.sort(l);
            for (int i = 0; i < a.length; i++)
                a[i] = l.get(i);
            return a;
        }
    }
}