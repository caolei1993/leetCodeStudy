package main.java.com.exercise.week_012;

/**
 * @Author cl
 * @Date 2021/4/14 10:40
 * @Version 1.0
 * https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 */
public class LeetCode_208_1_实现Trie前缀树 {

    class Trie {
        Trie[] children;
        boolean isEnd;

        /** Initialize your data structure here. */
        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length() ; i++) {
                char ch = word.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Trie node = searchStr(word);
            return node != null && node.isEnd;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            Trie node = searchStr(prefix);
            return node != null;
        }

        private Trie searchStr(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int index = ch - 'a';
                if (node.children[index] != null) {
                    node = node.children[index];
                } else {
                    return null;
                }
            }
            return node;
        }
    }

}
