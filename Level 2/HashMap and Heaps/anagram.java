public class anagram {

	// 242. Valid Anagram
	public boolean hashSol(String s1 , String s2) {
		Map<Character, Integer> map = new HashMap<>();
		if (s1.length() != s2.length()) {
			return false;
		}
		//fmap of s1
		for (char ch : s1.toCharArray()) {
			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}
		//compare
		for (char ch : s2.toCharArray()) {
			if (!map.containsKey(ch)) {
				return false;
			}
			map.put(ch, map.get(ch) - 1);
			if (map.get(ch) == 0)map.remove(ch);
		}
		return map.size() == 0;
	}

	// Lintcode 813 · Find Anagram Mappings
	public int[] anagramMappings(int[] A, int[] B) {
		Map<Integer, LinkedList<Integer>> map = new HashMap<>();
		for (int i = 0; i < B.length; i++) {
			if (map.containsKey(B[i])) {
				map.get(B[i]).add(i);
			} else {
				LinkedList<Integer> list = new LinkedList<>();
				list.add(i);
				map.put(B[i], list);
			}
		}

		int[] res = new int[A.length];

		for (int i = 0; i < A.length; i++) {
			LinkedList<Integer> list = map.get(A[i]);
			int bIdx = list.remove();
			if (list.size() == 0)map.remove(A[i]);

			res[i] = bIdx;
		}

		return res;
	}

	// 438. Find All Anagrams in a String (V.V.I)

	// k Anagrams
	public static boolean areKAnagrams(String str1, String str2, int k) {

		if (str1.length() != str2.length()) {
			return false;
		}

		Map<Character, Integer> map = new HashMap<>();

		int change = 0;

		for (char ch : str1.toCharArray()) {
			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}

		for (char ch : str2.toCharArray()) {
			if (map.containsKey(ch) && map.get(ch) >= 1) {
				map.put(ch, map.get(ch) - 1);
			}
		}

		for (char key : map.keySet()) {
			change += map.get(key);
		}

		return change <= k;
	}

	//49. Group Anagrams

	//123 ms
	public List<List<String>> groupAnagrams(String[] strs) {
		Map<Map<Character, Integer>, List<String>> map = new HashMap<>();

		for (String str : strs) {
			Map<Character, Integer> fmap = new HashMap<>();

			for (char ch : str.toCharArray()) {
				fmap.put(ch, fmap.getOrDefault(ch, 0) + 1);
			}

			if (map.containsKey(fmap)) {
				List list = map.get(fmap);
				list.add(str);
				map.put(fmap, list);
			} else {
				List<String> list = new ArrayList<>();
				list.add(str);
				map.put(fmap, list);
			}
		}

		List<List<String>> res = new ArrayList<>();
		for (List<String> l : map.values()) {
			res.add(l);
		}
		return res;
	}

	// 5 ms
	public List < List < String >> groupAnagrams(String[] strs) {
		Map < String, Integer > map = new HashMap < > ();
		List < List < String >> res = new ArrayList < > ();
		int idx = 0;

		for (String str : strs) {

			String s = sortString(str);
			if (map.containsKey(s)) {
				int i = map.get(s);
				res.get(i).add(str);
			} else {
				map.put(s, idx);
				res.add(new ArrayList < > ());
				int i = map.get(s);
				res.get(i).add(str);
				idx++;
			}
		}

		return res;
	}

	public static String sortString(String inputString) {
		// convert input string to char array
		char tempArray[] = inputString.toCharArray();

		// sort tempArray
		Arrays.sort(tempArray);

		// return new sorted string
		return new String(tempArray);
	}

	// 249. Group Shifted Strings
	public static ArrayList<ArrayList<String>> groupShiftedStrings(String[] strs) {
		Map<String, ArrayList<String>> map = new HashMap<>();

		for (String str : strs) {
			String key = ".";

			for (int i = 1; i < str.length(); i++) {
				int val = str.charAt(i) - str.charAt(i - 1);
				if (val < 0) val += 26;
				key += val + "#";
			}

			if (map.containsKey(key)) {
				ArrayList list = map.get(key);
				list.add(str);
				map.put(key, list);
			} else {
				ArrayList<String> list = new ArrayList<>();
				list.add(str);
				map.put(key, list);
			}
		}
	}


	// 205. Isomorphic Strings
	public boolean isIsomorphic(String s, String t) {
		if (s.length() != t.length()) return false;

		Map < Character, Character > map = new HashMap < > ();
		Set<Character> set = new HashSet<>();

		for (int i = 0; i < s.length(); i++) {
			char ch1 = s.charAt(i);
			char ch2 = t.charAt(i);

			if (map.containsKey(ch1)) {
				if (map.get(ch1) != ch2)
					return false;
			} else {
				if (set.contains(ch2)) { //ch2 mapped with another char
					return false;
				} else {
					map.put(ch1, ch2);
					set.add(ch2); //ch2 is mapped
				}
			}
		}

		return true;
	}

	// 290. Word Pattern
	public boolean wordPattern(String pattern, String s) {
		Map < Character, String > map = new HashMap < > ();
		Set<String> set = new HashSet<>();

		String[] strs = s.split("\\s");

		if (pattern.length() != strs.length)return false;

		for (int i = 0; i < pattern.length(); i++) {
			char ch1 = pattern.charAt(i);
			String str = strs[i];

			if (map.containsKey(ch1)) {
				if (!map.get(ch1).equals(str))
					return false;
			} else {
				if (set.contains(str)) { //ch2 mapped with another char
					return false;
				} else {
					map.put(ch1, str);
					set.add(str); //ch2 is mapped
				}
			}
		}

		return true;
	}


	// Check Arithmetic Sequence Easy
	// Rabbits In The Forest Medium
	// Recurring Sequence In A Fraction Hard
	// Equivalent Subarrays Medium
	// Pairs With Equal Sum

	//16.Jan.2022
	// Pairs With Given Sum In Two Sorted Matrices

	// Smallest Subarray With All Occurrences Of The Most Frequent Element
	public static void solution(int[] arr) {
		Map<Integer, Integer> fmap = new HashMap<>(); //freq
		Map<Integer, Integer> imap = new HashMap<>(); // st idx

		int si = 0, ei = 0, len = 0, maxFreq = 0;
		for (int i = 0; i < arr.length; i++) {
			int val = arr[i];

			//freq
			if (fmap.containsKey(val)) {
				fmap.put(val, fmap.get(val) + 1);
			} else {
				fmap.put(val, 1);
				imap.put(val, i);
			}

			// updation
			if (maxFreq < fmap.get(val)) {
				//update max , si , ei
				maxFreq = fmap.get(val);
				si = imap.get(val);
				ei = i;
				len = ei - si + 1;
			} else if (maxFreq == fmap.get(val)) {
				//update si,ei, if len is shorter
				int nLen = i - imap.get(val) + 1;
				if (nLen < len) {
					len = nLen;
					ei = i;
					si = imap.get(val);
				}

			} else {
				//nothing to do
			}
		}

		System.out.println(arr[si]);
		System.out.println(si);
		System.out.println(ei);
	}

	// 914. X of a Kind in a Deck of Cards
	public boolean hasGroupsSizeX(int[] deck) {
		int freq = 0;
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < deck.length; i++) {
			map.put(deck[i], map.getOrDefault(deck[i], 0) + 1);
		}

		int ans = 0;
		for (int key : map.keySet()) {
			int val = map.get(key);
			ans = gcd(ans, val);
		}

		return ans >= 2;
	}

	public int gcd(int a, int b) {
		if (b == 0)return a;
		else return gcd(b, a % b);
	}

	// 554. Brick Wall
	public int leastBricks(List < List < Integer >> wall) {
		//The Vertical Line with most no. of gaps will have least cuts
		Map < Integer, Integer > map = new HashMap < > (); //index, no. of gaps
		int max = 0, layers = wall.size();
		for (List < Integer > layer : wall) {
			int preSum = 0;
			for (int i = 0; i < layer.size() - 1; i++) { //ignore last brick
				int sz = layer.get(i);
				preSum += sz;
				map.put(preSum, map.getOrDefault(preSum, 0) + 1);

				//update max
				max = Math.max(map.get(preSum), max);
			}
		}

		return layers - max;
	}
}