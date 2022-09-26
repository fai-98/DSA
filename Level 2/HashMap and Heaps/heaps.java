public class heaps {

	//kth largest/smallest element

	// A1 - sorting nlogn
	// A2 - heap of size n O(n+klogn)
	// A3 - use set if all are distinct
	// A4 - heap of size k



	// k size heap build - O(k*log(k)) + n-k times extract + heapify (n-k)*log(k)

	// T = (k*logk + (n-k)*logk) = n*log(k)
	public static void kLargestElements(int[] arr, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();     //min heap for kth largest
		//add first k elem
		for (int i = 0; i < k; i++)pq.add(arr[i]);

		//if elem > pq.peek() , rem peek , add elem, size always k
		for (int i = k; i < n; i++)
			if (pq.peek() < arr[i]) {
				pq.poll();
				pq.add(arr[i]);
			}


		while (pq.size() > 0) {
			System.out.println(pq.poll());
		}
	}

	//similarly for kth smallest
	public static int kthSmallest(int[] arr, int l, int r, int k) {
		//use max heap
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

		for (int i = 0; i < k; i++) {
			pq.add(arr[i]);
		}

		for (int i = k; i < arr.length; i++) {
			if (pq.peek() > arr[i]) {
				pq.poll();
				pq.offer(arr[i]);
			}
		}

		return pq.peek();
	}

	// A5 - quick select
	//Quick Select Algorithm - kth largest/smallest

	//code for kth smallest , for largest find (n-k)th smallest
	//select pivot = arr[hi] , after part it is at correct idx
	//if idx < k , recur right side , else idx>k recur left side
	// idx == k means kth smallest ele was selected as pivot and now it is at correct pos..
	public int findKthLargest(int[] nums, int k) {
		// (n-k)th smallest
		return quickSelect(nums, 0, nums.length - 1, nums.length - k);
	}

	public int quickSelect(int[] arr, int lo, int hi, int k) {

		int pivotIndex = partition(arr, arr[hi] , lo , hi);

		if (pivotIndex > k) {
			return quickSelect(arr, lo, pivotIndex - 1, k);
		} else if (pivotIndex < k) {
			return quickSelect(arr, pivotIndex + 1, hi, k);
		} else {
			return arr[pivotIndex];
		}
	}

	public int partition(int[] arr, int pivot, int lo, int hi) {
		int i = lo, j = lo;
		while (j <= hi) {
			if (arr[j] <= pivot) {
				swap(arr, i, j);
				i++;
				j++;
			} else {
				j++;
			}
		}

		return (i - 1);
	}

	public void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	//Approach

	//Smallest - Max Heap
	//Largest - Min Heap
	//add values in heap
	//if heap.size() > k , pop val
	//always maintain the size of heap to be k

	//Similar
	//if we strictly maintain k size
	//for largest if(num>peek) -> pop
	//for smallest if(num < peek) -> pop

	//k largest/smallest elements
	public int[] solve(int[] A, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < k; i++) {
			pq.offer(A[i]);
		}
		for (int i = k; i < A.length; i++) {
			if (A[i] > pq.peek()) {
				pq.poll();
				pq.offer(A[i]);
			}
		}

		int[] res = new int[k];
		int i = 0;
		while (pq.size() > 0) {
			res[i++] = pq.poll();
		}
		return res;
	}

	//Top K frequent elements/words  - leetcode 347
	public class Pair {
		int val;
		int freq;

		Pair() {}

		Pair(int val, int freq) {
			this.val = val;
			this.freq = freq;
		}
	}

	public int[] topKFrequent(int[] nums, int k) {
		Map < Integer, Integer > map = new HashMap < > ();
		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
		}

		PriorityQueue < Pair > pq = new PriorityQueue < > ((a, b) -> {
			return a.freq - b.freq;
		});

		for (int key : map.keySet()) {
			Pair np = new Pair(key, map.get(key));
			pq.offer(np);

			if (pq.size() > k) {
				pq.poll();
			}
		}

		int[] res = new int[k];
		int i = 0;
		while (pq.size() > 0) {
			res[i++] = pq.poll().val;
		}

		return res;
	}

	// 1337. The K Weakest Rows in a Matrix
	public int[] kWeakestRows(int[][] mat, int k) {
		// one ct , i(row) , Max Heap
		PriorityQueue < int[] > pq = new PriorityQueue < > ((a, b) -> {
			return b[0] - a[0] != 0 ? b[0] - a[0] : b[1] - a[1];
		});

		for (int i = 0; i < mat.length; i++) {
			int li = lastIdx(mat[i]);
			int onect = li + 1;

			pq.offer(new int[] {
			             onect,
			             i
			         });

			if (pq.size() > k) pq.poll();
		}

		int[] res = new int[k];
		for (int i = k - 1; i >= 0; i--) {
			int[] rem = pq.poll();
			res[i] = rem[1];
		}

		return res;
	}

	public int lastIdx(int[] row) {
		int lo = 0, hi = row.length - 1, li = -1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;

			if (row[mid] == 1) {
				li = mid;
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}

		return li;
	}

	//K closest Numbers - Leetcode 658


	// 1. Max Heap Approach
	// 	make a max-heap and keep storing the pair of (absolute difference with x, corresponding key)
	// if the size of heap becomes more than k,
	// just pop it (this will remove the element with largest absolute difference).
	// like this iterate over the array.
	// return the elements of the heap.

	public class Pair {
		int val;
		int gap;

		Pair() {}

		Pair(int val, int gap) {
			this.val = val;
			this.gap = gap;
		}
	}

	public List < Integer > findClosestElements(int[] arr, int k, int x) {
		PriorityQueue < Pair > pq = new PriorityQueue < > ((a, b) -> {
			if (b.gap != a.gap) {
				return b.gap - a.gap;
			} else {
				return b.val - a.val;
			}
		});

		for (int i = 0; i < arr.length; i++) {
			Pair np = new Pair(arr[i], Math.abs(arr[i] - x));

			pq.offer(np);
			if (pq.size() > k) {
				pq.poll();
			}
		}

		List < Integer > res = new ArrayList < > ();

		while (pq.size() > 0) {
			res.add(pq.poll().val);
		}

		Collections.sort(res);
		return res;
	}

	// 2. Binary Search
	public List < Integer > findClosestElements(int[] arr, int k, int x) {
		int lo = 0, hi = arr.length - k;

		//modified binary search
		while (lo < hi) {

			int mid = lo + (hi - lo) / 2;

			//if arr[mid] closer to x , we have to inc it , so don't
			//move it out of the search space -> so hi==mid
			//else-> if arr[mid+k] is closer , arr[mid] can't be in ans so lo=mid+1;

			if (x - arr[mid] <= arr[mid + k] - x) {
				hi = mid;
			} else lo = mid + 1;
		}

		//make res from st to st+k(non inc)
		List < Integer > res = new ArrayList < > ();
		for (int i = lo; i < lo + k; i++) {
			res.add(arr[i]);
		}

		return res;

	}

	// 973. K Closest Points to Origin
	public class Pair {
		long dist;
		int idx;

		Pair() {}

		Pair(long dist, int idx) {
			this.dist = dist;
			this.idx = idx;
		}
	}
	public int[][] kClosest(int[][] points, int k) {
		// (x1-x2)^2 + (y1-y2)^2 = dist^2  //x2 and y2 are 0
		// x^2 + y^2 = dist^2
		// make pq of dist^2 , idx

		PriorityQueue < Pair > pq = new PriorityQueue < > ((a, b) -> {
			return (int)(b.dist - a.dist);
		});

		for (int i = 0; i < points.length; i++) {
			int x = points[i][0];
			int y = points[i][1];

			long dis = x * x + y * y;

			pq.add(new Pair(dis, i));

			if (pq.size() > k) {
				pq.poll();
			}
		}

		int[][] res = new int[k][2];
		while (k-- > 0) {
			Pair rem = pq.poll();
			res[k] = points[rem.idx];
		}

		return res;
	}

	// 451. Sort Characters By Frequency

	public class Pair {
		char ch;
		int freq;

		Pair() {}

		Pair(char ch, int freq) {
			this.ch = ch;
			this.freq = freq;
		}
	}

	public String frequencySort(String s) {
		//max heap of char,freq , peek = highest freq
		PriorityQueue < Pair > pq = new PriorityQueue < > ((a, b) -> {
			return (int)(b.freq - a.freq);
		});

		Map < Character, Integer > map = new HashMap < > ();

		for (char ch : s.toCharArray()) { //build freq map
			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}

		for (char key : map.keySet()) { //fill pq , max size possible = 56
			pq.add(new Pair(key, map.get(key)));
		}

		StringBuilder sb = new StringBuilder();

		while (pq.size() > 0) {   //make ans
			Pair rem = pq.poll();
			char ch = rem.ch;
			int k = rem.freq;

			while (k-- > 0) {
				sb.append(ch);
			}
		}

		return sb.toString();
	}

	// ************************************K****************************************************

	//Sort A K sorted array - GFG
	public void sortArr(int[] arr, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (int i = 0; i < k + 1; i++) {
			pq.add(arr[i]);
		}

		int idx = k + 1;
		while (pq.size() > 0) {
			System.out.println(pq.poll());
			if (idx < n) {
				pq.add(arr[idx]);
				idx++;
			}
		}
	}

	//Merge K Sorted Lists

	// 	O(nk)
	// or O(k λ^2)    n=k λ
	// where

	// N = total no. of nodes
	// k = size of array

	// avg length of nodes =  λ (max length of list)
	// total no of nodes = k λ

	//A0.
	public ListNode mergeKLists(ListNode[] lists) {
		if (lists.length == 0)return null;
		ListNode d1 = lists[0];
		for (int i = 1; i < lists.length; i++) {
			d1 = mergeTwoLists(d1, lists[i]);
		}
		return d1;
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode();
		ListNode dd = dummy;

		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				dd.next = l1;
				dd = l1;
				l1 = l1.next;
			} else {
				dd.next = l2;
				dd = l2;
				l2 = l2.next;
			}
		}
		while (l1 != null) {
			dd.next = l1;
			dd = l1;
			l1 = l1.next;
		}
		while (l2 != null) {
			dd.next = l2;
			dd = l2;
			l2 = l2.next;
		}


		return dummy.next;
	}

	// 2.
	// solve in (nlogk)
	// i. using PQ
	// ii. without using PQ

	// for example, 8 ListNode, and the length of every ListNode is x1, x2,
	// x3, x4, x5, x6, x7, x8, total is n.

	// on level 3: x1+x2, x3+x4, x5+x6, x7+x8 sum: n

	// on level 2: x1+x2+x3+x4, x5+x6+x7+x8 sum: n

	// on level 1: x1+x2+x3+x4+x5+x6+x7+x8 sum: n

	// total 3n, nlog8

	//A1. Heap
	public static class Pair implements Comparable<Pair> {
		int li;
		int di;
		int val;

		Pair() {}

		Pair(int li, int di, int val) {
			this.li = li;
			this.di = di;
			this.val = val;
		}

		public int compareTo(Pair o) {
			return this.val - o.val;
		}
	}


	public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> lists) {
		ArrayList<Integer> rv = new ArrayList<>();
		PriorityQueue<Pair> pq = new PriorityQueue<>();

		//add first of every list to PQ
		for (int i = 0; i < lists.size(); i++) {
			pq.add(new Pair(i, 0, lists.get(i).get(0)));
		}

		while (!pq.isEmpty()) {
			Pair rem = pq.poll();
			rv.add(rem.val);

			int list = rem.li;
			int idx = rem.di;

			if (idx < lists.get(list).size() - 1) {
				pq.add(new Pair(list, idx + 1, lists.get(list).get(idx + 1)));
			}
		}
		return rv;
	}

	//A2. Divide and Conquer
	public ListNode mergeKLists(ListNode[] lists) {
		if (lists.length == 0)return null;
		return mergeK(lists, 0, lists.length - 1);
	}

	public ListNode mergeK(ListNode[] lists, int st , int end ) {
		if (st == end) {
			return lists[st];
		}

		int mid = st + (end - st) / 2;
		ListNode left = mergeK(lists, st, mid);
		ListNode right = mergeK(lists, mid + 1, end);

		return mergeTwoLists(left, right);
	}

	public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		if (list1 == null)return list2;
		else if (list2 ==  null) return list1;

		ListNode dummy = new ListNode(-1);
		ListNode temp = dummy;

		ListNode p1 = list1, p2 = list2;

		while (p1 != null && p2 != null) {
			if (p1.val < p2.val) {
				temp.next = p1;
				p1 = p1.next;
			} else {
				temp.next = p2;
				p2 = p2.next;
			}

			temp = temp.next;
		}

		temp.next = p1 != null ? p1 : p2;
		return dummy.next;
	}
	// 632. Smallest Range Covering Elements from K Lists
	public int[] smallestRange(List < List < Integer >> nums) {
		PriorityQueue < rPair > pq = new PriorityQueue < > ();
		int max = -(int) 1e9;

		//add first 3 vals
		for (int i = 0; i < nums.size(); i++) {
			int val = nums.get(i).get(0);
			pq.offer(new rPair(val, i, 0));
			max = Math.max(val, max);
		}

		int sp = 0;
		int ep = 0;
		int length = (int) 1e9;

		while (true) {
			rPair rem = pq.poll();
			int minV = rem.val;
			int maxV = max;

			if (maxV - minV < length) { //update
				sp = minV;
				ep = maxV;
				length = maxV - minV;
			}

			//add next val from list of removed val
			if (rem.c + 1 < nums.get(rem.r).size()) {
				int val = nums.get(rem.r).get(rem.c + 1);
				pq.offer(new rPair(val, rem.r, rem.c + 1));
				max = Math.max(max, val);
			} else {
				break;
			}
		}

		int[] res = {
			sp,
			ep
		};
		return res;
	}

	private class rPair implements Comparable < rPair > {
		int val;
		int r;
		int c;

		public rPair() {}

		public rPair(int val, int r, int c) {
			this.val = val;
			this.r = r;
			this.c = c;
		}

		public int compareTo(rPair o) {
			return this.val - o.val;
		}
	}

	//Find mth largest/smallest from k sorted arrays GFG

	//Connect n ropes with min cost
	//Concept : the len which we pick first gets added multiple times in res
	//So pick the smaller wts first

	// T : nlogn , S : O(n)
	public static long minCost(long arr[], int n) {
		long res = 0;
		PriorityQueue<Long> pq = new PriorityQueue<>();
		for (long ele : arr) pq.offer(ele);

		while (pq.size() > 1) {
			long l1 = pq.poll();
			long l2 = pq.poll();
			long l3 = l1 + l2;
			res += l3;
			pq.offer(l3);
		}

		return res;
	}

	// O(1) space


	// 895. Maximum Frequency Stack

	// A1.
	public class Pair {
		int val;
		int idx;
		int freq;

		Pair() {}

		Pair(int val, int idx, int freq) {
			this.val = val;
			this.idx = idx;
			this.freq = freq;
		}
	}
	// map val,freq
	// pq of val,idx,freq

	//if map have , update freq in map , update idx by using size and push in pq
	//else add in map and also in pq
	int idx;
	PriorityQueue < Pair > pq;
	Map < Integer, Integer > map; //val,freq

	public FreqStack() {
		pq = new PriorityQueue < > ((a, b) -> {
			return b.freq - a.freq != 0 ? b.freq - a.freq : b.idx - a.idx;
		}); //max heap , prio freq > idx

		map = new HashMap < > ();

		idx = 0;
	}

	public void push(int val) {
		idx++;

		if (map.containsKey(val) == false) { //new val
			map.put(val, 1); //val,freq
			Pair np = new Pair(val, idx, 1);
			pq.add(np);
		} else {
			Pair np = new Pair(val, idx, map.get(val) + 1);
			map.put(val, map.get(val) + 1); //inc freq
			//maintain indexing by size of pq , maintain freq using map
			pq.add(np);
		}
	}

	public int pop() {
		Pair rem = pq.poll();

		int rval = rem.val;

		map.put(rval, map.get(rval) - 1);

		if (map.get(rval) == 0) {
			map.remove(rval);
		}

		return rval;
	}

	//A2. Stack of Stacks
	//at every freq vals are stored in LIFO order and we start popping from max freq stack
	//maxFreq maintains freq order and stack maintains which was added later

	int maxFreq;
	Map < Integer, Stack < Integer > > smap; //val,freq
	Map < Integer, Integer > fmap; //val,freq

	public FreqStack() {
		smap = new HashMap < > (); // freq vs stack
		fmap = new HashMap < > (); // val vs freq(i.e to get key in smap)
		maxFreq = -(int) 1e9;
	}

	public void push(int val) {
		int freq = fmap.getOrDefault(val, 0) + 1;
		fmap.put(val, freq);
		if (smap.containsKey(freq) == false) {
			smap.put(freq, new Stack < > ());
		}
		smap.get(freq).push(val);
		maxFreq = Math.max(maxFreq, fmap.get(val));
	}

	public int pop() { //assume pop is valid
		int rval = smap.get(maxFreq).pop();
		fmap.put(rval, fmap.get(rval) - 1);
		if (smap.get(maxFreq).size() == 0) maxFreq--;

		return rval;
	}

	// 1046. Last Stone Weight
	public int lastStoneWeight(int[] stones) {
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)-> {
			return b - a;
		});

		for (int stone : stones) pq.offer(stone);

		while (pq.size() > 1) {
			int s1 = pq.poll();
			int s2 = pq.poll();

			if (s1 != s2) {
				pq.offer(s1 - s2);
			}
		}

		return pq.size() > 0 ? pq.poll() : 0;
	}

	//***************************MEDIAN************************************************************

	//Median Priority Queue
	public static class MedianPriorityQueue {
		PriorityQueue<Integer> left;
		PriorityQueue<Integer> right;

		public MedianPriorityQueue() {
			left = new PriorityQueue<>(Collections.reverseOrder());
			right = new PriorityQueue<>();
		}

		public void add(int val) {
			if (right.size() > 0 && val > right.peek()) {
				right.add(val);
			} else if (left.size() == 0) {
				left.add(val);
			} else {
				left.add(val);
			}
			if (left.size() - right.size() > 1) {
				right.add(left.poll());
			} else if (right.size() - left.size() > 1) {
				left.add(right.poll());
			}
		}

		public int remove() {
			if (left.size() == 0 && right.size() == 0) {
				System.out.println("Underflow");
				return -1;
			}
			if (left.size() == right.size()) {
				return left.poll();
			} else if (left.size() > right.size()) {
				return left.poll();
			} else {
				return right.poll();
			}
		}

		public int peek() {
			if (left.size() == 0 && right.size() == 0) {
				System.out.println("Underflow");
				return -1;
			}
			if (left.size() == right.size()) {
				return left.peek();
			} else if (left.size() > right.size()) {
				return left.peek();
			} else {
				return right.peek();
			}
		}

		public int size() {
			return left.size() + right.size();
		}
	}

	// 295. Find Median from Data Stream
	class MedianFinder {
		PriorityQueue < Integer > left;
		PriorityQueue < Integer > right;
		/** initialize your data structure here. */
		public MedianFinder() {
			left = new PriorityQueue < > (Collections.reverseOrder()); //smaller half
			right = new PriorityQueue < > (); //larger half of arr
		}

		public void addNum(int num) {
			if (right.size() > 0 && num > right.peek()) { //better cand for larger half
				right.offer(num);
			} else {
				left.offer(num);
			}
			//balancing
			if (left.size() - right.size() > 1) {
				right.offer(left.poll());
			} else if (right.size() - left.size() > 1) {
				left.offer(right.poll());
			}
		}

		public double findMedian() {
			if (left.size() == right.size()) {
				double num = (left.peek() + right.peek()) / 2.0;
				return num;
			} else if (left.size() > right.size()) {
				return (double) left.peek();
			} else {
				return (double) right.peek();
			}
		}
	}

	//480. Sliding Window Median (very similar to #295)

	PriorityQueue < Integer > left; //maxHeap (n/2 smallest ele)
	PriorityQueue < Integer > right; //minHeap (n/2 largest ele)

	public double[] medianSlidingWindow(int[] nums, int k) {
		left = new PriorityQueue<>(1, Collections.reverseOrder()); //smaller half
		right = new PriorityQueue < > (); //larger half of arr

		int n = nums.length;
		double[] res = new double[n - k + 1];

		for (int i = 0; i < k; i++) {
			insert(nums[i]);
		}

		res[0] = findMedian();

		for (int i = k; i < n; i++) {
			remove(nums[i - k]);
			insert(nums[i]);
			res[i - k + 1] = findMedian();
		}

		return res;
	}

	public void remove(int val) {
		if (left.contains(val)) {
			left.remove(val);
		} else {
			right.remove(val);
		}

		balance();
	}
	public double findMedian() {
		if (left.size() == right.size()) {
			double num = ((double)left.peek() + (double)right.peek()) / 2.0;
			return num;
		} else if (left.size() > right.size()) {
			return (double) left.peek();
		} else {
			return (double) right.peek();
		}
	}

	public void insert(int num) {
		if (right.size() > 0 && num > right.peek()) { //better cand for larger half
			right.offer(num);
		} else {
			left.offer(num);
		}

		balance();
	}

	public void balance() {
		//balancing
		if (left.size() - right.size() > 1) {
			right.offer(left.poll());
		} else if (right.size() - left.size() > 1) {
			left.offer(right.poll());
		}
	}


	//***************************************************************************************

	//https://www.interviewbit.com/problems/n-max-pair-combinations/
	private class Pair {

		public Pair(int i1, int i2) {
			this.i1 = i1;
			this.i2 = i2;
		}

		int i1;
		int i2;

		@Override
		public boolean equals(Object o) {
			if (o == null) {
				return false;
			}
			if (!(o instanceof Pair)) {
				return false;
			}
			Pair obj = (Pair)o;
			return (i1 == obj.i1 && i2 == obj.i2);
		}

		@Override
		public int hashCode() {
			return Objects.hash(i1, i2);
		}
	}

	private class PairSum implements Comparable<PairSum> {

		public PairSum(int sum, int i1, int i2) {
			this.sum = sum;
			this.i1 = i1;
			this.i2 = i2;
		}

		int sum;
		int i1;
		int i2;

		@Override
		public int compareTo(PairSum o) {
			return Integer.compare(o.sum, sum);
		}
	}

	public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B) {

		ArrayList<Integer> result = new ArrayList<Integer>();

		Collections.sort(A);
		Collections.sort(B);

		PriorityQueue<PairSum> sums = new PriorityQueue<PairSum>();
		HashSet<Pair> pairs = new HashSet<Pair>();

		int i1 = A.size() - 1;
		int i2 = B.size() - 1;
		pairs.add(new Pair(i1, i2));
		sums.add(new PairSum(A.get(i1) + B.get(i2), i1, i2));

		for (int i = 0; i < A.size(); i++) {
			PairSum max = sums.poll();
			result.add(max.sum);

			i1 = max.i1 - 1;
			i2 = max.i2;

			if (i1 >= 0 && i2 >= 0 && !pairs.contains(new Pair(i1, i2))) {
				sums.add(new PairSum(A.get(i1) + B.get(i2), i1, i2));
				pairs.add(new Pair(i1, i2));
			}

			i1 = max.i1;
			i2 = max.i2 - 1;

			if (i1 >= 0 && i2 >= 0 && !pairs.contains(new Pair(i1, i2))) {
				sums.add(new PairSum(A.get(i1) + B.get(i2), i1, i2));
				pairs.add(new Pair(i1, i2));
			}

		}

		return result;
	}
}

