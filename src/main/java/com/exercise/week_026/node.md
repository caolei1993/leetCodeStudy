[toc]
# [LeetCode_1838_1_最高频元素的频数](https://leetcode-cn.com/problems/frequency-of-the-most-frequent-element/)
## 题目
元素的 频数 是该元素在一个数组中出现的次数。

给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。

执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。

 
示例 1：
```
输入：nums = [1,2,4], k = 5
输出：3
解释：对第一个元素执行 3 次递增操作，对第二个元素执 2 次递增操作，此时 nums = [4,4,4] 。
4 是数组中最高频元素，频数是 3 。
```

示例 2：
```
输入：nums = [1,4,8,13], k = 5
输出：2
解释：存在多种最优解决方案：
- 对第一个元素执行 3 次递增操作，此时 nums = [4,4,8,13] 。4 是数组中最高频元素，频数是 2 。
- 对第二个元素执行 4 次递增操作，此时 nums = [1,8,8,13] 。8 是数组中最高频元素，频数是 2 。
- 对第三个元素执行 5 次递增操作，此时 nums = [1,4,13,13] 。13 是数组中最高频元素，频数是 2 。
```

示例 3：
```
输入：nums = [3,9,6], k = 2
输出：1
```

提示：

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^5
1 <= k <= 10^5

## 理解
解法一：使用排序+前缀和+二分+滑动窗口求解：  
* 首先我们对数组进行排序，如果存在真实的最优解len，意味着至少存在一个大小为len的区间[l,r]，
使得调整次数不超过k次的前提下，任意的nums[i]都能调整为nums[r]
* 这引导了我们在数组有序及前缀和的辅助下，快速判断某个区间[l,r]能否再不超过k次操作下，将所有值
变为nums[r]
* 具体我们可以以len为窗口，nums[r] * len为目标值，前缀和差为真实值，真实值与目标值差值小于等于
k，代表可以通过不超过k次操作使窗口内的所有值都达到nums[r]，否则不能。

最终通过二分，返回满足条件的窗口的最大长度即可。

## 解法一
### 代码
```java
public class LeetCode_1838_1_最高频元素的频数 {
    int[] nums, sums;
    int n, k;
    public int maxFrequency(int[] nums1, int k1) {
        nums = nums1;
        k = k1;
        n = nums.length;
        // 对数组进行排序，保证前缀和的单调性
        Arrays.sort(nums);
        // 定义前缀和数组，并初始化前缀和数组
        sums = new int[n + 1];
        for (int i = 1; i <= n ; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        int l = 0, r = n;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private boolean check(int len) {
        for (int l = 0; l + len - 1 < n; l++) {
            int r = l + len - 1;
            int a = sums[r + 1] - sums[l];
            int b = nums[r] * len;
            if (b - a <= k) {
                return true;
            }
        }
        return false;
    }
}
```


# [LeetCode_1877_1_数组中最大数对和的最小值](https://leetcode-cn.com/problems/minimize-maximum-pair-sum-in-array/)
## 题目
一个数对 (a,b) 的 数对和 等于 a + b 。最大数对和 是一个数对数组中最大的 数对和 。

* 比方说，如果我们有数对 (1,5) ，(2,3) 和 (4,4)，最大数对和 为 max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8 。

给你一个长度为 偶数 n 的数组 nums ，请你将 nums 中的元素分成 n / 2 个数对，使得：

* nums 中每个元素 恰好 在 一个 数对中，且
* 最大数对和 的值 最小 。

请你在最优数对划分的方案下，返回最小的 最大数对和 。

示例 1：
```
输入：nums = [3,5,2,3]
输出：7
解释：数组中的元素可以分为数对 (3,3) 和 (5,2) 。
最大数对和为 max(3+3, 5+2) = max(6, 7) = 7 。
```

示例 2：
```
输入：nums = [3,5,4,2,4,6]
输出：8
解释：数组中的元素可以分为数对 (3,5)，(4,4) 和 (6,2) 。
最大数对和为 max(3+5, 4+4, 6+2) = max(8, 8, 8) = 8 。
```

提示：

* n == nums.length
* 2 <= n <= 10^5
* n 是 偶数 。
* 1 <= nums[i] <= 10^5

## 理解
按题意的知道，数组中所有数对和的最大值为当前数组的最大数对和，我们可以对数进行任意配对，但是要求
出最大数对和的最小值。那么为了尽量使数对和小，我们只能采取最小数和最大数配对这种方式，首先对数组
中的值进行排序，接着不断使用未配对的（首尾）进行配对，并求数对和，最终返回数对和中的最大值。

### 代码
```java
public class LeetCode_1877_1_数组中最大数对和的最小值 {
    public int minPairSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n >> 1 ; i++) {
            int max = nums[i] + nums[n - 1 - i];
            ans = Math.max(ans, max);
        }
        return ans;
    }
}
```

# [LeetCode_剑指Offer52_1_两个链表的第一个公共节点](https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/)
## 题目
输入两个链表，找出它们的第一个公共节点。

如下面的两个链表：

在节点 c1 开始相交。

示例 1：
```
输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
输出：Reference of the node with value = 8
输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
```

示例 2：
```
输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
输出：Reference of the node with value = 2
输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
```

示例 3：
```
输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
输出：null
输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
解释：这两个链表不相交，因此返回 null。
```
 
注意：

