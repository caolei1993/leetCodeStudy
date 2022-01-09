package main.java.com.exercise.week_046;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/1/8 10:17 下午
 */
public class LeetCode_89_1_格雷编码 {
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        while (n-- > 0) {
            int m = ans.size();
            for (int i = m - 1; i >= 0; i--) {
                ans.set(i, ans.get(i) << 1);
                ans.add(ans.get(i) + 1);
            }
        }
        return ans;
    }
}
