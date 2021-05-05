package main.java.com.exercise.week_012;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author cl
 * @Date 2021/4/12 11:27
 * @Version 1.0
 */
public class LeetCode_179_2_最大数 {
    public String largestNumber(int[] nums) {
        int length = nums.length;
        Integer[] arr = new Integer[length];
        for (int i = 0; i < length; i++) {
            arr[i] = nums[i];
        }
        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int sx = 10;
                int sy = 10;
                while (sx <= o1) {
                    sx *= 10;
                }
                while (sy <= o2) {
                    sy *= 10;
                }
                return (sx * o2 + o1) - (sy * o1 + o2);
            }
        });
        if (arr[0] == 0) {
            return "0";
        }
        StringBuilder builder = new StringBuilder();
        for(Integer str : arr) {
            builder.append(str);
        }
        return builder.toString();
    }
}
