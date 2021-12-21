package main.java.com.exercise.week_044;

/**
 * @author cl
 * @version 1.0
 * @date 2021/12/21 9:29 上午
 */
public class LeetCode_1154_1_一年中的第几天 {
    static int[] nums = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static int[] f = new int[13];
    static {
        // 初始化前缀和
        for (int i = 1; i <= 12; i++) {
            f[i] = nums[i - 1] + f[i - 1];
        }
    }
    public int dayOfYear(String date) {
        String[] s = date.split("-");
        int y = Integer.parseInt(s[0]), m = Integer.parseInt(s[1]), d = Integer.parseInt(s[2]);
        boolean isLeaf = (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0);
        int ans = m > 2 && isLeaf ? f[m - 1] + 1 : f[m - 1];
        return ans + d;
    }
}
