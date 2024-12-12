package exercises;

public class LongestString {
    public LongestString() {
        System.out.println("Creating longest string");
    }

    public int getLongestSubstringWithoutRepeating(String s) {
        if (s.length() == 1) {
            return 1;
        }
        int longest = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            String currentString = "";
            currentString = currentString + s.charAt(i);
            for (int j = i + 1; j < s.length(); j++) {
                if (currentString.indexOf(s.charAt(j)) == -1) {
                    currentString = currentString + s.charAt(j);
                } else {
                    break;
                }
            }
            if (currentString.length() > longest) {
                longest = currentString.length();
            }
        }
        return longest;
    }

    private boolean isPalindrome(String s) {
        System.out.println("Check if palindrome: " + s);
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public String getLongestPalindromeSubstring(String s) {
        if (s.length() == 1) {
            return s;
        }
        int count = 0;
        String maxPalindrome = "";
        for (int i = 0; i < s.length() - 1; i++) {
            boolean found = false;
            for (int j = s.length(); j > i; j--) {
                if (j - i > 1000) {
                    break;
                }
                if (isPalindrome(s.substring(i, j))) {
                    maxPalindrome = s.substring(i, j);
                    count = maxPalindrome.length();
                    found = true;
                    break;
                }
            }
            if (found) {
                System.out.println("Longest: " + count + " " + maxPalindrome);
                return maxPalindrome;
            }
        }
        System.out.println("Longest: " + count + " " + maxPalindrome);
        return maxPalindrome;
    }
}
