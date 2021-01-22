package main.java.com.exercise.week_003;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author cl
 * @Date 2021/1/22 13:42
 * @Version 1.0
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 */
public class LeetCode_104_1_二叉树的最大深度 {
    public int maxDepth(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        int ans = 0;
        if (root == null) {
            return ans;
        }
        // 每一层节点个数
        int size = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            size--;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            // 代表本层已经遍历完，该遍历下一层
            if (size == 0) {
                ans++;
                size = queue.size();
            }
        }
        return ans;
    }
}
