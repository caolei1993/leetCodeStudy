package main.java.com.exercise.week_004;

import java.util.*;

/**
 * @Author cl
 * @Date 2021/1/25 10:44
 * @Version 1.0
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
 */
public class LeetCode_107_1_二叉树的层序遍历II {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> levelList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return list;
        }
        int size = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            levelList.add(node.val);
            size--;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (size == 0) {
                list.add(levelList);
                levelList = new ArrayList<>();
                size = queue.size();
            }
        }
        Collections.reverse(list);
        return list;
    }
}
