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

# [LeetCode_400_1_第N位数字](https://leetcode-cn.com/problems/nth-digit/)
## 理解
计算思路：  
1. 确认目标值x的长度（利用累加法，判断n的长度落在哪个长度区间）  
2. 确认当前长度的初始值
3. 确定目标值x和当前长度初始值的偏移量
4. 求解n对应目标值x的元素的下标，获取并返回

时间复杂度为log(n)，空间复杂度为O(1)

### 代码
```java
public class LeetCode_400_1_第N位数字 {
    public static int findNthDigit(int n) {
        // i代表数字长度
        int i = 1;
        // sum用来统计小于n的长度的数字总个数
        long sum = 0;
        for (; i <= 9; i++) {
            // 计算每个len的数字的个数
            long count = (long)(i * 9 * Math.pow(10, i - 1));
            if (sum + count >= n) {
                break;
            }
            sum += count;
        }

        // 计算i长度的数字的起始值
        long start = (long)Math.pow(10, i - 1);

        // 计算目标数字在start上的增量
        // 本身计算时应该使用(n - sum - i) / i，如果能整除，则增量就是当前值，不能整除，增量就是结果+1
//        long add = (n - sum - i) % i == 0 ? (n - sum - i) / i : (n - sum - i) / i + 1;
        long add = (n - sum - 1) / i;
        long x = start + add;

        // 因为字符坐标是从0排序的，所以需要多减一个1
        return String.valueOf(x).charAt((int)(n - sum - 1) % i) - '0';
    }

    public static void main(String[] args) {
        System.out.println(findNthDigit(9));
    }
}
```