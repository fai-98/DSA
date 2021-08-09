public class Dice_Board_Path {

    public static void display(int[] arr) {
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    // Ques -> from st to end how many paths are available
    // moves length = number on dice
    // same type of ques when array of moves is given
    public static void dice_Path() {
        int st = 0, end = 20;
        int[] dp = new int[end + 1];
        memo(st, end, dp);
        display(dp);
    }

    // Faith : src - intermediate - dest , recursion will give ans from all
    // intermediate to des , add all of them
    public static int memo(int st, int end, int[] dp) {
        if (st == end) {
            return dp[st] = 1;
        }

        if (st > end)
            return 0;

        if (dp[st] != 0)
            return dp[st];

        int res = 0;
        for (int i = 1; i <= 6; i++) {
            res += memo(st + i, end, dp);
        }
        return dp[st] = res;
    }

    public static int tab(int st, int end) {
        int[] dp = new int[end + 1];

        for (int i = end; i >= 0; i--) {
            if (i == end) {
                dp[i] = 1;
                continue;
            }

            // dice moves
            for (int dice = 1; dice <= 6 && dice + i <= end; dice++) {
                dp[i] += dp[i + dice];
            }

        }
        return dp[0];
    }

    public static void main(String[] args) throws Exception {
        // int st = 0, end = 10;
        // int[] dp = new int[end + 1];
        // memo(st, end, dp);
        // display(dp);
        System.out.println(tab(0, 10));
    }
}
