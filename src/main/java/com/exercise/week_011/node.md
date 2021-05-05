[toc]
# [LeetCode_80_1_删除有序数组中的重复项II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/)
## 题目
给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。

不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。

说明：
```
为什么返回数值是整数，但输出的答案是数组呢？

请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。

你可以想象内部操作如下:

// nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
int len = removeDuplicates(nums);

// 在函数里修改输入数组对于调用者是可见的。
// 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
for (int i = 0; i < len; i++) {
    print(nums[i]);
}
```

示例 1：
```
输入：nums = [1,1,1,2,2,3]
输出：5, nums = [1,1,2,2,3]
解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 不需要考虑数组中超出新长度后面的元素。
```

示例 2：
```
输入：nums = [0,0,1,1,1,1,2,3,3]
输出：7, nums = [0,0,1,1,2,3,3]
解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。 不需要考虑数组中超出新长度后面的元素。
```

提示：

* 0 <= nums.length <= 3 * 104
* -104 <= nums[i] <= 104
* nums 已按升序排列

## 理解
解法一：双指针，利用变量value记录前一个如何规则的值，利用count统计重复次数，再重复次数
超过2次时需要更新最新值到ans的下一个位置，依次类推直到遍历完数组。
解法二：双指针，区别解法一在于，减少了记录前置的变量value与统计次数的count，慢指针记录
符合规则值的插入位置，快指针记录遍历数组的位置，只需判断nums[slow - 2]与nums[fast]是否
相等，如果与相等则代表重复超过2次。如果不相等，则将fast指向的值赋值到slow位置，slow++。

## 解法一
### 代码
```java
public class LeetCode_80_1_删除有序数组中的重复项II {
    public int removeDuplicates(int[] nums) {

        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        // 记录重复测试超过2，需要更新
        boolean updated = false;
        // 初始化前一个值value, 重复次数count, 以及当前满足条件的数字长度ans
        int value = nums[0], count = 1, ans = 1;
        for (int i = 1; i < length; i++) {
            // 如果已经存在超过重复的，每次遍历先将遍历的值复制到指定位
            if (i != ans) {
                nums[ans] = nums[i];
            }
            if (value == nums[i]) {
                count++;
                if (count > 2) {
                    while (i + 1 <length && value == nums[i + 1]) {
                        i++;
                        updated = true;
                    }
                } else {
                    ans++;
                }
            } else {
                if (updated) {
                    nums[ans + 1] = nums[i];
                    updated = false;
                }
                value = nums[i];
                count = 1;
                ans++;
            }
        }
        return ans;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_80_2_删除有序数组中的重复项II {
    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        if (length <= 2) {
            return length;
        }
        int slow = 2, fast = 2;
        while (fast < length) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
```
# [LeetCode_26_1_删除有序数组中的重复项](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/)
## 题目
给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。

不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。

说明:
```
为什么返回数值是整数，但输出的答案是数组呢?

请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。

你可以想象内部操作如下:

// nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
int len = removeDuplicates(nums);

// 在函数里修改输入数组对于调用者是可见的。
// 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
for (int i = 0; i < len; i++) {
    print(nums[i]);
}
```
 
示例 1：
```
输入：nums = [1,1,2]
输出：2, nums = [1,2]
解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
```

示例 2：
```
输入：nums = [0,0,1,1,1,2,2,3,3,4]
输出：5, nums = [0,1,2,3,4]
解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
```

提示：

* 0 <= nums.length <= 3 * 104
* -104 <= nums[i] <= 104
* nums 已按升序排列

## 理解
利用双指针，慢指针指向有效数据该插入的坐标，快指针为轮询的数据位置。当快指针轮询的值与慢指针
前一个值不同时，将快指针轮询的值赋值到慢指针位置，依次直到轮询完数组数据。

### 代码
```java
public class LeetCode_26_1_删除有序数组中的重复项 {
    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        if (length <= 1) {
            return length;
        }
        int slow = 1, fast = 1;
        for (int i = 1; i < length; i++) {
            if (nums[slow - 1] != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
```
# [LeetCode_33_1_搜索旋转排序数组](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/)
## 题目
整数数组 nums 按升序排列，数组中的值 互不相同 。

在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。

给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。

示例 1：
```
输入：nums = [4,5,6,7,0,1,2], target = 0
输出：4
```

示例 2：
```
输入：nums = [4,5,6,7,0,1,2], target = 3
输出：-1
```

示例 3：
```
输入：nums = [1], target = 0
输出：-1
```

提示：

* 1 <= nums.length <= 5000
* -10^4 <= nums[i] <= 10^4
* nums 中的每个值都 独一无二
* 题目数据保证 nums 在预先未知的某个下标上进行了旋转
* -10^4 <= target <= 10^4

