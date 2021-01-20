package ds.trees.trie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import ds.trees.trie.Trie.TrieNode;

/**
 * A Patricia trie is a trie where silly nodes are merged into their parents.
 * 
 * A node is a silly node if it is a non-root node that only has one child.
 * 
 * Observations: - Every internal node in a patricia trie (except possibly the
 * root) has two or more children.
 * 
 * - Leaves correspond to words; internal nodes are there for routing purposes.
 * 
 * Theorem:
 * 
 * The number of nodes in a Patricia trie with k words is always O(k),
 * regardless of what those words are.
 * 
 * @author Luis Preciado <luisapreciado99@gmail.com>
 */
public class PatriciaTrie {
    public static class PTrieNode {
        String val;
        HashMap<String, PTrieNode> children;
        boolean isEnd;
        int index = -1;

        public PTrieNode() {
            children = new HashMap<>();
        }

        public PTrieNode(String val) {
            this();
            this.val = val;
        }

        public String toString() {
            return val;
        }
    }

    private Trie trie;
    private PTrieNode root;

    public PatriciaTrie() {
        trie = new Trie();
        root = new PTrieNode();
    }

    public int size() {
        return trie.size();
    }

    public void insert(String str) {
        trie.insert(str);
    }

    public void compress() {
        PTrieNode child = compressUtil(trie.getRoot());
        root.children.put(child.val.trim(), child);
    }

    public PTrieNode compressUtil(TrieNode root) {
        TrieNode triePtr = root;
        StringBuilder str = new StringBuilder();

        while (triePtr.children.size() == 1) {
            str.append(triePtr.val);
            triePtr = triePtr.children.values().iterator().next();
        }
        str.append(triePtr.val);

        PTrieNode out = new PTrieNode(str.toString());
        out.isEnd = triePtr.isEnd;
        out.index = triePtr.index;

        for (TrieNode node : triePtr.children.values()) {
            PTrieNode child = compressUtil(node);
            out.children.put(child.val, child);
        }

        return out;
    }

    public void traverse() {
        traverseUtil(root);
    }

    public void traverseUtil(PTrieNode root) {
        if (root == null)
            return;
        System.out.println(root.val + "->" + root.children);
        for (PTrieNode adj : root.children.values()) {
            traverseUtil(adj);
        }
    }

    /**
     * A Preorder traversal of the trie prints all words in sorted order
     */
    public void preorder() {
        this.preorderUtil(root, new StringBuilder());
    }

    /**
     * Performs a DFS on the trie, printing all words contained in the structure.
     * 
     * @param curr
     * @param builder
     */
    private void preorderUtil(PTrieNode curr, StringBuilder builder) {
        int oldBuilderSize = builder.length();

        if (curr.val != null) {
            builder.append(curr.val);
        }

        if (curr.isEnd) {
            System.out.println(curr.index + " " + builder);
        }
        // Perform Recursive DFS
        for (Map.Entry<String, PTrieNode> entry : curr.children.entrySet()) {
            preorderUtil(entry.getValue(), builder);
        }
        // Delete the recently appended characters of curr
        builder.delete(oldBuilderSize, builder.length());
    }

    public List<String> query(String prefix) {
        List<String> output = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        StringBuilder preStr = new StringBuilder();
        PTrieNode curr = root;
        int prefixPtr = 0;

        while (prefixPtr < prefix.length()) {

            builder.append(prefix.charAt(prefixPtr++));
            // For some reason map always returns false
            if (curr.children.containsKey(builder.toString())) {
                curr = curr.children.get(builder.toString());
                preStr.append(builder);
                builder.delete(0, builder.length());
            }
        }

        if (curr == root)
            return output;

        for (String key : curr.children.keySet()) {
            output.add(preStr.toString() + key);
        }

        return output;
    }

    public static void main(String[] args) {
        String[] words = { "ant", "anteater", "antelope", "antique" };
        PatriciaTrie pt = new PatriciaTrie();
        for (String word : words) {
            pt.insert(word);
        }
        pt.compress();
        pt.traverse();
        pt.preorder();
        System.out.println(pt.query("ant"));
    }
}
