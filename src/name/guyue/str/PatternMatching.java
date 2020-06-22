package name.guyue.str;

import java.util.Arrays;
import java.util.Map;
import name.guyue.Asserts;

/**
 * @author hujia
 * @since 2020/6/22, Mon
 */
public class PatternMatching {
    // LeetCode 16.18
    public boolean patternMatching(String pattern, String value) {

        if (pattern.isEmpty() && value.isEmpty()) {
            return true;
        } else if (pattern.isEmpty()) {
            return false;
        } else if (value.isEmpty()) {
            return pattern.chars().distinct().count() == 1;
        } else {
            /*
             nA + nB = len(pattern)
             nA * len(a) + nB * len(b) = len(value)
             */
            int nA = pattern.chars().filter(c -> c == 'a').map(i -> 1).sum();
            int nB = pattern.length() - nA;
            int lenV = value.length();

            if (nA == 0 || nB == 0) {
                int n = nA == 0 ? nB : nA;
                int len = lenV / n;
                return lenV == n * len && value.substring(0, len).repeat(n).equals(value);
            } else {
                for (int lenA = 0; lenA <= lenV / nA; lenA++) {
                    int lenB = (lenV - lenA * nA) / nB;
                    if (nA * lenA + nB * lenB == lenV && match(lenA, lenB, pattern, value)) {
                        return true;
                    }
                }
                return false;
            }
        }
    }

    private boolean match(int lenA, int lenB, String pattern, String target) {
        char[] pA = new char[lenA];
        boolean[] initializedA = new boolean[] {false};
        char[] pB = new char[lenB];
        boolean[] initializedB = new boolean[] {false};
        int[] tIndex = new int[] {0};
        for (char c : pattern.toCharArray()) {
            if (c == 'a') {
                if (mismatch(pA, initializedA, tIndex, lenA, target)) {
                    return false;
                }
            } else {
                if (mismatch(pB, initializedB, tIndex, lenB, target)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean mismatch(char[] p, boolean[] initialized, int[] index, int len, String target) {
        var s = target.substring(index[0], (index[0] = index[0] + len)).toCharArray();
        if (initialized[0]) {
            return !Arrays.equals(p, s);
        } else {
            System.arraycopy(s, 0, p, 0, s.length);
            initialized[0] = true;
            return false;
        }
    }

    public static void main(String[] args) {
        var s = Map.of(
            new String[] {"abba", "dogcatcatdog"}, true,
            new String[] {"abba", "dogcatcatfish"}, false,
            new String[] {"aaaa", "dogcatcatdog"}, false,
            new String[] {"abba", "dogdogdogdog"}, true,
            new String[] {"", ""}, true,
            new String[] {"", "dogdogdogdog"}, false,
            new String[] {"aa", ""}, true,
            new String[] {"abba", ""}, false,
            new String[] {"bbb", "xxxxxx"}, true
        );

        PatternMatching matching = new PatternMatching();
        s.forEach((ss, r) -> Asserts.expect(matching.patternMatching(ss[0], ss[1]), r, ss[0], ss[1]));
    }
}