## 理解
使用二分查找法（「二分」的本质是两段性，并非单调性。只要一段满足某个性质，另外一段不满足某个性质，就可以用「二分」。）
* 通过第一次二分法查找旋转点（旋转点定义为第一段的最大值的坐标，存在风险，如果数组无旋转点会下标越界异常）
* 通过比对target与nums[0]的大小来确认目标值可能存在在第一段还是第二段，第一段所有值都>=nums[0],
第二段所有值都<nums[0]
* 选出目标段后在对目标段进行二分法查找目标值

### 代码
```java
public class LeetCode_33_1_搜索旋转排序数组 {
    public int search(int[] nums, int target) {
        int length = nums.length;
        if (length == 0) {
            return -1;
        }
        if (length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = length - 1;

        while (l < r) {
            int mid = (l + r + 1) >> 1;
            // 寻找分割点（前段的最后一个值的index）
            if (nums[mid] > nums[0]) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        if (target >= nums[0]) {
            l = 0;
        } else {
            l = l + 1;
            r = length - 1;
        }

        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[r] == target ? l : -1;
    }
}
```
# [LeetCode_81_1_搜索旋转排序数组II](https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/)
## 题目
已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。

在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。

给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。

示例 1：
```
输入：nums = [2,5,6,0,0,1,2], target = 0
输出：true
```

示例 2：
```
输入：nums = [2,5,6,0,0,1,2], target = 3
输出：false
```

提示：

* 1 <= nums.length <= 5000
* -104 <= nums[i] <= 104
* 题目数据保证 nums 在预先未知的某个下标上进行了旋转
* -104 <= target <= 104
 

进阶：

* 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
* 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？

## 理解
解法一：直接遍历时间复杂度在O(n)
解法二：使用二分法查找
* 因为数组可能存在重复元素，有一种情况 [1,1,2,2,2,2,2,2,3]通过下标为5处的2旋转
得到[2,2,2,3,1,1,2,2,2]，数据从旋转点分为两段也不具备两段性。首先恢复两段性，去除
尾部和nums[0]相等的元素。（可能存在恢复二段性后，数组重新具有单调性 &[2,2,2,3,2,2,2]）
* 通过二分法查找旋转点的位置（暂定旋转点为第二段首元素下标）
* 判断nums[0]与target的大小，确认目标值在第一段还是第二段
* 通过二分法在目标段查询指定值

## 解法一
### 代码
```java
public class LeetCode_81_1_搜索旋转排序数组II {
    public boolean search(int[] nums, int target) {
        for (int i = 0; i < nums.length ; i++) {
            if (nums[i] == target) {
                return true;
            }
        }
        return false;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_81_2_搜索旋转排序数组II {
    public boolean search(int[] nums, int target) {
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
}
```
# [LeetCode_153_1_寻找旋转排序数组中的最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/)
## 题目
已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：  
若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]  
若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]  
注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。

给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。

示例 1：
```
输入：nums = [3,4,5,1,2]
输出：1
解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
```

示例 2：
```
输入：nums = [4,5,6,7,0,1,2]
输出：0
解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
```

示例 3：
```
输入：nums = [11,13,15,17]
输出：11
解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
```

提示：

* n == nums.length
* 1 <= n <= 5000
* -5000 <= nums[i] <= 5000
* nums 中的所有整数 互不相同
* nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转

## 理解
解法一：通过二分法寻找第一个比nums[0]小的元素的下标，如果旋转为数组元素的整数倍（数组还是单调的）
那么上述算法会返回l=r=n，此时返回nums[0]即可。
解法二：通过二分法，与上面不同的是本次参考值是数组尾部值，第一段值都是大于尾部值的，第二段
值都是小于等于尾部值的。我们取中间值，如果中间值小于尾部值，缩减右区间（包含中间值，因为有
可能当前值就是最小值），如果大于尾部值，缩减左区间（为中间值+1）。

## 解法一
### 代码
```java
public class LeetCode_153_1_寻找旋转排序数组中的最小值 {
    public int findMin(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int l = 0, r = n;
        // 搜索区间为[l,r)
        while (l < r) {
            int mid =  l + r >> 1;
            if (nums[mid] == nums[0]) {
                l = mid + 1;
            } else if (nums[mid] > nums[0]){
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l == n ? nums[0] : nums[l];
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_153_2_寻找旋转排序数组中的最小值 {
    public int findMin(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        // 搜索区间为[l, n], l=n未处理
        while (l < r) {
            int mid = l + (r - l) / 2;
            // 不存在nums[mid] = nums[r]的情况，除非数组只有一个元素
            // 一个元素的数组，不会进入while循环
            if (nums[mid] < nums[r]) {
                r = mid;
            // nums[mid] > nums[r]
            } else {
                l = mid + 1;
            }
        }
        return nums[l];
    }
}
```
# [LeetCode_154_1_寻找旋转排序数组中的最小值II](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/)
## 题目
已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：  
若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]  
若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]  
注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。

