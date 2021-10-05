public class intervals_type {
	// Minimum Number of Platforms Required for a Railway/Bus Station
	// Max number of trains at a particular timw

	//find max num of overlapping intervals
	public static int findPlatform(int arr[], int dep[], int n) {
		Arrays.sort(arr);
		Arrays.sort(dep);

		// arr[curr] <= dep[prev];
		//prev ke jane s phle hi or just at that moment agli train agayi , we need another platform
		int platform = 0, oMax = -(int)1e9;
		int i = 0; //arrival
		int j = 0; //departure

		while (i < n) {
			if (arr[i] <= dep[j]) {
				platform++;
				i++;
			} else {
				platform--;
				j++;
			}

			oMax = Math.max(oMax , platform);
		}
		return oMax;

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

	// leetcode 986. https://leetcode.com/problems/interval-list-intersections/
	public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {

	}

	// leetcode 57. https://leetcode.com/problems/insert-interval/
	public int[][] insert(int[][] intervals, int[] newInterval) {

	}

}