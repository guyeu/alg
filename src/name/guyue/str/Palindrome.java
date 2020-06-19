package name.guyue.str;

import java.util.OptionalInt;

/**
 * @author hujia
 * @since 2020/6/19, Fri
 */
public class Palindrome {

    // LeetCode 125
    public boolean isPalindrome(String s) {
        if (s == null || s.isEmpty() || s.isBlank()) {
            return true;
        }

        int start = -1;
        int end = s.length();

        while (start < end) {
            OptionalInt next = next(start + 1, s);
            OptionalInt pre = pre(end - 1, s);
            if (next.isEmpty() || pre.isEmpty()) {
                break;
            } else if (!equals(s.charAt((start = next.getAsInt())), s.charAt((end = pre.getAsInt())))) {
                return false;
            }
        }

        return true;
    }

    private OptionalInt next(int index, String s) {
        do {
            if (valid(s.charAt(index))) {
                return OptionalInt.of(index);
            }
            index++;
        }
        while(index(index, s));
        return OptionalInt.empty();
    }

    private OptionalInt pre(int index, String s) {
        do {
            if (valid(s.charAt(index))) {
                return OptionalInt.of(index);
            }
            index--;
        }
        while(index(index, s));
        return OptionalInt.empty();
    }

    private boolean equals(char a, char b) {
        return a == b
            || (a >= 'a' && a <= 'z' && b >= 'A' && b <= 'Z' && a - 'a' == b - 'A')
            || (a >= 'A' && a <= 'Z' && b >= 'a' && b <= 'z' && a - 'A' == b - 'a');
    }

    private boolean index(int index, String s) {
        return index >= 0 && index < s.length();
    }

    private boolean valid(char c) {
        return (c >= '0' && c <= '9')
            || (c >= 'a' && c <= 'z')
            || (c >= 'A' && c <= 'Z');
    }

    public static void main(String... args) {
        Palindrome palindrome = new Palindrome();
        System.out.println(palindrome.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(palindrome.isPalindrome("race a car"));
        System.out.println(palindrome.isPalindrome(""));
        System.out.println(palindrome.isPalindrome(" "));
        System.out.println(palindrome.isPalindrome(","));
    }
}
