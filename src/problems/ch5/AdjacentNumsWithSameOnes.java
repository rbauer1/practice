package problems.ch5;

public class AdjacentNumsWithSameOnes {
	public static void main(String[] args) {
		Long time = System.nanoTime();
		for(int i=0; i<100000; i++){
			findNextLargest(i);
		}
		System.out.println(System.nanoTime()-time);
		time = System.nanoTime();
		for(int i=0; i<100000; i++){
			findNextLargest(i);
		}
		System.out.println(System.nanoTime()-time);
		time = System.nanoTime();
		for(int i=0; i<100000; i++){
			findNextLargest(i);
		}
		System.out.println(System.nanoTime()-time);
		time = System.nanoTime();
		for(int i=0; i<100000; i++){
			findNextLargest(i);
		}
		System.out.println(System.nanoTime()-time);
		time = System.nanoTime();
		for(int i=0; i<100000; i++){
			findNextLargest(i);
		}
		System.out.println(System.nanoTime()-time);
		System.out.println("-------------------");
		time = System.nanoTime();
		for(int i=0; i<100000; i++){
			getNext_NP(i);
		}
		System.out.println(System.nanoTime()-time);
		time = System.nanoTime();
		for(int i=0; i<100000; i++){
			getNext_NP(i);
		}
		System.out.println(System.nanoTime()-time);
		time = System.nanoTime();
		for(int i=0; i<100000; i++){
			getNext_NP(i);
		}
		System.out.println(System.nanoTime()-time);
		time = System.nanoTime();
		for(int i=0; i<100000; i++){
			getNext_NP(i);
		}
		System.out.println(System.nanoTime()-time);
		time = System.nanoTime();
		for(int i=0; i<100000; i++){
			getNext_NP(i);
		}
		System.out.println(System.nanoTime()-time);
	}

	private static int findNextLargest(int n) {
		if (n == 0) {
			return Integer.MIN_VALUE;
		}
		int pos1 = 1;
		boolean found = false;
		while (!found) {
			found = (pos1 & n) != 0;
			pos1 <<= 1;
		}
		int pos0 = pos1;
		pos1 >>= 1;
		found = false;
		while (!found) {
			found = (pos0 & n) == 0;
			pos0 <<= 1;
		}
		pos0 >>= 1;
		n ^= pos1 | pos0;
		int temp = 1;
		pos1 <<= 1;
		while (pos0 > pos1) {
			n ^= pos1;
			temp <<= 1;
			temp++;
			pos1 <<= 1;
		}
		temp >>= 1;
		n += temp;
		return n;
	}

	public static boolean GetBit(int n, int index) {
		return ((n & (1 << index)) > 0);
	}

	public static int SetBit(int n, int index, boolean b) {
		if (b) {
			return n | (1 << index);
		} else {
			int mask = ~(1 << index);
			return n & mask;
		}
	}

	public static int getNext_NP(int n) {
		if (n <= 0)
			return -1;

		int index = 0;
		int countOnes = 0;

		// Find first one.
		while (!GetBit(n, index))
			index++;

		// Turn on next zero.
		while (GetBit(n, index)) {
			index++;
			countOnes++;
		}
		n = SetBit(n, index, true);

		// Turn off previous one
		index--;
		n = SetBit(n, index, false);
		countOnes--;

		// Set zeros
		for (int i = index - 1; i >= countOnes; i--) {
			n = SetBit(n, i, false);
		}

		// Set ones
		for (int i = countOnes - 1; i >= 0; i--) {
			n = SetBit(n, i, true);
		}

		return n;
	}
}
