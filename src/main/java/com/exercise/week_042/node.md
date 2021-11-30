[toc]

# [LeetCode_786_1_第K个最小的素数分数](https://leetcode-cn.com/problems/k-th-smallest-prime-fraction/)
## 理解
解法一：暴力求解，利用优先队列，根据从大到小排列数据，在元素等于k个时，当队头元素大于遍历元素时，
队头元素出队，遍历元素入队，保证queue中只存储k个元素，遍历完成后，队头元素即为第K小元素。

时间复杂度：遍历所有元素的复杂度为O(n^2)，将元素入堆出堆的复杂度为O(logk),
所以整体复杂度为O(n^2*logk)，空间复杂度为O(k)

解法二：多路合并  
因为原数组是有序的，所以组成(0,1)的分数为多个有序序列，问题转化为在n-1个有序
序列中找第K小的元素，那我们利用多路合并思想，先将所有的队头存入优先队列，优先
队列按从小到大排列，出队k-1次，每次出队，将对应序列的下一个元素存入，最后第
k次出队即为第K小的元素。

时间复杂度：起始将 n - 1 个序列的头部元素放入堆中，复杂度为 O(nlogn)；
然后重复 k 次操作得到第 k 小的值，复杂度为 O(klogn)。整体复杂度为 O(max(n, k) * logn)  
空间复杂度：O(n)

## 解法一
### 代码
```java
public class LeetCode_786_1_第K个最小的素数分数 {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        // 优先队列从大到小排列，队头为最大元素
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Double.compare(b[0] * 1.0 / b[1], a[0] * 1.0 / a[1]));
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 当前遍历的值
                double t = arr[i] * 1.0 / arr[j];
                if (queue.size() > 0 && queue.peek()[0] * 1.0 / queue.peek()[1] > t) {
                    if (queue.size() == k) {
                        queue.poll();
                    }
                    queue.offer(new int[]{arr[i], arr[j]});
                }
            }
        }
        return queue.poll();
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_786_2_第K个最小的素数分数 {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        // 优先队列从小到大排列，队头为最小元素
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
           double t1 = arr[a[0]] * 1.0 / arr[a[1]];
           double t2 = arr[b[0]] * 1.0 / arr[b[1]];
           return Double.compare(t1, t2);
        });
        int n = arr.length;
        // 将没有有序队列的头存入优先队列中
        for (int i = 1; i < n; i++) {
            queue.offer(new int[]{0, i});
        }
        // 将前k-1个小值都poll掉
        while (k-- > 1) {
            int[] v = queue.poll();
            int i = v[0], j = v[1];
            if (i + 1 < j) {
                queue.offer(new int[]{i + 1, j});
            }
        }
        // 取出第K个小值
        int[] val = queue.poll();
        return new int[]{arr[val[0]], arr[val[1]]};
    }
}
```