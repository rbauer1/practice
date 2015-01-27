package problems.cracking_code.ch1;

import java.util.Arrays;

public class RemoveCharDuplicatesNoBuffer {

	public static void main(String[] args) {
		String test = "aabbccddeeffabcdefg";
		char[] str = test.toCharArray();
		// System.out.println(str);
		removeDuplicates(str);
		System.out.println(Arrays.toString(str));
		for (int i = 0; i < str.length; i++) {
			System.out.println(str[i] + " ");
		}
		// System.out.println((new char[10]).length);
		// System.out.println(Arrays.toString(new char[10]));

		// System.out.println(test.replaceAll(test.substring(i, i + 1), ""));
	}

	/**
	 * This is evil! Don't do this! setting str[tail] = 0 adds a null character
	 * to terminate the char array at index 'tail'
	 * 
	 * @param str
	 */
	public static void removeDuplicates(char[] str) {
		if (str == null)
			return;
		int len = str.length;
		if (len < 2)
			return;
		int tail = 1;
		for (int i = 1; i < len; ++i) {
			int j;
			for (j = 0; j < tail; ++j) {
				if (str[i] == str[j])
					break;
			}
			if (j == tail) {
				str[tail] = str[i];
				++tail;
			}
			// System.out.println(Arrays.toString(str));
		}
		str[tail] = 0;
	}
}
