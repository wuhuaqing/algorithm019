package leetcode.week04;

import leetcode.TreeNode;
import leetcode.week03.BuildTree;

import java.util.*;

/**
 * 102. 二叉树的层序遍历  https://leetcode-cn.com/problems/binary-tree-level-order-traversal/#/description
 */
public class TreeLevelOrder {

    public static void main(String[] args) {
        int[] preOrder = new int[]{3, 9, 20, 15, 7};
        int[] inOrder = new int[]{9, 3, 15, 20, 7};

        TreeNode buildTree = BuildTree.getBuildTree(preOrder, inOrder);
        List<List<Integer>> lists = new TreeLevelOrder().levelOrder(buildTree);
        for (List<Integer> item : lists) {
            System.out.println(item);
        }
    }


    //---- 第三遍 2020/11/26 -----

    /**
     *  层序遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }

        Queue <TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if(poll!=null){
                    res.add(poll.val);
                }
                if(poll.left!=null){
                    queue.add(poll.left);
                }
                if(poll.right!=null){
                    queue.add(poll.right);
                }

            }
            lists.add(res);
        }

        return lists;
    }


    /**
     * 深度遍历递归模式
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder03(TreeNode root) {

        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }
        List<Integer> res = new ArrayList<>();
        dfs(root, lists,   0);
        return lists;
    }

    public void dfs(TreeNode node, List<List<Integer>> lists, int level) {
        if (node == null) {
            return;
        }
        if (level == lists.size()) {
            lists.add(new ArrayList<>());
        }
        lists.get(level).add(node.val);
        dfs(node.left, lists,  level + 1);
        dfs(node.right, lists, level + 1);

    }


    //-----

    /**
     * 深度搜索遍历处理 需要记录节点层级
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder02(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }
        helper(root, lists, 0);
        return lists;
    }

    public void helper(TreeNode root, List<List<Integer>> lists, int level) {
        //第一步 终止条件
        if (root == null) {
            return;
        }
        //第二步 当前逻辑层代码

        //当层序集合中的层数等于 level，代表一个新的层级，此时需要往层序集合中添加一层节点数组
        //lists.size() -1 < level
        if (level == lists.size()) {
            lists.add(new ArrayList<Integer>());
        }

        //拿到当前层的数组，往里添加元素
        lists.get(level).add(root.val);

        //第三步 下探至下一层 传递参数

        //遍历左子树
        helper(root.left, lists, level + 1);
        //遍历右子树
        helper(root.right, lists, level + 1);
    }

    /**
     * 层序遍历 使用队列（先进先出特性）作为辅助容器，一层一层将数据装入层级集合中
     *
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
}
