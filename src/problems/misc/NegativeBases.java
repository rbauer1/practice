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
            System.out.printf("Base: %4d N: %- 10d   -Base: %4d N: % d%n", testCase[1], testCase[0], -testCase[1], nb.convertToOppositeSignBase(testCase[0], testCase[1]));
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

    /**
     * Given a <code>number</code> in some <code>base</code>, this will return the same value number represented in
     * <code>-base</code>. <br>
     * e.g. 324 base 7 is 454 base -7 <br>
     * 342 base -5 is 212 base 5
     * @param num in some base given by
     * @param base
     * @return the same value number represented in <code>-base</code>
     */
    public int convertToOppositeSignBase(int num, int base) {
        if (base == 0) {
            throw new IllegalArgumentException("Cannot have base 0");
        }
        boolean pos = base > 0;
        base *= pos ? 1 : -1;
        int powerTen = 10;
        while (powerTen < num) {
            final int thisDigit = (num / powerTen) % 10;
            if (thisDigit != 0) {
                if (pos) {
                    num += powerTen * (10 + base - 2 * thisDigit);
                } else {
                    /*
                    this step has had some algebra done to simplify the operation. what's actually happening is the
                    number is having its digit one to the left of the current odd-power/place digit (thisDigit)
                    decremented by 1 (if thisDigit is not zero, hence the if above, thisDigit is then reduced to 0, and
                    then replaced by abs(base)-(previous value of thisDigit). These multiple steps can be combined into
                    the single operation seen below. The same, but reversed, goes for the other branch of this innermost
                     if-statement
                     */
                    num -= powerTen * (10 - base + 2 * thisDigit);
                }
            }
            powerTen *= 100;
        }
        return num;
    }

    /**
     * Very quickly determines the number of digits in an int
     * http://stackoverflow.com/questions/1306727/way-to-get-number-of-digits-in-an-int
     * @param n
     * @return
     */
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
