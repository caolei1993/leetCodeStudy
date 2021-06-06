[toc]
# [LeetCode_342_1_4的幂](https://leetcode-cn.com/problems/power-of-four/)
## 题目
给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。

整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4x

示例 1：
```
输入：n = 16
输出：true
```

示例 2：
```
输入：n = 5
输出：false
```

示例 3：
```
输入：n = 1
输出：true
```

 
提示：

* -231 <= n <= 231 - 1
 

进阶：

你能不使用循环或者递归来完成本题吗？

## 理解
解法一：因为为4的幂，那一定是2的幂，将题目转化为求2的幂，4的x次方可以转化为2的2x次方，
也就是，我们先对n开平方得到x，再看x是否是2的幂即可。
解法二：因为4的幂，值转化为二进制，有且只有一个1，并且1在偶数位上，位数从0开始数，也就是
n即为2的幂，且n与上0101 0101 0101 0101 0101 0101....32位数的值为0。
解法三：4的幂的值除以3取余为1，证明：  
4 ^ n = (3 + 1) ^ n = a1 * 3 ^ n + a2 * 3 ^ (n - 1) ... + an * 3 + 1  
如果是2的幂，又不是4的幂，数字可以表示为4^n * 2，按上面的推导该值除以3取余为2。

## 解法一
### 代码
```java
public class LeetCode_342_1_4的幂 {
    public boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }
        int x = (int)Math.sqrt(n);
        return x * x == n && (x & -x) == x;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_342_2_4的幂 {
    public boolean isPowerOfFour(int n) {
        return n > 0 && (n & -n) == n && (n & 0xaaaaaaaa) == 0;
    }
}
```

## 解法三
### 代码
```java
public class LeetCode_342_3_4的幂 {
    public boolean isPowerOfFour(int n) {
        return (n & -n) == n && n % 3 == 1;
    }
}
```

# [LeetCode_1744_1_在指定天吃到想吃的糖果](https://leetcode-cn.com/problems/can-you-eat-your-favorite-candy-on-your-favorite-day/)
## 题目
给你一个下标从 0 开始的正整数数组 candiesCount ，其中 candiesCount[i] 表示你拥有的第 i 类糖果的数目。同时给你一个二维数组 queries ，其中 queries[i] = [favoriteTypei, favoriteDayi, dailyCapi] 。

你按照如下规则进行一场游戏：

你从第 0 天开始吃糖果。  
你在吃完 所有 第 i - 1 类糖果之前，不能 吃任何一颗第 i 类糖果。  
在吃完所有糖果之前，你必须每天 至少 吃 一颗 糖果。  
请你构建一个布尔型数组 answer ，满足 answer.length == queries.length 。answer[i] 为 true 的条件是：在每天吃 不超过 dailyCapi 颗糖果的前提下，你可以在第 favoriteDayi 天吃到第 favoriteTypei 类糖果；否则 answer[i] 为 false 。注意，只要满足上面 3 条规则中的第二条规则，你就可以在同一天吃不同类型的糖果。

请你返回得到的数组 answer 。

示例 1：
```
输入：candiesCount = [7,4,5,3,8], queries = [[0,2,2],[4,2,4],[2,13,1000000000]]
输出：[true,false,true]
提示：
1- 在第 0 天吃 2 颗糖果(类型 0），第 1 天吃 2 颗糖果（类型 0），第 2 天你可以吃到类型 0 的糖果。
2- 每天你最多吃 4 颗糖果。即使第 0 天吃 4 颗糖果（类型 0），第 1 天吃 4 颗糖果（类型 0 和类型 1），你也没办法在第 2 天吃到类型 4 的糖果。换言之，你没法在每天吃 4 颗糖果的限制下在第 2 天吃到第 4 类糖果。
3- 如果你每天吃 1 颗糖果，你可以在第 13 天吃到类型 2 的糖果。
```

示例 2：
```
输入：candiesCount = [5,2,6,4,1], queries = [[3,1,2],[4,10,3],[3,10,100],[4,100,30],[1,3,1]]
输出：[false,true,true,false,false]
```

提示：

* 1 <= candiesCount.length <= 105
* 1 <= candiesCount[i] <= 105
* 1 <= queries.length <= 105
* queries[i].length == 3
* 0 <= favoriteTypei < candiesCount.length
* 0 <= favoriteDayi <= 109
* 1 <= dailyCapi <= 109

