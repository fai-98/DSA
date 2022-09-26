public class greedy {

	// 991. Broken Calculator
	public int brokenCalc(int X, int Y) {
		//greedily reduce Y as much as possible (less than X)
		//then add 1 X-Y times
		int res = 0;
		while (Y > X) {
			if (Y % 2 == 0) {
				Y = Y / 2;
			} else {
				Y++;
			}
			res++;
		}
		return res + X - Y;
	}

	// 2139. Minimum Moves to Reach Target Score

	public int minMoves(int target, int maxDoubles) {
		public int minMoves(int Y, int max) {
			int X = 1, res = 0; //reach Y -> X , allowed divide and minus
			if (max == 0) return Y - X;

			while (max > 0 && Y > X) {
				if (Y % 2 == 0) {
					Y = Y / 2;
					max--;
				} else {
					Y = Y - 1;
				}
				res++;
			}

			return res + Y - X; //we exit loop coz we cant divide , so only way is inc X (y-x) times
		}
	}

	// 1342. Number of Steps to Reduce a Number to Zero
	public int numberOfSteps(int num) {
		int count = 0;
		while (num != 0) {
			if (num % 2 == 0) {
				num = num / 2;
				count++;
			} else {
				num = num - 1;
				count++;
			}
		}
		return count;
	}

	// 1217. Minimum Cost to Move Chips to The Same Position
	public int minCostToMoveChips(int[] position) {
		int odd = 0 , even = 0;
		for (int p : position) {
			if (p % 2 == 1)odd++;
			else even++;
		}

		return Math.min(odd, even);
	}


	//all chips from even pos to 0 for free
	//all chips from odd pos to 1 for free
	//now 1 rs per chip for 0->1 or 1->0
	//move min chips to other side
	//cost is 1rs per chip
	//count even pos, odd pos retun min

	// 2169. Count Operations to Obtain Zero

	// 55. Jump Game

	//DP
	public boolean canJump(int[] nums) {
		return dfs(0, nums);
	}

	public boolean dfs(int idx, int[] nums) {
		if (idx >= nums.length) return false;
		if (idx == nums.length - 1) return true;

		boolean ans = false;
		for (int i = idx; i < nums[idx]; i++) {
			ans = ans || dfs(idx + i + 1 , nums);
			if (ans) return ans;
		}

		return ans;
	}

	//direction - end to st
	//meaning - dp[i] = true means we can reach end from that idx
	public boolean canJump(int[] nums) {
		boolean[] dp = new boolean[nums.length];
		dp[nums.length - 1] = true;

		for (int i = nums.length - 2; i >= 0; i--) {
			for (int j = i; j <= i + nums[i] && j < nums.length; j++) {
				if (dp[j] == true) dp[i] = true;
			}
		}

		return dp[0];
	}


	//Greedy (start from back)
	public boolean canJump(int[] nums) {
		int maxRange = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i > maxRange) return false;
			maxRange = Math.max(maxRange , i + nums[i]);
		}
		return true;
		//Why Greedy :
		//when we are at idx i , we set max range (farthest we can go from here)
		//then in that range we try all elements to get better range (so we do not skip ele)
		//if all ele in that range cannot take us to the end (i.e i exceed range without updating           it) , return false

		//ex : 2 4 1 0 0 1
		//itr1 max = 2idx
		//we dont go to idx 2 directly , now we go to idx 1 and now max is updated
	}

	public boolean canJump(int[] nums) {
		int gap = 1;
		for (int i = nums.length - 2; i >= 0; i--) {
			if (i == 0 && nums[i] >= gap)return true;
			if (nums[i] >= gap)gap = 1;
			if (nums[i] < gap)gap++;
		}

		return nums.length == 1 ? true : false;
	}


	// 45. Jump Game II

	//DP
	public int jump(int[] nums) {
		int[] dp = new int[nums.length];
		Arrays.fill(dp, (int)1e9);
		return min_jumps(nums, 0, nums.length - 1, dp);
		// return tab(nums);
	}

	public int min_jumps(int[] nums , int idx , int end, int[] dp) {

		if (idx == end) {
			return dp[idx] = 0;
		}

		if (dp[idx] != (int)1e9) {
			return dp[idx];
		}

		int ans = (int)1e9;
		for (int jump = 1; jump <= nums[idx]; jump++) {
			if (idx + jump <= end) {
				ans = Math.min(ans , 1 + min_jumps(nums, idx + jump, end, dp));
			}
		}

		return dp[idx] = ans;
	}

	//BFS / Greedy Solution
	public int jump(int[] nums) {
		// BFS/Greedy approach
		int l = 0, r = 0, jumps = 0;

		while (r < nums.length - 1) {
			int max_range = 0;
			for (int i = l; i <= r; i++) {
				max_range = Math.max(max_range , i + nums[i]);
			}

			l = r + 1;
			r = max_range;
			jumps++; //level
		}

		return jumps;
	}

	//*********************************************4 Similar*******************************************

	// 8.1953. Maximum Number of Weeks for Which You Can Work
	// 	We can complete all milestones for all projects, unless one project has
	// more milestones then all other projects combined.

	// For the latter case:

	// We can complete all milestones for other projects,
	// plus same number of milestones for the largest project,
	// plus one more milestone for the largest project.

	// After that point, we will not be able to alternate projects anymore.

	public long numberOfWeeks(int[] arr) {
		long sum = 0;
		int max = -(int) 1e9;

		for (int ele : arr) {
			sum += ele;
			max = Math.max(max, ele);
		}
		//pairing if max >= rem + 1 , max1.rem1... (rem times) + max(last)
		// (rest max ele are discarded)
		//else if max < rem (all can be paired) , 2*(sum-max)+1 will be large ,
		// so sum will get returned
		long rem = sum - max;
		if (max >= rem + 1) return 2 * rem + 1;
		return sum;
	}


	// 767. Reorganize String

	public class Pair {
		char ch;
		int fq;

		Pair() {}

		Pair(char ch, int fq) {
			this.ch = ch;
			this.fq = fq;
		}
	}

	public String reorganizeString(String s) {
		// 1953. Maximum Number of Weeks for Which You Can Work - Greedy
		// Leetcode 358
		int max = 0;
		int all = s.length();

		Map < Character, Integer > map = new HashMap < > ();

		PriorityQueue < Pair > pq = new PriorityQueue < > ((a, b) -> { //char , freq
			return b.fq - a.fq; //max heap based on freq
		});

		for (char ch : s.toCharArray()) {
			map.put(ch, map.getOrDefault(ch, 0) + 1);
			if (map.get(ch) > max) max = map.get(ch);
		}

		int remain = all - max;
		if (max > remain + 1) return ""; //pair = max -1 with all rem and in the end 1 more ch

		for (char key : map.keySet()) {
			pq.offer(new Pair(key, map.get(key)));
		}

		//now ans making is possible
		StringBuilder sb = new StringBuilder();

		//greedily choose max freq ch to add to ans (max ch shd be curr max not overall max)
		//ex : a-5 , b-4 , c-3 , after aba  now a - 4 , b-3 , c-3 so max is a
		while (pq.size() > 0) {
			Pair rem = pq.poll();

			if (sb.length() == 0 || sb.charAt(sb.length() - 1) != rem.ch) { //prev ch not same
				sb.append(rem.ch);
				rem.fq = rem.fq - 1;
			} else {         //if sb tail is same as first , pop again before push f1 back
				Pair rem2 = pq.poll();
				sb.append(rem2.ch);
				rem2.fq = rem2.fq - 1;

				if (rem2.fq != 0) pq.offer(rem2);
			}

			if (rem.fq != 0) pq.offer(rem);

		}
		return sb.toString();
	}

	// 621. Task Scheduler


	// Rearrange String k Distance Apart


	// ***********************************************************************************************


	// 630. Course Schedule III


	//9.   (134. Gas Station)
	// Circular Tour Problem
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int n = gas.length, sum = 0;
		// check if circular tour possible
		for (int i = 0; i < n; i++) {
			sum += gas[i] - cost[i];
		}

		if (sum < 0) return -1;

		//now it is surely possible
		//brute force = take every idx as st point and check
		//if at any pt , balance is -ve , take nxt st point

		//optimize , if at any pt bal is -ve , all pts before it are invalid
		int balance = 0, start = 0;
		for (int i = 0; i < n; i++) {
			balance += gas[i] - cost[i];

			if (balance < 0) {
				start = i + 1;
				balance = 0;
			}
		}

		return start;
	}

	// 1007. Minimum Domino Rotations For Equal Row
	public int minDominoRotations(int[] tops, int[] bottoms) {
		int val1 = tops[0];
		int val2 = bottoms[0];

		int count1 = 0; // rotation required to make top as val1
		int count2 = 0; // rotation required to make top as val2
		int count3 = 0; // rotation required to make bottom as val1
		int count4 = 0; // rotation required to make bottom as val2

		for (int i = 0; i < tops.length; i++) {
			if (count1 != Integer.MAX_VALUE) {
				if (tops[i] == val1) {
					// nothing to do
				} else if (bottoms[i] == val1) {
					count1++;
				} else {
					count1 = Integer.MAX_VALUE;
				}
			}

			if (count2 != Integer.MAX_VALUE) {
				if (tops[i] == val2) {
					// nothing to do
				} else if (bottoms[i] == val2) {
					count2++;
				} else {
					count2 = Integer.MAX_VALUE;
				}
			}

			if (count3 != Integer.MAX_VALUE) {
				if (bottoms[i] == val1) {
					// nothing to do
				} else if (tops[i] == val1) {
					count3++;
				} else {
					count3 = Integer.MAX_VALUE;
				}
			}

			if (count4 != Integer.MAX_VALUE) {
				if (bottoms[i] == val2) {
					// nothing to do
				} else if (tops[i] == val2) {
					count4++;
				} else {
					count4 = Integer.MAX_VALUE;
				}
			}
		}
		int res = Math.min(Math.min(count1, count2), Math.min(count3, count4));
		return res == Integer.MAX_VALUE ? -1 : res;
	}


	// 1710. Maximum Units on a Truck
	public int maximumUnits(int[][] boxTypes, int truckSize) {
		Arrays.sort(boxTypes, (a, b) -> Integer.compare(b[1], a[1]));
		int ans = 0 ;

		for (int[] arr : boxTypes) {
			if (arr[0] <= truckSize) {
				ans += arr[0] * arr[1];
				truckSize -= arr[0];
			} else {
				ans += arr[1] * truckSize;
				truckSize = 0;
			}
			if (truckSize == 0)break;
		}
		return ans;
	}

	// 1899. Merge Triplets to Form Target Triplet
	public boolean mergeTriplets(int[][] triplets, int[] target) {
		int[] temp = new int[3];
		for (int[] arr : triplets) {
			if (arr[0] <= target[0] && arr[1] <= target[1] && arr[2] <= target[2]) {
				temp[0] = Math.max(arr[0], temp[0]);
				temp[1] = Math.max(arr[1], temp[1]);
				temp[2] = Math.max(arr[2], temp[2]);
				if ((temp[0] == target[0]) && (temp[1] == target[1]) && (temp[2] == target[2])) {
					return true;
				}
			}
		}
		return false;

	}

	// 921. Minimum Add to Make Parentheses Valid
	public int minAddToMakeValid(String S) {
		Stack<Character> st = new Stack<>();
		int add = 0;
		for (int i = 0; i < S.length(); i++) {
			char ch = S.charAt(i);

			if (ch == '(') {
				st.push(ch);
			} else {
				if (st.size() != 0 && st.peek() == '(') {
					st.pop();
				} else {
					add++;
				}
			}
		}

		add = st.size() + add;
		return add;
	}

	// 135. Candy
	public int candy(int[] arr.get) {
		int n = arr.get.length;
		int res = 0;
		int[] LR = new int[n];
		int[] RL = new int[n];
		Arrays.fill(LR, 1);
		Arrays.fill(RL, 1);

		//l->r
		for (int i = 1; i < n; i++) {
			if (arr.get[i] > arr.get[i - 1]) LR[i] = LR[i - 1] + 1;
		}
		for (int i = n - 2; i >= 0; i--) {
			if (arr.get[i] > arr.get[i + 1]) RL[i] = RL[i + 1] + 1;
		}
		for (int i = 0; i < n; i++) {
			res += Math.max(LR[i], RL[i]);
		}
		return res;
	}
}
