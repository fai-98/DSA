class Solution {
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        int sum = 0;

        for(int val : piles){
            sum += val;
            pq.add(val);
        }

        while(k-- >0){
            int num = pq.poll();
            int half = num/2;
            int left = num - half;

            sum -= half;
            pq.offer(left);
        }

        return sum;
    }
}