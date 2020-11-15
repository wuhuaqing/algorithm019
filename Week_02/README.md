#### 哈希表/映射与集合

哈希表是一组通过hash函数直接确定数组位置的列表。hash函数的设计水平，直接影响数据处理的效率。如果效率不高，容易出现hash碰撞，常见解决哈希碰撞的方法有：再哈希法，链地址法，线性探测法。在常见的数据结构中，HashMap使用的是链地址法（拉链法），JDK1.7的数据结构是数组+单链表，JDK1.8中是数组+单链表+红黑树。之所以引入红黑树，是对时间空间使用的权衡，当哈希冲突导致的单链表长度达到8，则将链表转换成红黑树，以此来降低哈希表中的时间复杂度（但会增加使用空间）。

因此它的增删改查时间复杂度在理想的情况下（没有hash冲突时）为：O(1)；  哈希冲突中有单链表时是O(n)；哈希冲突中有红黑树时是O(logn)。



#### 算法题解

##### [242. 有效的字母异位词](https://leetcode-cn.com/problems/valid-anagram/)

``

```java
/**
 * 异位词 中 只是字符的顺序不同，出现的字符以及字符出现的个数是相同
 * 可以将字符串 转成字符数组，然后进行排序
 * 遍历比较每一个位置上的字符，如果不同则代表两个字符串不是相互异位词
 *
 * @param s
 * @param t
 * @return
 */
public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
        return false;
    }
    char[] sChars = s.toCharArray();
    char[] tChars = t.toCharArray();

    Arrays.sort(sChars);
    Arrays.sort(tChars);
    for (int i = 0; i < sChars.length; i++) {
        if (sChars[i] != tChars[i]) {
            return false;
        }
    }
    return true;

}
```

``

```java
/**
 * 异位词，每一个相同的字符通过hash算法得到的哈希值作为hash表的下标，
 * 如果s中有一个字符，通过hash得到它的位置，此位置加1，t中有一个字符，通过hash得到它的位置，此位置减1
 * 如果此位置最终的值为0，代表两个字符串中此字符的个数是相同的。
 * 如果hash表中有位置的值不为0，代表两个字符串有字符的个数不一样，则代表两个字符串不是异位词
 *
 * @param s
 * @param t
 * @return
 */
public boolean isAnagram00(String s, String t) {
    if (s.length() != t.length()) {
        return false;
    }
    //26 为字母的个数为26，hash表的长度也就是26
    int[] store = new int[26];
    for (int i = 0; i < s.length(); i++) {
        //s.charAt(i)-'a' 以 'a'的编码值为起点
        store[s.charAt(i) - 'a']++;
        store[t.charAt(i) - 'a']--;
    }
    for (int j = 0; j < store.length; j++) {
        if (store[j] != 0) {
            return false;
        }
    }

    return true;

}
```

上一种思路的微调

```java
/**
 * 判断两个char[]数组是否相同
 * @param s
 * @param t
 * @return
 */
public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
        return false;
    }

    char[] sChars = s.toCharArray();
    char[] tChars = t.toCharArray();

    char [] sCodeArr =  new char[26];
    char [] tCodeArr =  new char[26];
    for (int index= 0;index<sChars.length;index++){
        char sChar = sChars[index];
        char tChar = tChars[index];
        sCodeArr [sChar -'a'] ++;
        tCodeArr [tChar -'a'] ++;
    }
    String sCoStr = String.valueOf(sCodeArr);
    String tCoStr = String.valueOf(tCodeArr);
     System.out.println(sCoStr);
     System.out.println(tCoStr);
    return sCoStr.equals(tCoStr)  ; 

}
```

##### [49. 字母异位词分组](https://leetcode-cn.com/problems/group-anagrams/)

好的题解：https://leetcode-cn.com/problems/group-anagrams/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--16/

 使用字符数组排序后作为hashmap 的key,消除异位词的字符顺序不一致

```java
/**
 * hashmap str--> list来储存
 * key str使用字符数组排序
 * @param strs
 * @return
 */
public List<List<String>> groupAnagrams00(String[] strs) {


    if (strs == null ||strs.length == 0) {
        return null;
    }

    List<String> subList = null;
    HashMap<String, List<String>> map = new HashMap<>();


    for (int index = 0; index < strs.length; index++) {

        String str = strs[index];
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        String sortStr = String.valueOf(chars);
        if (map.containsKey(sortStr)) {
            map.get(String.valueOf(chars)).add(str);
        } else {
            subList = new ArrayList<>();
            subList.add(str);
            map.put(sortStr, subList);
        }

    }

    return new ArrayList<>(map.values());

}
```

使用异位词的字符ASCII码数组为key,消除异位词的字符顺序不一致

