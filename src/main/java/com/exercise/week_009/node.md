[toc]
# [LeetCode_456_1_132模式](https://leetcode-cn.com/problems/132-pattern/)
## 题目
给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。

如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。


进阶：很容易想到时间复杂度为 O(n^2) 的解决方案，你可以设计一个时间复杂度为 O(n logn) 或 O(n) 的解决方案吗？

示例 1：
```
输入：nums = [1,2,3,4]
输出：false
解释：序列中不存在 132 模式的子序列。
```

示例 2：
```
输入：nums = [3,1,4,2]
输出：true
解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
```

示例 3：
```
输入：nums = [-1,3,2,0]
输出：true
解释：序列中有 3 个 132 模式的的子序列：[-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0] 。
```

提示：

* n == nums.length
* 1 <= n <= 104
* -109 <= nums[i] <= 109

## 理解
* 解法一：我们利用变量leftMin来存储左边最小元素，再用一个有序结构来存储右边所有元素，
对于132下标分别为i,j,k的三个值，我们现在只需要遍历nums，遍历时取的值为j位置的值，分别比对
nums[j]与leftMin的大小，以及在有序结构中取比leftMin大的最小值ceilMinValue，再通过
对比nums[j]与ceilMinValue的大小来确认是否满足132模式
* 解法二：单调栈，从后往前遍历数组，遍历过程中维护一个单调递减的栈，栈中的元素相当于j，
当遇到num[i] > 栈顶元素时，元素出栈赋值给k，继续判断栈顶元素与num[i]的大小，总是
维护一个单调递减的栈相当于j，与一个小于栈内元素的k，当遍历的元素小于k时，即找到了132
模式

## 代码
### 解法一
```java
public class LeetCode_456_1_132模式 {
    public boolean find132pattern(int[] nums) {
        int length = nums.length;
        if (length < 3) {
            return false;
        }
        // 遍历值左侧的最小值即132中的1
        int minLeft = nums[0];
        // 遍历值右侧的有序结构，用来判断是否有3
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int k = 2; k < length; k++) {
            map.put(nums[k], map.getOrDefault(nums[k], 0) + 1);
        }
        // 相当于遍历下标j的元素
        for (int j = 1; j < length - 1; j++) {
            if (nums[j] > minLeft) {
                Integer ceilMinValue = map.ceilingKey(minLeft + 1);
                if (ceilMinValue != null && nums[j] > ceilMinValue) {
                    return true;
                }
            }
            minLeft = Math.min(minLeft, nums[j]);
            // 将map中保存的下一个要遍历的元素剔除
            map.put(nums[j+1], map.get(nums[j+1]) - 1);
            if (map.get(nums[j+1]) == 0) {
                map.remove(nums[j+1]);
            }
        }
        return false;
    }
}
```
## 解法二
### 代码
```java
public class LeetCode_456_2_132模式 {
    public boolean find132pattern(int[] nums) {
        int length = nums.length;
        Deque<Integer> deque = new ArrayDeque<>();
        int k = Integer.MIN_VALUE;
        for (int i = length - 1; i >= 0; i--) {
            if (nums[i] < k) {
                return true;
            }
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                k = deque.pollLast();
            }
            deque.addLast(nums[i]);
        }

        return false;
    }
}
```

# [LeetCode_61_1_旋转链表](https://leetcode-cn.com/problems/rotate-list/)
## 题目
给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。

示例 1：
```
输入：head = [1,2,3,4,5], k = 2
输出：[4,5,1,2,3]
```

示例 2：
```
输入：head = [0,1,2], k = 4
输出：[2,0,1]
```

提示：

* 链表中节点的数目在范围 [0, 500] 内
* -100 <= Node.val <= 100
* 0 <= k <= 2 * 109

