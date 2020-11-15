package leetcode.week02;

import sun.tools.jconsole.inspector.XNodeInfo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * 102. 二叉树的层序遍历
 * 二叉树层序遍历 （广度优先）
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 */
public class LevelOrder {


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
}
