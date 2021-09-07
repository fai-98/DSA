public class diff_ways_to_add_parenthesis {

    //LeetCode 241. Different Ways to Add Parentheses

    public List < Integer > diffWaysToCompute(String expression) {
        int n = expression.length();
        return ways(expression, 0, n - 1);
    }

    public List < Integer > ways(String str, int si, int ei) {
        //apply cuts at the operators
        List < Integer > myRes = new ArrayList < > ();

        for (int cut = si + 1; cut < ei; cut++) {
            char ch = str.charAt(cut);
            if (ch == '*' || ch == '+' || ch == '-') {
                List < Integer > lRes = ways(str, si, cut - 1);
                List < Integer > rRes = ways(str, cut + 1, ei);

                //generate all possible exp with lRes[i] (opr) rRes(j)
                for (int a : lRes) {
                    for (int b : rRes) {
                        if (ch == '+') {
                            myRes.add(a + b);
                        } else if (ch == '*') {
                            myRes.add(a * b);
                        } else {
                            myRes.add(a - b);
                        }
                    }
                }
            }
        }

        if (myRes.size() == 0) { //base case
            myRes.add(Integer.valueOf(str.substring(si, ei + 1)));
        }
        return myRes;
    }


    //Tabulation
}