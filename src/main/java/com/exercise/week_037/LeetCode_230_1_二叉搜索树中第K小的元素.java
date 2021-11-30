package main.java.com.exercise.week_037;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cl
 * @Date 2021/10/17 16:04
 * @Version 1.0
 */
public class LeetCode_230_1_二叉搜索树中第K小的元素 {
    List<TreeNode> list = new ArrayList<>();
    public int kthSmallest(TreeNode root, int k) {
        inorder(root);
        return list.get(k - 1).val;
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
