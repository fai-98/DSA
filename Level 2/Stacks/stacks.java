public class stacks {

	// Next Greater Element To The Right
	public static int[] ngetr(int[] arr) {
		int[] res = new int[arr.length];
		Stack<Integer> st = new Stack<>();

		st.push(0);
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] <= arr[st.peek()]) {
				st.push(i);
			} else {
				while (!st.isEmpty() && arr[i] > arr[st.peek()]) {
					int idx = st.pop();
					res[idx] = arr[i];
				}
				st.push(i);
			}
		}

		while (!st.isEmpty()) {
			res[st.pop()] = -1;
		}
		return res;
	}

	// Next Greater Element To The Left
	public static int[] ngetl(int[] arr) {
		int[] res = new int[arr.length];
		Stack<Integer> st = new Stack<>();

		st.push(arr.length - 1);
		for (int i = arr.length - 2; i >= 0; i--) {
			if (arr[i] <= arr[st.peek()]) {
				st.push(i);
			} else {
				while (!st.isEmpty() && arr[i] > arr[st.peek()]) {
					int idx = st.pop();
					res[idx] = arr[i];
				}
				st.push(i);
			}
		}

		while (!st.isEmpty()) {
			res[st.pop()] = -1;
		}
		return res;
	}


	// Next Smaller Element To The Right
	public static int[] nsetr(int[] arr) {
		int[] res = new int[arr.length];
		Stack<Integer> st = new Stack<>();

		st.push(0);
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] >= arr[st.peek()]) {
				st.push(i);
			} else {
				while (!st.isEmpty() && arr[i] < arr[st.peek()]) {
					int idx = st.pop();
					res[idx] = arr[i];
				}
				st.push(i);
			}
		}

		while (!st.isEmpty()) {
			res[st.pop()] = -1;
		}
		return res;
	}


	// Next Smaller Element To The Left
	public static int[] nsetl(int[] arr) {
		int[] res = new int[arr.length];
		Stack<Integer> st = new Stack<>();

		st.push(arr.length - 1);
		for (int i = arr.length - 2; i >= 0; i--) {
			if (arr[i] >= arr[st.peek()]) {
				st.push(i);
			} else {
				while (!st.isEmpty() && arr[i] < arr[st.peek()]) {
					int idx = st.pop();
					res[idx] = arr[i];
				}
				st.push(i);
			}
		}

		while (!st.isEmpty()) {
			res[st.pop()] = -1;
		}
		return res;
	}



	// 496. Next Greater Element I - Use HasMap
	public int[] nextGreaterElement(int[] nums1, int[] nums2) {
		Map<Integer, Integer> map = new HashMap<>();
		ngetr(nums2, map);
		int[] res = new int[nums1.length];

		for (int i = 0; i < nums1.length; i++) {
			res[i] = map.get(nums1[i]);
		}

		return res;
	}

	public void ngetr(int[] arr, Map<Integer, Integer> map) {
		Stack<Integer> st = new Stack<>();

		st.push(0);
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] <= arr[st.peek()]) {
				st.push(i);
			} else {
				while (!st.isEmpty() && arr[i] > arr[st.peek()]) {
					int idx = st.pop();
					map.put(arr[idx], arr[i]);
				}
				st.push(i);
			}
		}

		while (!st.isEmpty()) {
			map.put(arr[st.pop()], -1);
		}

	}

	// 503. Next Greater Element II
	public int[] nextGreaterElements(int[] arr) {
		int n = arr.length;
		int[] res = new int[n];
		Arrays.fill(res, (int)1e9 + 7);
		Stack<Integer> st = new Stack<>();

		st.push(0);
		for (int i = 1; i < 2 * n; i++) {
			int idx = i % n;

			if (arr[idx] <= arr[st.peek()]) {
				st.push(idx);
			} else {
				while (!st.isEmpty() && arr[idx] > arr[st.peek()]) {
					res[st.pop()] = arr[idx];
				}
				st.push(idx);
			}
		}

		while (!st.isEmpty()) {
			if (res[st.peek()] == (int)1e9 + 7)
				res[st.pop()] = -1;
			else st.pop();
		}
		return res;
	}


	// 946. Validate Stack Sequences
	public boolean validateStackSequences(int[] pushed, int[] popped) {
		int i = 0, j = 0, n = pushed.length;

		Stack<Integer> st = new Stack<>();

		while (i < n || j < n) {
			if (!st.isEmpty() && st.peek() == popped[j]) {
				st.pop();
				j++;
			} else {
				if (i == n) break;
				st.push(pushed[i]);
				i++;
			}
		}

		return j == n;
	}

	// 1021. Remove Outermost Parentheses
	// Without Stack


	// 1249. Minimum Remove to Make Valid Parentheses
	public String minRemoveToMakeValid(String s) {
		Stack < Integer > st = new Stack < > ();

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);

			if (ch == '(') {
				st.push(i);
			} else if (ch == ')') {
				if (st.isEmpty() || s.charAt(st.peek()) == ')') {
					st.push(i);
				} else {
					st.pop();
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = s.length() - 1; i >= 0; i--) {
			if (!st.isEmpty() && st.peek() == i) {
				st.pop();
			} else {
				sb.append(s.charAt(i));
			}
		}

		return sb.reverse().toString();
	}


	// 1541. Minimum Insertions to Balance a Parentheses String


	// 901. Online Stock Span

	//1762. Buildings with an Ocean view

	// 42. Trapping Rain Water - Using Stack

	// 4 methods

	// Trapping Rain Water 2 - 3D

	// Number Of Valid Subarrays
	public static int validSubarrays(int[] nums) {
		int ct = 0;

		Stack<Integer> st = new Stack<>();

		for (int i = 0; i < nums.length; i++) {
			while (!st.isEmpty() && nums[st.peek()] > nums[i]) {
				ct += i - st.pop();
			}
			st.push(i);
		}

		while (!st.isEmpty()) {
			ct += nums.length - st.pop();
		}
		return ct;
	}

	// 402. Remove K Digits
	public String removeKdigits(String num, int k) {
		LinkedList<Character> st = new LinkedList<>();

		for (char ch : num.toCharArray()) {
			while (k > 0 && st.size() > 0 && st.getLast() > ch) {
				st.removeLast();
				k--;
			}
			st.addLast(ch);
		}

		//cases like all equal or strictly increasing nums
		while (k-- > 0) {
			st.removeLast();
		}

		//manage leading zeroes
		while (st.size() > 0 && st.getFirst() == '0') {
			st.removeFirst();
		}

		StringBuilder sb = new StringBuilder();
		while (st.size() > 0) {
			sb.append(st.removeFirst());
		}

		return sb.length() == 0 ? "0" : sb.toString();
	}

	// Lexicographically Smallest Subsequence / Same as Remove k digits
	// remove n-k digits
	public static int[] smallest(int[] nums, int k) {

		int rem = nums.length - k;

		Stack<Integer> st = new Stack<>();

		for (int i = 0; i < nums.length; i++) {
			while (!st.isEmpty() && rem > 0 && nums[st.peek()] > nums[i]) {
				st.pop();
				rem--;
			}
			st.push(i);
		}

		while (rem-- > 0) {
			st.pop();
		}

		int[] res = new int[st.size()];
		int n = st.size();
		for (int i = n - 1 ; i >= 0; i--) {
			res[i] = nums[st.pop()];
		}

		return res;
	}

	// Design A Stack With Increment Operation
	//Optimized Approach inc O(1)
	class CustomStack {
		int[] st;
		int[] inc;
		int top;
		public CustomStack(int maxSize) {
			st = new int[maxSize];
			inc = new int[maxSize];
			top = -1;
		}

		public void push(int x) {
			if (top + 1 == st.length) {
				return;
			}

			st[top + 1] = x;
			top++;
		}

		public int pop() {
			if (top ==  -1) {
				return -1;
			}

			int rem = st[top] + inc[top];
			if (top != 0) {
				inc[top - 1] += inc[top]; //shiting inc info
			}

			inc[top] = 0;
			top--;
			return rem;
		}

		public void increment(int k, int val) {
			if (top == -1) {
				return;
			}

			if (top + 1 < k) {
				inc[top] += val;
			} else {
				inc[k - 1] += val;
			}
		}
	}


	// Max-stack

	public static class MaxStack {

		Stack<Integer> vstack; //val
		Stack<Integer> mstack; // max stack

		public MaxStack() {
			vstack = new Stack<>();
			mstack = new Stack<>();
		}

		public void push(int x) {
			vstack.push(x);

			if (mstack.isEmpty()) {
				mstack.push(x);
			} else {
				mstack.push(Math.max(mstack.peek(), x));
			}
		}

		public int pop() {
			mstack.pop();
			return vstack.pop();
		}

		public int top() {
			return vstack.peek();
		}

		public int peekMax() {
			return mstack.peek();
		}

		public int popMax() {
			int max = mstack.peek();
			Stack<Integer> helper = new Stack<>();

			while (vstack.peek() != max) {
				helper.push(vstack.pop());
				mstack.pop();
			}
			vstack.pop();
			mstack.pop();

			while (helper.size() > 0) {
				push(helper.pop());
			}

			return max;
		}

	}


	// Check If Word Is Valid After Insertion - Similar to valid parentheses
	public static boolean isValid(String S) {
		Stack<Character> st = new Stack<>();

		for (char ch : S.toCharArray()) {
			if (ch == 'c') {
				if (st.size() >= 2 && st.pop() == 'b' && st.pop() == 'a') {
					//pair matched , continue
				} else {
					return false;
				}
			} else {
				st.push(ch);
			}
		}
		return st.size() == 0;
	}


	// Design Hit Counter - Queue
	static class HitCounter {
		Queue<Integer> q;
		/** Initialize your data structure here. */
		public HitCounter() {
			q = new ArrayDeque<>();
		}

		/** Record a hit.
		    @param timestamp - The current timestamp (in seconds granularity). */
		public void hit(int timestamp) {
			q.add(timestamp);
			int st = timestamp - 300 + 1; //maintain range of 300 in que

			while (q.size() > 0 && q.peek() < st) { //remove useless vals
				q.remove();
			}
		}

		/** Return the number of hits in the past 5 minutes.
		    @param timestamp - The current timestamp (in seconds granularity). */
		public int getHits(int timestamp) {
			int st = timestamp - 300 + 1 ;
			while (q.size() > 0 && q.peek() < st) {
				q.remove();
			}
			return q.size();
		}
	}

	// 933. Number of Recent Calls
	class RecentCounter {

		Queue<Integer> q;
		public RecentCounter() {
			q = new ArrayDeque<>();
		}

		public int ping(int t) {
			q.add(t);
			int st = t - 3000;

			while (q.size() > 0 && q.peek() < st) {
				q.remove();
			}
			return q.size();
		}
	}

	// 346. Moving Average from Data Stream

}