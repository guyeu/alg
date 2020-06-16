package name.guyue.bt;


import java.util.Arrays;

/**
 * @author hujia
 * @since 2020/6/16, Tue
 */
public class Codec {

    // Encodes a tree to a single string.
    // (left(), right)
    public String serialize(TreeNode root) {
        if (root != null) {
            return serialize(new StringBuilder(), root).toString();
        } else {
            return "";
        }
    }

    private StringBuilder serialize(StringBuilder builder, TreeNode node) {
        if (node != null) {
            return builder.append(node.val)
                .append('(')
                .append(serialize(new StringBuilder(), node.left))
                .append(',')
                .append(serialize(new StringBuilder(), node.right))
                .append(')');
        } else {
            return builder;
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        if (data == null || data.isEmpty()) {
            return null;
        } else {
            int[] bracket = new int[] {data.indexOf('(')};
            return deserialize(new TreeNode(Integer.parseInt(data.substring(0, bracket[0]))), data, bracket);
        }
    }

    private TreeNode deserialize(TreeNode node, String data, int[] bracket) {

        if (data.charAt(bracket[0]+1) == ',') {
            node.left = null;
        } else {
            int index = data.indexOf('(', bracket[0]+1);
            int value = Integer.parseInt(data.substring(bracket[0]+1, index));
            bracket[0] = index;
            node.left = deserialize(new TreeNode(value), data, bracket);
        }

        int comma = data.indexOf(',', bracket[0]);
        if (data.charAt(comma+1) == ')') {
            node.right = null;
        } else {
            int index = data.indexOf('(', comma);
            int value = Integer.parseInt(data.substring(comma+1, index));
            bracket[0] = index;
            node.right = deserialize(new TreeNode(value), data, bracket);
        }

        bracket[0] = data.indexOf(')', bracket[0]+1);
        return node;
    }

    public static void main(String[] args) {
        Integer[] input = new Integer[] {3,2,4,1};

        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(2);
        node.right = new TreeNode(4);
        node.left.left = new TreeNode(1);

        System.out.println(node);

        Codec codec = new Codec();
        String serialize = codec.serialize(node);
        System.out.println(serialize);
        System.out.println(codec.deserialize(serialize));
    }
}
