package name.guyue;

import java.util.Arrays;

public class Asserts {

    private Asserts() {}

    public static void equals(int a, int b, String... messages) {
        if (a != b) {
            throw new AssertionError(String.format("%s: %d != %d.", Arrays.toString(messages), a, b));
        }
    }

    public static void expect(boolean expression, boolean expect, String... messages) {
        if (expression != expect) {
            throw new AssertionError(String.format("%s: expect %s but %s.", Arrays.toString(messages), expect, expression));
        }
    }
}
