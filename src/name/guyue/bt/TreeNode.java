package name.guyue.bt;

/**
 * @author hujia
 * @since 2020/6/16, Tue
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }

    public static TreeNode from(Integer[] array) {
        return from(array, 0);
    }

    private static TreeNode from(Integer[] array, int index) {
        if (index < array.length && array[index] != null) {
            TreeNode node = new TreeNode(array[index]);
            node.left = from(array, (index + 1) * 2 - 1);
            node.right = from(array, (index + 1) * 2);
            return node;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "{\"TreeNode\":{"
            + "\"val\":\"" + val + "\""
            + ", \"left\":" + left
            + ", \"right\":" + right
            + "}}";
    }
}
