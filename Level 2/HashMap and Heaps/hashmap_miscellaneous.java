public class hashmap_miscellaneous {

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

	//387. First Unique Character in a String

	public int firstUniqChar(String s) {
		int[] map = new int[256];
		Arrays.fill(map, -1);

		//Single Iteration + Count Array
		//-1 means zero ocuurence
		//val >= 0 means first idx
		//-2 means occurence >= 2

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			int idx = ch - '0';

			if (map[idx] == -1) { //zero occur..
				map[idx] = i; //first occur
			} else {
				map[idx] = -2; // >=2
			}
		}

		int min = (int) 1e9;

		for (int idx : map) {
			if (idx >= 0) {
				min = Math.min(min, idx);
			}
		}

		return min == (int) 1e9 ? -1 : min;
	}

	// 763. Partition Labels
	public List < Integer > partitionLabels(String s) {
		Map < Character, Integer > map = new HashMap < > ();
		//map of ending indexes of each char
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			map.put(ch, i);
		}

		List < Integer > res = new ArrayList < > ();
		//the char with the farthest effect will decide the chunk size
		//merge all other chars will less effect range
		int max = -(int) 1e9;
		int prev = 0;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			max = Math.max(max, map.get(ch));
			//if reached end of chunk
			if (max == i) {
				res.add(i - prev + 1);
				prev = i + 1; //si of next chunk
			}
		}

		return res;
	}


	// 380. Insert Delete GetRandom O(1)
	/** Initialize your data structure here. */
	Map < Integer, Integer > map;
	List < Integer > vals;
	public RandomizedSet() {
		map = new HashMap < > (); //val v/s its idx in arraylist
		vals = new ArrayList < > (); // contains all keys
	}

	/** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
	public boolean insert(int val) {
		if (map.containsKey(val)) {
			return false;
		} else {
			int idx = vals.size();
			map.put(val, idx);
			vals.add(val);
			return true;
		}
	}

	/** Removes a value from the set. Returns true if the set contained the specified element. */
	public boolean remove(int val) {
		if (map.containsKey(val)) {
			int idx = map.get(val);
			//not last val , so del is O(n) -> idx shifting , swap
			int lastVal = vals.get(vals.size() - 1);
			if (lastVal != val) {
				Collections.swap(vals, idx, vals.size() - 1);
				//also update new pos of lastVal in map
				map.put(lastVal, idx); //old pos of del val = new pos of lastVal
			}
			//now remove from last O(1);
			vals.remove(vals.size() - 1);
			map.remove(val);
			return true;
		} else {
			return false;
		}
	}

	/** Get a random element from the set. */
	public int getRandom() {
		return vals.get(new Random().nextInt(vals.size()));
	}


	// 381. Insert Delete GetRandom O(1) - Duplicates allowed

	Map < Integer, Set < Integer >> map;
	List < Integer > list;
	public RandomizedCollection() {
		map = new HashMap < > (); //val v/s its idx in Set
		list = new ArrayList < > (); // contains all keys
	}

	public boolean insert(int val) {
		int idx = list.size();

		if (map.containsKey(val)) {
			map.get(val).add(idx);
			list.add(val);
			return false;
		} else {
			map.put(val, new HashSet < > ());
			map.get(val).add(idx);
			list.add(val);
			return true;
		}
	}

	public boolean remove(int val) {
		if (map.containsKey(val)) {
			Set < Integer > set = map.get(val);
			int idx = 0;

			//if it is last val in list
			int lastIdx = list.size() - 1;

			if (val == list.get(lastIdx)) {
				list.remove(lastIdx); //remove from list
				map.get(val).remove(lastIdx);
			} else {
				for (int num : set) { //find ant idx to remove
					idx = num;
					break;
				}

				//swap with last in list
				Collections.swap(list, idx, lastIdx);
				list.remove(lastIdx); //remove from list
				map.get(val).remove(idx); //remove that idx from map;

				//now the val which was at index = last in list has new index = idx
				int swapped_val = list.get(idx);
				map.get(swapped_val).remove(lastIdx); //prev it was at last idx
				map.get(swapped_val).add(idx); //now it is at idx
			}

			if (map.get(val).size() == 0) map.remove(val);
			return true;
		} else {
			return false;
		}
	}

	public int getRandom() {
		return list.get(new Random().nextInt(list.size()));
	}


	// 1488. Avoid Flood in The City
	public int[] avoidFlood(int[] rains) {
		//if rain at same lake again - then we dry it
		//we dry it after the prev rain day
		//Store days which are availabe to dry in treeset , so we can get day > prev rain
		//if first rain , store it for future ref

		Map < Integer, Integer > lastRain = new HashMap < > (); //lake , idx
		TreeSet < Integer > dry = new TreeSet < > (); //idx of 0 - dry day

		int[] res = new int[rains.length];

		for (int i = 0; i < rains.length; i++) {
			if (rains[i] == 0) {
				res[i] = 1; //default val
				dry.add(i); //potential dry day
			} else {
				int lake_id = rains[i];
				if (lastRain.containsKey(lake_id)) { //prev rain exists
					int prevDay = lastRain.get(lake_id);
					Integer dryDay = dry.higher(prevDay); //get pot dry day after the prev rain

					if (dryDay == null) {
						return new int[0];
					} else {
						res[dryDay] = lake_id; //dry lake
						dry.remove(dryDay);
					}
				}

				//in any case update lake idx
				lastRain.put(lake_id, i);
				res[i] = -1;
			}
		}

		return res;
	}

	// 218. The Skyline Problem  - hard

	//tiny url
	//snapshot arr
	//line reflectionn
	//reorganize str
}