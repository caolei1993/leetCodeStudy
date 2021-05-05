package main.java.com.exercise.week_012;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author cl
 * @Date 2021/4/18 10:52
 * @Version 1.0
 */
public class LeetCode_220_2_存在重复元素III {
    int size;
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int length = nums.length;
        Map<Long, Long> map = new HashMap<>(k);
        size = t + 1;
        for (int i = 0; i < length; i++) {
            long value = (long)nums[i];
            long idx = getIdx(value);
            if (map.containsKey(idx)) {
                return true;
            }
            long left = idx - 1;
            long right = idx + 1;
            // 在[0,2t]区间也可能存在差值小于等于t的两个数。
            if (map.containsKey(left) && value - map.get(left) <= t) {
                return true;
            }
            if (map.containsKey(right) && map.get(right) - value <= t) {
                return true;
            }
            map.put(idx, value);

            // 最多k个桶，保证下标符合i-j <= k
            // 当 i = k时，我们需要删除nums[0]对应的桶
            if (i >= k) {
                map.remove(getIdx(nums[i - k]));
            }
        }
        return false;
    }

    private long getIdx(long value) {
        /*
         比如 [0, 1, 2, 3], t = 3, 这个数组中的值都是符合差值小于等于t的，应该放在同一个桶里，所以我们除以 t+1，保证
         他们除出来的结果都为0，获取到桶的index都是同一个值即放在同一个桶里；
         当value小于0时，比如[-4, -3, -2, -1]，这四个值的差值也符合小于等于t，按我们设计他们应该放在index = -1的桶里，
         但是现在除以 t+1的结果有 -1 和 0 两个值，为了统一我们给value统一加1，这样计算的结果都为0，再将最后计算的结果-1，
         这样就将负数也合理的处理在目标桶里了。
         */

        return value >= 0 ? value / size : (value + 1) / size - 1;
    }
}
