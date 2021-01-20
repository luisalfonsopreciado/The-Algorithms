package algorithms.string;

import java.util.Stack;

/**
 * CheckPalindrome class supports various implementations for checking if a
 * string is a valid palindrome.
 */
public class CheckPalindrome {

    /**
     * Return true if the given input string is a valid palindrome. Uses two pointer
     * method.
     * 
     * @param str
     * @return
     */
    public boolean isPalindrome(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i))
                return false;
        }
        return true;
    }

    /**
     * Returns true if the given input string is a valid palindrome. Uses a stack.
     * 
     * @param s
     * @return
     */
    public boolean isPalindromeWithStack(String s) {
        Stack<Character> stack = new Stack<>();
        int N = s.length();

        for (int i = 0; i < N; i++) {
            stack.add(s.charAt(i));
        }

        for (int i = 0; i < N; i++) {
            if (s.charAt(i) != stack.pop())
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        CheckPalindrome p = new CheckPalindrome();
        System.out.println(p.isPalindromeWithStack("aba"));
    }
}
