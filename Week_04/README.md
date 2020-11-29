#### 深度优先搜索/广度优先搜索

#### 基本描述

在搜索领域，有常见的暴力搜索和优先搜索。特别是针对遍历这种情景，需要对数据结构中的元素进行遍历，为了保证每个元素有且仅有一次被遍历到，通常有两种广为人知的暴力搜索方式：深度优先搜索（DFS:Depth-First-Search）/  广度优先搜索(BFS:Breadth-First-Search)这是两种不同的搜索思路，通常用于二叉树和图的搜索。

#### 思路

**深度优先搜索DFS**：以二叉树为例，它的搜索思路是搜索从某个节点开始，选中某个子节点，然后一级一级的沿某条路径下探到树的最深处，之后就是用同样的方式对节点下面的所有路径进行相同的操作，直到整棵树的节点都被遍历到。

过程图：

​                            <img src="https://cdn.nlark.com/yuque/0/2020/png/401416/1606119606674-44cf3fa0-16c5-4d31-bbc5-638cdfcc711f.png" alt="image.png" style="zoom:50%;" />         



​                            <img src="https://cdn.nlark.com/yuque/0/2020/png/401416/1606119512478-24cd9fa7-194e-4cc4-9616-8b8d079c4b0d.png" alt="image.png" style="zoom:50%;" />    



**广度优先搜索BFS**：以二叉树为例，它的搜索思路是搜索从某个节点开始，将这个节点的直接子节点都遍历到，然后以相同的方式对各个子节点的直接子节点进行遍历，如此一层一层的将树的所有节点都遍历到。

过程：

<img src="https://cdn.nlark.com/yuque/0/2020/png/401416/1606119354783-830cee89-0001-4cd1-9c56-3470132b5b6d.png" alt="image.png" style="zoom:50%;" />



两种思路的对比：

<img src="https://cdn.nlark.com/yuque/0/2020/png/401416/1606119251525-063035d6-3bcc-4f41-abf0-b139416c893f.png" alt="image.png" style="zoom:50%;" />



#### 算法思路

针对深度优先搜索算法，通常可以采用两种方式进行处理，一种是递归处理，一种是迭代处理。常常**使用栈Stack这种数据结构配合使用**来深度遍历整棵树。之所以能用递归处理，在于递归的方式是方法的递归，在Java虚拟机中就是虚拟机栈的方式进行方法调用，因此能够模拟栈的操作，从而可以递归处理。

针对广度优先搜索算法，通常用**迭代处理(没有递归)**，常常使用**队列queue或者双端队列deque**配合广度遍历整棵树。

#### 模版代码

##### 深度优先搜索递归模版代码 

```java
/**
 * 深度搜索遍历处理 需要记录节点层级
 * @param root
 * @return
 */
public List<List<Integer>>  levelOrder(TreeNode root) {
    List<Integer>  res = new ArrayList<>();
    List<List<Integer>> lists = new ArrayList<>();
    if (root == null) {
        return lists;
    }
    helper(root, lists, 0);
    return lists;
}

public void helper(TreeNode root,List<List<Integer>>  lists, int level){
    //第一步 终止条件
    if(root==null){
        return;
    }
    //第二步 当前逻辑层代码

    //当层序集合中的层数等于 level，代表一个新的层级，此时需要往层序集合中添加一层节点数组 
    if(level == lists.size()){
        lists.add(new ArrayList<Integer>());
    }

    //拿到当前层的数组，往里添加元素
    lists.get(level).add(root.val);

    //第三步 下探至下一层 传递参数

    //遍历左子树
    helper(root.left,lists, level+1);
    //遍历右子树
    helper(root.right,lists, level+1);
}
```



##### 广度优先搜索：层序遍历模版代码

