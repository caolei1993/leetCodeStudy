package main.java.com.exercise.week_011;

/**
 * @Author cl
 * @Date 2021/4/6 16:51
 * @Version 1.0
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/
 */
public class LeetCode_80_1_删除有序数组中的重复项II {
    public int removeDuplicates(int[] nums) {

        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        // 记录重复测试超过2，需要更新
        boolean updated = false;
        // 初始化前一个值value, 重复次数count, 以及当前满足条件的数字长度ans
        int value = nums[0], count = 1, ans = 1;
        for (int i = 1; i < length; i++) {
            // 如果已经存在超过重复的，每次遍历先将遍历的值复制到指定位
            if (i != ans) {
                nums[ans] = nums[i];
            }
            if (value == nums[i]) {
                count++;
                if (count > 2) {
                    while (i + 1 <length && value == nums[i + 1]) {
                        i++;
                        updated = true;
                    }
                } else {
                    ans++;
                }
            } else {
                if (updated) {
                    nums[ans + 1] = nums[i];
                    updated = false;
                }
                value = nums[i];
                count = 1;
                ans++;
            }
        }
        return ans;
    }
}
