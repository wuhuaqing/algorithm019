package leetcode.week03;

import java.util.LinkedList;
import java.util.Queue;

/***
 * 111. 二叉树的最小深度  https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
 *
 * 给定一个二叉树，找出其最小深度。
 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 说明：叶子节点是指没有子节点的节点。
 */
public class MinDepth {

    public static void main(String[] args) {
        BuildTree buildTree = new BuildTree();
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        TreeNode node = buildTree.buildTree(preorder, inorder);
        int depth = new MinDepth().minDepth(node);
        System.out.println(depth);
    }


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

    /**
     * 递归
     *
     * @param root
     * @return
     */
    public int minDepth03(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            return minDepth03(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth03(root.left) + 1;
        }

        return Math.min(minDepth03(root.left), minDepth03(root.right)) + 1;
    }


    // 第一二遍 2020/11/19 ---------

    /**
     * 递归
     *
     * @param root
     * @return
     */
    public int minDepth02(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //如果左节点为空，则返回右子树的最小深度
        if (root.left == null) {
            return minDepth02(root.right) + 1;
        }
        // 如果右节点为空，则返回左子树的最小深度
        if (root.right == null) {
            return minDepth02(root.left) + 1;
        }
        // 否则返回左右子树中 较小的那颗树的深度
        return Math.min(minDepth02(root.left), minDepth02(root.right)) + 1;
    }


    /**
     * 广度优先  层序遍历  某一层的第一个叶子节点，就是这棵树的最短深度
     *
     * @param root
     * @return
     */
    public int minDepth01(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                //左右子节点为空时，为叶子节点
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
            level++;
        }
        return level;
    }


    /**
     * 深度搜索 找最小深度 （注意审题：是根节点到叶子节点，叶子节点的定义是没有左右子节点）
     *
     * @param root
     * @return
     */
    public int minDepth00(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        int minH = Integer.MAX_VALUE;

        if (root.left != null) {
            minH = Math.min(minDepth00(root.left), minH);
        }
        if (root.right != null) {
            minH = Math.min(minDepth00(root.right), minH);
        }
        return minH + 1;

    }


}
