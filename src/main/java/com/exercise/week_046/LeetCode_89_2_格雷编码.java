package main.java.com.exercise.week_046;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/1/9 5:07 下午
 */
public class LeetCode_89_2_格雷编码 {
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {
            ans.add((i >> 1) ^ i);
        }
        return ans;
    }
}
