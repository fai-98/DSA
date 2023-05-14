class Solution {
    public int fib(int n) {
        // return dfs(n);
        int[] dp = new int[n+1];
        Arrays.fill(dp,-1);
        // return memo(n,dp);
        // return dp(n);
        return fibo(n);
    }
    
    // recursion
    public int dfs(int n){
        if(n == 0) return 0;
        if(n == 1) return 1;
        return dfs(n-1) + dfs(n-2);
    }
    //memoization
    public int memo(int n, int[] dp){
        if(n == 0) return dp[n] = 0;
        if(n == 1) return dp[n] = 1;
        if(dp[n] != -1) return dp[n];
        return dp[n] = dfs(n-1) + dfs(n-2);
    }
    //dp
    public int dp(int n){
        int[] dp = new int[n+1];
        dp[0] = 0; dp[1] = 1;
        
        for(int i=2; i<=n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
    //O(1) space 
    public int fibo(int n){
        if(n == 0) return 0;
        if(n == 1) return 1;
        
        int a = 0;
        int b = 1;
        
        for(int i=2; i<=n; i++){
            int c = a + b;
            a = b;
            b = c;
        }
        return b;
    }
}

// recursive
// fib(0) == 0, fib(1) = 1
// fib(n) = fib(n-1) + fib(n-2)

//memo dp of size n+1 fib(n) is at n th idx 

// a b c 
// c = a + b;
// a = b 
// b = c