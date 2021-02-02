package main.java.com.exercise.week_004;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cl
 * @Date 2021/1/31 14:48
 * @Version 1.0
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 */
public class LeetCode_114_1_二叉树展开为链表 {
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preorder(root, list);
        for (int i=1; i < list.size(); i++) {
            TreeNode node = list.get(i-1);
            node.left = null;
            node.right = list.get(i);
        }
    }
    public void preorder(TreeNode node, List<TreeNode> list) {
        if (node == null) {
            return;
        }
        list.add(node);
        preorder(node.left, list);
        preorder(node.right, list);
    }

}
