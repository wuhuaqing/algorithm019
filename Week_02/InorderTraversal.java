package leetcode.week02;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.List;

/**
 * 94 中序遍历
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 */
public class InorderTraversal {


    //------------ 第三遍 2020/11/13 -----------

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> lists = new ArrayList<>();
        if(root == null){
            return lists;
        }

        if(root!=null){
            reverNode01(root.left,lists);
            lists.add(root.val);
            reverNode01(root.right,lists);
        }
        return lists;

    }

    public void reverNode01(TreeNode root, List<Integer> lists){
        if(root!=null){
            reverNode01(root.left,lists);
            lists.add(root.val);
            reverNode01(root.right,lists);
        }
    }



    //------------ 第一二遍 2020/11/11 -----------

    /**
     * 中序遍历的要求是：根在中间遍历 顺序是：左子树 -  根节点 —— 右子树
     */

    public List<Integer> inorderTraversal00(TreeNode root) {
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
}
