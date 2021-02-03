package main.java.com.exercise.week_004;

import java.util.Stack;

/**
 * @Author cl
 * @Date 2021/2/3 16:11
 * @Version 1.0
 */
public class LeetCode_106_2_从中序与后序遍历序列构造二叉树 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0) {
            return null;
        }
        int size = postorder.length;
        TreeNode root = new TreeNode(postorder[size-1]);
        int inorderIndex = size-1;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        // 反向遍历后序遍历的节点，即中右左的顺序遍历
        for (int i=size-2; i >= 0; i-- ) {
            TreeNode postNode = new TreeNode(postorder[i]);
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.right = postNode;
                stack.push(postNode);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex--;
                }
                node.left = postNode;
                stack.push(postNode);
            }
        }
        return root;
    }
}
