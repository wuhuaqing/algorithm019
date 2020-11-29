package leetcode.week04;

import leetcode.TreeNode;
import leetcode.week03.BuildTree;

import java.util.*;

/**
 * 515. 在每个树行中找最大值 https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/#/description
 */
public class LevelLargestValues {

    public static void main(String[] args) {
        int[] preOrder = new int[]{3, 9, 20, 15, 7};
        int[] inOrder = new int[]{9, 3, 15, 20, 7};
//        int[] preOrder = new int[]{0, -2147483648, 2147483647};
//        int[] inOrder = new int[]{-2147483648, 0, 2147483647};

        TreeNode buildTree = BuildTree.getBuildTree(preOrder, inOrder);
        List<Integer> lists = new LevelLargestValues().largestValues(buildTree);
        for (int item : lists) {
            System.out.println(item);
        }
    }


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

    public List<Integer> largestValues01(TreeNode root) {
        List<Integer> maxValueList = new ArrayList<>();
        if (root == null) {
            return maxValueList;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int[] ints = new int[size];
            for (int i = 0; i < size; i++) {
                TreeNode pop = queue.pop();
                if (pop != null) {
                    ints[i] = (pop.val);
                }
                if (pop.left != null) {
                    queue.add(pop.left);
                }
                if (pop.right != null) {
                    queue.add(pop.right);
                }
            }
            Arrays.sort(ints);
            maxValueList.add(ints[ints.length - 1]);
        }

        return maxValueList;
    }

    public List<Integer> largestValues00(TreeNode root) {
        List<Integer> maxValueList = new ArrayList<>();
        if (root == null) {
            return maxValueList;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        PriorityQueue<Integer> heap = new PriorityQueue<>(1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode pop = queue.pop();
                if (pop != null) {
                    heap.offer(pop.val);
                }
                if (pop.left != null) {
                    queue.add(pop.left);
                }
                if (pop.right != null) {
                    queue.add(pop.right);
                }
            }
            maxValueList.add(heap.poll());
            heap.clear();
        }

        return maxValueList;
    }
}
