package main.java.com.exercise.week_056;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/1/5 21:32
 */
public class LeetCode_167_2_两数之和II输入有序数组 {
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum < target) {
                l++;
            } else if (sum == target) {
                return new int[]{l + 1, r + 1};
            } else {
                r--;
            }
        }
        return new int[]{};
    }
}
