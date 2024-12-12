import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import adapters.SquarePegAdapter;
import exercises.LongestString;
import round.RoundHole;
import round.RoundPeg;
import round.SquarePeg;
import exercises.LongestString;

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

    public static String solveEquation(Map<String, String> equation) {

        String result = null;

        for (Map.Entry<String, String> entry : equation.entrySet()) {

            String regexEquation = "^[0-9\\s+\\-*/]+$|^[0-9]+$";
            if (Pattern.matches(regexEquation, entry.getValue())) {
                if (entry.getKey().matches("[A-Z]+") && entry.getKey().length() > 3) {
                    return ("Error: " + entry.getKey() + " - nonterminal max length exceeded!");
                }
                continue;
            }
            while (!Pattern.matches(regexEquation, entry.getValue())) {
                // Remove all spaces
                String withoutSpaces = entry.getValue().replaceAll("\\s+", "");

                // // Split by "*/+-"
                String[] equationEntities = withoutSpaces.split("[*/+\\-]");

                for (String ent : equationEntities) {
                    if (ent.matches("[A-Z]+") && ent.length() > 3) {
                        return ("Error: " + ent + " - nonterminal max length exceeded!");
                    }
                }

                if (withoutSpaces.contains(entry.getKey())) {
                    return ("Error: " + entry.getKey() + " - self implication not allowed!");
                }
                if (entry.getKey().matches("-?\\d+(\\.\\d+)?")) {
                    return ("Error: " + entry.getKey() + " - terminals can't be left-hand operands!");
                }
                for (String ent : equationEntities) {
                    if (!Pattern.matches(regexEquation, ent)) {
                        String eq = equation.get(ent);
                        String v = entry.getValue();
                        String replacedV = v.replace(ent, eq);
                        equation.put(entry.getKey(), replacedV);
                    }
                }
            }

        }

        result = equation.entrySet()
                .stream()
                .map(entry -> entry.getKey() + " = " + entry.getValue())
                .collect(Collectors.joining("\n"));
        return result;

    }

    public static void main(String[] args) {

        // String input = "A = B + C\nB = D * C\nC = 1\n D = E / F\n E = 1\n F = 1";
        String input = "CCCC=1";

        Map<String, String> equationMap = Arrays.stream(input.split("\n"))
                .map(line -> line.split("=", 2))
                .collect(Collectors.toMap(
                        parts -> parts[0].trim(),
                        parts -> parts[1].trim()));

        String result = solveEquation(equationMap);
        System.out.println(result);

        // String myStr = "Split a string by spaces, and also punctuation.";
        // System.out.println("Hello world!");
        // parseStringsPunctuation(myStr);
        // System.out.println(fibonnacci(7, 0));
        // int[] n = { 6, 7, 8 };
        // int[] p = { 3, 8, 9 };
        // System.out.println("Media ponderata:" + mediaPonderata(p, n));

        // System.out.println();
        // testRoundAdapter();

        // String myStr = "pwwkew";
        // LongestString ls = new LongestString();
        // ls.getLongestSubstringWithoutRepeating(myStr);

        // myStr = "asddsa";
        // ls.getLongestPalindromeSubstring(myStr);
    }
}