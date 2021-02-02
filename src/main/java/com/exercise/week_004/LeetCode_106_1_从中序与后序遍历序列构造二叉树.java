package main.java.com.exercise.week_004;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author cl
 * @Date 2021/2/2 16:47
 * @Version 1.0
 */
public class LeetCode_106_1_从中序与后序遍历序列构造二叉树 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int size = inorder.length;
        if (size == 0) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[size-1]);

        List<Integer> inorderList = Arrays.stream(inorder).boxed().collect(Collectors.toList());;
        int rootIndex = inorderList.indexOf(root.val);
        int[] inorderLeft = new int[rootIndex];
        int[] postorderLeft = new int[rootIndex];
        for (int i=0; i<rootIndex; i++) {
            inorderLeft[i] = inorder[i];
            postorderLeft[i] = postorder[i];
        }
        int[] inorderRight = new int[size-rootIndex-1];
        int[] postorderRight = new int[size-rootIndex-1];
        for (int i=0; i<size-rootIndex-1; i++) {
            inorderRight[i] = inorder[rootIndex+i+1];
            postorderRight[i] = postorder[rootIndex+i];
        }
        root.left = buildTree(inorderLeft, postorderLeft);
        root.right = buildTree(inorderRight, postorderRight);
        return root;
    }
}
