package leetcode.week03;

import java.util.ArrayList;
import java.util.List;

/**
 * 98 验证二叉搜索树
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 * <p>
 * https://leetcode-cn.com/problems/validate-binary-search-tree/solution/yan-zheng-er-cha-sou-suo-shu-by-leetcode-solution/
 */
public class IsValidBST {

    //第三遍


    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        return  isValidBST(root,  null,  null);
    }

    private boolean isValidBST(TreeNode root, Integer leftLow, Integer rightUp){
        if(root == null){
            return true;
        }
        int val =  root.val;
        if(leftLow!= null && leftLow >= val){
            return false;
        }
        if(rightUp!= null && rightUp<=val){
            return false;
        }

        return isValidBST(root.left,leftLow,val) && isValidBST(root.right,val,rightUp) ;
    }

    /**
     * 二叉搜索树的中序遍历数组 是正序数组，如果数组中后一个元素大于前一个元素，则表明不是二叉搜索树
     * @param root
     * @return
     */
    public boolean isValidBST01(TreeNode root) {
        if (root == null) {
            return true;
        }

        List<Integer> list = new ArrayList<>();
        inOrderTree(root, list);
        for (int index = 0; index < list.size(); index++) {
            if (index != 0 && list.get(index) <= list.get(index - 1)) {
                return false;
            }
        }
        return true;
    }

    public void inOrderTree(TreeNode node, List<Integer> list) {
        if (node != null) {
            inOrderTree(node.left, list);
            list.add(node.val);
            inOrderTree(node.right, list);
        }
    }


    // 第一二遍

    public boolean isValidBST00(TreeNode root) {

        return isValidBST00(root, null, null);

    }

    /**
     * 使用递归的方式调用，不能只判断左右子节点，应该是左右子树
     *
     * @param node
     * @param low
     * @param up
     * @return
     */
    public boolean isValidBST00(TreeNode node, Integer low, Integer up) {
        if (node == null) {
            return true;
        }
        int nodeValue = node.val;
        //左节点值大于等于根节点值，不是二叉搜索树
        if (low != null && low >= nodeValue) {
            return false;
        }
        //右节点值小于等于根节点值，不是二叉搜索树
        if ((up != null && nodeValue >= up)) {
            return false;
        }
        //递归查询右子树是否满足条件
        if (!isValidBST00(node.right, nodeValue, up)) {
            return false;
        }
        //递归查询左子树是否满足条件
        if (!isValidBST00(node.left, low, nodeValue)) {
            return false;
        }

        return true;

    }
}
