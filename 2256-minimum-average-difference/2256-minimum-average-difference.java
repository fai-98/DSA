class Solution {
    public int minimumAverageDifference(int[] nums) {
        int n = nums.length;
        long sum = 0;
        
        for(int i=0; i<n; i++){
           sum += nums[i];
        }
        
        long min = (int)1e18, pre = 0, suf = 0;
        int idx = -1;
        
        for(int i=0; i<n; i++){
            long avg = 0;
            pre += nums[i];
            suf = sum - pre;
                
            if(i == n-1){
                avg = pre/(i+1);
            }else{
                long avg1 = pre/(i+1);
                long avg2 = suf/(n-i-1);
                
                avg = Math.abs(avg1-avg2);
            }
            
            if(avg < min){
                idx = i;
                min = avg;
            }
        }
        
        return idx;
    }
}