```java
/**
 * 层序遍历 使用队列（先进先出特性）作为辅助容器，一层一层将数据装入层级集合中
 * @param root
 * @return
 */
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> lists = new ArrayList<>();
    if (root == null) {
        return lists;
    }
    //使用队列作为辅助容器
    LinkedList<TreeNode> queue = new LinkedList<>();
    //根节点放入队列中
    queue.offer(root);
    while (!queue.isEmpty()) {
        //当层队列中的节点个数
        int size = queue.size();
        List<Integer> levelLis = new ArrayList<>();
        //遍历层中的所有元素，将其装入层级集合中，并将下层节点元素装入队列中
        for (int i = 0; i < size; i++) {
            TreeNode pop = queue.pop();
            //当前层的元素装入层级集合
            if (pop != null) {
                levelLis.add(pop.val);
            }
            //下一层子节点装入队列中
            if (pop.left != null) {
                queue.add(pop.left);

            }
            if (pop.right != null) {
                queue.add(pop.right);
            }
        }
        //一层遍历完就将当层 集合装入大集合中
        lists.add(levelLis);
    }
  return lists;
}
```



#### 算法题解

##### [102. 二叉树的层序遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/)

层序遍历一般都是使用广度优先遍历来处理二叉树，一层一层的记录层级节点

广度优先BFS：

```java
/**
 * 层序遍历 使用队列（先进先出特性）作为辅助容器，一层一层将数据装入层级集合中
 * @param root
 * @return
 */
public List<List<Integer>> levelOrder01(TreeNode root) {
    List<List<Integer>> lists = new ArrayList<>();
    if (root == null) {
        return lists;
    }
    LinkedList<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
        //当层队列中的节点个数
        int size = queue.size();
        List<Integer> levelLis = new ArrayList<>();
        //遍历层中的所有元素，将其装入层级集合中，并将下层节点元素装入队列中
        for (int i = 0; i < size; i++) {
            TreeNode pop = queue.pop();
            //当前层的元素装入层级集合
            if (pop != null) {
                levelLis.add(pop.val);
            }
            //下一层子节点装入队列中
            if (pop.left != null) {
                queue.add(pop.left);

            }
            if (pop.right != null) {
                queue.add(pop.right);
            }
        }
        //一层遍历完就将当层 集合装入大集合中
        lists.add(levelLis);
    }
  return lists;
}
```

深度优先DFS：

深度优先需要记录每个节点的层级信息，以此确定节点是被添加到哪个层级的数组中

```java
/**
 * 深度搜索遍历处理 需要记录节点层级
 * @param root
 * @return
 */
public List<List<Integer>>  levelOrder(TreeNode root) {
    List<Integer>  res = new ArrayList<>();
    List<List<Integer>> lists = new ArrayList<>();
    if (root == null) {
        return lists;
    }
    helper(root, lists, 0);
    return lists;
}

public void helper(TreeNode root,List<List<Integer>>  lists, int level){
    //第一步 终止条件
    if(root==null){
        return;
    }
    //第二步 当前逻辑层代码

    //当层序集合中的层数小于 level，代表一个新的层级，此时需要往层序集合中添加一层节点数组
    //lists.size() -1 < level
    if(level >= lists.size()){
        lists.add(new ArrayList<Integer>());
    }

    //拿到当前层的数组，往里添加元素
    lists.get(level).add(root.val);

    //第三步 下探至下一层 传递参数

    //遍历左子树
    helper(root.left,lists, level+1);
    //遍历右子树
    helper(root.right,lists, level+1);
}
```

##### [515. 在每个树行中找最大值](https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/)

广度优先搜索BFS：每一层都保留这一层的最大值，将其存入最大值数组中。

```java
public List<Integer> largestValues02(TreeNode root) {
    List<Integer> maxValueList = new ArrayList<>();
    if (root == null) {
        return maxValueList;
    }
    LinkedList<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
        int size = queue.size();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            TreeNode pop = queue.pop();
            if (pop != null) {
                max = Math.max(pop.val, max);
            }
            if (pop.left != null) {
                queue.add(pop.left);
            }
            if (pop.right != null) {
                queue.add(pop.right);
            }
        }
        maxValueList.add(max);
    }
    return maxValueList;
}
```

深度优先搜索DFS： 一种比较精简的写法

```java
/**
 * 深度搜索遍历处理
 * @param root
 * @return
 */
public List<Integer> largestValues(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    helper(root, res, 0);
    return res;
}

public void helper(TreeNode root,List<Integer> res,int dep){
    //终止条件
    if(root==null){
        return;
    }
    //当前逻辑层代码

    if(dep == res.size()){
        res.add(root.val);
    }else{
        int max = Math.max(res.get(dep),root.val);
        res.set(dep,max);
    }
    //下探至下一层 传递参数
    helper(root.left,res,dep+1);
    helper(root.right,res,dep+1);
}
```

