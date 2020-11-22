package leetcode.week03;

import java.util.Stack;

/**
 * 104. 二叉树的最大深度
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 */
public class MaxTreeDepth {

    public static void main(String[] args) {
        BuildTree buildTree = new BuildTree();
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        TreeNode node = buildTree.buildTree(preorder, inorder);
        int depth = new MaxTreeDepth().maxDepth(node);
        System.out.println(depth);
    }


    /**
     * 深度遍历
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> levelStack = new Stack<>();
        stack.push(root);
        levelStack.push(1);
        int max = 0;
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            Integer level = levelStack.pop();
            if(pop.left == null && pop.right == null){
                max = Math.max(level, max);
            }

            if (pop.left != null) {
                stack.push(pop.left);
                levelStack.push(level + 1);
            }
            if (pop.right != null) {
                stack.push(pop.right);
                levelStack.push(level + 1);
            }

        }

        return max;

    }


    /**
     * 递归
     *
     * @param root
     * @return
     */
    public int maxDepth02(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int maxLH = maxDepth02(root.left);
        int maxRH = maxDepth02(root.right);
        return Math.max(maxLH, maxRH) + 1;
    }


    //------- 第一二遍 2020/11/18 ----------

    public int maxDepth00(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = maxDepth00(root.left);
        int rightHeight = maxDepth00(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }


}
