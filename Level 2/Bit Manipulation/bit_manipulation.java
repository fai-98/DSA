public class bit_manipulation {

	// Basics Of Bit Manipulation
	// set/on ith bit
	// unset/off jth
	// toggle kth
	// check mth

	public static void basics() {
		int onmask = (1 << i);
		int offmask = ~(1 << j);
		int tmask = (1 << k);
		int cmask = (1 << m);


		System.out.println(n | onmask);
		System.out.println(n & offmask);
		System.out.println(n ^ tmask);
		System.out.println((n & cmask) == 0 ? "false" : "true");
	}

	//Print RSBM - Right Most Set Bit
}