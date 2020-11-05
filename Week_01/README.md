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

#### [盛更多水的容器](https://leetcode-cn.com/problems/container-with-most-water/)

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

#### [移动零](https://leetcode-cn.com/problems/move-zeroes/)

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

