package main.java.com.exercise.week_011;

/**
 * @Author cl
 * @Date 2021/4/7 10:51
 * @Version 1.0
 */
public class LeetCode_81_2_搜索旋转排序数组II {
    public static boolean search(int[] nums, int target) {
        int n = nums.length;
        if (n == 1) {
            return nums[0] == target;
        }
        int l = 0, r = n - 1;
        // 恢复二段性(有可能恢复后，整段成为升序 & [2,2,2,3,2,2,2] target=3)
        while (l < r && nums[r] == nums[0]) {
            r--;
        }
        int tail = r;

        // 搜索区间为[l,r)
        // 旋转位为l = r = 第二段的首元素下标，如果整段是升序，l = r = tail
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= nums[0]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        if (nums[0] == target) {
            return true;
        } else if (nums[0] > target){
            r = tail;
        } else {
            l = 0;
            r = r == tail ? r : r - 1;
        }

        // 搜索区间为[l,r]
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(search(new int[]{4,5,6,7,8,1}, 1));
    }
}
