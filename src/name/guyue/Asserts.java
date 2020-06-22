package name.guyue;

public class Asserts {

    private Asserts() {}

    public static void equals(int a, int b) {
        if (a != b) {
            throw new AssertionError(String.format("%d != %d", a, b));
        }
    }
}
