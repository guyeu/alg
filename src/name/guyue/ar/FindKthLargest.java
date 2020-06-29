package name.guyue.ar;

import java.util.Arrays;
import name.guyue.Asserts;

/**
 * @author hujia
 * @since 2020/6/29, Mon
 */
public class FindKthLargest {
    // LeetCode 215
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public static void main(String[] args) {
        FindKthLargest func = new FindKthLargest();
        Asserts.equals(5, func.findKthLargest(new int[] {3,2,1,5,6,4}, 2));
        Asserts.equals(4, func.findKthLargest(new int[] {3,2,3,1,2,4,5,5,6}, 4));
    }
}
