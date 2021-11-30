[toc]
# [LeetCode_275_1_H指数II](https://leetcode-cn.com/problems/h-index-ii/)
## 题目
给定一位研究者论文被引用次数的数组（被引用次数是非负整数），数组已经按照 升序排列 。编写一个方法，计算出研究者的 h 指数。

h 指数的定义: “h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。（其余的 N - h 篇论文每篇被引用次数不多于 h 次。）"

示例:
```
输入: citations = [0,1,3,5,6]
输出: 3 
解释: 给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 0, 1, 3, 5, 6 次。
     由于研究者有 3 篇论文每篇至少被引用了 3 次，其余两篇论文每篇被引用不多于 3 次，所以她的 h 指数是 3。
```
 
说明:

如果 h 有多有种可能的值 ，h 指数是其中最大的那个。


进阶：

这是 H 指数 的延伸题目，本题中的 citations 数组是保证有序的。
你可以优化你的算法到对数时间复杂度吗？

## 理解
解法一：因为给定的就是有序数组，定义并初始化H值为0，我们从后往前遍历，如果当前论文的引用值大于H值，
就给H值+1，只到不满足条件返回H值即为结果。整体的时间复杂度为O(n)

解法二：根据题意我们要找到引用次数至少为x的x篇论文，并且要找到最大x的值。  
因为数组是有序的，所以根据H指数的定义，我们的最大的符合条件分割点x的右边（包含分割点），必然满足
citations[i] >= x，我们需要对其进行计数；对于分割点的左边，必然不满足citations[i] >= x，我们无需
对其进行计数。  
因此我们可以利用分割点右边论文的个数与分割点的citations[x]的大小关系进行二分：  
假设真实存在分割点x，其值的大小为citation[x]，分割点右边的论文数量为n - x，根据H指数的定义，必然
有citation[x] >= n - x。
* 在分割点x的右边，citations[i]非严格单调递增，而论文的数量严格单调递减，所以仍然满足
citation[i] >= n - i
* 在分割点x的左边，citations[i]非严格单调递减，论文的个数严格单调递增，x作为真实的分割点，必然
不满足citation[i] >= n - i

根据此二分法计算出一个最终值，最后再判断x是否真实存在（即找到的点是否满足H指数的定义）

## 解法一
### 代码
```java
public class LeetCode_275_1_H指数II {
    public int hIndex(int[] citations) {
        int h = 0;
        for (int i = citations.length - 1; i >= 0; i--) {
            if (citations[i] > h) {
                h++;
            }
        }
        return h;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_275_2_H指数II {
    public int hIndex(int[] citations) {
        int n = citations.length;

        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r >> 1;
            //
            if (citations[mid] >= n - mid) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return citations[r] >= n - r ? n - r : 0;
    }
}
```

# [LeetCode_218_1_天际线问题](https://leetcode-cn.com/problems/the-skyline-problem/)
## 题目
城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。给你所有建筑物的位置和高度，请返回由这些建筑物形成的 天际线 。

每个建筑物的几何信息由数组 buildings 表示，其中三元组 buildings[i] = [lefti, righti, heighti] 表示：

* lefti 是第 i 座建筑物左边缘的 x 坐标。
* righti 是第 i 座建筑物右边缘的 x 坐标。
* heighti 是第 i 座建筑物的高度。

天际线 应该表示为由 “关键点” 组成的列表，格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标 进行 排序 。关键点是水平线段的左端点。列表中最后一个点是最右侧建筑物的终点，y 坐标始终为 0 ，仅用于标记天际线的终点。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。

注意：输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答案；三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]

示例 1：
```
输入：buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
输出：[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
解释：
图 A 显示输入的所有建筑物的位置和高度，
图 B 显示由这些建筑物形成的天际线。图 B 中的红点表示输出列表中的关键点。
```

示例 2：
```
输入：buildings = [[0,2,3],[2,5,3]]
输出：[[0,3],[5,0]]
```

提示：

