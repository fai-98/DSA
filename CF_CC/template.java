import java.io.*;
import java.util.*;

public class A {

	static long mod = (long) (1e9 + 7);

	public static void main(String[] args) throws IOException {
		Scanner scn = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		int T = scn.ni(), tcs = 0;
		A: while (tcs++ < T) {

			// sb.append(min);
			// sb.append("\n");
		}
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


//make a proper CF Java template which is suitable for me 