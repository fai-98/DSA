public class acquire_and_release {
	//Acquire and release group*********************************************************

	// Smallest Substring Of A String Containing All Characters Of Another String
	// 76. Minimum Window Substring

	public static String minWindow(String s1, String s2) {
		//make fmap of s2
		Map<Character, Integer> fmap = new HashMap<>();
		for (char ch : s2.toCharArray()) {
			fmap.put(ch, fmap.getOrDefault(ch, 0) + 1);
		}

		Map<Character, Integer> map = new HashMap<>();
		int st = 0, end = 0, len = (int)1e9, head = 0, count = s2.length();

		while (true) {
			boolean acFlag = false; // acquire flag
			boolean reFlag = false; // release flag
			//acquire
			while (end < s1.length() && count > 0) {
				//acq
				char ch = s1.charAt(end);
				map.put(ch, map.getOrDefault(ch, 0) + 1);
				//count manage
				if (fmap.containsKey(ch) && map.get(ch) <= fmap.get(ch)) {
					count--;
				}
				end++;
				acFlag = true;
			}

			//Release until valid / make window invalid
			while (count == 0) {
				//make ans
				if (end - st < len) {
					len = end - st;
					head = st;
				}
				//rel
				char ch = s1.charAt(st);
				map.put(ch, map.get(ch) - 1);

				if (fmap.containsKey(ch) && map.get(ch) < fmap.get(ch)) {
					count++;
				}

				st++;
				reFlag = true;
			}

			if (acFlag == false && reFlag == false) {
				break;
			}
		}
		return len == (int)1e9 ? "" : s1.substring(head, head + len);
	}

	//ALternate Code using only 2 while loops ?
	public String minWindow(String s, String t) {
		int st = 0, end = 0, len = (int) 1e9, missing = 0, head = 0;

		Map < Character, Integer > map = new HashMap < > ();

		for (char ch : t.toCharArray()) {
			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}

		missing = map.size();
		//Acquire
		while (end < s.length()) {
			char ch = s.charAt(end);
			if (map.containsKey(ch)) {
				map.put(ch, map.get(ch) - 1);
				if (map.get(ch) == 0) {
					missing--; // req number of that char found
				}
			}

			end++;

			while (missing == 0) {
				char ch2 = s.charAt(st);
				if (map.containsKey(ch2)) {
					map.put(ch2, map.get(ch2) + 1);
					if (map.get(ch2) > 0) missing++; //we are leaving char out of the window
				}

				if (end - st < len) {
					head = st;
					len = end - st;
				}

				st++;
			}
		}

		return len == (int) 1e9 ? "" : s.substring(head, head + len);
	}

	// Smallest Substring Of A String Containing All Unique Characters Of Itself
	public static int solution(String str) {
		Set<Character> set = new HashSet<>();
		for (char ch : str.toCharArray()) {
			set.add(ch);
		}

		Map<Character, Integer> map = new HashMap<>();
		int st = 0, end = 0, len = (int)1e9, head = 0, count = set.size();

		while (true) {
			boolean acFlag = false; // acquire flag
			boolean reFlag = false; // release flag
			//acquire
			while (end < str.length() && count > 0) {
				//acq
				char ch = str.charAt(end);
				map.put(ch, map.getOrDefault(ch, 0) + 1);
				//count manage
				if (set.contains(ch) && map.get(ch) == 1) {
					count--;
				}
				end++;
				acFlag = true;
			}

			//Release until valid / make window invalid
			while (count == 0) {
				//make ans
				if (end - st < len) {
					len = end - st;
					head = st;
				}
				//rel
				char ch = str.charAt(st);
				map.put(ch, map.get(ch) - 1);

				if (set.contains(ch) && map.get(ch) == 0) {
					count++;
				}

				st++;
				reFlag = true;
			}

			if (acFlag == false && reFlag == false) {
				break;
			}
		}
		return len ;
	}

	// 3. Longest Substring Without Repeating Characters
	public static int solution(String str) {
		Map < Character, Integer > map = new HashMap < > ();
		int st = 0, end = 0, len = -(int) 1e9, head = 0, extra = 0;

		while (true) {
			boolean acFlag = false; // acquire flag
			boolean reFlag = false; // release flag
			//acquire
			while (end < str.length() && extra == 0) {
				//acq
				char ch = str.charAt(end);
				map.put(ch, map.getOrDefault(ch, 0) + 1);
				//count manage
				end++;
				if (map.get(ch) > 1) {
					extra++;
					break;
				} else {
					len = Math.max(len, end - st);
				}

				acFlag = true;
			}

			//extra/rep chars are present
			//Release until window is valid again
			while (extra > 0) {
				//rel
				char ch = str.charAt(st);
				map.put(ch, map.get(ch) - 1);

				if (map.get(ch) == 1) {
					extra--;
				}

				st++;
				reFlag = true;
			}

			if (acFlag == false && reFlag == false) {
				break;
			}
		}
		return len;
	}

	//Alt
	public int lengthOfLongestSubstring(String s) {
		int st = 0, end = 0, len = 0, extra = 0, head = 0;

		Map < Character, Integer > map = new HashMap < > ();

		// initially st=end=0 , so all chars are extra

		while (end < s.length()) {
			char ch = s.charAt(end);
			if (map.containsKey(ch)) {
				map.put(ch, map.get(ch) + 1);
				if (map.get(ch) > 1) {
					extra++; // req number of that char found
				}
			} else {
				map.put(ch, 1);
			}
			// here end has moved 1 step ahead of the valid window end
			end++;

			while (extra > 0) {

				char ch2 = s.charAt(st);
				if (map.containsKey(ch2)) {
					map.put(ch2, map.get(ch2) - 1);
					if (map.get(ch2) == 1)
						extra--; // we are leaving char out of the window
				}

				st++;
			}

			//find max here
			len = Math.max(len , end - st);

		}

		return  len;
	}

	//Using st=-1, and end = -1 is better than 0, it's easy to manage

	// Count Of Substrings Having All Unique Characters
	public static int solution(String str) {
		Map < Character, Integer > map = new HashMap < > ();
		int st = -1, end = -1, count = 0, head = 0;

		while (true) {
			boolean acFlag = false; // acquire flag
			boolean reFlag = false; // release flag
			//acquire
			while (end < str.length() - 1) {
				acFlag = true;
				end++;
				//acq
				char ch = str.charAt(end);
				map.put(ch, map.getOrDefault(ch, 0) + 1);

				if (map.get(ch) > 1) {
					break;
				} else {
					count += end - st;
				}
			}

			//Release until valid / make window invalid
			while (st < end) {
				reFlag = true;
				st++;
				//rel
				char ch = str.charAt(st);
				map.put(ch, map.get(ch) - 1);

				if (map.get(ch) == 1) {
					count += end - st;
					break;
				}
			}

			if (acFlag == false && reFlag == false) {
				break;
			}
		}
		return count;
	}

	// Longest Substring With Exactly K Unique Characters
	public static int solution(String str, int k) {
		Map < Character, Integer > map = new HashMap < > ();
		int st = -1, end = -1, len = -1, head = 0;


		while (true) {
			boolean acFlag = false;
			boolean reFlag = false;

			while (end < str.length() - 1) {
				acFlag = true;
				end++;

				char ch = str.charAt(end);
				map.put(ch, map.getOrDefault(ch, 0) + 1);

				if (map.size() > k) {
					break;
				} else if (map.size() == k) {
					len = Math.max(len, end - st);
				}
			}

			while (st < end) {
				reFlag = true;
				st++;

				char ch = str.charAt(st);
				map.put(ch, map.get(ch) - 1);

				if (map.get(ch) == 0) {
					map.remove(ch);
					break;
				}
			}

			if (acFlag == false && reFlag == false)break;
		}

		return len;
	}


	// Count Of Substrings With Exactly K Unique Characters (V.V.I)


	// Maximum Consecutive Ones - 1 / Leetcode 487.
	public static int solution(int[] arr) {
		int st = -1, end = -1, len = -(int)1e9, zero = 0;

		while (end < arr.length - 1) {
			end++;
			if (arr[end] == 0) {
				zero++;
			}

			while (zero > 1) {
				st++;
				if (arr[st] == 0) {
					zero--;
				}
			}

			len = Math.max(len, end - st);
		}

		return len;
	}


	// Maximum Consecutive Ones - 2 / 1004. Max Consecutive Ones III
	public static int solution(int[] arr, int k) {
		int st = -1, end = -1, len = -(int)1e9, zero = 0;

		while (end < arr.length - 1) {
			end++;
			if (arr[end] == 0) {
				zero++;
			}

			while (zero > k) {
				st++;
				if (arr[st] == 0) {
					zero--;
				}
			}

			len = Math.max(len, end - st);
		}

		return len;
	}

	// longest subarray with contiguous elements
	// if elements can be arranged in contiguous order
	// diff bw max(arr) - min(arr) == last idx - first idx
	public static int solution(int[] arr) {
		int len = -(int)1e9;

		for (int i = 0; i < arr.length; i++) {
			int min = (int)1e9;
			int max = -(int)1e9;
			Set<Integer> set = new HashSet<>();

			for (int j = i; j < arr.length; j++) {
				if (set.contains(arr[j])) { //for dups
					break;
				}
				set.add(arr[j]);

				min = Math.min(arr[j], min);
				max = Math.max(max, arr[j]);

				if (max - min == j - i) {
					len = Math.max(len, j - i + 1);
				}

			}
		}

		return len;
	}

	// Count Of Substrings With Exactly K Unique Characters
	// WA , re-do
	public static int solve_k1(String str) {
		int st = -1, end = -1, count = 0;
		Map<Character, Integer> map = new HashMap<>();

		while (true) {
			boolean f1 = false;
			boolean f2 = false;

			while (end < str.length() - 1) {
				f1 = true;
				end++;
				char ch = str.charAt(end);
				map.put(ch, map.getOrDefault(ch, 0) + 1);

				if (map.size() == 2) {
					end--;
					removeFromMap(map, ch);
					break;
				}
			}

			while (st < end) {
				f2 = true;
				if (map.size() == 1) {
					count += end - st;
				}

				end++;

				char ch = str.charAt(st);
				removeFromMap(map, ch);

				if (map.size() == 0) {
					break;
				}

			}

			if (f1 == false && f2 == false)break;
		}

		return count;
	}
	public static int solution(String str, int k) {

		if (k == 1) {
			return solve_k1(str);
		}

		int is = -1, ib = -1, j = -1, count = 0;
		Map<Character, Integer> s_map = new HashMap<>();
		Map<Character, Integer> b_map = new HashMap<>();

		while (true) {
			boolean f1 = false;
			boolean f2 = false;
			boolean f3 = false;


			//acquire big   k map
			while (ib < str.length() - 1) {
				f2 = true;
				ib++;
				char ch = str.charAt(ib);
				b_map.put(ch, b_map.getOrDefault(ch, 0) + 1);

				if (b_map.size() == k + 1) {
					ib--;
					removeFromMap(b_map, ch);
					break;
				}
			}

			//acquire small (k-1)
			while (is < ib) {
				f1 = true;
				is++;
				char ch = str.charAt(is);
				s_map.put(ch, s_map.getOrDefault(ch, 0) + 1);

				if (s_map.size() == k) {
					is--;
					removeFromMap(s_map, ch);
					break;
				}
			}

			//release both
			while (j < is ) {
				if (s_map.size() == k - 1 && b_map.size() == k) {
					count += ib - is;
				}

				j++;
				char ch = str.charAt(j);
				removeFromMap(s_map, ch);
				removeFromMap(b_map, ch);

				//invalid
				if (s_map.size() < k - 1 || b_map.size() < k ) {
					break;
				}
				//conditional break
				if (f1 == false && f2 == false && f3 == false)break;
			}

			return count;
		}
	}
	public static void removeFromMap(Map<Character, Integer> map, Character ch) {
		if (map.get(ch) == 1) {
			map.remove(ch);
		} else {
			map.put(ch, map.get(ch) - 1);
		}
	}

	//09-Jan-22

	// 386. Longest Substring with At Most K Distinct Characters - Lintcode
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		int aq = -1, rel = -1, len = 0;
		Map<Character, Integer> map = new HashMap<>();

		while (true) {
			boolean aqFlag = false;
			boolean reFlag = false;

			while (aq < s.length() - 1) {
				aqFlag = true;
				aq++;

				char ch = s.charAt(aq);
				map.put(ch, map.getOrDefault(ch, 0) + 1);

				if (map.size() == k + 1) {
					break;
				} else {
					len = Math.max(len, aq - rel);
				}
			}

			while (rel < aq) {
				reFlag = true;
				rel++;

				char ch = s.charAt(rel);
				map.put(ch, map.getOrDefault(ch, 0) - 1);
				if (map.get(ch) == 0) {
					map.remove(ch);
					break;
				}
			}

			if (aqFlag == false && reFlag == false)break;

		}
		return len;
	}

	// Count Of Substrings Having At Most K Unique Characters
	public static int solution(String s, int k) {
		int aq = -1, rel = -1, count = 0;
		Map<Character, Integer> map = new HashMap<>();

		while (true) {
			boolean aqFlag = false;
			boolean reFlag = false;

			while (aq < s.length() - 1) {
				aqFlag = true;
				aq++;

				char ch = s.charAt(aq);
				map.put(ch, map.getOrDefault(ch, 0) + 1);

				if (map.size() == k + 1) {
					break;
				} else {
					count += aq - rel;
				}
			}

			if (aq == s.length() - 1 && map.size() <= k) {
				break;
			}

			while (rel < aq) {
				reFlag = true;
				rel++;

				char ch = s.charAt(rel);
				map.put(ch, map.getOrDefault(ch, 0) - 1);
				if (map.get(ch) == 0)map.remove(ch);

				if (map.size() == k) {
					count += aq - rel;
					break;
				}
			}

			if (aqFlag == false && reFlag == false)break;

		}

		return count;
	}


	// 904. Fruit Into Baskets
	public int totalFruit(int[] arr) {
		Map < Integer, Integer > map = new HashMap < > ();
		int acq = -1, rel = -1, len = 0;

		while (acq < arr.length - 1) {
			acq++;
			int num = arr[acq];

			//acquire
			map.put(num, map.getOrDefault(num, 0) + 1);

			while (map.size() > 2) {
				rel++;
				num = arr[rel];
				if (map.get(num) == 1) {
					map.remove(num);
				} else {
					map.put(num, map.get(num) - 1);
				}
			}
			//if we reach here, it is surely valid win , update max
			len = Math.max(len, acq - rel);
		}

		return len;
	}

	// 187. Repeated DNA Sequences
	public List < String > findRepeatedDnaSequences(String s) {
		List < String > res = new ArrayList < > ();
		int n = s.length();
		if (s == null || n <= 10) return res;
		Set < String > set = new HashSet < > ();
		Set < String > vis = new HashSet < > ();

		int i = 0, j = 10;
		while (j <= s.length()) {
			String str = s.substring(i, j);
			if (set.contains(str) && !vis.contains(str)) {
				res.add(str);
				vis.add(str);
			} else set.add(str);
			i++;
			j++;
		}
		return res;
	}

}