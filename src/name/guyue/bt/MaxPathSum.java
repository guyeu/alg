package name.guyue.bt;

import name.guyue.Asserts;

public class MaxPathSum {
    // LeetCode 124
    public int maxPathSum(TreeNode root) {
        return maxPathSum(root, root.val);
    }

    private int max = Integer.MIN_VALUE;

    // 返回值是该节点的子树中，从孩子结点开始的最大路径和
    private int maxPathSum(TreeNode node, int above) {
        if (node.left == null && node.right == null) {
            return node.val;
        } else if (node.left == null) {
            return node.val + maxPathSum(node.right, above + node.val);
        } else if (node.right == null) {
        } else {
            max = Math.max(max, above);
            return 0;
        } else {
            int left, right;
            above = above + node.val;
            if (above >= 0) {
                left = maxPathSum(node.left, above);
                right = maxPathSum(node.right, above);
            } else {
                left = maxPathSum(node.left, 0);
                right = maxPathSum(node.right, 0);
            }
            return Math.max(Math.max(left, right), left + right + node.val - above - above);
        }
    }

    public static void main(String[] args) {
        MaxPathSum sum = new MaxPathSum();
        Asserts.equals(sum.maxPathSum(TreeNode.from(new Integer[]{-10,9,20,null,null,15,7})), 42);
        Asserts.equals(sum.maxPathSum(TreeNode.from(new Integer[]{1,2,3})), 6);
    }
}