```java
/**
 *  hashmap str--> list来储存
 *  hash函数的设计思路：使用ASCII 码来消除字符串的顺序，并以此来保证异位词的key是同一个
 * @param strs
 * @return
 */
public List<List<String>> groupAnagrams(String[] strs) {
    if (strs == null ||strs.length == 0) {
        return null;
    }

    List<String> subList = null;
    HashMap<String, List<String>> map = new HashMap<>();

    for (String str: strs ) {
        char[] chars = new char[26];
        for(char ch: str.toCharArray()){
            chars[ch - 'a'] ++;
        }
        String numStr =String.valueOf(chars);
        if(map.containsKey(numStr)){
            map.get(numStr).add(str);
        }else{
            subList =   new ArrayList< >();
            subList.add(str);
            map.put(numStr,subList);
        }

    }
    return new ArrayList<>(map.values()); 
}
```

##### [1. 两数之和](https://leetcode-cn.com/problems/two-sum/)

（**精选**）只能说解法很巧妙

```java
/**
 * 使用hashmap number -->k
 *            index -->value
 *
 *    {2, 7, 11, 15} target= 9
 *    map() => map(2,0)
 *    map(2,0)  map.containkey(2) ==> map.get(2) = 0 => [map.get(2),index = 1] == > [0,1]
 *
 * @param nums
 * @param target
 * @return
 */

public int[] twoSum(int[] nums, int target){
    int length = nums.length;

    HashMap<Integer, Integer> map = new HashMap<>();

    for (int index = 0; index < length; index++) {
        int numb = target - nums[index];
        if(map.containsKey(numb)){
            return new int[]{map.get(numb),index};
        }
        map.put(nums[index],index);
    }


    return null;
}
```

暴力求解

```java
/**
 * 暴力遍历  leetcode中提交超时  时间复杂度 O(n^2)
 *
 * @param nums
 * @param target
 * @return
 */
public int[] twoSum00(int[] nums, int target) {
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

#### 二叉树/二叉搜索树

链表/数组是一维结构，树是二维结构；链表是特殊的树（只有左子树或者只有右子树时，树就退化为链表）；树是特殊的图（没有环的图可以看成是树）。

##### 二叉树的遍历

原则：根节点的遍历先后顺序分为 （**左右是指左右子树，而不单单是左右节点**）

前序遍历：根节点 -- 左子树  -- 右子树

中序遍历： 左子树-- 根节点  -- 右子树

后序遍历：左子树  -- 右子树 -- 根节点

常用的思路：使用递归

我的理解：对树的增删改查操作，都涉及到树的遍历，而树的结构特点，基本就是根据某个节点（根节点）来遍历左子树或者右子树，这样重复性的操作，比较适合递归操作来完成。

##### 记忆点

**二叉树/N叉树的 前序遍历/中序遍历/后序遍历 都是深度优先搜索的遍历：在算法中常常使用Stack(栈) + LinkedList(双端队列)作为辅助容器存储节点 来进行遍历操作**

**二叉树/N叉树的 层序遍历 都是广度优先搜索的遍历：在算法中常使用队列（queue）作为辅助容器存储节点来进行遍历**



##### 二叉搜索树

二叉搜索树是一棵有序的树，通常都是左节点小于根节点，根节点小于有节点。

好的直观操作网站：https://visualgo.net/zh/bst

各类数据结构复杂度陈列：https://www.bigocheatsheet.com/



#### 算法题解

##### [94. 二叉树的中序遍历](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)

二叉树的遍历通常都是使用递归操作

```java
/**
 * 中序遍历的要求是：根在中间 遍历顺序是：左子树 -  根节点 —— 右子树
 */

public List<Integer> inorderTraversal(TreeNode root) {
    if (root == null) {
        return new ArrayList();
    }
    List<Integer> list = new ArrayList<>();

    if (root != null) {
        reverNode(root.left, list);
        list.add(root.val);
        reverNode(root.right, list);
    }

    return list;

}

public void reverNode(TreeNode root, List<Integer> list) {
    if (root != null) {
        reverNode(root.left, list);
        list.add(root.val);
        reverNode(root.right, list);
    }
}
```



##### [144. 二叉树的前序遍历](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/)

```java
/**
 * 前序遍历的要求是：根在前面 遍历顺序是： 根节点 -  左子树  —— 右子树
 */

public List<Integer> preorderTraversal(TreeNode root) {
    if (root == null) {
        return new ArrayList();
    }
    List<Integer> list = new ArrayList<>();

    if (root != null) {
        list.add(root.val);
        reverNode(root.left, list);
        reverNode(root.right, list);
    }

    return list;

}

