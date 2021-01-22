package main.java.com.exercise.week_003;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cl
 * @Date 2021/1/19 20:43
 * @Version 1.0
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 */
public class LeetCode_145_1_二叉树的后序遍历 {
    private List<Integer> list = new ArrayList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        postorder(root);
        return list;
    }
    public void postorder(TreeNode node) {
        if (node == null) {
            return;
        }
        postorder(node.left);
        postorder(node.right);
        list.add(node.val);
    }
}
