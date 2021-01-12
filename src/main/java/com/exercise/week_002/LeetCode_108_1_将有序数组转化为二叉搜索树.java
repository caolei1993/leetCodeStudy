package main.java.com.exercise.week_002;

/**
 * @Author cl
 * @Date 2021/1/12 16:59
 * @Version 1.0
 * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
 */
public class LeetCode_108_1_将有序数组转化为二叉搜索树 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1 );
    }

    public TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1 , right);
        return root;
    }
}
