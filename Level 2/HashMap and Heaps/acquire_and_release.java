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

		Map<Character, Integer> map = new HashMap<>();
		int st = 0, end = 0, len = -(int)1e9, head = 0, extra = 0;

		while (true) {
			boolean acFlag = false; // acquire flag
			boolean reFlag = false; // release flag
			//acquire
			while (end < str.length() && extra == 0) {
				//acq
				char ch = str.charAt(end);
				map.put(ch, map.getOrDefault(ch, 0) + 1);
				//count manage
				if (map.get(ch) > 1) {
					extra++;
				}
				end++;
				acFlag = true;
			}

			//Release until valid / make window invalid
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

			len = Math.max(len , end - st);

			if (acFlag == false && reFlag == false) {
				break;
			}
		}
		return len ;
	}

	//incorrect ans , do again

}