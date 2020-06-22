package name.guyue.str;

import java.util.List;

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
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = false;
        }
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = i > 1 && '*' == p.charAt(i-1) && dp[0][i-2];
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (p.charAt(j-1) == '*') {
                    dp[i][j] = dp[i][j-2] || (s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.') && dp[i-1][j];
                } else {
                    dp[i][j] = (p.charAt(j-1) == '.' || s.charAt(i-1) == p.charAt(j-1)) && dp[i-1][j-1];
                }
            }
        }
        return dp[s.length()][p.length()];
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
