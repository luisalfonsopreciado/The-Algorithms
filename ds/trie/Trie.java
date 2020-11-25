package ds.trie;

import java.util.HashMap;

public class Trie {
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

    public boolean containsWord(String str) {
        TrieNode curr = root;

        for (char c : str.toCharArray()) {
            if (!curr.nextNodes.containsKey(c))
                return false;
            curr = curr.nextNodes.get(c);
        }

        return curr.isEnd;
    }

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
