package name.guyue.ds;

import java.util.HashSet;

public class RemoveDuplicated {

    public ListNode removeDuplicateNodes(ListNode head) {
        var foo = head;
        var pre = (ListNode) null;
        var set = new HashSet<Integer>();
        while (foo != null) {
            if (set.contains(foo.val)) {
                pre.next = foo.next;
            } else {
                set.add(foo.val);
                pre = foo;
            }
            foo = foo.next;
        }
        return head;
    }

    public static void main(String[] args) {
        RemoveDuplicated solution = new RemoveDuplicated();
        System.out.println(solution.removeDuplicateNodes(ListNode.from(1, 2, 3, 3, 2, 1)));
        System.out.println(solution.removeDuplicateNodes(ListNode.from(1, 1, 1, 1, 2)));
    }
}
