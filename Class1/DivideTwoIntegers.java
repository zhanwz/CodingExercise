package Class1;

// https://leetcode.com/problems/divide-two-integers/
// Start time: 11:23
//End time: 11:38
//Pass all cornercase: 12:10 it took me some time to pass all the corner cases.
public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        // if dividend < divisor  then we get remainder
        // a very naive thought is to keep substract divisor from dividend till we can find remainder.
        //  if dividend = m, divisor = n
        // Time complexity would be o(m / n)
        // Space complexity: o(1)
        // We can improve time complexity by doing using recursion:
        // increment  divisor iteratively
        // divisor + divisor
        // divisor + divisor + divisor + divisor in next iteration


        if (divisor == 0) {
            throw new IllegalArgumentException();
        }

        if (dividend == 0) {
            return 0;
        }

        if (divisor == 1) {
            return dividend;
        }

        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        if (dividend == Integer.MIN_VALUE && divisor == 1) {
            return Integer.MIN_VALUE;
        }

        boolean isNegative = (dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0) ? false : true;

        int result = 0;

        long dividendLong =  Math.abs((long) dividend);
        long divisorLong = Math.abs((long) divisor);

        while(dividendLong >= divisorLong) {
            int count = 1;
            long divisorCopy = divisorLong;
            while (dividendLong >= divisorCopy) {
                result += count;
                dividendLong -= divisorCopy;
                divisorCopy += divisorCopy;
                count = count + count;
            }
        }

        return isNegative ? (-1) * result : result;
    }
}
