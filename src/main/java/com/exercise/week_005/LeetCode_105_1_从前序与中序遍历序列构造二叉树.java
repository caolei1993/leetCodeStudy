package main.java.com.exercise.week_005;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author cl
 * @Date 2021/2/2 19:22
 * @Version 1.0
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/submissions/
 */
public class LeetCode_105_1_从前序与中序遍历序列构造二叉树 {
    Map<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        for (int i=0; i<inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, preorder.length-1, 0, inorder.length-1);
    }

    private TreeNode myBuildTree(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preLeft]);
        int rootIndex = map.get(preorder[preLeft]);
        root.left = myBuildTree(preorder, inorder, preLeft+1, preLeft+rootIndex-inLeft, inLeft, rootIndex-1);
        root.right = myBuildTree(preorder, inorder, preLeft+rootIndex-inLeft+1, preRight, rootIndex+1, inRight);
        return root;
    }
}