* 1 <= buildings.length <= 104
* 0 <= lefti < righti <= 231 - 1
* 1 <= heighti <= 231 - 1
* buildings 按 lefti 非递减排序

## 理解
扫描线问题，我们可以将问题转化：  
相邻的两个横坐标以及最大高度，可以确认一个矩形，题目让我们求取每个矩形的“上边”的左边端点的坐标，
但需要跳过从前一个矩形“上边”延续而来的那些边（需要记录前一个矩形的高度）。
创建集合将所有的端点按横坐标和高度的形式存入集合，左端点高度存负值（用来区分左右端点） 
利用优先队列存储高度信息（由高到低存储）  
在我们处理到左端点时，在它右边肯定有一条新的延续的边，所以我们将高度入队  
在我们处理到右端点时，代表着当前边的结束，我们将它的高度出队  
每遍历一个点都看当前队列的最大高度是否和前一个矩形的高度相等，如果不等，将端点存入结果

### 代码
```java
public class LeetCode_218_1_天际线问题 {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> ans = new ArrayList<>();
        List<int[]> ps = new ArrayList<>();
        // 将所有的左右端点都存入集合，方便做排序
        for (int[] p : buildings) {
            int l = p[0], r = p[1], h = p[2];
            ps.add(new int[]{l, -h});
            ps.add(new int[]{r, h});
        }
        // 优先使用x坐标排序，如果x坐标相等，再用高度排序
        Collections.sort(ps, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        // 定义由大到小排列的优先队列
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        // 记录前一个处理的矩形高度，初始化为0（便于最后一个端点的获取）
        int prev = 0;
        queue.offer(prev);
        // 遍历所有的点
        for (int[] point : ps) {
            int index = point[0], height = point[1];
            // 如果是左端点，高度入队，代表右边有一条延长边
            if (height < 0) {
                queue.offer(-height);
            } else {
                // 如果是右端点，高度出队，代表这个高度的边的结束
                queue.remove(height);
            }
            // 取出最高高度
            int cur = queue.peek();
            if (cur != prev) {
                List<Integer> list = new ArrayList<>();
                list.add(index);
                list.add(cur);
                ans.add(list);
                prev = cur;
            }
        }
        return ans;
    }
}

```

# [LeetCode_1818_1_绝对差值和](https://leetcode-cn.com/problems/minimum-absolute-sum-difference/)
## 题目
给你两个正整数数组 nums1 和 nums2 ，数组的长度都是 n 。

数组 nums1 和 nums2 的 绝对差值和 定义为所有 |nums1[i] - nums2[i]|（0 <= i < n）的 总和（下标从 0 开始）。

你可以选用 nums1 中的 任意一个 元素来替换 nums1 中的 至多 一个元素，以 最小化 绝对差值和。

在替换数组 nums1 中最多一个元素 之后 ，返回最小绝对差值和。因为答案可能很大，所以需要对 10^9 + 7 取余 后返回。

|x| 定义为：

* 如果 x >= 0 ，值为 x ，或者
* 如果 x <= 0 ，值为 -x
 

示例 1：
```
输入：nums1 = [1,7,5], nums2 = [2,3,5]
输出：3
解释：有两种可能的最优方案：
- 将第二个元素替换为第一个元素：[1,7,5] => [1,1,5] ，或者
- 将第二个元素替换为第三个元素：[1,7,5] => [1,5,5]
两种方案的绝对差值和都是 |1-2| + (|1-3| 或者 |5-3|) + |5-5| = 3
```

示例 2：
```
输入：nums1 = [2,4,6,8,10], nums2 = [2,4,6,8,10]
输出：0
解释：nums1 和 nums2 相等，所以不用替换元素。绝对差值和为 0
```

示例 3：
```
输入：nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]
输出：20
解释：将第一个元素替换为第二个元素：[1,10,4,4,2,7] => [10,10,4,4,2,7]
绝对差值和为 |10-9| + |10-3| + |4-5| + |4-1| + |2-7| + |7-4| = 20
```

提示：

