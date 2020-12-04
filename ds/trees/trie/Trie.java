package ds.trees.trie;

import java.util.HashMap;

/**
 * Implementation of a Prefix Trie Data Structure
 */
public class Trie {

    /**
     * Internal TrieNode class that represents a character
     */
    class TrieNode {
        char val;
        boolean isEnd = false;
        HashMap<Character, TrieNode> nextNodes;

        public TrieNode() {
            nextNodes = new HashMap<>();
        }

        public TrieNode(char val) {
            this();
            this.val = val;
        }
    }

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a string into the trie
     * 
     * @param str
     */
    public void insert(String str) {
        TrieNode curr = root;

        for (char c : str.toCharArray()) {
            if (curr.nextNodes.containsKey(c)) {
                curr = curr.nextNodes.get(c);
                continue;
            }
            curr.nextNodes.put(c, new TrieNode(c));
            curr = curr.nextNodes.get(c);
        }

        curr.isEnd = true;
    }

    /**
     * ContainsWord returns true only if the trie contains the complete input string
     * (not Prefix)
     * 
     * @param str input String
     * @return Boolean
     */
    public boolean containsWord(String str) {
        TrieNode curr = root;

        for (char c : str.toCharArray()) {
            if (!curr.nextNodes.containsKey(c))
                return false;
            curr = curr.nextNodes.get(c);
        }

        return curr.isEnd;
    }

    /**
     * ContainsPrefix returns true if the input string is contained in the trie as a
     * prefix or complete word
     * 
     * @param str
     * @return Boolean
     */
    public boolean containsPrefix(String str) {
        TrieNode curr = root;

        for (char c : str.toCharArray()) {
            if (!curr.nextNodes.containsKey(c))
                return false;
            curr = curr.nextNodes.get(c);
        }

        return true;
    }

    // Driver Code
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.containsWord("apple"));
        System.out.println(trie.containsPrefix("a"));
    }

}
