package name.guyue;

public class Asserts {

    private Asserts() {}

    public static void equals(int a, int b) {
        if (a != b) {
            throw new AssertionError(String.format("%d != %d.", a, b));
        }
    }

    public static void expect(boolean expression, boolean expect) {
        if (expression != expect) {
            throw new AssertionError(String.format("expect %s but %s.", expect, expression));
        }
    }
}