## 理解
利用前缀和来记录想吃第i类糖前要吃掉多少颗糖，在遍历第i个queries时，我们可以确认
当前要吃第几类糖type，在哪天day，以及每天最多吃多少颗daily，我们可以算出要吃第type
类糖需要的最少天数min和最大天数max（需要注意天数可能会超过int的取值范围），只要day
在[min,max]中ans[i] = true，否则为false。需要注意我们算的天数是从1开始，题意从0开始
所以day = queries[i][1] + 1

### 代码
```java
public class LeetCode_1744_1_在指定天吃到想吃的糖果 {
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int n = candiesCount.length, m = queries.length;
        boolean[] ans = new boolean[m];
        // 求取前缀和，来确认吃第i类糖果前共需要吃多少个糖果
        long[] sum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + candiesCount[i - 1];
        }
        for (int i = 0; i < m; i++) {
            int type = queries[i][0], day = queries[i][1] + 1, daily = queries[i][2];
            long min = sum[type] / daily + 1, max = sum[type + 1];
            ans[i] = day >= min && day <= max;
        }
        return ans;
    }
}
```

# [LeetCode_523_1_连续的子数组和](https://leetcode-cn.com/problems/continuous-subarray-sum/)
## 题目
给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：

子数组大小 至少为 2 ，且  
子数组元素总和为 k 的倍数。  
如果存在，返回 true ；否则，返回 false 。  

如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。

示例 1：
```
输入：nums = [23,2,4,6,7], k = 6
输出：true
解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。
```

示例 2：
```
输入：nums = [23,2,6,4,7], k = 6
输出：true
解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。 
42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
```

示例 3：
```
输入：nums = [23,2,6,4,7], k = 13
输出：false
```

提示：

* 1 <= nums.length <= 105
* 0 <= nums[i] <= 109
* 0 <= sum(nums[i]) <= 231 - 1
* 1 <= k <= 231 - 1

## 理解
利用前缀和加HashSet来求解，前缀和是为了快速求取某个子区间的值（O(1)复杂度下求取）。  
我们假设[i,j]区间的和是k的倍数，所以有 sum[j] - sum[i - 1] = n * k  
等式两边同时除以k得：sum[j]/k - sum[i - 1]/k = n  
n是个整数，所以我们只需要保证sum[j]和sum[i - 1]除以k取余的结果相同即可。
所以我们遍历区间的右边界，在其左边找是否存在除以k取余结果与之相等的i即可（这里需要
注意子数组的最小长度是2，所以我们前缀和数组的差值最小要等于2），注意按本题的题意n=0
即区间的结果为0，多个连续元素为0，这种子区间也是满足的。

证明： a = x1 * k + y1，b = x2 * k + y2  
       a - b = （x1 - x2）k + y1 - y2
       所以要想a-b是k的一个倍数，y1 - y2 = 0，y1和y2分别为数字a和b除以k取余的值  

### 代码
```java
public class LeetCode_523_1_连续的子数组和 {
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        // 创建并初始化前缀和数组
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n ; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 2; i <= n; i++) {
            set.add(sum[i - 2] % k);
            if (set.contains(sum[i] % k)) {
                return true;
            }
        }
        return false;
    }
}
```

# [LeetCode_525_1_连续数组](https://leetcode-cn.com/problems/contiguous-array/)
## 题目
给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。

示例 1:
```
输入: nums = [0,1]
输出: 2
说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
```

示例 2:
```
输入: nums = [0,1,0]
输出: 2
说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
```

提示：

* 1 <= nums.length <= 105
* nums[i] 不是 0 就是 1

## 理解
利用前缀树和哈希表求解，前缀和处理时将0看做-1，那么0和1数量相等的情况下和为0，所以我们只需要求
最长和为0的子数组即可，再使用哈希表记录每一个前缀序列的坐标，方便求取子数组长度，除了0以外的答案
数组长度为2的倍数，最小也为2，所以我们可以从2遍历前缀和数组，这时保证至少包含坐标为0,1的两个元素，
当map包含与sum[i]相等的左边界时，求取子数组长度，且我们保证了同一值只在map中存了最左的边界，保证
了求取子数组的长度最大。

### 代码
```java
public class LeetCode_525_1_连续数组 {
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        // 定义前缀和数组，并初始化，把0当做-1处理
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n ; i++) {
            sum[i] = sum[i - 1] + (nums[i - 1] == 1 ? 1 : -1);
        }
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 2; i <= n ; i++) {
            if (!map.containsKey(sum[i - 2])) {
                map.put(sum[i - 2], i - 2);
            }
            if (map.containsKey(sum[i])) {
                ans = Math.max(ans, i - map.get(sum[i]));
            }
        }
        return ans;
    }
}
```
# [LeetCode_160_1_相交链表](https://leetcode-cn.com/problems/intersection-of-two-linked-lists/)
## 题目
给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。

