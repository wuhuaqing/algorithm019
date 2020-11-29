

### 算法解题的思维方式

####  1.升维

   多用一层维度来帮助数据的操作

####  2.空间换时间



### 复杂度

#### 概述

衡量程序的性能有两个维度，一个根据数据规模算法所需要的时间消耗，一个是根据数据规模算法所需要的内存空间消耗，这两个指标就是算法复杂度分析中的**时间复杂度**和**空间复杂度。**

#### 时间复杂度

一段代码或者一个算法的执行时间，总是与它的数据规模以及执行结构关联。

常见的时间复杂度有 **O(1),O(n),O(logN),O(nlogN),O(n^2)** 以及指数级别的**O(2^n)**

常常只用关注算法的核心结构对时间耗时的影响，这是关键指标。

##### 各个复杂度样例

##### O(1) 常数操作 

 操作都是常数级的操作，虽然代码需要三步走完，但是在常数级。

一般情况下，只要算法中不存在循环语句、递归语句，即使有成千上万行的代码，其时间复杂度也是Ο(1)。

```
 int i = 8;
 int j = 6;
 int sum = i + j;
```

##### O(n) 操作

影响运行时间的是n，所以时间复杂度为O(n)

```
 int cal(int n) {
   int sum = 0;
   int i = 1;
   for (; i <= n; ++i) {
     sum = sum + i;
   }
   return sum;
 }
```

##### O(logN)

运行规律：

