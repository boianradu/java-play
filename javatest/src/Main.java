import adapters.SquarePegAdapter;
import round.RoundHole;
import round.RoundPeg;
import round.SquarePeg;

public class Main {
    static void parseStringsPunctuation(String s) {
        String regex = "[,\\.\\s]";
        String[] myArray = s.split(regex);
        for (String x : myArray) {
            System.out.println(x);
        }
    }

    public static int fibonnacci(int n, int x) {
        x++;
        if (n > 1) {
            return fibonnacci(n - 1, x) + fibonnacci(n - 2, x);
        } else {
            return n;
        }
    }

    public static void testRoundAdapter() {
        // Round fits round, no surprise.
        RoundHole hole = new RoundHole(5);
        RoundPeg rpeg = new RoundPeg(5);
        if (hole.fits(rpeg)) {
            System.out.println("Round peg r5 fits round hole r5.");
        }

        SquarePeg smallSqPeg = new SquarePeg(2);
        SquarePeg largeSqPeg = new SquarePeg(20);
        // hole.fits(smallSqPeg); // Won't compile.

        // Adapter solves the problem.
        SquarePegAdapter smallSqPegAdapter = new SquarePegAdapter(smallSqPeg);
        SquarePegAdapter largeSqPegAdapter = new SquarePegAdapter(largeSqPeg);
        if (hole.fits(smallSqPegAdapter)) {
            System.out.println("Square peg w2 fits round hole r5.");
        }
        if (!hole.fits(largeSqPegAdapter)) {
            System.out.println("Square peg w20 does not fit into round hole r5.");
        }
    }

    public static float mediaPonderata(int[] p, int[] n) {
        int result = 0, pFinal = 0;
        for (int i = 0; i < n.length; i++) {
            result += n[i] * p[i];
        }
        for (int i = 0; i < p.length; i++) {
            pFinal += p[i];
        }
        System.out.println("rez " + result + " " + pFinal);
        return (float) ((float) result / (float) pFinal);
    }

    public static void main(String[] args) {

        String myStr = "Split a string by spaces, and also punctuation.";
        System.out.println("Hello world!");
        parseStringsPunctuation(myStr);
        System.out.println(fibonnacci(7, 0));
        int[] n = { 6, 7, 8 };
        int[] p = { 3, 8, 9 };
        System.out.println("Media ponderata:" + mediaPonderata(p, n));

        System.out.println();
        testRoundAdapter();
    }
}