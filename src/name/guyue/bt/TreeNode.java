package name.guyue.bt;

import java.util.StringJoiner;

/**
 * @author hujia
 * @since 2020/6/16, Tue
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }

    @Override
    public String toString() {
        return new StringJoiner(", ", TreeNode.class.getSimpleName() + "[", "]")
            .add("val=" + val)
            .add("left=" + left)
            .add("right=" + right)
            .toString();
    }
}
