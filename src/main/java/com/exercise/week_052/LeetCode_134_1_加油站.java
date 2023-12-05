package main.java.com.exercise.week_052;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/12/1 17:17
 */
public class LeetCode_134_1_加油站 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        int i = 0;
        while (i < len) {
            int sumGas = 0;
            int sumCost = 0;
            int cnt = 0;
            while (cnt < len) {
                int j = (i + cnt) % len;
                sumGas += gas[j];
                sumCost += cost[j];
                if (sumGas >= sumCost) {
                    cnt++;
                } else {
                    break;
                }
            }
            if (cnt == len) {
                return i;
            } else {
                i = i + cnt + 1;
            }
        }
        return -1;
    }
}
