package leetcode.week02;

import java.util.ArrayList;
import java.util.List;

/**
 * 144 前序遍历 https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 */
public class PreorderTraversal {



    public List<Integer> preorderTraversal(TreeNode root){
           List<Integer> lists = new ArrayList<>();
           if(root == null){
               return lists;
           }

           if(root!=null){
               lists.add(root.val);
               reverNode(root.left,lists);
               reverNode(root.right,lists);
           }
         return lists ;
    }


    private void reverNode(TreeNode node,List<Integer> lists){
        if(node!=null){
            lists.add(node.val);
            reverNode(node.left,lists);
            reverNode(node.right,lists);
        }
    }



    //----------第一二遍 2020/11/11 -----------------

    /**
     * 前序遍历的要求是：根在前面遍历 顺序是： 根节点 -  左子树  —— 右子树
     */

    public List<Integer> preorderTraversal00(TreeNode root) {
        if (root == null) {
            return new ArrayList();
        }
        List<Integer> list = new ArrayList<>();

        if (root != null) {
            list.add(root.val);
            reverNode00(root.left, list);
            reverNode00(root.right, list);
        }

        return list;

    }

    public void reverNode00(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            reverNode00(root.left, list);
            reverNode00(root.right, list);
        }
    }
}
