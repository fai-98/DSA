class Solution {
	public boolean canArrange(int[] arr, int k) {
		//make map of remainder
		// if rem == 0 (freq shd be even)
		// if rem = k/2 freq even
		// rem k freq shd be eq to rem k-x

		Map<Integer, Integer> map = new HashMap<>();
		for (int val : arr) {
			int rem = val % k;
			if (rem < 0) rem += k;
			map.put(rem, map.getOrDefault(rem, 0) + 1);
		}

		for (int rem : map.keySet()) {

			if (rem == 0) {
				int freq = map.get(rem);
				if (freq % 2 == 1) {
					return false;
				}
			} else if (rem * 2 == k) {
				int freq = map.get(rem);
				if (freq % 2 == 1 ) {
					return false;
				}
			} else {
				int oval = k - rem ; //k-x
				int fq = map.getOrDefault(oval, 0);
				if (fq != map.get(rem)) {
					return false;
				}
			}
		}
		return true;
	}
}