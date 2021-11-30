[toc]
# [LeetCode_743_1_网络延迟时间](https://leetcode-cn.com/problems/network-delay-time/)
## 题目
有 n 个网络节点，标记为 1 到 n。

给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。

现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。

示例 1：
```
输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
输出：2
```

示例 2：
```
输入：times = [[1,2,1]], n = 2, k = 1
输出：1
```

示例 3：
```
输入：times = [[1,2,1]], n = 2, k = 2
输出：-1
```


提示：

* 1 <= k <= n <= 100
* 1 <= times.length <= 6000
* times[i].length == 3
* 1 <= ui, vi <= n
* ui != vi
* 0 <= wi <= 100
* 所有 (ui, vi) 对都 互不相同（即，不含重复边）

## 理解
解法一：构图（链式前向星存图），从起点遍历各个边，利用数组dist记录到达各个终点的最短用时，再
取所有最短用时中的最大值返回。


## 解法一
### 代码
```java
public class LeetCode_743_1_网络延迟时间 {
    /**
     * 节点个数最大值100
     */
    static final int N = 110;
    /**
     * 有向边的条数最大值6000
     */
    static final int M = 6010;

    int[] head = new int[N];
    int[] end = new int[M];
    int[] next = new int[M];
    int[] weight = new int[M];
    int idx;
    int INF = Integer.MAX_VALUE;
    int[] dist = new int[N];
    boolean[] vis = new boolean[N];
    int K;

    private void add (int s, int e, int w) {
        end[idx] = e;
        next[idx] = head[s];
        head[s] = idx;
        weight[idx] = w;
        idx++;
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        K = k;
        Arrays.fill(head, -1);
        // 将所有的有向边初始化到图中
        for (int[] time : times) {
            int u = time[0], v = time[1], w = time[2];
            add(u, v , w);
        }
        spfa();
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dist[i]);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;

    }

    private void spfa() {
        Arrays.fill(dist, INF);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(K);
        dist[K] = 0;
        vis[K] = true;
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            for (int i = head[poll]; i != -1 ; i = next[i]) {
                int val = end[i];
                vis[val] = false;
                if (dist[val] > dist[poll] + weight[i]) {
                    dist[val] = dist[poll] + weight[i];
                    if (vis[val]) {
                        continue;
                    }
                    queue.offer(val);
                    vis[val] = true;
                }
            }
        }
    }
}
```

# [LeetCode_581_1_最短无序连续子数组](https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/)
## 题目
给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。

请你找出符合题意的 最短 子数组，并输出它的长度。

示例 1：
```
输入：nums = [2,6,4,8,10,9,15]
输出：5
解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
```

示例 2：
```
输入：nums = [1,2,3,4]
输出：0
```

示例 3：
```
输入：nums = [1]
输出：0
```

提示：

* 1 <= nums.length <= 10^4
* -10^5 <= nums[i] <= 10^5
 

进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？

## 理解
解法一：我们可以把数组nums分为三部分，numsA,numsB,numsC，B部分代表需要排序的子数组，AC两部分
代表不需要排序的，克隆数组，对新的数组进行排序，我们通过对排序数组和原数组元素判断是否相等，求出
AC两部分的长度，再用数组原长度减去相同部分的长度就是需要排序的子数组的长度。  
时间复杂度：因为需要排序所以时间复杂度为O(nlogn)  
空间复杂度：因为需要克隆数组排序，所以空间复杂度为O(n)  

解法二：单次遍历  
我们将整段数组分为三个部分：  
* [0, left - 1]，这部分是单调递增的，最大值就存在于left - 1处，且这部分的所有数都要**小于**后面
两部分的所有数
* [left, right]，这部分就是我们要排序的部分
* [right + 1, len)，这部分就是数组的后部分，也是单调递增的，最小值存在于right + 1处，且这部分
的所有值都要**大于**前面两个部分的所有数  

1.为了求取left边界，我们可以换个思路，前部分小于后面两部分的所有值，也就是小于后面两个部分的最小值，
我们如果通过不断从后往前求取最小值，如果发现当前判断的值比最小值大就更新left边界，否则更新最小值
为当前值，如果数组本身就是严格单调递增的，那我们注定每次判断都是当前值<=最小值，都是在更新最小值，
left边界为初始值。  

2.求取right边界的思路刚好与left相反，right边界后面的数组比前两部分中的最大值还大，我们可以通过从
前往后不断求取较大值，如果遍历当前的值比最大值小我们就更新right边界，否则更新最大值为当前值。如果
数组是严格单调递增，那么right的也为初始值

最终结果求取为right - left + 1，如果数组本身是自增序列，题意返回0，为了支持题意返回，我们可以
初始化right为-1，left为0，当数组本身自增时 -1 - 0 + 1 = 0，本身满足题意，也可以初始化为特殊值，
单独判断。

## 解法一
### 代码
```java
public class LeetCode_581_1_最短无序连续子数组 {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int[] arr = nums.clone();
        Arrays.sort(arr);
        int left = 0, right = n - 1;
        while (left < n && arr[left] == nums[left]) {
            left++;
        }
        if (left == n) {
            return 0;
        }
        while (arr[right] == nums[right]) {
            right--;
        }

        return right - left + 1;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_581_2_最短无需连续子数组 {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        // 定义最小值，从后往前遍历，确认left边界
        int min = nums[n - 1], left = 0;
        // 定义最大值，从前往后遍历。确认right边界
        int max = nums[0], right = -1;

        for (int i = 0; i < n; i++) {
            if (max > nums[i]) {
                right = i;
            } else {
                max = nums[i];
            }

            if (min < nums[n - i - 1]) {
                left = n - i - 1;
            } else {
                min = nums[n - i - 1];
            }
        }
        return right - left + 1;
    }
}
```