##### [127. 单词接龙](https://leetcode-cn.com/problems/word-ladder/)

也是广度优先搜索的题，使用模版，优化操作细节，其中step++ 是在外层for循环后处理，代表当前这一层遍历完成加1操作，此外，当达到结束条件时，代表已经来到了endword也就是终节点，由此返回退出while/for循环。都是模版代码。和[111. 二叉树的最小深度](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/)  的层序遍历解法一样的操作

```java
/**
 * 无向无权图中的广度优先搜索 思路  （广度优先模版代码 + 建图处理）
 *
 * @param beginWord
 * @param endWord
 * @param wordList
 * @return
 */
public int ladderLength00(String beginWord, String endWord, List<String> wordList) {

    //字典数组存入set中
    HashSet<String> wordSets = new HashSet<>(wordList);
    //字符数组长度为0，字典中没有目标字符串
    if (wordList == null || wordList.size() == 0 || !wordList.contains(endWord)) {
        return 0;
    }
    wordSets.remove(beginWord);

    //已经访问过的字符串集合
    HashSet<String> visited = new HashSet<>();
    visited.add(beginWord);
    //广度优先搜索 必要的辅助容器： 队列
    Queue<String> queue = new LinkedList<String>();
    queue.offer(beginWord);
    //可达路径
    int step = 1;
    while (!queue.isEmpty()) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            //取出队头字符串
            String poll = queue.poll();
            char[] chars = poll.toCharArray();
            //当前字符串的长度， 将当前字符串每个位置 依次替换 形成字符串与 字符串数组中的字符串比较，
            // 如果有，则代表当前字符串 通过一次字符变幻 后 与字符串数组中某个字符串相同，路径可达
            for (int j = 0; j < chars.length; j++) {
                //某个位置上的字符要匹配字符时，将原来的的字符保留，遍历匹配后，还原这个位置上的字符，因为字符串每次只能更改一个字符
                char oldPositionChar = chars[j];

                for (char k = 'a'; k <= 'z'; k++) {

                    chars[j] = k;
                    String nextword = String.valueOf(chars);
                    if (wordSets.contains(nextword)) {
                        //最终组装的字符等于目标字符串，返回路径长度
                        if (nextword.equals(endWord)) {
                            //长度加1再返回
                            return step+1;
                        }
                        //当前字符串没有被访问过，加入队列，用来进行下一层级搜索
                        if (!visited.contains(nextword)) {
                            queue.offer(nextword);
                            visited.add(nextword);
                           // step++;
                        }
                    }
                }
                //还原该位置上的字符
                chars[j] = oldPositionChar;

            }
        }
        //一次字符串匹配，则代表路径加1
         step++;
    }
    //上面循环操作没有返回路径长度，则代表start - > end 无法在给定的字符数组中转变成功
    return 0;

}
```

##### [111. 二叉树的最小深度](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/) 

加深理解，与上题对比，更能体现层序遍历模版的细节与共通点。

```java
/**
 * 广度优先搜索 第一个叶子节点的深度就是这棵树的最短深度
 *
 * @param root
 * @return
 */
public int minDepth(TreeNode root) {
    if (root == null) {
        return 0;
    }

    LinkedList<TreeNode> queue = new LinkedList<>();
    //将元素 往队列 后部加
    queue.offer(root);
    int level = 1;
    while (!queue.isEmpty()) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            //弹出头部 元素
            TreeNode poll = queue.poll();
            //层序遍历，叶子节点   返回深度 return 直接退出for while
            if (poll.left == null && poll.right == null) {
                return level;
            }
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
        }
        //遍历完一层，深度加1
        level++;
    }
    return level;

}
```

#### 二分查找

##### 三个必要条件：

1.单调递增或递减 2.可以通过索引查找 3.数组数据上下有界

##### 时间复杂度

因为每次数据查找都减半，因此是O(log n)

数组在有序情况下可以使用二分查找快速查找；普通链表无法使用二分查找，因为无法通过索引查找，不过可以通过改造，添加索引，改造成跳表，也能实现二分查找。

##### 模版代码

