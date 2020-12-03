package algorithms.string;

import java.util.Arrays;

public class CheckAnagrams {
    public static boolean isAnagrams(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        char[] letters1 = s1.toCharArray();
        char[] letters2 = s2.toCharArray();
        Arrays.sort(letters1);
        Arrays.sort(letters2);
        return Arrays.equals(letters1, letters2);
    }

    public static void main(String[] args) {
        assert isAnagrams("Silent", "Listen");
        assert isAnagrams("This is a string", "Is this a string");
        assert !isAnagrams("There", "Their");
    }
}
