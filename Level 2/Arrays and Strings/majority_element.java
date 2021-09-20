public class majority_element {
	// ... Moore's Voting Algorithm ***********************************************


	// 169. Majority Element  ( >N/2 times)
	public int majorityElement(int[] nums) {
		int cand = nums[0], ct = 1;
		for (int i = 1; i < nums.length; i++) {
			int ele = nums[i];

			if (ele == cand) {
				ct++;
			} else {
				if (ct > 0) {
					//pair up with the cand
					ct--;
				} else { //all prev ele are paired up
					//start a new candidate
					cand = ele;
					ct = 1;
				}
			}
		}

		//if majority element exists , its definitely = cand
		//follow up if no maj ele exists , return -1;
		int count = 0;
		for (int ele : nums) {
			if (ele == cand) count++;
		}

		return count > nums.length / 2 ? cand : -1;
	}

	//alternative
	public int majorityElement(int[] nums) {
		int candidate = 0;
		int votes = 0;
		for (int num : nums) {
			if (votes == 0)candidate = num;
			if (candidate == num)votes++;
			else votes--;
		}
		return candidate;
	}


	// 229. Majority Element II  (  >N/3 times )
	public List<Integer> majorityElement(int[] nums) {
		//make pairs of 3 ele together
		int val1 = nums[0] , count1 = 1, val2 = 1, count2 = 0 , n = nums.length;

		for (int i = 1; i < n; i++) {
			if (nums[i] == val1) {
				count1++;
			} else if (nums[i] == val2) {
				count2++;
			} else {
				if (count1 == 0) { //set new candidate
					val1 = nums[i];
					count1 = 1;
				} else if (count2 == 0) { //set new candidate
					val2 = nums[i];
					count2 = 1;
				} else {    //pair up
					count1--;
					count2--;
				}
			}
		}

		//check validity
		int freq1 = 0 , freq2 = 0;
		for (int ele : nums) {
			if (ele == val1)
				freq1++;
			else if (ele == val2)
				freq2++;
		}

		List<Integer> res = new ArrayList<>();
		if (freq1 > n / 3) res.add(val1);
		if (freq2 > n / 3) res.add(val2);

		return res;

	}


	//General >n/k times
	// 1. using HashMap
	// 2. using pairing of k items , using k-1 vals and counts
	// use k-1 size arr vals and count

	public List<Integer> majorityK(int[] nums , int k) {
		int n = nums.length;
		int[] val = new int[k - 1];
		int[] count = new int[k - 1];


	}

}