package main.java.com.exercise.week_039;

/**
 * @Author cl
 * @Date 2021/10/28 21:02
 * @Version 1.0
 */
public class LeetCode_869_1_重新排序得到2的幂 {
    int m;
    int[] cnts = new int[10];
    public boolean reorderedPowerOf2(int n) {
        // 统计n中各个数字的个数以及n的长度
        while (n != 0) {
            cnts[n % 10]++;
            n /= 10;
            m++;
        }
        return dfs(0, 0);
    }

    /**
     *
     * @param u 当前数字长度
     * @param cur 当前的值
     * @return
     */
    private boolean dfs(int u, int cur) {
        if (u == m) {
            return (cur & -cur) == cur;
        }
        for (int i = 0; i < 10; i++) {
            if (cnts[i] != 0) {
                cnts[i]--;
                if (!(i == 0 && cur == 0) && dfs(u + 1, cur * 10 + i)) {
                    return true;
                }
                cnts[i]++;
            }
        }
        return false;
    }
}