## 理解
闭合成环解法，首先计算链表长度，求取有效的k，因为选链表长度的整数倍相当于没有操作，
所以我们求取有效的k，k%size，因为旋转k后，新的头节点为原链表倒数第k个元素，那么新的 
尾节点就为正数第size-k个节点，找到新的尾结点后，我们将原链表闭合成环，再重新赋值新的
头节点（新的尾节点的next），新的尾节点后断开，即新的尾节点的next赋值为空。

### 代码
```java
public class LeetCode_61_1_旋转链表 {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0 || head.next == null) {
            return head;
        }
        // 链表长度
        int size = 1;
        ListNode node = head;
        while (node.next != null) {
            node = node.next;
            size++;
        }
        // 计算最后一个元素的位置(原本是倒数第k个节点，现在就是求正数第a个节点)
        int a = size - k % size;
        // k是size的倍数
        if (a == size) {
            return head;
        }
        // 将链表链接为环形链表
        node.next = head;
        // 找到新链表的尾部节点
        while (a > 0) {
            node = node.next;
            a--;
        }
        head = node.next;
        node.next = null;
        return head;
    }
}
```


# [LeetCode_82_1_删除排序链表中的重复元素II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/)
## 题目
给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。

示例 1:
```
输入: 1->2->3->3->4->4->5
输出: 1->2->5
```

示例 2:
```
输入: 1->1->1->2->3
输出: 2->3
```

## 理解
解法一：
* 由于给定的链表是排序的，所以所有相同的节点都是连续的
* 利用虚拟头节点，来使得所有的节点的操作统一
* 利用变量pre来记录之前的值（有可能是前一个节点的值，也有可能是已删除重复节点的值）
* 利用变量preNode来记录遍历节点的前一个节点，方便判断出相同值时的删除操作。
* 轮询判断当前节点的next节点与pre的值，来确认是删除还是继续轮询。

  时间复杂度O(n)，空间复杂度O(1)
解法二：
* 使用递归的方法
* 确认递归截止条件，head=null 或 head.next=null，都直接返回head（没有节点或只有一个节点）
* 如果head.val!= head.next.val，继续轮询head.next即可
* 如果上述条件相等，记录相等的值，继续判断直到找到与该值不相等的值，返回递归该值后的节点

  时间复杂度O(n)，空间复杂度O(n)，因为递归会用到系统栈
## 解法一
### 代码
```java
public class LeetCode_82_1_删除排序链表中的重复元素II {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        // 创建虚拟头节点(方便所有节点情况统一处理)
        ListNode virtualHead = new ListNode(Integer.MIN_VALUE, head);
        // 初始化pre为头节点的值
        int pre = head.val;
        // 初始化遍历节点为头节点，前置节点为虚拟头节点
        ListNode node = head, preNode = virtualHead;
        // 当有且仅有一个节点时，不需要处理
        while (node.next != null) {
            // 当前节点的下一个节点值与pre的值做比较（pre可能是前一个节点的值，也可能是判断有相等的已删除节点的值）
            if (node.next.val == pre) {
                // 当判断有相同值的节点
                // preNode的next,等于当前节点next.next(过滤掉当前节点和当前节点next两个值)
                preNode.next = node.next.next;
                node = preNode;
            } else {
                // 如果判断node.next与pre不相同，则表明node值是唯一的
                // 前置初始化为node, 遍历节点轮询到下一个，pre的值置为轮询节点的值
                preNode = node;
                node = node.next;
                pre = node.val;
            }
        }
        return virtualHead.next;
    }
}
```
## 解法二
### 代码
```java
public class LeetCode_82_2_删除排序链表中的重复元素II {
    public ListNode deleteDuplicates(ListNode head) {
       if (head == null || head.next == null) {
           return head;
       }
       if (head.val != head.next.val) {
            head.next = deleteDuplicates(head.next);
       } else {
           ListNode move = head.next;
           while (move != null && head.val == move.val){
               move = move.next;
           }
           return deleteDuplicates(move);
       }
        return head;
    }
}
```