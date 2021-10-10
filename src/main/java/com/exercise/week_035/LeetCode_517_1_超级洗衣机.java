package main.java.com.exercise.week_035;

/**
 * @Author cl
 * @Date 2021/9/29 9:10
 * @Version 1.0
 */
public class LeetCode_517_1_超级洗衣机 {
    public int findMinMoves(int[] machines) {
        int n = machines.length;
        int sum = 0, ans = 0;
        for (int m : machines) {
            sum += m;
        }
        if (sum % n != 0) {
            return -1;
        }
        int t = sum / n;

        int ls = 0, rs = sum;
        for (int i = 0; i < n; i++) {
            rs -= machines[i];
            // 需要从左向右传输的
            int a = Math.max(i * t - ls, 0);
            // 需要从右向左传输的
            int b = Math.max((n - i - 1) * t - rs, 0);
            ans = Math.max(ans, a + b);
            ls += machines[i];
        }
        return ans;
    }
}
