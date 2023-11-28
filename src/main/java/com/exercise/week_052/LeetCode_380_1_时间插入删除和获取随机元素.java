package main.java.com.exercise.week_052;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/11/28 19:44
 */
public class LeetCode_380_1_时间插入删除和获取随机元素 {

    private final static int[] nums = new int[200010];
    Random random = new Random();
    int idx = -1;
    Map<Integer,Integer> map = new HashMap<>();
    public LeetCode_380_1_时间插入删除和获取随机元素() {

    }

    public boolean insert(int val) {
        if (!map.containsKey(val)) {
            nums[++idx] = val;
            map.put(val, idx);
            return true;
        }
        return false;
    }

    public boolean remove(int val) {
        if (map.containsKey(val)) {
            int loc = map.remove(val);
            if (loc != idx) {
                map.put(nums[idx], loc);
                nums[loc] = nums[idx];
            }
            idx--;
            return true;
        }
        return false;
    }

    public int getRandom() {
        return nums[random.nextInt(idx + 1)];
    }
}