```java
/**
 * 二分查找三个必要条件：
 * 1。数据单调性（单调递增或者单调递减）
 * 2。上下有界 数组有上下界
 * 3。可以通过索引查找
 * @param array
 * @param target
 */
public int binarySearch(int[] array,int target){
    int left = 0 ;
    int right = array.length-1;
    int mid =  0;
     while (left<=right){
         //中间索引
         mid = (right - left)/2 +left ;
         //中间元素为要查找的目标值，返回目标索引
         if(array[mid] == target){
             return  mid ;
         }else if(array[mid]>target){
            //目标值在左侧
             right = mid -1;
         }else if(array[mid]<target){
             //目标值在右侧
             left = mid+1;
         }
     }
     return -1; 
}
```

##### [33. 搜索旋转排序数组](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/)

```java
/**
 * 数组不是全部有序，部分有序时，需要判断
 * left<=mid 正序时 , nums[left]<= target && target <num[mid] 则代表目标值在左边有序数组中，否则在右侧数组中
 * left > mid 反序时 ，nums[mid]< target && target<=nums[right] 则代表目标值在右边有序数组中，否则在左侧数组中
 * <p>
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/solution/sou-suo-xuan-zhuan-pai-xu-shu-zu-by-leetcode-solut/
 *
 * @param nums
 * @param target
 * @return
 */
public int search(int[] nums, int target) {
    if (nums.length == 0) {
        return 0;
    }

    int left = 0, right = nums.length - 1;
    while (left <= right) {
        int mid = (right - left) / 2 + left;
        if (nums[mid] == target) {
            return mid;
        }
        //左边数组正序时
        if (nums[left] <= nums[mid]) {
            //判断目标值在不在左边数组中，如果在，移动右边界，不在移动左边界
            if (nums[left] <= target && target < nums[mid]) {
                right = mid - 1;
            } else {
                //不在左边数组，移动左边界
                left = mid + 1;
            }
        } else {
            //左边数组倒序时
            //判断目标值是否在右边数组 ，在就移动做边界，不在移动右边界
            if (nums[mid] < target && target <= nums[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }
    }
    return -1;

}
```



##### [74. 搜索二维矩阵](https://leetcode-cn.com/problems/search-a-2d-matrix/)

时间复杂度：O(m+n)

```java
/**
 * 从矩阵右上方开始遍历，一层层一列列判断
 * @param matrix
 * @param target
 * @return
 */
public boolean searchMatrix(int[][] matrix, int target) {
    // System.out.println(matrix.length);
    // System.out.println(matrix[0].length);
    
    if(matrix.length == 0){
        return false;
    }
    int row = 0, column = matrix[0].length - 1;
    //边界条件： 行数 && 列数 不越界
    while ((row >= 0 && row < matrix.length) && (column >= 0 && column < matrix[0].length)) {
        // System.out.println(matrix[row][column]);
        if (matrix[row][column] == target) {
            return true;
        }
        //x层y列大于目标值，列减小
        if (matrix[row][column] > target) {
            column--;
        } else if (matrix[row][column] < target) {//x层y列小于目标值，行增大
            row++;
        }
    }
    return false;
}
```

二分查找法来处理此方法：

时间复杂度 O(log m*n)

https://leetcode-cn.com/problems/search-a-2d-matrix/solution/sou-suo-er-wei-ju-zhen-by-leetcode/

```java
/**
 * 搜索矩阵 矩阵行中从左到右，列中从上到下 （递增）可以看作是一个从左到右的递增 一维数组
 * 满足二分查找的三个条件：上下有界/单调性（单调递增）/有索引值
 * 需要特殊处理mid值，数学规律： 虚拟一维数组中中间值 行数= 中间值除以列数  列数 = 中间值取余列数
 *
 * @param matrix
 * @param target
 * @return
 */
public boolean searchMatrix(int[][] matrix, int target) {

    if (matrix.length == 0) {
        return false;
    }
    //数组行数
    int rows = matrix.length;
    //数组列数
    int columns = matrix[0].length;
    int left = 0, right = rows * columns - 1;
    while (left <= right) {
        int mid = (right - left) / 2 + left;
        //虚拟一维数组中中间值 行数= 中间值除以列数  列数 = 中间值取余列数
        int midValue = matrix[mid / columns][mid % columns];
        if (midValue == target) {
            return true;
        } else if (target < midValue) {
            right = mid - 1;
        } else {

            left = mid + 1;
        }
    }

    return false;

}
```