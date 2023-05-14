public class Leetcode_Ques{

	//SweepLine/ScanLine/LineSweep/Prefix sum trick and all similar questions 
	
	//2381. Shifting Letters II
    //here we add +1 at si and -1 at ei+1 index , so then after taking prefix sum 
    //at every index the required net increase is present 

     public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        int[] arr = new int[n+1];
        
        for(int[] shift : shifts){
            int si = shift[0], ei = shift[1];
            
            if(shift[2] == 1){ //forw
                arr[si] += 1;
                arr[ei+1] -= 1;
            }else{
                arr[si] -= 1;
                arr[ei+1] += 1;
            }
        }
        
        for(int i=1; i<=n; i++){
            arr[i] += arr[i-1];
        }
        
        char[] a = s.toCharArray();
        
        for(int i=0; i<n; i++){
            char ch = a[i];
            int num = 0;
            
            num = ch + arr[i]%26;
            if(num < 97) num += 26;
            if(num > 122) num -= 26;
            ch = (char)num;            
            
            a[i] = ch;
        }
        
        return String.valueOf(a);
    }

	// 1094. Car Pooling


	// 729. My Calendar I 
	// A1 O(n^2)
	// ei > si (obvious)
	// not-overlap :  (e1 <= s2 || e2 <= s1)   : A U B
	// by demorgans law 
	// overlap condition = (A U B)'
	// (A U B ) ' = A' ∩ B'
	// so for overlap condition 
	// (e1 > s2 ) && (e2 > s1) - this one condition covers all four cases of overlap

	List<int[]> calendar;

    MyCalendar() {
        calendar = new ArrayList();
    }

    public boolean book(int start, int end) {
        for (int[] iv: calendar) {
            if (iv[0] < end && start < iv[1]) return false;
        }
        calendar.add(new int[]{start, end});
        return true;
    }

	//A2. Using TreeMap 
	TreeMap<Integer,Integer> t_map;
        //  start , end 
    MyCalendar() {
        t_map = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Integer floor_key = t_map.floorKey(start);
        Integer ceiling_key = t_map.ceilingKey(start);
        
        if(floor_key != null && start <t_map.get(floor_key))return false;
        if(ceiling_key != null && end > ceiling_key)return false;
        
        t_map.put(start,end);
        return true;
    }

	// A3. Using Boundary Count
	// same as my calender 2 , condition is count > 1


	// 731. My Calendar II

	//Boundary Count 
	TreeMap<Integer,Integer> map;
    public MyCalendarTwo() {
        map = new TreeMap<>();
    }
    
    //we are adding K:V pair start , +1; end,-1 , all timings are sorted
    //on the timeline 
    public boolean book(int start, int end) {
        map.put(start,map.getOrDefault(start,0)+1);
        map.put(end,map.getOrDefault(end,0)-1);
        
        // now look for triple bookings i.e cumulative > 2 
        int count = 0;
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            count += entry.getValue();
            
            if(count > 2){ //triple booking 
                map.put(start,map.get(start)-1);
                if(map.get(start) == 0){
                    map.remove(start);
                }
                map.put(end,map.get(end)+1);
                if(map.get(end) == 0){
                    map.remove(end);
                }
                return false;
            }
        }
        return true;
    }

	// Time Complexity: O(N^2)
	// where N is the number of events booked. 
	// For each new event, we traverse delta in O(N) time.

	// Space Complexity: O(N), the size of delta.

	// 732. My Calendar III (Hard.)
    //Sweep Line Algorithm (follow up to My Cal 1/2)

    TreeMap<Integer,Integer> map;
    public MyCalendarThree() {
        map = new TreeMap<>();
    }
    
    public int book(int start, int end) {
        map.put(start,map.getOrDefault(start,0)+1);
        map.put(end,map.getOrDefault(end,0)-1);
        
        // now look for triple bookings i.e cumulative > 2 
        int count = 0, res = 0;
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            count += entry.getValue();
            res = Math.max(count,res);
        }
        return res;
    }

    //For Strongly Connected Components (SCC)
    //1. KosaRaju Algorithm
    //2. Tarjan's Algorithm


    // 1328. Break a Palindrome
    public String breakPalindrome(String palindrome) {
        //start from left , if odd , skip mid char , if even procede normally
        //else find first non a char and convert to a   , if not found 
        // convert last char to b        
        boolean isChanged = false, isOdd = false;
        int n = palindrome.length();
        char[] str = palindrome.toCharArray();
        
        if(n == 1) return "";
        if(n % 2 == 1) isOdd = true;
        
        for(int i=0; i<str.length; i++){
            if(isOdd && i == n/2) continue;
            
            if(str[i] != 'a'){
                str[i] = 'a';
                isChanged = true;
                break;
            }
        }
        
        if(!isChanged) str[n-1] = 'b';
        
        return String.valueOf(str);
    }

    // 334. Increasing Triplet Subsequence

    //arr for 2 1 5 0 4 6 
    //        3 3 1 2 1 0  numbers larger to right 
    //        0 0 2 0 3 5  numbers smaller to left
    // if smaller to left and larger to right sum >=2 ans is possible
    //in O(n) if min to left is > num[i] -> no smaller to left
    // all we need is one smaller to left and one larger to right

    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n];
        
        if(n < 3) return false;
        int min = (int)1e18, max = -(int)1e18;
        
        for(int i=0; i<n; i++){
            if(min <nums[i]) arr[i]++;
            min = Math.min(min,nums[i]);
        }
        
        for(int i=n-1; i>=0; i--){
            if(max > nums[i]) arr[i]++;
            max = Math.max(max,nums[i]);
            if(arr[i] == 2) return true;
        }
        return false;
    }

    //optimize for O(1) space 


    // Dynamic Programming 
    // 1335. Minimum Difficulty of a Job Schedule 
    public int min;
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if(n < d) return -1;
        if(n == d) return Arrays.stream(jobDifficulty).sum();
        
        min = (int)1e9;
        dfs(jobDifficulty,0,n-1,0,d);
        return min;
    }
    
    // dfs like MCM but only on right call 
    public void dfs(int[] arr, int si, int ei, int prevMax, int d){
        
        if(d == 1){
            
            int max = -(int)1e9;
            for(int i=si; i<=ei; i++){
                max = Math.max(arr[i],max);
            }
            min = Math.min(min,prevMax+max);
            return;
        }
        
        for(int cut = si + 1; cut <= ei; cut++){
            int max = -(int)1e9;
            for(int i=si; i<=cut; i++){
                max = Math.max(arr[i],max);
            }
            //call next arr(rt side) for this cut and add our max to 
            //prev max 
            dfs(arr,cut,ei,prevMax + max, d-1);
        }
    }

    //wrong ans , solve this and submit 


    //for ith index 0-i-1 all jobs shd be done 
    // minimax -> minimize the maximum - binary Search ? ❌
    //           MCM 
    //      int minRes = (int)1e8;
    // 		for (int cut = si + 1; cut < ei; cut++) {
    // 			int leftRes = mcm_memo(arr, si, cut, dp);
    // 			int rightRes = mcm_memo(arr, cut, ei, dp);

    // 			int myRes = leftRes + arr[si] * arr[cut] * arr[ei] + rightRes;
    // 			minRes = Math.min(minRes, myRes);
    // 		}


    // 692. Top K Frequent Words

    public class Pair implements Comparable < Pair > {
        String str;
        int val;

        Pair() {};

        Pair(String str, int val) {
            this.str = str;
            this.val = val;
        }

        //decreasing nums , inc str 
        @Override public int compareTo(Pair other) {
            return other.val - this.val != 0 ? other.val - this.val :                             this.str.compareTo(other.str);
        }
    }

    public List < String > topKFrequent(String[] words, int k) {
        List < String > res = new ArrayList < > ();
        Map < String, Integer > map = new HashMap < > ();

        for (String word: words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        //pair string vs integer : first greater freq , the lexico smaller word
        PriorityQueue < Pair > pq = new PriorityQueue < > ();

        for (String s: map.keySet()) {
            pq.offer(new Pair(s, map.get(s)));
        }

        while (k--> 0) {
            res.add(pq.poll().str);
        }

        return res;
    }

    // 443. String Compression

    // 1531. String Compression II

    // 1239. Maximum Length of a Concatenated String with Unique Characters
    int max;
    public int maxLength(List<String> arr) {
        max = -(int)1e9;
        subseq(arr,0,"");
        return max;
    }
    
    public void subseq(List<String> arr, int idx, String prev){
        if(idx == arr.size()){
            max = Math.max(prev.length(), max);
            return;
        }
        
        if(isPos(prev,arr.get(idx))){
            subseq(arr,idx+1,prev+arr.get(idx));
        }
        
        subseq(arr,idx+1,prev);
    }
    
    public boolean isPos(String s, String p){
        Set<Character> set = new HashSet<>();

        for(char ch : s.toCharArray()){
            if(set.size() > 0 && set.contains(ch)){
                return false;
            }
            set.add(ch);
        }

        for(char ch : p.toCharArray()){
            if(set.size() > 0 && set.contains(ch)){
                return false;
            }
            set.add(ch);
        }

        return true;
    }


    // 587. Erect the Fence
    // Convex Hull 
    // 1. Jarvis's Algoritm or Wrapping
    // 2. Graham's Scan

    
}




