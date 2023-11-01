package main.java.com.exercise.week_001;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/5/15 11:43 上午
 */
public class Test_5 {

    int count;
    public int largestCombination(int[] candidates) {
        dfs(candidates[0], 0, new boolean[candidates.length], candidates);
        return count;
    }

    private void dfs(int val, int cnt, boolean[] flag, int[] candidates) {
        int n = candidates.length;

        for (int i = 0; i < n; i++) {
            if (flag[i]) {
                continue;
            } else {
                int value = val & candidates[i];
                flag[i] = true;
                if (value > 0) {
                    int cur = cnt + 1;
                    count = Math.max(count, cur);
                    dfs(value, cur, flag, candidates);
                }
                flag[i] = false;
            }
        }


    }

    public static void main(String[] args) {
        Test_5 test = new Test_5();
        System.out.println(test.largestCombination(new int[]{16,17,71,62,12,24,14}));
    }
}
