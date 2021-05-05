package main.java.com.exercise.week_013;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cl
 * @Date 2021/4/25 9:23
 * @Version 1.0
 */
public class LeetCode_897_1_递增顺序搜索树 {
    List<TreeNode> list = new ArrayList<>();
    public TreeNode increasingBST(TreeNode root) {
        inorder(root);
        TreeNode head = new TreeNode(-1);
        TreeNode pre = head;
        for (TreeNode node : list) {
            pre.right = node;
            node.left = null;
            pre = node;
        }
        return head.right;
    }

    public void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        list.add(node);
        inorder(node.right);
    }
}
