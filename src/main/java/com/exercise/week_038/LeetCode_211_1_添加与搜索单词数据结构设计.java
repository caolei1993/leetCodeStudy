package main.java.com.exercise.week_038;

/**
 * @Author cl
 * @Date 2021/10/19 21:06
 * @Version 1.0
 */
public class LeetCode_211_1_添加与搜索单词数据结构设计 {
    static class Node {
        Node[] tns = new Node[26];
        boolean isWord;
    }
    Node root = new Node();

    public LeetCode_211_1_添加与搜索单词数据结构设计() {

    }

    public void addWord(String word) {
        Node node = root;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            int idx = word.charAt(i) - 'a';
            if (node.tns[idx] == null) {
                node.tns[idx] = new Node();
            }
            node = node.tns[idx];
        }
        node.isWord = true;
    }

    public boolean search(String word) {
        return dfs(word, root, 0);
    }

    private boolean dfs(String word, Node node, int idx) {
        int n = word.length();
        if (n == idx) {
            return node.isWord;
        }
        char c = word.charAt(idx);
        if (c == '.') {
            for (int i = 0; i < 26; i++) {
                if (node.tns[i] != null && dfs(word, node.tns[i], idx + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            int value = c - 'a';
            if (node.tns[value] != null) {
                return dfs(word, node.tns[value], idx + 1);
            }
            return false;
        }
    }
}
