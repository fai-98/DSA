class Solution {
    public int minimumAverageDifference(int[] nums) {
        int n = nums.length;
        long[] pre = new long[n];
        long[] suf = new long[n];
        
        pre[0] = nums[0];
        suf[n-1] = nums[n-1];
        
        for(int i=1; i<n; i++){
            pre[i] = pre[i-1] + nums[i];
        }
        
        for(int i=n-2; i>=0; i--){
            suf[i] = suf[i+1] + nums[i];
        }
        
        long min = (int)1e18;
        int idx = -1;
        
        for(int i=0; i<n; i++){
            long avg = 0;
                
            if(i == n-1){
                avg = pre[i]/(i+1);
            }else{
                long avg1 = pre[i]/(i+1);
                long avg2 = suf[i+1]/(n-i-1);
                
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