给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。

 

示例 1：
```
输入：nums = [1,3,5]
输出：1
```

示例 2：
```
输入：nums = [2,2,2,0,1]
输出：0
```

提示：

* n == nums.length
* 1 <= n <= 5000
* -5000 <= nums[i] <= 5000
* nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 

进阶：

* 这道题是 寻找旋转排序数组中的最小值 的延伸题目。
* 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？

## 理解
处理因为存在重复值，旋转后重复值在首尾同时存在，破坏了数组的两段性。
* 首选恢复数组两段性
* 接着使用二分法(使用中间值和尾部值做比较)，如果小于等于都缩减右区间，如果大于缩减
左区间为mid+1

### 代码
```java
public class LeetCode_154_1_寻找旋转排序数组中的最小值II {
    public int findMin(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        // 恢复数组两段性
        while (l < r && nums[0] == nums[r]) {
            r--;
        }

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] <= nums[r]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[l];
    }
}
```
# [LeetCode_263_1_丑数](https://leetcode-cn.com/problems/ugly-number/)
## 题目
给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。

丑数 就是只包含质因数 2、3 和/或 5 的正整数。

示例 1：
```
输入：n = 6
输出：true
解释：6 = 2 × 3
```

示例 2：
```
输入：n = 8
输出：true
解释：8 = 2 × 2 × 2
```
示例 3：
```
输入：n = 14
输出：false
解释：14 不是丑数，因为它包含了另外一个质因数 7 。
```

示例 4：
```
输入：n = 1
输出：true
解释：1 通常被视为丑数。
```

提示：

* -231 <= n <= 231 - 1

## 理解
根据题目，因子只有2,3,5的数为丑数，所以循环除2,3,5最终结果为1的就是丑数

### 代码
```java
public class LeetCode_263_1_丑数 {
    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        int[] factors = {2, 3, 5};
        for (int factor : factors) {
            while (n % factor == 0) {
                n /= factor;
            }
        }
        return n == 1;
    }
}
```
# [LeetCode_264_1_丑数II](https://leetcode-cn.com/problems/ugly-number-ii/)
## 题目
给你一个整数 n ，请你找出并返回第 n 个 丑数 。

丑数 就是只包含质因数 2、3 和/或 5 的正整数。

示例 1：
```
输入：n = 10
输出：12
解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
```

示例 2：
```
输入：n = 1
输出：1
解释：1 通常被视为丑数。
```

提示：

* 1 <= n <= 1690

## 理解
解法一：利用最小堆（队列，利用其排序的特性），队中先存入第一个丑数1，按求值次数遍历，
每次遍历取队中元素，依次和2,3,5做乘积，将结果存入set（用于去重），如果存入成功就入队。
最终返回第n次从队列中取出的值即可。
解法二：动态规划，初始化丑数数组用来保存求取出来的丑数，初始化数组第一个元素为第一个
丑数1，利用三个指针p1,p2,p3分别代表与质因子2,3,5相乘的丑数数组的下标。三个指针均
初始化为0，求取第n个丑数，我们已经有了1，就遍历剩余的n-1次，每次求取质因子与对应指针
指向丑数的乘积，求取三个值中的最小值存入指定位置，并判断是那个质因子求取结果，并对其
指针做+1操作。最终返回数组n-1位置的数即可。

## 解法一
### 代码
```java
public class LeetCode_264_1_丑数II {
    public int nthUglyNumber(int n) {
        long[] factors = new long[]{2, 3, 5};
        Set<Long> set = new HashSet<>();
        PriorityQueue<Long> keep = new PriorityQueue<>();
        set.add(1L);
        keep.offer(1L);
        int ugly = 0;
        for (int i = 0; i < n; i++) {
            long value = keep.poll();
            ugly = (int)value;
            for (long factor : factors) {
                long a = factor * value;
                if (set.add(a)) {
                    keep.add(a);
                }
            }
        }
        return ugly;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_264_2_丑数II {
    public int nthUglyNumber(int n) {
        int[] uglys = new int[n];
        uglys[0] = 1;
        int p1 = 0, p2 = 0, p3 = 0;
        for (int i = 1; i < n; i++) {
            int value1 = uglys[p1] * 2;
            int value2 = uglys[p2] * 3;
            int value3 = uglys[p3] * 5;
            uglys[i] = Math.min(Math.min(value1, value2), value3);
            if (uglys[i] == value1) {
                p1++;
            }
            if (uglys[i] == value2) {
                p2++;
            }
            if (uglys[i] == value3) {
                p3++;
            }
        }
        return uglys[n - 1];
    }
}
```