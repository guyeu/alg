package name.guyue.str;

import java.util.List;
import name.guyue.Asserts;

/**
 * @author hujia
 * @since 2020/6/23, Tue
 */
public class AddBinary {
    // LeetCode 67
    public String addBinary(String a, String b) {
        StringBuilder sum = new StringBuilder();

        int al = a.length();
        int bl = b.length();
        int max = Math.max(al, bl);
        boolean c = false;
        for (int i = 1; i <= max; i++) {
            boolean ai = al >= i && a.charAt(al - i) == '1';
            boolean bi = bl >= i && b.charAt(bl - i) == '1';
            sum.append((ai^bi^c) ? "1" : "0");
            c = ai && bi || bi && c || ai && c;
        }

        return c ? sum.append("1").reverse().toString() : sum.reverse().toString();
    }

    public static void main(String[] args) {
        List<String[]> list = List.of(
            new String[]{"11", "1", "100"},
            new String[]{"1010", "1011", "10101"}
        );
        AddBinary binary = new AddBinary();
        list.forEach(is -> Asserts.equals(binary.addBinary(is[0], is[1]), is[2], is[0], is[1]));
    }
}
