package main.java.com.exercise.week_021;

/**
 * @Author cl
 * @Date 2021/6/17 14:53
 * @Version 1.0
 */
public class LeetCode_65_1_有效数字 {
    public boolean isNumber(String s) {
        int n = s.length();
        // 用来记录e/E的坐标
        int ids = -1;
        for (int i = 0; i < n; i++) {
            char a = s.charAt(i);
            if (a == 'e' || a == 'E') {
                if (ids == -1) {
                    ids = i;
                } else {
                    // 含有多个e/E
                    return false;
                }
            }
        }

        boolean ans = true;
        // 包含e，e之前可以是整数或小数，e之后只能是整数
        if (ids != -1) {
            ans &= check(0, ids - 1, s,false);
            ans &= check(ids + 1, n - 1, s,true);
        } else {
            ans &= check(0, n - 1, s,false);
        }
        return ans;
    }

    /**
     *
     * @param start 起始坐标
     * @param end 截止坐标
     * @param s 目标字符串
     * @param mustInteger 是否需要是整数
     * @return 符合有效数字或不符合
     */
    private boolean check(int start, int end, String s, boolean mustInteger) {
        if (start > end) {
            return false;
        }
        if (s.charAt(start) == '+' || s.charAt(start) == '-') {
            start++;
        }
        boolean hasDot = false, isNum = false;
        for (int i = start; i <= end; i++) {
            char v = s.charAt(i);
            if (v == '.') {
                // 需要是整数或多个.
                if (mustInteger || hasDot) {
                    return false;
                }
                hasDot = true;
            } else if (v >= '0' && v <= '9') {
                isNum = true;
            } else {
                return false;
            }
        }
        return isNum;
    }
}
