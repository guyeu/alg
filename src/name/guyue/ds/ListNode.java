package name.guyue.ds;

public class ListNode {
    public final int val;
    public ListNode next;
    public ListNode(int x) { val = x; }

    @Override
    public String toString() {
        var builder = new StringBuilder().append('[');
        var next = this;
        while (next.next != null) {
            builder.append(next.val).append(',');
            next = next.next;
        }
        return builder.append(next.val).append(']').toString();
    }

    public static ListNode from(int value, int... values) {
        var foo = new ListNode(value);
        var head = foo;
        for (int v : values) {
            foo.next = new ListNode(v);
            foo = foo.next;
        }
        return head;
    }
}
