package main.java.com.exercise.week_008;

import java.util.List;

/**
 * @Author cl
 * @Date 2021/3/12 20:55
 * @Version 1.0
 * https://leetcode-cn.com/problems/count-items-matching-a-rule/
 */
public class LeetCode_1773_1_统计匹配检索规则的物品数量 {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        switch (ruleKey) {
            case "type":
                return statics(items, 0, ruleValue);
            case "color":
                return statics(items, 1, ruleValue);
            case "name":
                return statics(items, 2, ruleValue);
            default:
                return 0;
        }
    }

    private int statics(List<List<String>> items, int index, String value) {
        int ans = 0;
        for (List<String> list : items) {
            if (value.equals(list.get(index))) {
                ans++;
            }
        }
        return ans;
    }
}
