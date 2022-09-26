import java.util.Arrays;

public class ClimbStairs {
    // ******************************70-ClimbStairs************************************************************

    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return CSmemo(n, dp);
    }

    public int CSmemo(int n, int[] dp) {
        if (n < 0)
            return 0;
        if (n == 0)
            return dp[0] = 1;
        if (dp[n] != -1)
            return dp[n];
        return dp[n] = CSmemo(n - 1, dp) + CSmemo(n - 2, dp);
    }

    public int climbStairsTab(int n) {
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            if (i <= 1) {
                dp[i] = 1;
                continue;
            }

            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public int optimize(int n) {
        int a = 1, b = 1;
        for (int i = 0; i < n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return a;
    }

    // ******************************746-min-cost-climb-stairs************************************************************

    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        Arrays.fill(dp, -1);
        return Math.min(memo(cost, 0, dp), memo(cost, 1, dp));
    }

    public int memo(int[] cost, int idx, int[] dp) {
        if (idx >= cost.length)
            return 0;

        if (dp[idx] != -1)
            return dp[idx];
        int ans = cost[idx] + Math.min(memo(cost, idx + 1, dp), memo(cost, idx + 2, dp));
        return dp[idx] = ans;
    }

    // tab
    public int tab(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n];
        dp[n - 1] = cost[n - 1];
        dp[n - 2] = cost[n - 2];

        for (int i = n - 3; i >= 0; i--) {
            dp[i] = cost[i] + Math.min(dp[i + 1], dp[i + 2]);
        }

        return Math.min(dp[0], dp[1]);
    }

    // using 2 var a,b optimized
    public int optimize(int[] cost) {
        int n = cost.length;
        int a = cost[0], b = cost[1];
        for (int i = 2; i < n; i++) {
            int c = cost[i] + Math.min(a, b);
            a = b;
            b = c;
        }
        return Math.min(a, b);
    }


    // 45. Jump Game II
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, (int)1e9);
        return min_jumps(nums, 0, nums.length - 1, dp);
        // return tab(nums);
    }

    public int min_jumps(int[] nums , int idx , int end, int[] dp) {

        if (idx == end) {
            return dp[idx] = 0;
        }

        if (dp[idx] != (int)1e9) {
            return dp[idx];
        }

        int ans = (int)1e9;
        for (int jump = 1; jump <= nums[idx]; jump++) {
            if (idx + jump <= end) {
                ans = Math.min(ans , 1 + min_jumps(nums, idx + jump, end, dp));
            }
        }

        return dp[idx] = ans;
    }

    // 55. Jump Game
    public boolean canJump(int[] nums) {
        int gap = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i == 0 && nums[i] >= gap) return true;
            if (nums[i] >= gap) gap = 1;
            if (nums[i] < gap) gap++;
        }

        return nums.length == 1 ? true : false;
    }
}
