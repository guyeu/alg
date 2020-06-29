package name.guyue.ar;

import name.guyue.Asserts;

/**
 * @author hujia
 * @since 2020/6/28, Sun
 */
public class MinSubArrayLen {
    // LeetCode 209
    public int minSubArrayLen(int s, int[] nums) {
        int start = 0;
        int length = 0;
        int sum = 0;
        for (int end = 0; end < nums.length; end++) {
            sum += nums[end];
            while ((sum - nums[start]) >= s) {
                sum -= nums[start];
                start++;
            }
            if (sum >= s) {
                length = length == 0 ? end - start + 1 : Math.min(end - start + 1, length);
            }
        }
        return length;
    }

    public static void main(String[] args) {
        MinSubArrayLen func = new MinSubArrayLen();
        Asserts.equals(2, func.minSubArrayLen(7, new int[] {2,3,1,2,4,3}));
    }
}
