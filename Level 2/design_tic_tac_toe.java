import java.util.*;
import java.io.*;

public class design_tic_tac_toe {

    static class TicTacToe {
        int n;
        int[] row;
        int[] col;
        int diag = 0;
        int revDiag = 0;
        public TicTacToe(int n) {
            // initialize data members
            this.n = n;
            row = new int[n];
            col = new int[n];
        }
        public int move(int r, int c, int player) {
            int inc = 1;
            if (player == 2) {
                inc = -1;
            }

            row[r] += inc;
            if (row[r] == n || row[r] == -n) {
                return player;
            }
            col[c] += inc;
            if (col[c] == n || col[c] == -n) {
                return player;
            }

            if (r == c) {
                diag += inc;
                if (diag == n || diag == -n) {
                    return player;
                }
            }

            if (r + c == n - 1) {
                revDiag += inc;
                if (revDiag == n || revDiag == -n) {
                    return player;
                }
            }

            return 0; //DRAW OR Not Decided yet
        }
    }
    public static void main(String[] args) throws java.lang.Exception {

        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();

        TicTacToe obj = new TicTacToe(n);
        for (int i = n * n; i >= 1; i--) {
            int ans = obj.move(scn.nextInt(), scn.nextInt(), scn.nextInt());

            System.out.println(ans);
            if (ans != 0)break;
        }
    }
}