![image.png](https://cdn.nlark.com/yuque/0/2020/png/401416/1604545944661-7a798255-ef6a-4905-ab65-8cea802e181b.png)

因为： n = 2 ^ x       所以  x =  log n

```
  int i=1;
  while (i <= n) {
     i = i * 2;
 }
```

##### O(nlogN)

在 O(log n)的基础上循环n次

```
 for(int index = 0 ; index<n;index++) {
     int i=1;
     while (i <= n){
     i = i * 2;
    }
 }
  
```

##### O(n^2) 平方级

 多出现在循环等结构上

```java
 int cal(int n) {
   int sum = 0;
   int i = 1;
   int j = 1;
   for (; i <= n; ++i) {
     j = 1;
     for (; j <= n; ++j) {
       sum = sum +  i * j;
     }
   }
 }
```

##### O(2 ^ n) 指数级

 斐波拉契数列的递归解法就是指数级时间复杂度



##### 各个时间复杂度的数据规模与耗时关系

![image.png](https://cdn.nlark.com/yuque/0/2020/png/401416/1604546429183-c2eb8cba-80c3-4c17-ab0d-f0a35f2823de.png)



### 数据结构

#### 数组

##### 时间复杂度

在内存中一块内存地址连续的操作空间，在使用方面：

查找元素与修改元素效率很高，时间复杂度为O(1)

删除和添加元素的操作会让整个数组进行元素移动操作，平均下来时间复杂度为 O(n)



#### 链表

##### 类型 

 单链表/循环单链表

 双向链表/循环双向链表

##### 时间复杂度

在内存中一块内存地址不连续的操作空间，在使用方面：

查找元素与修改元素效率较低，时间复杂度为O(n)

删除和添加元素的操作只会删除当前节点，无需移动位置，时间复杂度为 O(1)



学习资料

[数据结构与算法之美](https://time.geekbang.org/column/article/40036)

[算法训练营](https://u.geekbang.org/lesson/36?article=246042&utm_source=time_web&utm_medium=menu&utm_term=timewebmenu)





### 算法解题

#### [11盛更多水的容器 ](https://leetcode-cn.com/problems/container-with-most-water/)

解法：

[第一种思路](https://leetcode-cn.com/problems/container-with-most-water/solution/sheng-zui-duo-shui-de-rong-qi-by-leetcode-solution/)：使用双重循环，一个一个元素与其他元素进行面积计算，最终得出最大的面积（水）

时间复杂度为O(n^2)

```java
  /**
     * 一个一个元素往后和所有的元素算面积 O（n^2）
     * @param height
     * @return
     */
    public int maxArea01Se(int[] height) {
        int area = 0;
        // 右指针
        int moveIndex = 0 ;

        for(int index = 0; index<height.length;index++){
            for (moveIndex = index +1;moveIndex<height.length;moveIndex++ ){
                // 两个元素的最小值
                int faction =    Math.min(height[moveIndex],height[index]);
                //当次的面积
                int tempArea = faction * (moveIndex - index );
                //留取面积大的
                area = Math.max(tempArea,area);
            }
        }
        return area;
    }
```

##### 

第二种思路：使用左右双指针在一层循环中进行左右夹逼的方式求取面积，最终得出最大的面积（水）

 时间复杂度为O(n)

```java
 public int maxAreaSe(int[] height) {
        int area = 0;
        //左右夹逼，比较出最大面积值
        int leftIndex = 0 ; int rightIndex =  height.length -1;
        for(int index = 0; index<height.length;index++){
            //左右不重合时求取面积
            if(leftIndex<rightIndex){
              // 两个元素的最小值
             int faction =    Math.min(height[leftIndex],height[rightIndex]);
             //当次的面积
             int tempArea = faction * (rightIndex - leftIndex);
             //留取面积大的
             area = Math.max(tempArea,area);
             //哪边值大，哪边暂时不动
             if(height[leftIndex]<height[rightIndex]){
                leftIndex ++ ;
             }else {
                 rightIndex--;
             }
            }

        }
        return area;
    }
```

#### [283移动零](https://leetcode-cn.com/problems/move-zeroes/)

解法

[思路](https://leetcode-cn.com/problems/move-zeroes/solution/javayi-xie-xiang-fa-you-qu-de-ti-jie-by-wenjingh/)：牵引法

 解法一：

```java
 /**
     * [0,1,3,0,12]
     * @param nums
     * @return
     */
    public  int[] moveZeros(int[] nums){
        int change = 0 ;
        //单个循环中
        for(int index =0 ;index<nums.length;index++){
            //将数组中的非零元素复制到数组前部，原先位置上的数据置0
            if(nums[index]!=0){
              nums[change] =  nums[index];
              //不一致代表此处数据需要置0
              if(change!=index){
                 nums[index] = 0;
              }
              //只有数组中元素不为0，change才能往后挪
              change++;
            }
        }
        return nums;
    }
```

解法二：

```java
 /**
     * [0,1,3,0,12]
     * @param nums
     * @return
     */
    public  int[] moveZeros01(int[] nums){
        int change = 0 ;
        //第一个循环将非零元素都复制到数组前部
        for(int index =0 ;index<nums.length;index++){
            if(nums[index]!=0){
               nums[change] = nums[index];
               change ++ ;
            }
        }
       //第二个循环将数组后部的元素全部置为0
        for (;change<nums.length;){
            nums[change] = 0;
            change++;
        }
        return nums;
    }
```



#### [70 爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/submissions/)

```java
 /**
     * 数组储存值
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        int[] nums = new int[n];
        nums[0] = 1;
        nums[1] = 2;
        for (int index = 2; index < n; index++) {
            nums[index] = nums[index-1]+nums[index-2];
        }
        return nums[n-1] ;
    }
```

 

```java
  /**
     * 三变量处理值
     * @param n
     * @return
     */
    public int climbStairsSe00(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        int fPreAndPre = 1;
        int fPre = 2;
        int fn =  fPre + fPreAndPre;
        for (int index = 3; index <= n; index++) {

              fn =  fPre + fPreAndPre;
              fPreAndPre =  fPre;
              fPre=fn;

        }
        return fn ;
    }
```

#### [66加一](https://leetcode-cn.com/problems/plus-one/)

``

```java
public int[] plusOne03Second(int[] digits) {
    for (int index = digits.length - 1; index >= 0; index--) {
        //加一后，取余
        digits[index]++;
        digits[index] =  digits[index]%10;
        //不为9,加一返回,无需进制
        if(digits[index]!=0){
            return digits;
        }
    }
    //（9，9等情况）数组加一个长度，第一位置1
    if (digits[0] == 0) {
        digits = new int[digits.length + 1];
        digits[0] = 1;
    }

    return digits;
}
```

``

```java
public int[] plusOne02Second(int[] digits) {
    for (int index = digits.length - 1; index >= 0; index--) {
        if(digits[index]!=9){
            digits[index]++;
            return digits;
        }
        digits[index]++;
        digits[index] =  digits[index]%10;
    }

    if (digits[0] == 0) {
        digits = new int[digits.length + 1];
        digits[0] = 1;
    }

    return digits;
}
```

#### [26删除排序数组中的重复项](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/)

```java
 /**
     *  前提：有序数组
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {

        int storeIndex = 0;

        if (nums.length == 0) {
            return 0;
        }
        for (int index = 0; index < nums.length; index++) {
            //前后不相等时，代表
            if (nums[storeIndex] != nums[index]) {
                //排重指针往后挪一步位置 （1，1，1，3）以及（1，1，3）都是如此
                storeIndex++;
                //将不相等的元素复制到排重指针位
                nums[storeIndex] = nums[index];
            }
        }
       //角标从零开始，表示个数得加一
        return storeIndex+=1;

    }
```

#### [01 两数之和](https://leetcode-cn.com/problems/two-sum/)

```java
  /**
     * 暴力遍历  leetcode中提交超时  时间复杂度 O(n^2)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        //  List<List<Integer>> ans = new ArrayList<List<Integer>>();
        int[] positionNum = new int[2];
        int length = nums.length;

        int moveJ = 0;
        for (int index = 0; index < length; index++) {

            for (moveJ = index + 1; moveJ < length; moveJ++) {

                int a = nums[index];
                int b = nums[moveJ];
                if (a + b == target) {
                    positionNum[0] = index;
                    positionNum[1] = moveJ;
                }
            }

        }

        return positionNum;
    }
```

``

```java
/***
 * 使用hashmap存储 匹配值得出符合目标的数组下标 时间复杂度 O(n)
 * @param nums
 * @param target
 * @return
 */
public int[] twoSum01(int[] nums, int target) {
    int[] positionNum = new int[2];

    int length = nums.length;

    HashMap<Integer, Integer> map = new HashMap<>();

    for (int index = 0; index < length; index++) {

        if (map.containsKey(target - nums[index])) {
            return new int[]{map.get(target - nums[index]), index};
        }
        map.put(nums[index], index);
    }
    //map() => map(2,0)
    // map(2,0)  map.containkey(2) ==> map.get(2) = 0 => [map.get(2),index = 1] == > [0,1]

    return positionNum;
}
```



#### [15 三数之和](https://leetcode-cn.com/problems/3sum/)

```java
/**
     * 整体思路： a + b + c = 0
     *      * -a =  b+c
     *      * b,c使用双指针进行选择
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum00(int[] nums) {
        int length = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<List<Integer>>();

        for (int index = 0; index < length; index++) {
            //临近两值相等，则容易出现重复的数组
            if (index > 0 && nums[index] == nums[index - 1]) {
                continue;
            }
            int a = nums[index];

            int rightMove = length - 1;

            //得到 需要的  b+c 的和
            int target = -(a);

            for (int leftMove = index + 1; leftMove < length; leftMove++) {

                //临近两值相等，则容易出现重复的数组
                if (leftMove > index + 1 && nums[leftMove] == nums[leftMove - 1]) {
                    continue;
                }

                // 需要保证 b 的指针在 c 的指针的左侧
                while (leftMove < rightMove && nums[leftMove] + nums[rightMove] > target) {
                    rightMove--;
                }
                 // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (leftMove == rightMove) {
                    break;
                }
                int b = nums[leftMove];
                int c = nums[rightMove];

                if (b + c == target) {

                    List subList = new ArrayList();
                    subList.add(a);
                    subList.add(b);
                    subList.add(c);
                    lists.add(subList);

                }
            }
        }
        return lists;
    }

```

####  [142环形链表2](https://leetcode-cn.com/problems/linked-list-cycle-ii/)

``

```java
/**
 * 快慢指针，
 * 公式推导的结论：https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/142-huan-xing-lian-biao-ii-jian-hua-gong-shi-jia-2/
 * 当快慢指针相遇，就在环内，此时慢指针到入环口的距离就是起点到入环口的距离，新开一个真正和满指针一样的步伐，两个相遇点就是入环口
 *
 * @param head
 * @return
 */
public ListNode detectCycle(ListNode head) {
    if (head == null || head.next == null) {
        return null;
    }

    ListNode slowNode = head.next;
    ListNode fastNode = head.next.next;

    while (fastNode != null && fastNode.next != null) {

        slowNode = slowNode.next;
        fastNode = fastNode.next.next;

        //如果快指针为空 或者 快指针的下一个节点为空，则代表没有环
        if (slowNode == fastNode) {
            ListNode preNode = head;
            while (preNode != slowNode) {
                preNode = preNode.next;
                slowNode = slowNode.next;
            }
            return preNode;
        }
    }
    return null;

}
```

``

```java
/**
 * 使用hashset做容器，如果容器中存在此node，代表是环形链表
 *
 * @param head
 * @return
 */
public ListNode detectCycle00(ListNode head) {
    if (head == null || head.next == null) {
        return null;
    }

    HashSet<ListNode> sets = new HashSet<>();

    while (head != null) {
        if (sets.contains(head)) {
            return head;
        }
        sets.add(head);
        head = head.next;
    }
    return null;

}
```

#### [206反转链表](https://leetcode-cn.com/problems/reverse-linked-list/)

``

```java
/**
 * 使用双指针（一一翻转）+ 临时链 实现链表翻转
 *
 * @param head
 * @return
 */
public ListNode reverseList(ListNode head) {
    if (head == null) {
        return null;
    }
    if (head.next == null) {
        return head;
    }
    //当前节点
    ListNode current = head.next;
    //当前节点的下一个节点的临时链
    ListNode tempNode = null;
    //首节点给前节点变量
    ListNode pre = head;
    //切断第一个节点后面的链接
    head.next = null;

    while (current.next != null) {
        //将下一个节点地址给临时节点
        tempNode = current.next;
        //将当前节点指向前一节点
        current.next = pre;
        //将前一节点的位置往后挪
        pre = current;
        //将当前节点往后挪一位
        current = tempNode;
    }
    //将最后一个节点接着反转过的链表前面
    current.next = pre;
    return current;



         /* 上述思路精炼写法

          ListNode prev = null;
          ListNode curr = head;
          while (curr != null) {
                ListNode nextTemp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextTemp;
            }
            return prev;

         */


}
```

``

```java
/***
 * 耗时太长
 * @param head
 * @return
 */
public ListNode reverseList00(ListNode head) {
    if (head == null) {
        return null;
    }
    //使用容器装载节点，然后反转遍历复值
    ArrayList<ListNode> listNodes = new ArrayList<>();
    while (head != null) {
        ListNode node = new ListNode(head.val);
        listNodes.add(node);
        head = head.next;
    }

    //此种做法，listNodes 0～size-2 的元素都是链表不全的废数据 debug查看便知
    for (int index = listNodes.size() - 1; index >= 0; index--) {
        if (index > 0) {
            listNodes.get(index).next = listNodes.get(index - 1);
        }
    }

    return listNodes.get(listNodes.size() - 1);

}
```

#### [92. 反转链表 II](https://leetcode-cn.com/problems/reverse-linked-list-ii/)

```java

    /**
     * 解题图解  https://leetcode-cn.com/problems/reverse-linked-list-ii/solution/ji-bai-liao-100de-javayong-hu-by-reedfan-6/
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
public ListNode reverseBetween(ListNode head, int m, int n) {

    //第一步 定义一个先节点，用来指向的是链表的头节点（包括翻转后的节点）
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    //翻转的前一个节点
    ListNode nodePre = dummy;
    //移动到翻转的前一个点
    for (int i = 1; i < m; i++) {
        nodePre = nodePre.next;
    }

    System.out.println("nodePre: " + nodePre.val);
    //第二步 翻转指定位置的链表
    ListNode pre = null;
    //注意翻转起点位置
    ListNode current = nodePre.next;

    for (int i = 0; i < n - m + 1; i++) {
        ListNode nextTemp = current.next;
        current.next = pre;
        pre = current;
        current = nextTemp;
    }

    //第三步 翻转指定链表后，需要重塑链表，避免出现链表断开的情况

    //先指定下下个节点 注意代码顺序
    nodePre.next.next = current;
    nodePre.next = pre;

    return dummy.next;
}
```