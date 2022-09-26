public class frog_jump {

	//HashMap + HashSet
	// if we reached at curr pos with jump = k
	// explore the 3 next possible pos and update the jumps allowed from there before hand
	// ex ->[0,1,3,5,6,8,12,17]
	// when we are at 0 we update jums of 1 to be 1
	// when we are at 1 , we update jumps of 3 = 1,2,3 -> reach 3 with jump = 2 , updated 2-1,2,2+1 for 3

	//Recursive (TLE)
	public boolean canCross(int[] stones) {
		Set < Integer > set = new HashSet < > ();
		//when at 0 , it can only make jump of 1 len
		if (stones[1] != 1) return false;

		for (int i = 2; i < stones.length; i++) {
			if (i > 0 && stones[i] > (i * (i + 1)) / 2) {
				return false;
			}
			set.add(stones[i]);
		}
		return can_frog_reach(stones, 1, 1, set);
	}

	public boolean can_frog_reach(int[] arr, int pos, int jump, Set < Integer > set) {

		if (arr[arr.length - 1] == pos) {
			return true;
		}

		if (set.contains(pos + jump + 1)) {
			if (can_frog_reach(arr, pos + jump + 1, jump + 1, set) == true) return true;
		}
		if (set.contains(pos + jump)) {
			if (can_frog_reach(arr, pos + jump, jump, set) == true) return true;
		}
		if (jump > 1 && set.contains(pos + jump - 1)) {
			if (can_frog_reach(arr, pos + jump - 1, jump - 1, set) == true) return true;
		}

		return false;
	}
}