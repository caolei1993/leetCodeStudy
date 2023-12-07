package main.java.com.exercise.week_053;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/12/5 19:44
 */
public class LeetCode_135_1_分发糖果 {
    public int candy(int[] ratings) {
        int len = ratings.length;
        int[] left = new int[len];
        int sum = 0;
        for (int i = 0, index = 0; i < len; i++) {
            if (i > 0 && ratings[i] > ratings[i - 1]) {
                index++;
            } else {
                index = 1;
            }
            left[i] = index;
        }

        for (int i = len - 1, index = 0; i >= 0; i--) {
            if (i < len - 1 && ratings[i] > ratings[i + 1]) {
                index++;
            } else {
                index = 1;
            }
            sum += Math.max(left[i], index);
        }
        return sum;
    }

    public static void main(String[] args) {
//        int[] ratings = new int[]{1,3,2,2,1};
        int[] ratings = new int[]{1,2,3,4,3,2,4,3,2};
        // 1 2 1 2 1
        System.out.println(new LeetCode_135_1_分发糖果().candy(ratings));
    }
}