* n == nums1.length
* n == nums2.length
* 1 <= n <= 10^5
* 1 <= nums1[i], nums2[i] <= 10^5

## 理解


### 代码
```java
public class LeetCode_1818_1_绝对差值和 {
    long mod = (int) 1e9 + 7;
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int n = nums1.length;
        // 记录差值总和
        long sum = 0;
        // 记录最大差值
        long max = 0;
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums1) {
            set.add(num);
        }
        int minNums1 = set.first();
        int maxNums1 = set.last();

        int d;
        for (int i = 0; i < n; i++) {
            int a = nums1[i], b = nums2[i];
            int v = Math.abs(a - b);
            sum += v;
            if (b >= maxNums1) {
                d = b - maxNums1;
            } else if (b <= minNums1){
                d = minNums1 - b;
            } else {
                // 大于等于b的最小值，并计算差值
                int d1 = Math.abs(set.ceiling(b) - b);
                // 小于等于b的最大值，并计算差值
                int d2 = Math.abs(set.floor(b) - b);
                // 取较小的差值
                d = Math.min(d1, d2);
            }
            max = Math.max(max, Math.abs(v - d));
        }
        return (int)((sum - max) % mod);
    }
}
```

# [LeetCode_1846_1_减小和重新排列数组后的最大元素](https://leetcode-cn.com/problems/maximum-element-after-decreasing-and-rearranging/)
## 题目
给你一个正整数数组 arr 。请你对 arr 执行一些操作（也可以不进行任何操作），使得数组满足以下条件：

* arr 中 第一个 元素必须为 1 。
* 任意相邻两个元素的差的绝对值 小于等于 1 ，也就是说，对于任意的 1 <= i < arr.length （数组下标从 0 开始），都满足 abs(arr[i] - arr[i - 1]) <= 1 。abs(x) 为 x 的绝对值。

你可以执行以下 2 种操作任意次：

* 减小 arr 中任意元素的值，使其变为一个 更小的正整数 。
* 重新排列 arr 中的元素，你可以以任意顺序重新排列。

请你返回执行以上操作后，在满足前文所述的条件下，arr 中可能的 最大值 。


示例 1：
```
输入：arr = [2,2,1,2,1]
输出：2
解释：
我们可以重新排列 arr 得到 [1,2,2,2,1] ，该数组满足所有条件。
arr 中最大元素为 2 。
```

示例 2：
```
输入：arr = [100,1,1000]
输出：3
解释：
一个可行的方案如下：
1. 重新排列 arr 得到 [1,100,1000] 。
2. 将第二个元素减小为 2 。
3. 将第三个元素减小为 3 。
现在 arr = [1,2,3] ，满足所有条件。
arr 中最大元素为 3 。
```

示例 3：
```
输入：arr = [1,2,3,4,5]
输出：5
解释：数组已经满足所有条件，最大元素为 5 。
```

提示：

* 1 <= arr.length <= 10^5
* 1 <= arr[i] <= 10^9

## 理解
解法1：排序 + 贪心

根据题意数组的首位置必须是1，且每个数字只能减小或者不变，数值的位置可以任意调整。我们要求取经过
处理后的数组中的最大值。  
1. 证明一：取得最优解的数组（必然是）或者是（可调整）为非严格的单调递增的形式。  
* 满足条件的数组现在一共有三种形式，非严格递减，存在波段，非严格递增。
* 非严格递减，因为题目有要求。所有值均为正整数，所有直接舍弃
* 存在波段的数组（即起起伏伏如[1,2,1,2,1,2]），我们以[1,2,1]为例，我们以洪峰为分割线将值分为两部分，
右边一部分始终可以通过调整和左边合并将数组转化为非严格单调递增的数组形式，如[1,1,2]。

所以，出现最优解的情况肯定是非严格单调递增形式数组的最后一位。

2. 证明二：当必须要对当前位置的数值进行调整时，优先调整为[与前一个值差1的较大值]不会比调整为比
[比前一个值差1的较小值]更差。  
取比前值差1的较小值会导致数组不满足我们最优解的形式，需要通过调整到非严格单调递增形式。所以可能
得到的结果小于等于最优解。

