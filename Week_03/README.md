#### 递归思维

##### 递归思维方法论

1.抵制人肉思维

2.寻找最近重复性

3.数学归纳法归纳数据现象

##### 递归模版代码

1.设定递归终止条件

2.当前层逻辑处理

3.传递参数开启下一层递归

4.清理数据状态

 



#### 回溯 backtrack

一种深度搜索过程中用于重置某次搜索的状态，以结束某次搜索。回溯通常配合递归一起出现，用于解决：组合/排列/切割/子集/棋盘等应用场景。

##### 模版代码

[视频讲解](https://www.bilibili.com/video/BV1cy4y167mM/)

其实就是递归四步中，对第四步的重置状态 具体为回溯状态，在一般的递归中不需要第四步，而在回溯算法中，第四步就很重要。

```java
 public void backtrack (int n,int k,int start,Deque<Integer> path,List<List<Integer>> lists){
        //第一步 递归终止条件
        if( path.size() == k ){
            //收集搜索结果
            lists.add(new ArrayList<>(path));
            return;
        }
       // 集合元素的遍历
        for (int i = start;i<=n ;i++ ) {
            //第二步 本层操作
            path.addLast(i); 
            // 第三步 递归往下一层传递参数
            dfs(n,k,i+1,path,lists);
            //第四步 重置状态   // 递归后紧接着就是回溯重置状态 
            path.removeLast();  
        }
    }
```



##### 算法题解

###### [77. 组合](https://leetcode-cn.com/problems/combinations/)

回溯算法，剪枝增加效率

[剪枝处理思路](https://leetcode-cn.com/problems/combinations/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-ma-/)

```Java
public List<List<Integer>> combine(int n,int k){
    if(n<k){
        return new ArrayList<>();
    }
    List<List<Integer>> lists = new ArrayList<>();
    Deque<Integer> stack = new ArrayDeque<>();
    dfsCutsub(n,k,1,stack,lists);
    return lists;

}

/**
 * 深度遍历 回溯 + 剪枝法
 * https://leetcode-cn.com/problems/combinations/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-ma-/
 * @param n
 * @param k
 * @param start
 * @param path
 * @param lists
 */
public void dfsCutsub(int n,int k,int start,Deque<Integer> path,List<List<Integer>> lists){
    if(path.size() == k){
        lists.add(new ArrayList<>(path));
        return;
    }
    //剪枝后的遍历终点
    int end = n-(k-path.size()) +1;
    for (int i = start; i <= end; i++) {
        path.addLast(i);
        System.out.println("递归前path:" + path);
        dfsCutsub(n,k,i+1,path,lists);
        path.removeLast();
        System.out.println("递归后path:" + path);
    }
}
```

###### [78. 子集](https://leetcode-cn.com/problems/subsets/)

```java
/**
 * 结合 77 组合问题 使用递归回溯的方法进行处理，同时注意剪枝减少不必要的递归
 * @param nums
 * @return
 */
public List<List<Integer>> subsets(int[] nums) {
    if (nums.length == 0) {
        return new ArrayList<>();
    }
    List<List<Integer>> lists = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    // 遍历 获取 nums 数组不同元素个数的集合
    for (int i = 0; i <= nums.length; i++) {
        backtrack(nums, 0, i, path, lists);
    }
    return lists;
}

/**
 * 回溯处理
 * @param nums 数组元素
 * @param start 开始角标
 * @param k 组合元素个数为k  【1，2】[1,3],[1,4] 代表这类组合的元素是两个
 * @param path k个元素的组合数集合
 * @param lists 大集合
 */
public void backtrack(int[] nums, int start, int k, List<Integer> path, List<List<Integer>> lists) {
    if (path.size() == k) {
        lists.add(new ArrayList<>(path));
        return;
    }
    //剪枝处理
    int end = nums.length - (k - path.size()) + 1;
    for (int i = start; i < end; i++) {
        path.add(nums[i]);
        backtrack(nums, i + 1, k, path, lists);
        path.remove(path.size() - 1);
    }

}
```

###### [46. 全排列](https://leetcode-cn.com/problems/permutations/)

回溯思路：递归中涉及对状态重置 

```java
public List<List<Integer>> permute(int[] nums) {
    if (nums == null || nums.length == 0) {
        return new ArrayList<>();
    }

    List<List<Integer>> lists = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    // dfs(nums, permuteSub,lists);
    boolean[] used = new boolean[nums.length];
    backTrack(nums, used, path, lists);
    return lists;
}

public void backTrack(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> lists) {
    if (path.size() == nums.length) {
        lists.add(new ArrayList<Integer>(path));
        return;
    }

    for (int i = 0; i < nums.length; i++) {
        if (!used[i]) {
            path.add(nums[i]);
            used[i] = true;
            // System.out.println("递归前 path => " + path );
            backTrack(nums, used, path, lists);
            path.remove(path.size() - 1);
            used[i] = false;
            // System.out.println("递归回溯 path  used=> " + path +  used[i]);
        }
    }

}
```

###### [236. 二叉树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/)

‘’

```java
/**
 * 通过存储父节点一级一级往上找
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/er-cha-shu-de-zui-jin-gong-gong-zu-xian-by-leetc-2/
 * @param root
 * @param p
 * @param q
 * @return
 */
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) {
        return root;
    }
    //存储所有节点的父节点
    saveNodeParent(root);
    return getLCP(p, q);
}

public TreeNode getLCP(TreeNode pNode, TreeNode q) {
    while (pNode != null) {
        //将父节点元素值放入集合
        parentSet.add(pNode.val);
        //通过当前节点元素取它的父节点
        TreeNode treeNode = parentMap.get(pNode.val);
        //将父节点赋给当前节点，接着往上获取父节点
        pNode = treeNode;
    }
    while (q != null) {
        // set中包含 q, q为父节点，则返回q
        if (parentSet.contains(q.val)) {
            return q;
        }
        //如果当前节点不在set中，从父集合中取q的父集合 接着遍历
        q = parentMap.get(q.val);
    }
    return null;
}


public void saveNodeParent(TreeNode node) {
    if (node.left != null) {
        parentMap.put(node.left.val, node);
        saveNodeParent(node.left);
    }
    if (node.right != null) {
        parentMap.put(node.right.val, node);
        saveNodeParent(node.right);
    }
}
```

###### [105. 从前序与中序遍历序列构造二叉树](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)

```java
private HashMap<Integer, Integer> map;

/**
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/cong-qian-xu-yu-zhong-xu-bian-li-xu-lie-gou-zao-9/
 * 先序遍历的第一个元素是根节点
 * 根据先序遍历的根节点找到中序遍历的角标，确定中序遍历中左子树的个数，确定中序遍历右子树的个数
 * 递归构造二叉树
 *
 * @param preorder
 * @param inorder
 * @return
 */
public TreeNode buildTree(int[] preorder, int[] inorder) {
    map = new HashMap<>();
    for (int index = 0; index < inorder.length; index++) {
        map.put(inorder[index], index);
    }
    return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
}

public TreeNode buildTree(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight) {
    //第一步 递归终止条件
    if (preLeft > preRight) {
        return null;
    }
    // 第二步 当前层逻辑处理

    //前序遍历数组第一位是根节点
    int rootVal = preorder[preLeft];

    //得到中序遍历中根节点所在的角标
    Integer inRootIndex = map.get(rootVal);

    int leftCount = inRootIndex - inLeft;

    TreeNode root = new TreeNode(rootVal);

    // 第三步 传入参数开启下一层
    //左子树需要的先序，中序角标
    root.left = buildTree(preorder, preLeft + 1, preLeft + leftCount, inorder, inLeft, inRootIndex - 1);
    //右子树需要的先序，中序角标
    root.right = buildTree(preorder, preLeft + leftCount + 1, preRight, inorder, inRootIndex + 1, inRight);

    // 清理本层状态 （没有）

    return root;
}
```

