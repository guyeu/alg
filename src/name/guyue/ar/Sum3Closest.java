package name.guyue.ar;

import java.util.Arrays;
import name.guyue.Asserts;

/**
 * @author hujia
 * @since 2020/6/24, Wed
 */
public class Sum3Closest {
    // LeetCode 16
    public int threeSumClosest(int[] nums, int target) {
        int sum3 = nums[0] + nums[1] + nums[2];
        int abs3 = Math.abs(target - sum3);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                for (int k = j+1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    int abs = Math.abs(target - sum);
                    if (abs < abs3) {
                        sum3 = sum;
                        abs3 = abs;
                    }
                }
            }
        }
        return sum3;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {-3,-2,-5,3,-4};
        int target = -2;
        Sum3Closest closest = new Sum3Closest();
        Asserts.equals(closest.threeSumClosest(nums, target), -2, Arrays.toString(nums), String.valueOf(target));
    }
}
