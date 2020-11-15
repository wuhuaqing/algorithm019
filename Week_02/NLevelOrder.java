package leetcode.week02;

import java.util.*;

/**
 * 429. N叉树的层序遍历
 * https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
 * <p>
 * 层序遍历（广度优先）思路讲解
 */
public class NLevelOrder {

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


}
