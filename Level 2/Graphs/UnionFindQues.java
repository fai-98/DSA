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

    public static void main(String args[]) {
        String a = "parker";
        String b = "morris";
        String s = "parser";

        String ans = smallestEquivalentString(a, b, s);
        System.out.println(ans);
    }
}
