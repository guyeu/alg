package name.guyue.bt;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * @author hujia
 * @since 2020/6/19, Fri
 */
public class RecoverFromPreorder {

    // LeetCode 1028
    public TreeNode recoverFromPreorder(String S) {

        Deque<TreeNode> deque = new LinkedList<>();
        for (var per : new Preorder(S)) {
            while (per.depth < deque.size()) deque.removeLast();
            TreeNode parent = deque.peekLast();
            TreeNode child = new TreeNode(per.value);
            if (parent != null) {
                if (parent.left == null) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            }
            deque.addLast(child);
        }
        return deque.peekFirst();
    }

    private static class Node {
        private final int depth;
        private final int value;

        private Node(int depth, int value) {
            this.depth = depth;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{\"Node\":{"
                + "\"depth\":\"" + depth + "\""
                + ", \"value\":\"" + value + "\""
                + "}}";
        }
    }

    private static class Preorder implements Iterable<Node> {

        private final String source;

        private Preorder(String source) {
            this.source = source;
        }

        @Override
        public Iterator<Node> iterator() {
            int[] index = new int[] {0};
            return new Iterator<>() {
                @Override
                public boolean hasNext() {
                    return source != null && !source.isEmpty() && index[0] >= 0 && index[0] < source.length();
                }

                @Override
                public Node next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }

                    int depth = 0;
                    while (source.charAt(depth + index[0]) == '-') {
                        depth++;
                    }
                    int next = source.indexOf('-', index[0] + depth);
                    int value;
                    if (next < 0) {
                        value = Integer.parseInt(source.substring(index[0] + depth));
                    } else {
                        value = Integer.parseInt(source.substring(index[0] + depth, next));
                    }
                    index[0] = next;
                    return new Node(depth, value);
                }
            };
        }
    }

    public static void main(String[] args) {
        String[] sources = new String[] {"1-2--3--4-5--6--7", "", "123"};
        for (String source : sources) {
            RecoverFromPreorder recover = new RecoverFromPreorder();
            System.out.println(recover.recoverFromPreorder(source));
        }
    }
}
