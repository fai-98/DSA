import java.io.*;
import java.util.*;

public class NQueensLeetCode {

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();

        char[][] board = new char[n][n];
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n - 1];
        boolean[] diag2 = new boolean[2 * n - 1];

        for (char[] col : board) {
            Arrays.fill(col, '.');
        }

        solve(board, cols, diag1, diag2, 0, "", res, new ArrayList<String>());
        return res;
    }


    public static void solve(char[][] chess, boolean[]cols, boolean[]d1,
                             boolean[]d2, int row, String asf, List<List<String>> res, List<String> temp) {



        if (row == chess.length) {
            asf = "";

            for (int i = 0; i < chess.length; i++) {
                for (int j = 0; j < chess.length; j++) {
                    asf += chess[i][j];
                }

                if (row != chess.length - 1)asf += ",";
            }

            // System.out.println(asf);
            temp.add(asf);
            res.add(new ArrayList(temp));
            temp.remove(temp.size() - 1);

        }

        //loop over col
        for (int col = 0; col < chess.length; col++) {
            if (cols[col] == false && d2[col + row] == false &&
                    d1[row - col + chess.length - 1] == false) {
                //do
                chess[row][col] = 'Q';
                cols[col] = true;
                d2[col + row] = true;
                d1[row - col + chess.length - 1] = true;

                //recur , we solve row by row , after placing in this row , row++
                solve(chess, cols, d1, d2, row + 1, asf, res, temp);

                //undo
                chess[row][col] = '.';
                cols[col] = false;
                d2[col + row] = false;
                d1[row - col + chess.length - 1] = false;
            }

        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // boolean[][] chess = new boolean[n][n];

        // nqueens(0, n, chess, -1);
        List<List<String>> ans = solveNQueens(4);
        System.out.println(ans);
    }

}



