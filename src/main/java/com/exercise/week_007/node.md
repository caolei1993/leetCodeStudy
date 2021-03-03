[toc]
# [LeetCode_232_1_用栈实现队列](https://leetcode-cn.com/problems/implement-queue-using-stacks/)
## 题目
请你仅使用两个栈实现先入先出队列。队列应当支持一般队列的支持的所有操作（push、pop、peek、empty）：

实现 MyQueue 类：

void push(int x) 将元素 x 推到队列的末尾  
int pop() 从队列的开头移除并返回元素  
int peek() 返回队列开头的元素  
boolean empty() 如果队列为空，返回 true ；否则，返回 false  
 

说明：

你只能使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。  
你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 

进阶：

你能否实现每个操作均摊时间复杂度为 O(1) 的队列？换句话说，执行 n 个操作的总时间复杂度为 O(n) ，即使其中一个操作可能花费较长时间。
 

示例：


```
输入：
["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]
输出：
[null, null, null, 1, 1, false]
```


解释：
```
MyQueue myQueue = new MyQueue();
myQueue.push(1); // queue is: [1]
myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
myQueue.peek(); // return 1
myQueue.pop(); // return 1, queue is [2]
myQueue.empty(); // return false
```


提示：

1 <= x <= 9  
最多调用 100 次 push、pop、peek 和 empty  
假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）  

## 理解
* 解法一：push操作o(n)复杂度，每次来都先看栈1为空，如果栈一不为空就把栈1的值全放入栈2，
再将新值压入栈1，再判断栈2如果不为空，将所有的值压回栈1，栈2实际作为数据临时存放点。pop，peek，
empty都只操作栈1。（push操作可能导致同一个值进出栈1，栈2多次）
* 解法二：同样是两个栈，inStack和outStack，push只是压入inStack，pop操作，需要判断outStack是否为
空，如果为空，需要将inStack的值全量压入outStack，最后弹出outStack栈顶元素。peek与pop类似 ，
empty需要判断两个栈是否都为空。（同一个元素只可能分别进出一次inStack和outStack）

## 解法一
### 代码
```java
public class LeetCode_232_1_用栈实现队列 {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    /** Initialize your data structure here. */
    public LeetCode_232_1_用栈实现队列() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        stack1.push(x);
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return stack1.pop();
    }

    /** Get the front element. */
    public int peek() {
        return stack1.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.isEmpty();
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_232_2_用栈实现队列 {
    private Stack<Integer> instack;
    private Stack<Integer> outStack;
    /** Initialize your data structure here. */
    public LeetCode_232_2_用栈实现队列() {
        instack = new Stack<>();
        outStack = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        instack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (outStack.isEmpty()) {
            while (!instack.isEmpty()) {
                outStack.push(instack.pop());
            }
        }
        return outStack.pop();

    }

    /** Get the front element. */
    public int peek() {
        if (outStack.isEmpty()) {
            while (!instack.isEmpty()) {
                outStack.push(instack.pop());
            }
        }
        return outStack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return instack.isEmpty() && outStack.empty();
    }
}
```

# [LeetCode_225_1_用队列实现栈](https://leetcode-cn.com/problems/implement-stack-using-queues/)
## 题目
请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通队列的全部四种操作（push、top、pop 和 empty）。

实现 MyStack 类：

void push(int x) 将元素 x 压入栈顶。  
int pop() 移除并返回栈顶元素。  
int top() 返回栈顶元素。  
boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。  
 

注意：

你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。  
你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 

示例：
```
输入：
["MyStack", "push", "push", "top", "pop", "empty"]
[[], [1], [2], [], [], []]
输出：
[null, null, null, 2, 2, false]
```


解释：
```
MyStack myStack = new MyStack();
myStack.push(1);
myStack.push(2);
myStack.top(); // 返回 2
myStack.pop(); // 返回 2
myStack.empty(); // 返回 False
```

提示：

1 <= x <= 9  
最多调用100 次 push、pop、top 和 empty  
每次调用 pop 和 top 都保证栈不为空  
 

进阶：你能否实现每种操作的均摊时间复杂度为 O(1) 的栈？换句话说，执行 n 个操作的总时间复杂度 O(n) ，尽管其中某个操作可能需要比其他操作更长的时间。你可以使用两个以上的队列。

## 理解
* 解法一：利用两个队列，push操作来的值先入队队列2，再判断队列1是否有值如果有将队列1的值
全入队队列2，保证本次入队的值在队列2的队头，再利用临时队列将队列2与队列1互换，队列1为包含
值的队列，队列2为空队列，pop，peek，empty都只需要操作队列1即可。
* 解法二：利用一个队列实现栈，push操作，值入队前先记录一下队列长度n,再将值入队，再对队列
操作n次值出队，再入队操作，保证本次入队的值轮换到了队头位置，pop，peek，empty调队列对应的
方法即可。

## 解法一
### 代码
```java
public class LeetCode_225_1_用队列实现栈 {
    Queue<Integer> queue1;
    Queue<Integer> queue2;
    /** Initialize your data structure here. */
    public LeetCode_225_1_用队列实现栈() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue2.offer(x);
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
        Queue<Integer> tmp = queue1;
        queue1 = queue2;
        queue2 = tmp;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue1.poll();
    }

    /** Get the top element. */
    public int top() {
        return queue1.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.isEmpty();
    }
}
```

## 解法二
### 代码
```java
public class LeetCode_225_2_用队列实现栈 {
    Queue<Integer> queue;
    /** Initialize your data structure here. */
    public LeetCode_225_2_用队列实现栈() {
        queue = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        int lengh = queue.size();
        queue.offer(x);
        for (int i = 0; i < lengh; i++) {
            queue.offer(queue.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.poll();
    }

    /** Get the top element. */
    public int top() {
        return queue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}

```