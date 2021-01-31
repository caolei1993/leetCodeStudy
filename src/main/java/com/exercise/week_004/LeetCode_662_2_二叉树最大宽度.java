package main.java.com.exercise.week_004;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author cl
 * @Date 2021/1/26 21:31
 * @Version 1.0
 */
public class LeetCode_662_2_二叉树最大宽度 {
    public int widthOfBinaryTree(TreeNode root) {
        int ans = 0;
        int currentDepth = 0;
        int left = 0;
        Queue<NewTreeNode> queue = new LinkedList<>();
        queue.offer(new NewTreeNode(root, 0 ,0));
        while (!queue.isEmpty()) {
            NewTreeNode node = queue.poll();
            if (node.node != null) {
                queue.offer(new NewTreeNode(node.node.left, node.depth + 1, node.position * 2 ));
                queue.offer(new NewTreeNode(node.node.right, node.depth + 1, node.position * 2 + 1 ));
                if (currentDepth != node.depth) {
                    currentDepth = node.depth;
                    left = node.position;
                }
                ans = Math.max(ans, node.position - left + 1);
            }
        }
        return ans;
    }

    public static class NewTreeNode {
        int depth, position;
        TreeNode node;
        public NewTreeNode (TreeNode node, int depth, int position) {
            this.node = node;
            this.depth = depth;
            this.position = position;
        }
    }
}
