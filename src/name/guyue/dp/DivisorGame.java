package name.guyue.dp;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;
import name.guyue.Asserts;

/**
 * @author hujia
 * @since 2020/6/22, Mon
 */
public class DivisorGame {

    // LeetCode 1025
    public boolean divisorGame(int N) {
        /*
          Alice先选
          N=1: 没有可选的，false
          N=2: 因子只有1，导致Bob处于N=1的状态，true；
          N=3: 因子只有1，导致Bob处于N=2的状态，false；
          N=4: 因子1、2，选1使Bob处于N=3的状态，true；选2使Bob处于N=2的状态，false；因此理智的Alice选1，true；
          ...
          N=n: 遍历所有的因子，检查是否有其中一个因子可以使Bob处于false的状态，如果有，Alice胜利，否则Bob胜利。
         */
        boolean[] dp = new boolean[N+1];
        IntStream.range(1, dp.length)
            .forEach(index -> {
                factors(index).forEachRemaining(factor -> {
                    dp[index] = !dp[index-factor] || dp[index];
                });
            });
        return dp[N];
    }

    private Iterator<Integer> factors(int n) {
        int divide = n / 2;
        int[] next = new int[] {1};
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return next[0] <= divide;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int r = next[0];

                while ((next[0] = next[0] + 1) <= divide) {
                    if (n % next[0] == 0) {
                        return r;
                    }
                }

                return r;
            }
        };
    }

    public static void main(String... args) {
        var map = Map.of(
            2, true,
            3, false,
            5, false
        );

        DivisorGame game = new DivisorGame();
        map.forEach((n, r) -> Asserts.expect(game.divisorGame(n), r));
    }
}
