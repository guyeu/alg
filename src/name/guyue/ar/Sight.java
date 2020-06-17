package name.guyue.ar;

/**
 * @author hujia
 * @since 2020/6/17, Wed
 */
public class Sight {

    public int maxScoreSightseeingPair(int[] A) {
        int max = Integer.MIN_VALUE;
        int maxOfAiPlusI = A[0];

        for (int j = 1; j < A.length; j++) {
            max = Math.max(max, maxOfAiPlusI + A[j] - j);
            maxOfAiPlusI = Math.max(maxOfAiPlusI, A[j] + j);
        }

        return max;
    }

    public static void main(String[] args) {
        Sight sight = new Sight();
        System.out.println("11=" + sight.maxScoreSightseeingPair(new int[] {8,1,5,2,6}));
        System.out.println("13=" + sight.maxScoreSightseeingPair(new int[] {4,7,5,8}));
    }
}
