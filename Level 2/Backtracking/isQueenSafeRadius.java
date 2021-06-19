import java.io.*;
import java.util.*;

public class isQueenSafeRadius {

    public static boolean IsQueenSafe(boolean[][] chess, int row, int col) {
        int[][] dir = {{ -1, 0}, { -1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, { -1, -1}};
        for (int r = 1; r < chess.length; r++) {
            for (int i = 0; i < dir.length; i++) {
                int R = row + dir[i][0] * r;
                int C = col + dir[i][1] * r;


                if (R >= 0 && C >= 0 && R < chess.length && C < chess.length && chess[R][C] == true) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void nqueens(int qpsf, int tq, boolean[][] chess, int lcno) {
        if (qpsf == tq) {
            for (int row = 0; row < chess.length; row++) {
                for (int col = 0; col < chess.length; col++) {
                    System.out.print(chess[row][col] ? "q\t" : "-\t");
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for (int i = lcno + 1; i < chess.length * chess.length; i++) {
            int row = i / chess.length;
            int col = i % chess.length;

            if (chess[row][col] == false && IsQueenSafe(chess, row, col)) {
                chess[row][col] = true;
                nqueens(qpsf + 1, tq, chess, row * chess.length + col);
                chess[row][col] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[][] chess = new boolean[n][n];

        nqueens(0, n, chess, -1);
    }
}