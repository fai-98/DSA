public class intervals_type {
	// Minimum Number of Platforms Required for a Railway/Bus Station
	// Max number of trains at a particular timw

	//find max num of overlapping intervals
	public static int findPlatform(int arr[], int dep[], int n) {
		Arrays.sort(arr);
		Arrays.sort(dep);

		// arr[curr] <= dep[prev];
		//prev ke jane s phle hi or just at that moment agli train agayi , we need another platform
		// min platform = max no. of trains at a particular time
		int maxTrain = 0, platform = -(int)1e9;
		int i = 0; //arrival
		int j = 0; //departure

		while (i < n && j < n) {
			if (arr[i] <= dep[j]) {
				maxTrain++;
				i++;
			} else {
				maxTrain--;
				j++;
			}

			platform = Math.max(platform , maxTrain);
		}
		return platform;

	}

	//Meeting Rooms

	public class Interval {
		int start;
		int end;
		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	// meeting rooms lintcode 920. https://www.lintcode.com/problem/920/
	public boolean canAttendMeetings(List<Interval> intervals) {
		if (intervals.size() == 0)return true;

		Collections.sort(intervals, (Interval a, Interval b)-> {
			return a.start - b.start;
		});

		int end1 = intervals.get(0).end;
		for (int i = 1; i < intervals.size(); i++) {
			int st2 = intervals.get(i).start;
			if (st2 < end1) {
				return false;
			}
			end1 = intervals.get(i).end;
		}

		return true;
	}

	//same as minimum number of platforms
	// meeting rooms 2 lintcode 919. https://www.lintcode.com/problem/919/
	public int minMeetingRooms(List<Interval> intervals) {
		int n = intervals.size();
		int[] arr = new int[n];
		int[] dep = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = intervals.get(i).start;
			dep[i] = intervals.get(i).end;
		}

		Arrays.sort(arr);
		Arrays.sort(dep);

		int i = 0, j = 0, plat = 0, max = -(int)1e9;

		//ek meeting end hone s pehle ya just ending time pe next start hogyi to
		//we need extra meeting room
		while (i < n) {
			if (arr[i] <= dep[j]) {
				plat++;
				i++;
			} else {
				plat--;
				j++;
			}

			max = Math.max(max, plat);
		}
		return max;
	}

	// leetcode 56. https://leetcode.com/problems/merge-intervals/
	public int[][] merge(int[][] intervals) {
		Arrays.sort(intervals, (a, b)-> {
			return a[0] - b[0];
		});

		List<int[]> list = new ArrayList<>();

		list.add(intervals[0]);

		for (int[] arr : intervals) {
			int[] newInt = arr;
			int[] prev = list.remove(list.size() - 1);

			if (prev[1] > arr[0]) {
				newInt[0] = Math.min(pre[0], arr[0]);
				newInt[1] = Math.max(prev[1], arr[1]);
			}

			list.add(newInt);
		}

		for (int[] i : list) {
			System.out.println(i[0] + "---" + i[1]);
		}
	}

	//better
	public int[][] merge(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> {
			return a[0] - b[0];
		});

		List < int[] > res = new ArrayList < > ();

		int l1 = intervals[0][0];
		int r1 = intervals[0][1];

		for (int i = 1; i < intervals.length; i++) {
			int l2 = intervals[i][0];
			int r2 = intervals[i][1];

			if (l2 > r1) { //1. non-overlapping , make new
				int[] sub = {l1, r1};
				res.add(sub);
				l1 = l2;
				r1 = r2;
			} else if (r2 > r1) { //2. Partial merging
				r1 = r2; //expand the interval to bigger rightbound
			} else { //3. full merge
				// interval already exists inside l1...r1 (r2 <= r1)
			}
		}

		int[] subinterval = {
			l1,
			r1
		};
		res.add(subinterval);
		return res.toArray(new int[res.size()][]);
	}

	// 452. Minimum Number of Arrows to Burst Balloons
	public int findMinArrowShots(int[][] arr) {
		// Arrays.sort(arr, (a, b) -> {
		//     return (a[0] - b[0]);
		// });
		Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

		int arrow = 1;
		int ei1 = arr[0][1];

		for (int i = 0; i < arr.length; i++) {
			int si2 = arr[i][0], ei2 = arr[i][1];

			if (si2 > ei1) { //no overlap , need arrow
				arrow++;
				ei1 = ei2; //update ranges
			} else {
				//we only need to care about ei , si are sorted si2 always >= si1
				//overlap si2 <= ei1 (full or partial)
				ei1 = Math.min(ei1, ei2);
				//this is the common area bw overlapped intervals
				//only when third interval overlaps here , we dont need the arrow
			}
		}

		return arrow;
	}
	// 56 Merge Intervals <- very similar😈
	// 435 Non-overlapping Intervals <- very similar😈
	// 252 Meeting Rooms
	// 253 Meeting Rooms II

	// leetcode 986. https://leetcode.com/problems/interval-list-intersections/
	public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
		List < int[] > res = new ArrayList < > ();
		int i = 0, j = 0;

		while (i < firstList.length && j < secondList.length) {
			int left = Math.max(firstList[i][0], secondList[j][0]);
			int right = Math.min(firstList[i][1], secondList[j][1]);

			if (left <= right) { //shd overlap
				int[] subres = {left, right};
				res.add(subres);
			}

			//how to increment pointers
			if (firstList[i][1] < secondList[j][1]) { //partial overlap cond.
				i++;
			} else { //full overlap
				j++;
			}
		}

		return res.toArray(new int[res.size()][]);
	}

	// leetcode 57. https://leetcode.com/problems/insert-interval/
	public int[][] insert(int[][] intervals, int[] newInterval) {
		if (intervals.length == 0) {
			return new int[][] {
				{
					newInterval[0], newInterval[1]
				}
			};
		}

		List < int[] > res = new ArrayList < > ();

		int i = 0;
		while (i < intervals.length && intervals[i][1] < newInterval[0]) {
			res.add(intervals[i]);
			i++;
		}

		//merge into one
		int l = newInterval[0], r = newInterval[1];
		while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
			l = Math.min(l, Math.min(intervals[i][0], newInterval[0]));
			r = Math.max(r, Math.max(intervals[i][1], newInterval[1]));
			i++;
		}

		res.add(new int[] {
		            l,
		            r
		        });

		//add rest of intervals
		while (i < intervals.length) {
			res.add(intervals[i]);
			i++;
		}

		return res.toArray(new int[res.size()][]);

	}

	// Leetcode 853. Car Fleet
	public int carFleet(int target, int[] position, int[] speed) {
		int n = speed.length;
		carPair[] data = new carPair[n];

		for (int i = 0; i < n; i++) {
			data[i] = new carPair(position[i], speed[i], (target - position[i]) * 1.0 / speed[i]);
		}

		Arrays.sort(data, (a, b) -> {
			return a.pos - b.pos;
		});

		double maxTime = data[n - 1].time;

		// if car behind us takes less time than me , it catches up fleet is same
		// else never catches up fleet++
		int fleet = 1;
		for (int i = n - 1; i >= 0; i--) {
			if (data[i].time > maxTime) {
				fleet++;
				maxTime = data[i].time;
			}
		}

		return fleet;
	}

	private class carPair {
		int pos;
		int speed;
		double time;

		public carPair() {}

		public carPair(int pos, int speed, double time) {
			this.pos = pos;
			this.speed = speed;
			this.time = time;
		}
	}

}