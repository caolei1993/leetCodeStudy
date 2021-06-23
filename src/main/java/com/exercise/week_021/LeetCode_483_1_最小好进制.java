package main.java.com.exercise.week_021;

/**
 * @Author cl
 * @Date 2021/6/18 15:42
 * @Version 1.0
 */
public class LeetCode_483_1_最小好进制 {
    public String smallestGoodBase(String n) {
        long m = Long.parseLong(n);
        // log以2为底的m求取最大长度，因为转为int为向下取整所以加了1
        int max = (int)(Math.log(m) / Math.log(2) + 1);
        // 遍历所有长度到3，len=2时单独处理
        for (int i = max; i >= 3 ; i--) {
            // 计算进制数
            long k = (long)Math.pow(m, 1.0 / (i - 1));
            long ans = 0;
            for (int j = 0; j < i; j++) {
                ans = ans * k + 1;
            }
            if (ans == m) {
                return String.valueOf(k);
            }
        }
        // len为2时，k为n-1
        return String.valueOf(m - 1);
    }
}
