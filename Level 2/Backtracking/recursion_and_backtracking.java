public class recursion_and_backtracking {

	// 93. Restore IP Addresses
	List < String > res;
	public List < String > restoreIpAddresses(String s) {
		res = new ArrayList < > ();
		if (s.length() > 12) return res;
		dfs("", s, 0, 0, s.length());
		return res;
	}

	public void dfs(String asf, String s, int idx, int ct, int n) {

		if (ct == 4 && idx == n) {
			res.add(asf.substring(0, asf.length() - 1));
			return;
		}

		for (int i = idx + 1; i <= s.length() && i <= idx + 3; i++) {
			String str = s.substring(idx, i);
			int num = Integer.parseInt(str);

			if (i == idx + 2) {
				if (num >= 10) {
					dfs(asf + str + ".", s, i, ct + 1, n);
				}
			} else if (i == idx + 3) {
				if (num >= 100 && num <= 255) {
					dfs(asf + str + ".", s, i, ct + 1, n);
				}
			} else {
				dfs(asf + str + ".", s, i, ct + 1, n);
			}
		}

		return;
	}
}