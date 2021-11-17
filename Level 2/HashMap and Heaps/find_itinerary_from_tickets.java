import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int noofpairs_src_des = scn.nextInt();
		HashMap<String, String> map = new HashMap<>();
		for (int i = 0; i < noofpairs_src_des; i++) {
			String s1 = scn.next();
			String s2 = scn.next();
			map.put(s1, s2);
		}

		//write your code here
		Map<String, Boolean> mp = new HashMap<>();

		//write your code here
		for (String src : map.keySet()) {
			String des = map.get(src);

			mp.put(des, false); //already des , cant be st pt

			//if not pres , mark as potential start pt.
			if (mp.containsKey(src) == false) {
				mp.put(src, true);
			}
		}

		String src = "";
		for (String key : mp.keySet()) {
			if (mp.get(key) == true) {
				src = key;
				break;
			}
		}


		while (map.containsKey(src)) {
			System.out.print(src + " -> ");
			src = map.get(src);
		}

		System.out.print(src + ".");
	}
}