3. 证明三：调整大小的操作不会改变数组元素之间的相对位置关系。  
在证明二中我们会对某些元素进行减小操作，使整个数组满足相邻元素差值小于等于1。  
但该证明有一个很重要的前提条件，就是调整是否会触发元素位置重排。  
假设现在有i,j两个位置的元素需要调整，且 i < j，为了让数组满足条件，对它们都进行了减小操作，分别
变为p,q，如果触发了位置重排的话，一定存在 p > q，因为证明二中分析，数值调整不会影响我们原数组的
非严格单调递增的性质，则不会出现p > q的情况。  
所以得出结论，调整操作不会触发数组元素的重排。

时间复杂度，因为使用的数组自带的双轴快排，时间复杂度为O(nlogn)  
空间复杂度为O(logn)

解法二：计数 + 贪心  
深层次剖析问题，因为我们数组从1开始，且相邻两个位置相差不超过1，则最大可能的数组元素也就是单调递增
数组的最后一个值n。也就是说我们的结果必定小于等于n。  
我们创建cnt数组记录数组中各个数字出现的次数，大于n的最终至少要调整到n，所以

### 代码
```java
public class LeetCode_1846_1_减小和重新排列数组后的最大元素 {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);
        arr[0] = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] - arr[i - 1] > 1) {
                arr[i] = arr[i - 1] + 1;
            }
        }
        return arr[n - 1];
    }
}
```

# [LeetCode_剑指Offer53_1_在排序数组中查找数字I](https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/)
## 题目
统计一个数字在排序数组中出现的次数。

示例 1:
```
输入: nums = [5,7,7,8,8,10], target = 8
输出: 2
```

示例 2:
```
输入: nums = [5,7,7,8,8,10], target = 6
输出: 0
```


提示：

* 0 <= nums.length <= 10^5
* -10^9 <= nums[i] <= 10^9
* nums 是一个非递减数组
* -10^9 <= target <= 10^9
 

注意：本题与主站 34 题相同（仅返回值不同）：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/

## 理解
解法一：使用二分法快速定位到我们要找的目标数字，再分别向前向后统计个数，最终返回目标数字总个数。  
时间复杂度为O(n)，空间复杂度为O(1)。

解法二：使用二分法分别找到目标值的左边界的坐标，再找到目标值右边界的坐标，最后求取差值。  
时间复杂度为O(logn)，空间复杂度为O(1)。

## 解法一
### 代码
```java
public class LeetCode_剑指Offer53_1_在排序数组中查找数字I {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        int count = 0;
        while (l <= r) {
            int mid = l + r >> 1;
            if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                count++;
                int c = mid;
                while (nums[--c] == target) {
                    count++;
                }
                while (nums[++mid] == target) {
                    count++;
                }
                return count;
            }
        }
        return count;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_剑指Offer53_2_在排序数组中查找数字I {
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (nums[l] != target) {
            return 0;
        }
        // target的左边界
        int a = l;
        l = 0;
        r = n - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        // target的右边界
        int b = l;

        return  b - a + 1;
    }
}
```

# [LeetCode_剑指Offer42_1_连续子数组的最大和](https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/)
## 题目
输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。

要求时间复杂度为O(n)。

示例1:
```
输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
```

提示：

* 1 <= arr.length <= 10^5
* -100 <= arr[i] <= 100

注意：本题与主站 53 题相同：https://leetcode-cn.com/problems/maximum-subarray/

## 理解
动态规划，本题的状态定义f[i]代表以nums[i]为结尾的子数组的最大和，我们不失一般性的考虑一下f[i]
该如何转化:  
* nums[i]可以单独看做一个子数组
* 或者是nums[i]和之前元素一起组成的子数组  

所以f[i] = Math.max(nums[i], f[i - 1] + nums[i])
在不断求解最大子数组和的过程中，我们通过比较记录下最大的值并返回。整体时间复杂度为O(n)，空间复杂
度也为O(n)。

