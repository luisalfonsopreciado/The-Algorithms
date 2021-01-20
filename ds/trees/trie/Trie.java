package ds.trees.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of a Prefix Trie Data Structure
 * 
 * @author Luis Preciado <luisapreciado99@gmail.com>
 */
public class Trie {

    /**
     * Internal TrieNode class that represents a character
     */
    class TrieNode {
        char val;
        boolean isEnd = false;
        HashMap<Character, TrieNode> children;
        // Last Character of a word contains an index > 0
        int index = -1;

        public TrieNode() {
            children = new HashMap<>();
        }

        public TrieNode(char val) {
            this();
            this.val = val;
        }
    }

    private TrieNode root;
    private int size;

    public Trie() {
        root = new TrieNode();
        size = 0;
    }

    public int size() {
        return size;
    }

    public TrieNode getRoot() {
        return root;
    }

    /**
     * Inserts a string into the trie
     * 
     * @param str
     */
    public void insert(String str) {
        TrieNode curr = root;

        for (char c : str.toCharArray()) {
            if (curr.children.containsKey(c)) {
                curr = curr.children.get(c);
                continue;
            }
            curr.children.put(c, new TrieNode(c));
            curr = curr.children.get(c);
        }

        curr.isEnd = true;
        curr.index = size++;
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
            if (!curr.children.containsKey(c))
                return false;
            curr = curr.children.get(c);
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
            if (!curr.children.containsKey(c))
                return false;
            curr = curr.children.get(c);
        }

        return true;
    }

    /**
     * A Preorder traversal of the trie prints all words in sorted order
     */
    public void preorder() {
        this.preorderUtil(root, new StringBuilder());
    }

    private void preorderUtil(TrieNode curr, StringBuilder builder) {
        builder.append(curr.val);
        if (curr.isEnd) {
            System.out.println(curr.index + " " + builder);
        }
        for (Map.Entry<Character, TrieNode> entry : curr.children.entrySet()) {
            preorderUtil(entry.getValue(), builder);
        }
        builder.deleteCharAt(builder.length() - 1);
    }

    // Driver Code
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("ant");
        trie.insert("apple");
        trie.insert("ante");
        trie.insert("anteater");
        trie.insert("antelope");
        trie.insert("antique");
        trie.preorder();
    }

}