* 如果两个链表没有交点，返回 null.
* 在返回结果后，两个链表仍须保持原有的结构。
* 可假定整个链表结构中没有循环。
* 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
* 本题与主站 160 题相同：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/

## 理解
* 解法一：差值法，因为链表如果有相交点，那么自相交点往后的节点都是相同的，即相交点后面的长度也是
相同的，所以我们分别遍历两个链表，记录下他们的长度len1，len2，计算出两个长度的差值，多出来的节点
肯定存在于未相交之前，所以我们先让长的链表遍历差值步，然后判断两个链表一起遍历，并判断两个链表的节
点是否相等，如果相等则为相交点。
* 解法二：等值法，我们记录链表1在相交之前的长度为a，链表2在相交之前的长度为b，两个链表相交后的长度
为c(c有可能为0，即两个链表不相交)，我们同时遍历两个链表，链表1遍历到尾部后遍历链表2，链表2遍历到
尾部后遍历链表1，因为两个链表同时遍历，所以走的步数相同，以链表1为例，他先走a长度，接着走c长度，
最后走到链表2的b长度，总共走了a + c + b，同理链表2先走b，再走c，最后走a，总共也走了a + c + b，
此时两个链表节点相同即为交点，如果不存在交点，即都走a + b后两节点都为null，最终返回null。

## 解法一
### 代码
```java
public class LeetCode_剑指Offer52_1_两个链表的第一个公共节点 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) {
                return null;
            }
            int len1 = 0, len2 = 0;
            ListNode node = headA;
            while (node != null) {
                len1++;
                node = node.next;
            }
            node = headB;
            while (node != null) {
                len2++;
                node = node.next;
            }
            int v = Math.abs(len1 - len2);
            node = headA;
            ListNode node1 = headB;
            if (len1 >= len2) {
                while (v > 0) {
                    node = node.next;
                    v--;
                }
            } else {
                while (v > 0) {
                    node1 = node1.next;
                    v--;
                }
            }

            while (node != null) {
                if (node == node1) {
                    return node;
                } else {
                    node = node.next;
                    node1 = node1.next;
                }
            }
            return null;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_剑指Offer52_2_两个链表的第一个公共节点 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }
}
```

# [LeetCode_138_1_复制带随机指针的链表](https://leetcode-cn.com/problems/copy-list-with-random-pointer/)
## 题目
给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。

构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。

例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。

返回复制链表的头节点。

用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：

* val：一个表示 Node.val 的整数。
* random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。

你的代码 只 接受原链表的头节点 head 作为传入参数。


示例 1：
```
输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
```

示例 2：
```
输入：head = [[1,1],[2,1]]
输出：[[1,1],[2,1]]
```

示例 3：
```
输入：head = [[3,null],[3,0],[3,null]]
输出：[[3,null],[3,0],[3,null]]
```

示例 4：
```
输入：head = []
输出：[]
解释：给定的链表为空（空指针），因此返回 null。
```

提示：

* 0 <= n <= 1000
* -10000 <= Node.val <= 10000
* Node.random 为空（null）或指向链表中的节点。

## 理解
解法一：  
使用双指针，一个指针指向原链表遍历位置，另外一个指针指向新的链表的遍历位置，遍历过程中不断创建新
的节点，并维护链表关系。  
因为本题加了一个random的指针所以我们可以借助Map记录下老的节点和新的节点的对应关系，最终再遍历一次
链表维护其random指针。  
时间复杂度和空间复杂度均为O(n)

解法二：  
我们可以简化空间复杂度，上述解法中使用了哈希表存储新老节点的对应关系，主要是为了在处理random指针
时，可以找到老的节点对应的新的节点。  
我们换一种思路，我们把创建的新的节点添加在链表里，跟在老的节点后面，如下所示：  
老1 -> 新1 —> 老2 —> 新2 —> 老3 —> 新3 —> 老4 —> 新4 ......  
那么random的维护就变为，新的节点的random等于老的节点的random节点的next.  
random指针处理好后，我们再分割新老链表，返回结果。  
时间复杂度为O(n)，空间复杂度为O(1)

## 解法一
### 代码
```java
public class LeetCode_138_1_复制带随机指针的链表 {
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node dummy = new Node(-1);
        Node tmp = head, tail = dummy;
        while (tmp != null) {
            Node node = new Node(tmp.val);
            map.put(tmp, node);
            tail.next = node;
            tail = tail.next;
            tmp = tmp.next;
        }
        tail = dummy.next;
        while (head != null) {
            if (head.random != null) {
                tail.random = map.get(head.random);
            }
            tail = tail.next;
            head = head.next;
        }
        return dummy.next;
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_138_2_复制带随机指针的链表 {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node dummy = new Node(-1);
        dummy.next = head;
        // 创建新的节点跟在对应的老的节点后面
        while (head != null) {
            Node node = new Node(head.val);
            node.next = head.next;
            head.next = node;
            head = head.next.next;
        }
        head = dummy.next;
        // 维护新的节点的random指针，新的节点的random指针等于老的节点的random指针的下一个
        while (head != null) {
            if (head.random != null) {
                head.next.random = head.random.next;
            }
            head = head.next.next;
        }
        head = dummy.next;
        Node ans = head.next;
        // 分割新老链表
        while (head != null) {
            Node tmp = head.next;
            if (tmp != null) {
                head.next = head.next.next;
            }
            head = tmp;
        }
        return ans;
    }
}
```