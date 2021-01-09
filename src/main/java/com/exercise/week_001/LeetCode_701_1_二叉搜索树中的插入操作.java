package main.java.com.exercise.week_001;

/**
 * @Author cl
 * @Date 2021/1/8 15:08
 * @Version 1.0
 * https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/
 */
public class LeetCode_701_1_二叉搜索树中的插入操作 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        if (root == null) {
            return node;
        }
        // 创建遍历的节点
        TreeNode node1 = root;
        // 记录添加节点的父节点
        TreeNode indexNode = root;
        // 记录新节点应该加在左边还是右边
        boolean flag = false;
        while (node1 != null) {
            if (val < node1.val) {
                indexNode = node1;
                node1 = node1.left;
                flag = true;
            } else {
                indexNode = node1;
                node1 = node1.right;
                flag = false;
            }
        }
        if (flag) {
            indexNode.left = node;
        } else {
            indexNode.right = node;
        }

        return root;
    }
}
