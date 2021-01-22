package main.java.com.exercise.week_003;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cl
 * @Date 2021/1/19 20:10
 * @Version 1.0
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 */
public class LeetCode_94_1_二叉树的中序遍历 {
    private List<Integer> list = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        inorder(root);
        return list;
    }

    public void inorder (TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        list.add(node.val);
        inorder(node.right);
    }
}