解法二：因为转移方程只与f[i - 1]相关，我们可以利用变量或者滚动数组将空间复杂度优化到O(1)。

## 解法一
### 代码
```java
public class LeetCode_剑指Offer42_1_连续子数组的最大和 {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        // 状态定义，f[i]代表以nums[i]为结尾的子数组的最大值
        int[] f = new int[n];
        f[0] = nums[0];
        int ans = f[0];
        for (int i = 1; i < n; i++) {
            f[i] = Math.max(nums[i], f[i - 1] + nums[i]);
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_1846_2_减小和重新排列数组后的最大元素 {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;
        // 初始化记录每个值的数量的数组，大于n的按n记录
        int[] cnt = new int[n + 1];
        for (int v : arr) {
            ++cnt[Math.min(v, n)];
        }
        int miss = 0;
        // 数值从1开始，题目规定值为正整数 (>=1)
        for (int i = 1; i <= n; i++) {
            if (cnt[i] == 0) {
                miss++;
            } else {
                miss -= Math.min(cnt[i] - 1, miss);
            }
        }
        return n - miss;
    }
}
```

# [LeetCode_152_1_乘积最大子数组](https://leetcode-cn.com/problems/maximum-product-subarray/)
## 题目
给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。

示例 1:
```
输入: [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。
```

示例 2:
```
输入: [-2,0,-1]
输出: 0
解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
```

## 理解
动态规划，状态定义f[i]代表以nums[i]为结尾的子数组的最大乘积值，因为考虑到有负数的存在，可能存在
负负得正的情况，我们还需要定义一个g[i]来记录以nums[i]为结尾的子数组的最小乘积值。状态数组的初始化，
很容易可以看出我们f[0],g[0]都等于nums[0]，不失一般性的考虑一下动态数组的转移问题：  
* nums[i]可以单独作为一个子数组
* nums[i]也可以和前面的值连在一起作为一个子数组，我们在求解子数组最大值与最小值的过程中都需要考虑
两种情况，前面的最小值与当前值的乘积，前面的最大值与当前值的乘积。（因为有符号的影响）

f[i]在这两种情况中取较大值，g[i]则在这两种情况中取较小值，遍历过程中记录不断取ans和f[i]中的较大值
作为答案最终返回。

整体是时间复杂度为O(n)，空间复杂度为O(n)

### 代码
```java
public class LeetCode_152_1_乘积最大子数组 {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        // 状态定义，g[i]表示以nums[i]结尾的子数组的乘积最小值
        int[] g = new int[n];
        // 状态定义，f[i]表示以nums[i]结尾的子数组的乘积最大值
        int[] f = new int[n];
        f[0] = nums[0];
        g[0] = nums[0];
        int ans = f[0];
        for (int i = 1; i < n; i++) {
            g[i] = Math.min(nums[i], Math.min(g[i - 1] * nums[i], f[i - 1] * nums[i]));
            f[i] = Math.max(nums[i], Math.max(g[i - 1] * nums[i], f[i - 1] * nums[i]));
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}
```

# [LeetCode_面试题1002_1_变位词组](https://leetcode-cn.com/problems/group-anagrams-lcci/)
## 题目
编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。

注意：本题相对原题稍作修改

示例:
```
输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
输出:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
```

说明：

* 所有输入均为小写字母。
* 不考虑答案输出的顺序。

## 理解
思路是把变位词组通过转化都变为同一个key，再通过Map把key相同的存储在一起，最后遍历map的key，组装
结果。  
因为所有输入都为小写字母，我们可以将字符串处理为字符数组，再通过排序，将变位词组都处理为相同的key。

### 代码
```java
public class LeetCode_面试题1002_1_变位词组 {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] c = str.toCharArray();
            Arrays.sort(c);
            String s = String.valueOf(c);
            List<String> list = map.getOrDefault(s, new ArrayList<>());
            list.add(str);
            map.put(s, list);
        }
        for (String key : map.keySet()) {
            ans.add(map.get(key));
        }
        return ans;
    }
}
```