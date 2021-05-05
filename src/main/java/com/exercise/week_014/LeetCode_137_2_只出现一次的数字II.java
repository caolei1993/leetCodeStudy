package main.java.com.exercise.week_014;

/**
 * @Author cl
 * @Date 2021/5/5 20:42
 * @Version 1.0
 */
public class LeetCode_137_2_只出现一次的数字II {
    public int singleNumber(int[] nums) {
        // 创建存储数字转化为2进制，每个位置1的数量
        int[] arr = new int[32];
        // 统计每个数字转化为2进制后每个位置的1的数量
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                if (((num >> i) & 1) == 1) {
                    arr[i]++;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (arr[i] % 3 == 1) {
                ans += (1 << i);
            }
        }
        return ans;
    }
}
