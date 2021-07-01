package main.java.com.exercise.week_023;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author cl
 * @Date 2021/7/1 11:04
 * @Version 1.0
 */
public class LeetCode_剑指Offer37_1_序列化二叉树 {
    int INF = -2000;
    TreeNode emptyNode = new TreeNode(INF);

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            TreeNode node = deque.poll();
            sb.append(node.val).append("_");
            if (!node.equals(emptyNode)) {
                deque.offer(node.left == null ? emptyNode : node.left);
                deque.offer(node.right == null ? emptyNode : node.right);
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("".equals(data) || data == null) {
            return null;
        }
        String[] ss = data.split("_");
        int n = ss.length;
        Deque<TreeNode> deque = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(ss[0]));
        deque.offer(root);
        for (int i = 1; i < n - 1; i += 2) {
            TreeNode node = deque.poll();
            int left = Integer.parseInt(ss[i]);
            if (left != INF) {
                node.left = new TreeNode(left);
                deque.offer(node.left);
            }
            int right = Integer.parseInt(ss[i + 1]);
            if (right != INF) {
                node.right = new TreeNode(right);
                deque.offer(node.right);
            }
        }
        return root;
    }
}
