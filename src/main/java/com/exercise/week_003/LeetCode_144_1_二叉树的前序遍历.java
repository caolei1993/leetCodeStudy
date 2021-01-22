package main.java.com.exercise.week_003;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cl
 * @Date 2021/1/19 19:26
 * @Version 1.0
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 */
public class LeetCode_144_1_二叉树的前序遍历 {
    List<Integer> list = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        preorder(root);
        return list;
    }

    public void preorder(TreeNode node) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        preorder(node.left);
        preorder(node.right);
    }
}
