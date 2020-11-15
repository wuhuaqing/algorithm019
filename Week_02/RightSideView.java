package leetcode.week02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 199. 二叉树的右视图
 * https://leetcode-cn.com/problems/binary-tree-right-side-view/
 */
public class RightSideView {


    /**
     * 广度优先 层序遍历
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int index = 0; index < size; index++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    //最后一个元素代表右子树的节点
                    if(index == size -1 ){
                        lists.add(node.val);
                    }
                    //加入队列的节点不为空
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    //加入队列的节点不为空
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }
        }
        return lists;
    }

}
