public class UnionFindQues {
    /*
     * #1061 Given strings A and B of the same length, we say A[i] and B[i] are
     * equivalent characters. For example, if A = "abc" and B = "cde", then we have
     * 'a' == 'c', 'b' == 'd', 'c' == 'e'. Equivalent characters follow the usual
     * rules of any equivalence relation: Reflexivity: 'a' == 'a' Symmetry: 'a' ==
     * 'b' implies 'b' == 'a' Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' ==
     * 'c' For example, given the equivalency information from A and B above, S =
     * "eed", "acd", and "aab" are equivalent strings, and "aab" is the
     * lexicographically smallest equivalent string of S. Return the
     * lexicographically smallest equivalent string of S by using the equivalency
     * information from A and B. Input: A = "parker", B = "morris", S = "parser"
     * Output: "makkek" Input: A = "hello", B = "world", S = "hold" Output: "hdld"
     * Input: A = "leetcode", B = "programs", S = "sourcecode" Output: "aauaaaaada"
     * String A, B and S consist of only lowercase English letters from 'a' - 'z'.
     * The lengths of string A, B and S are between 1 and 1000. String A and B are
     * of the same length.
     */

    static int[] par, size;

    public static int findPar(int u) {
        if (par[u] == u)
            return u;
        return par[u] = findPar(par[u]);

        // return par[u] == u ? u : par[u] = find(par[u]);
    }

    // written for smalles eqv string 1061
    public static void union(int p1, int p2) {
        if (p1 != p2) {
            par[Math.max(p1, p2)] = Math.min(p1, p2);
        }
    }

    public static String smallestEquivalentString(String A, String B, String S) {
        int N = 26;
        String res = "";
        par = new int[N];

        for (int i = 0; i < 26; i++) {
            par[i] = i;
        }

        for (int i = 0; i < A.length(); i++) {
            char a = A.charAt(i);
            char b = B.charAt(i);

            int p1 = findPar(a - 'a');
            int p2 = findPar(b - 'a');
            union(p1, p2);
        }

        for (int i = 0; i < S.length(); i++) {
            S += (char) findPar(S.charAt(i));
        }

        return res;
    }

    // 695. Max Area of Island
    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length, m = grid[0].length, maxSize = -1;
        int[] par = new int[n * m];
        int[] size = new int[n * m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                par[i * m + j] = i * m + j;
                size[i * m + j] = 1;
            }
        }

        int[][] dir = { { 1, 0 }, { 0, 1 } }; // only down , right needed for DSU
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int myIdx = i * m + j;

                    // vvi always make this as global parent
                    int p1 = findPar(myIdx);

                    for (int d = 0; d < 2; d++) {
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];

                        if (r < n && c < m && grid[r][c] == 1) {
                            int nbrIdx = r * m + c;
                            int p2 = findPar(nbrIdx);

                            if (p1 != p2) {
                                par[p2] = p1;
                                size[p1] += size[p2];
                            }

                        }

                        maxSize = Math.max(maxSize, size[p1]);
                    }
                }
            }
        }

        return maxSize;
    }

    // 990. Satisfiability of Equality Equations fist check all = , then check ! ,
    // if contradict - return false
    public boolean equationsPossible(String[] equations) {
        par = new int[26];
        for (int i = 0; i < 26; i++)
            par[i] = i;

        for (String eq : equations) {
            char c1 = eq.charAt(0);
            char sign = eq.charAt(1);
            char c2 = eq.charAt(3);

            if (sign == '=') {
                int p1 = findPar(c1 - 'a');
                int p2 = findPar(c2 - 'a');

                if (p1 != p2) {
                    par[p2] = p1;
                }
            }
        }

        for (String eq : equations) {
            char c1 = eq.charAt(0);
            char sign = eq.charAt(1);
            char c2 = eq.charAt(3);

            if (sign == '!') {
                int p1 = findPar(c1 - 'a');
                int p2 = findPar(c2 - 'a');

                if (p1 == p2) {
                    return false;
                }
            }
        }
        return true;
    }

    // Pending Number Of Islands II

    // 684. Redundant Connection
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        par = new int[n + 1];
        for (int i = 0; i < n + 1; i++)
            par[i] = i;

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            int p1 = findPar(u);
            int p2 = findPar(v);

            if (p1 == p2)
                return edge;
            else
                par[p1] = p2;
        }

        return new int[] { 0, 0 };
    }

    // 959. Regions Cut By Slashes

    public int regionsBySlashes(String[] grid) {
        int n = grid.length, regions = 1;
        // 2d as 1d
        int m = n + 1;
        par = new int[m * m];
        // init all bundary idx in same set
        for (int i = 0; i < par.length; i++) {
            // row/col in 2d
            par[i] = i; // diff par

            int r = i / m, c = i % m;
            if (r == 0 || r == m - 1 || c == 0 || c == m - 1)
                par[i] = 0;
        }

        /*
         * for ease int x1 = i, y1 = j + 1; int x2 = i + 1, y2 = j; int idx1 = x1 * m +
         * y1; int idx2 = x2 * m + y2; int p1 = findPar(idx1); int p2 = findPar(idx2);
         */

        for (int i = 0; i < n; i++) {
            String s = grid[i];
            for (int j = 0; j < n; j++) {
                if (s.charAt(j) == '/') {
                    int p1 = findPar(i * m + (j + 1));
                    int p2 = findPar((i + 1) * m + j);
                    if (p1 != p2) {
                        par[p1] = p2;
                    } else
                        regions++;
                } else if (s.charAt(j) == '\\') {
                    int p1 = findPar(i * m + j);
                    int p2 = findPar((i + 1) * m + j + 1);
                    if (p1 != p2) {
                        par[p1] = p2;
                    } else
                        regions++;
                }
            }
        }
        return regions;
    }

    // 685. Redundant Connection II (VVI )
    // 3 Cases

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length, skipIdx = -1;
        par = new int[n + 1];

        int[] par1 = { -1, -1 }, par2 = { -1, -1 };
        // 2nd par edge /or first in one case
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int u = edge[0];
            int v = edge[1];

            // if no par
            if (par[v] == 0)
                par[v] = u;
            else {
                par2 = new int[] { u, v };
                par1 = new int[] { par[v], v };

                skipIdx = i;
            }

        }

        // reset par[] array
        for (int i = 0; i < par.length; i++)
            par[i] = i;

        // Apply Union Find skipping 2nd parent Node
        // if still cycle then 2 cases arise
        boolean cycle = false;
        for (int i = 0; i < edges.length; i++) {

            if (i == skipIdx)
                continue;

            int[] edge = edges[i];
            int u = edge[0];
            int v = edge[1];

            int p1 = findPar(u);
            int p2 = findPar(v);

            // no cycle union , else if(no dup par - this edge is ans) , else 2 cases
            if (p1 != p2) {
                par[p1] = p2;
            } else {
                cycle = true;
                if (par1[0] == -1)
                    return edge;
            }
        }

        // 2 parents case
        // if cycle -
        // return cycle causing edge
        // if no cycle return the 2nd parent edge

        // if still cycle after skipping 2nd par egde -> par1 is the cycle causing edge
        // else par2 is cycle causing edge
        return cycle ? par1 : par2;
    }

    public static void main(String args[]) {
        String a = "parker";
        String b = "morris";
        String s = "parser";

        String ans = smallestEquivalentString(a, b, s);
        System.out.println(ans);
    }

}
