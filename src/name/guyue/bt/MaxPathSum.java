package name.guyue.bt;

import name.guyue.Asserts;

public class MaxPathSum {
    // LeetCode 124
    public int maxPathSum(TreeNode root) {
        calculate(root);
        return max;
    }

    private int max = Integer.MIN_VALUE;

    // 返回值是该节点的子树中，从该结点开始的最大路径和
    private int calculate(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = Math.max(calculate(node.left), 0);
        int right = Math.max(calculate(node.right), 0);
        max = Math.max(max, left + right + node.val);
        return Math.max(left, right) + node.val;
    }

    public static void main(String[] args) {
        Asserts.equals(new MaxPathSum().maxPathSum(TreeNode.from(new Integer[]{-10,9,20,null,null,15,7})), 42);
        Asserts.equals(new MaxPathSum().maxPathSum(TreeNode.from(new Integer[]{1,2,3})), 6);
    }
}