图示两个链表在节点 c1 开始相交：

题目数据 保证 整个链式结构中不存在环。

注意，函数返回结果后，链表必须 保持其原始结构 。


示例 1：
```
输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
输出：Intersected at '8'
解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
```

示例 2：
```
输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
输出：Intersected at '2'
解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。
从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。
在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
```

示例 3：
```
输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
输出：null
解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
这两个链表不相交，因此返回 null 。
```

提示：

* listA 中节点数目为 m
* listB 中节点数目为 n
* 0 <= m, n <= 3 * 104
* 1 <= Node.val <= 105
* 0 <= skipA <= m
* 0 <= skipB <= n
* 如果 listA 和 listB 没有交点，intersectVal 为 0
* 如果 listA 和 listB 有交点，intersectVal == listA[skipA + 1] == listB[skipB + 1]
 
进阶：你能否设计一个时间复杂度 O(n) 、仅用 O(1) 内存的解决方案？

## 理解
解法一：利用set存储链表一中的所有节点，遍历链表二，如果遍历的节点包含在set中，即为相交的节点。
解法二：如果两个链表有相交的节点，那么两个链表肯定有自相交处往回节点都是相同的，长度也是一致的，
那么优先处理较长的链表使它与较短的一样长，再一起往下遍历节点，直到找到遍历的节点相等时为相交节点。

## 解法一
### 代码
```java
public class LeetCode_160_1_相交链表 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode node = headA;
        Set<ListNode> set = new HashSet<>();
        set.add(node);
        while (node.next != null) {
            node = node.next;
            set.add(node);
        }
        node = headB;
        while (node != null) {
            if (set.contains(node)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }
}

```

## 解法二
### 代码
```java
public class LeetCode_160_2_相交链表 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        int m = 0, n = 0;
        while (a != null) {
            m++;
            a = a.next;
        }
        while (b != null) {
            n++;
            b = b.next;
        }
        int t = Math.abs(m - n);
        a = headA;
        b = headB;
        while (t-- != 0) {
            if (m > n) {
                a = a.next;
            } else {
                b = b.next;
            }
        }
        while (a != null) {
            if (a.equals(b)) {
                return a;
            }
            a = a.next;
            b = b.next;
        }
        return null;
    }
}
```

# [LeetCode_474_1_一和零](https://leetcode-cn.com/problems/ones-and-zeroes/)
## 题目
给你一个二进制字符串数组 strs 和两个整数 m 和 n 。

请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。

如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。

示例 1：
```
输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
输出：4
解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
```

示例 2：
```
输入：strs = ["10", "0", "1"], m = 1, n = 1
输出：2
解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
```

提示：

* 1 <= strs.length <= 600
* 1 <= strs[i].length <= 100
* strs[i] 仅由 '0' 和 '1' 组成
* 1 <= m, n <= 100

## 理解
动态规划问题，优先处理字符串列表，记录每个字符串中0和1的数量。定义  
f[k][i][j] 代表考虑前 k 件物品，在数字 1 容量不超过 i，数字 0 容量不超过 j 的条件下的「最大价值」（每个字符串的价值均为 1）。  
我们可以得到转移方程:  
f[k][i][j]=max(f[k−1][i][j],f[k−1][i−cnt[k][0]][j−cnt[k][1]]+1)

### 代码
```java
public class LeetCode_474_1_一和零 {
    public int findMaxForm(String[] strs, int m, int n) {
        int length = strs.length;
        // 定义并初始化记录每个字符串中0和1数量的二维数组
        int[][] cnt = new int[length][2];
        for (int i = 0; i < length; i++) {
            int zero = 0, one = 0;
            String str = strs[i];
            for (char a : str.toCharArray()) {
                if (a == '0') {
                    zero++;
                } else {
                    one++;
                }
                cnt[i] = new int[]{zero, one};
            }
        }

        // f[k][i][j]表示遍历过k个字符串，最多i个0，j个1，所得的分数（选中一个字符串得一分）
        int[][][] f = new int[length + 1][m + 1][n + 1];
        for (int k = 1; k < length + 1; k++) {
            int zero = cnt[k - 1][0], one = cnt[k - 1][1];
            for (int i = 0; i < m + 1; i++) {
                for (int j = 0; j < n + 1; j++) {
                    // 不选第k个字符串
                    int a = f[k - 1][i][j];
                    // 选第k个字符串
                    int b = (i >= zero && j >= one) ? f[k - 1][i - zero][j - one] + 1 : 0;
                    f[k][i][j] = Math.max(a , b);
                }
            }
        }
        return f[length][m][n];
    }
}
```