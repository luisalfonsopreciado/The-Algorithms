package algorithms.string;

import java.util.Stack;

public class CheckPalindrome {

    // Two Pointer Method
    public boolean isPalindrome(String str){
        for(int i = 0; i < str.length() / 2; i++){
            if(str.charAt(i) != str.charAt(str.length() - 1 - i)) return false;
        }
        return true;
    }

    // Stack Method
    public boolean isPalindromeWithStack(String s){
        Stack<Character> stack = new Stack<>();
        int N = s.length();

        for(int i = 0; i < N; i++){
            stack.add(s.charAt(i));
        } 

        for(int i = 0; i < N; i++){
            if(s.charAt(i) != stack.pop()) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        CheckPalindrome p = new CheckPalindrome();
        System.out.println(p.isPalindromeWithStack("aba"));
    }
}
