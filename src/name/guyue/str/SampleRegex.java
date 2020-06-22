package name.guyue.str;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author hujia
 * @since 2020/6/20, Sat
 */
public class SampleRegex {
//    // 使用标准库的取巧做法
//    public boolean isMatch(String s, String p) {
//        return Pattern.matches(p, s);
//    }

    // LeetCode 10
    public boolean isMatch(String s, String p) {
        if (s == null || s.isEmpty()) {
            return p == null || p.isEmpty();
        } else {
            if (p == null || p.isEmpty()) return false;

            int index = 0;
            for (IPattern pattern : new Patterns(p)) {
                if (index >= s.length() || (index = pattern.match(index, s)) < 0) {
                    return false;
                }
            }
            return index >= s.length();
        }
    }

    private static class Patterns implements Iterable<IPattern> {

        private final String p;

        private Patterns(String p) {
            this.p = p;
        }

        @Override
        public Iterator<IPattern> iterator() {
            int[] index = new int[] {0};
            return new Iterator<>() {

                @Override
                public boolean hasNext() {
                    return index[0] < p.length();
                }

                @Override
                public IPattern next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }

                    char c = p.charAt(index[0]);
                    index[0] = index[0] + 1;
                    if (index[0] < p.length() && p.charAt(index[0]) == '*') {
                        while (index[0] < p.length() && (p.charAt(index[0]) == c || p.charAt(index[0]) == '*')) index[0] = index[0] + 1;
                        return new Sentence(c);
                    } else {
                        return new Letter(c);
                    }
                }
            };
        }
    }

    private static class Sentence implements IPattern {

        private final char letter;

        private Sentence(char letter) {
            this.letter = letter;
        }

        @Override
        public int match(int index, String s) {
            if (letter == '.') {
                return s.length();
            } else {
                while (index < s.length() && letter == s.charAt(index)) index++;
                return index;
            }
        }

        @Override
        public String toString() {
            return "[" + letter + "*]";
        }
    }

    private static class Letter implements IPattern {

        private final char letter;

        private Letter(char letter) {
            this.letter = letter;
        }

        @Override
        public int match(int index, String s) {
            if (letter == '.' || letter == s.charAt(index)) {
                return index + 1;
            } else {
                return -1;
            }
        }

        @Override
        public String toString() {
            return "[" + letter + "]";
        }
    }

    private interface IPattern {

        /**
         * @param index 开始索引
         * @param s 字符串
         * @return 一个索引值，如果大于0.代表从开始索引到该索引值的字符串可以匹配该表达式；否则，无法匹配。
         */
        int match(int index, String s);
    }

    public static void main(String[] args) {
        List<String[]> list = List.of(
            new String[] {"aa", "a"},//false
            new String[] {"aa", "a*"},//true
            new String[] {"ab", ".*"},//true
            new String[] {"aab", "c*a*b"},//true
            new String[] {"aaa", "a*a"},//true
            new String[] {"aaa", "ab*a*c*a"},//true
            new String[] {"mississippi", "mis*is*p*."}//false
        );

        SampleRegex regex = new SampleRegex();
        list.forEach(s -> System.out.println(regex.isMatch(s[0], s[1])));
    }
}
