package main.java.com.exercise.week_052;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/11/27 19:37
 */
public class LeetCode_274_1_H指数 {
    public int hIndex(int[] citations) {
        int len = citations.length;
        int[] nums = new int[len];
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < len; j++) {
                if (citations[j] >= i) {
                    nums[i - 1]++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] >= i + 1) {
                ans = i + 1;
            } else {
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] citations =  new int[]{1,3,1};
        System.out.println(new LeetCode_274_1_H指数().hIndex(citations));
    }
}
