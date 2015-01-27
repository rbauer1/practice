package problems.misc;

/**
 * Created by riley on 1/26/15.
 */
public class NegativeBases {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        NegativeBases nb = new NegativeBases();
        int[][] tests = {
                {12345678, -10},
                {7211, -10},
                {454, -10},
                {324, 7},
                {342, -5},
                {141, -8},
                {4021553, -7},
                {4016423, 7}
        };
        for (int[] testCase : tests) {
            System.out.printf("Base: %4d N: %- 10d   -Base: %4d N: % d%n", testCase[1], testCase[0], -testCase[1], nb.convertNegToPos(testCase[0], testCase[1]));
        }
    }

    public int convertToBase10(int num, final int base) {
        final int length = num_digits(num);
        int mult = base;
        int result = 0;
        result += num % 10;
        num /= 10;
        for (int i = 1; i < length; i++) {
            result += mult * (num % 10);
            num /= 10;
            mult *= base;
        }
        return result;
    }

    //if digit location is odd (0-indexed. power is odd),
    // and the digit is > 0, then replace that digit with
    // base-digit and subtract 1 from digit to the left (greater)

    public int convertNegToPos(int num, int base){
        base *= base<0?-1:1;
        int powerTen = 10;
        while(powerTen < num) {
            final int thisDigit = (num / powerTen) % 10;
            if (thisDigit != 0) {
                num -= powerTen * 10 - powerTen * (base - 2 * thisDigit);
            }
            powerTen *= 100;
        }
        return num;
    }

    private int num_digits(int n) {
        if (n < 100000) {    // 5 or less digits
            if (n < 100) {   // 1 or 2
                if (n < 10)
                    return 1;
                return 2;
            }// 3, 4, or 5
            if (n < 1000)
                return 3;
            // 4 or 5
            if (n < 10000)
                return 4;
            return 5;
        }
        // 6 or more
        if (n < 10000000) { // 6 or 7
            if (n < 1000000)
                return 6;
            return 7;
        }
        // 8 to 10
        if (n < 100000000)
            return 8;
        // 9 or 10
        if (n < 1000000000)
            return 9;
        return 10;
    }
}
