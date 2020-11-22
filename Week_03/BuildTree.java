package leetcode.week03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 105. 从前序与中序遍历序列构造二叉树  https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * <p>
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * [3,9,20,null,null,15,7]
 */
public class BuildTree {

    public static TreeNode getBuildTree(){
        int[] preorder = new int[]{5, 8, 7, 4, 17, 12, 9, 2, 10};
        int[] inorder = new int[]{7, 8, 17, 4, 12, 5, 2, 9, 10};
        return new BuildTree().buildTree(preorder, inorder);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList();
        }
        List<Integer> list = new ArrayList<>();

        if (root != null) {
            list.add(root.val);
            reverNode(root.left, list);
            reverNode(root.right, list);
        }

        return list;

    }

    public void reverNode(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            reverNode(root.left, list);
            reverNode(root.right, list);
        }
    }

    public static void main(String[] args) {
        BuildTree buildTree = new BuildTree();
//        int[] preorder = new int[]{3, 9, 20, 15, 7};
//        int[] inorder = new int[]{9, 3, 15, 20, 7};

        int[] preorder = new int[]{5, 8, 7, 4, 17, 12, 9, 2, 10};
        int[] inorder = new int[]{7, 8, 17, 4, 12, 5, 2, 9, 10};
        TreeNode node = buildTree.buildTree(preorder, inorder);
        if (node != null) {
            List<Integer> integers = buildTree.preorderTraversal(node);
            for (Integer numb :
                    integers) {
                System.out.print(numb + " ");
            }
        }


    }


    // 第一二遍 2020/11/20

    private HashMap<Integer, Integer> map;

    /**
     * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/cong-qian-xu-yu-zhong-xu-bian-li-xu-lie-gou-zao-9/
     * 先序遍历的第一个元素是根节点
     * 根据先序遍历的根节点找到中序遍历的角标，确定中序遍历中左子树的个数，确定中序遍历右子树的个数
     * 递归构造二叉树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        map = new HashMap<>();
        for (int index = 0; index < inorder.length; index++) {
            map.put(inorder[index], index);
        }
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTree(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight) {
        //第一步 递归终止条件
        if (preLeft > preRight) {
            return null;
        }
        // 第二步 当前层逻辑处理

        //前序遍历数组第一位是根节点
        int rootVal = preorder[preLeft];

        //得到中序遍历中根节点所在的角标
        Integer inRootIndex = map.get(rootVal);

        int leftCount = inRootIndex - inLeft;

        TreeNode root = new TreeNode(rootVal);

        // 第三步 传入参数开启下一层
        //左子树需要的先序，中序角标
        root.left = buildTree(preorder, preLeft + 1, preLeft + leftCount, inorder, inLeft, inRootIndex - 1);
        //右子树需要的先序，中序角标
        root.right = buildTree(preorder, preLeft + leftCount + 1, preRight, inorder, inRootIndex + 1, inRight);

        // 清理本层状态 （没有）

        return root;
    }


    /**
     * 通过前序遍历得到根节点元素
     * 通过中序遍历得到左子树在数组中的区间，右子树在数组中的区间
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree00(int[] preorder, int[] inorder) {


        map = new HashMap<>();
        for (int index = 0; index < inorder.length; index++) {
            map.put(inorder[index], index);
        }
        int length = preorder.length;
        return buildTree00(preorder, inorder, 0, length - 1, 0, length - 1);


    }

    /**
     * @param preorder       先序遍历数组
     * @param inorder        中序遍历数组
     * @param preOrder_left  先序遍历数组第一个元素角标
     * @param preOrder_right 先序遍历数组最后一个元素角标
     * @param inOrder_left   中序遍历数组第一个元素角标
     * @param inOrder_right  中序遍历数组最后以个元素角标
     * @return
     */
    public TreeNode buildTree00(int[] preorder, int[] inorder, int preOrder_left, int preOrder_right, int inOrder_left, int inOrder_right) {
        if (preOrder_left > preOrder_right) {
            return null;
        }

        int preRootIndex = preOrder_left;

        //得到根节点的在中序数组的角标
        Integer inRootIndex = map.get(preorder[preRootIndex]);

        //构建根节点
        TreeNode root = new TreeNode(preorder[preRootIndex]);

        //左子树元素个数
        int leftTD = inRootIndex - inOrder_left;

        // 先序遍历数组左子树第一个元素角标 ，先序遍历数组左子树最后一个元素角标   中序号遍历数组左子树第一个元素角标  中序号遍历数组左子树最后一个元素角标

        root.left = buildTree00(preorder, inorder, preOrder_left + 1, preOrder_left + leftTD, inOrder_left, inRootIndex- 1);

        // 先序遍历数组右子树第一个元素角标 ，先序遍历数组右子树最后一个元素角标   中序号遍历数组右子树第一个元素角标  中序号遍历数组右子树最后一个元素角标

        root.right = buildTree00(preorder, inorder, preOrder_left + leftTD + 1, preOrder_right, inRootIndex + 1, inOrder_right);


        return root;
    }


}