public void reverNode(TreeNode root, List<Integer> list) {
    if (root != null) {
        list.add(root.val);
        reverNode(root.left, list);
        reverNode(root.right, list);
    }
}
```

##### [102. 二叉树的层序遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/)

[好的题解]( https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/solution/er-cha-shu-ceng-xu-bian-li-deng-chang-wo-yao-da-3/)

```java
/**
 * 层序遍历 使用队列（先进先出的特性，且删除操作为O(1)）作为辅助容器，帮助数据分层
 * @param root
 * @return
 */
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> lists = new ArrayList<>();
    if (root == null) {
        return lists;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()){
        int size = queue.size();
        List<Integer> subList = new ArrayList<>();
        for (int index = 0; index < size; index++) {
            TreeNode node = queue.poll();
            if(node!=null){
                subList.add(node.val);
                //加入队列的节点不为空
                if(node.left!=null){
                    queue.add(node.left);
                }if(node.right!=null){
                    queue.add(node.right);
                }
            }
        }
        lists.add(subList);
    }

    return lists;
}
```

##### [429. N叉树的层序遍历](https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/)

（广度优先）层序遍历常使用队列做辅助遍历

```java
/**
 * 层序遍历 使用队列（先进先出的特性，且删除操作为O(1)）作为辅助容器，帮助数据分层
 * @param root
 * @return
 */
public List<List<Integer>> levelOrder(Node root) {
    List<List<Integer>> lists = new ArrayList<>();
    if (root == null) {
        return lists;
    }
    //队列
    Queue<Node> queue = new LinkedList();
    queue.add(root);
    while (!queue.isEmpty()) {

        int size = queue.size();
        List<Integer> subList = new ArrayList<>();
        //使用size，提前固定大小， 循环里加入数据也不会影响循环次数，如果用queue.size(),则会影响分层
        for (int index = 0; index < size; index++) {
            // poll 队列头部没数据返回空，queue.remove(); 队列头部没数据抛异常
            Node node = queue.poll();
            subList.add(node.val);
            //队列中加入当前节点的全部子节点
            queue.addAll(node.children);
        }

        lists.add(subList);

    }

    return lists;
}
```

##### [589. N叉树的前序遍历](https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/)

 （深度优先）深度遍历常使用栈做辅助容器

```java
/**
 * 栈 + 链表列表 配合使用
 * @param root
 * @return
 */
public List<Integer> preorder(Node root) {

    LinkedList<Integer> linkedList = new LinkedList<>();
    if(root == null){
         return linkedList ;
    }
    Stack<Node> stack = new Stack<>();
    stack.push(root);
    while (!stack.isEmpty()){
        Node pop = stack.pop();
        linkedList.add(pop.val);
        if(pop.children!=null){
            //从后往前遍历
            for (int j = pop.children.size()-1;j>=0;j--) {
                Node item =  pop.children.get(j);
                if(item!=null){
                    stack.push(item);
                }
            }

        }
    }

    return linkedList;

}
```

递归方式

```java
/**
 * 递归求解N叉树前序遍历
 *
 * @param root
 * @return
 */
public List<Integer> preorder00(Node root) {

    if (root == null) {
        return new ArrayList<>();
    }
    List<Integer> list = new ArrayList<>();
    list.add(root.val);
    for (int index = 0; index < root.children.size(); index++) {
        reverNode(root.children.get(index), list);
    }
    return list;

}

private void reverNode(Node root, List<Integer> list) {
    if (root != null) {
        list.add(root.val);
        for (int i = 0; i < root.children.size(); i++) {
            reverNode(root.children.get(i), list);
        }
    }
}
```

##### [590. N叉树的后序遍历](https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/)

```java
/**
 * 使用栈先入后出的特性 + linkedlist 头插法  配合得到N叉树后序遍历值
 * @param root
 * @return
 */
public List<Integer> postorder(Node root) {

    LinkedList<Integer> list = new LinkedList<>();
    if (root == null) {
        return list;
    }
    Stack<Node> stack = new Stack<>();
    stack.push(root);
    while (!stack.isEmpty()){
        Node pop = stack.pop();
        //头插法，后面的元素排第一位
        list.addFirst(pop.val);
        if(pop.children!=null){
            for (Node item :   pop.children) {
                if(item!=null){
                    stack.push(item);
                }
            }
        }
    }
    return  list;

}
```

 递归完成N叉树的后序遍历

```java
/**
 * 递归完成N叉树 的后序遍历
 *
 * @param root
 * @return
 */
public List<Integer> postorder02(Node root) {
    List<Integer> list = new ArrayList<>();
    if (root == null) {
        return list;
    }
    for (int index = 0; index < root.children.size(); index++) {
        reverNode(root.children.get(index), list);
    }
    list.add(root.val);

    return list;
}


private void reverNode(Node node, List<Integer> list) {
    if (node != null) {
        for (int index = 0; index < node.children.size(); index++) {
            reverNode(node.children.get(index), list);
        }
        list.add(node.val);
    }

}
```