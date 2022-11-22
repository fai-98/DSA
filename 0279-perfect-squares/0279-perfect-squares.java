class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp,(int)1e9);
        dp[0] = 0;
        //loop for nums to try form 1 to num*num <= n
        for(int i=1; i*i<=n; i++){
            for(int j=i*i; j<=n; j++){ //for getting better ans for n = j
                dp[j] = Math.min(dp[j], dp[j-i*i] + 1);
                //ex. for j = 13 and i = 3   ans = 1 + 1 = 2
                //dp[13] = min(dp[13], dp[[4] + 1]), dp[4] = 1 i.e 2*2 
            }
        }
        return dp[n];
    }
}