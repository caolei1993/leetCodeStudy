package main.java.com.exercise.week_056;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/1/5 20:59
 */
public class LeetCode_167_1_两数之和II输入有序数组 {
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        for (int i = 0; i < n - 1; i++) {
            int l = i + 1, r = n - 1;
            while (l <= r) {
                int mid = (l + r) / 2;
                int sum = numbers[i] + numbers[mid];
                if (sum < target) {
                    l = mid + 1;
                } else if (sum == target) {
                    return new int[]{i + 1, mid + 1};
                } else {
                    r = mid - 1;
                }
            }
        }
        return new int[]{};
    }
}
