public class evaluations {

	//Prefix

	//Infix

	//Postfix

	// 150. Evaluate Reverse Polish Notation
	public int evalRPN(String[] tokens) {
		//it is Postfix notation
		Stack < Integer > st = new Stack < > ();
		for (String s : tokens) {
			int exp = 0;

			if (s.equals("+")) {
				int b = st.pop();
				int a = st.pop();
				exp = a + b;
				st.push(exp);
			} else if (s.equals("*")) {
				int b = st.pop();
				int a = st.pop();
				exp = a * b;
				st.push(exp);
			} else if (s.equals("-")) {
				int b = st.pop();
				int a = st.pop();
				exp = a - b;
				st.push(exp);
			} else if (s.equals("/")) {
				int b = st.pop();
				int a = st.pop();
				exp = a / b;
				st.push(exp);
			} else {
				st.push(Integer.parseInt(s));
			}

		}

		return st.peek();
	}
}