import java.io.*;
import java.util.*;

// https://leetcode.com/problems/delete-and-earn/description/
public class cf_455a {

	static long mod = (long) (1e9 + 7);

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		int n = sc.ni(), tcs = 0;
        
        long res = 1; // Initialize result
        long x = 1378, y = (long)n, p = 1000000007; 
        x = x % p;
    
        while (y > 0)
        {
            if ((y & 1) > 0)
                res = (res * x) % p;

            y = y >> 1; 
            x = (x * x) % p;
        }

		sb.append(res % 10);
		out.print(sb);
		out.close();
	}

	public static int count(int a, int b) {
		if (b == 1)
			return Integer.MAX_VALUE;
		int count = 0;
		while (a > 0) {
			a /= b;
			count++;
		}
		return count;
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