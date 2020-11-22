package leetcode.week03;

/**
 * 226. 翻转二叉树  https://leetcode-cn.com/problems/invert-binary-tree/description/
 */
public class InvertTree {

    public static void main(String[] args) {

    }


    // 第三遍 2020/11/20

    public TreeNode invertTree(TreeNode root){
        if(root!=null){
           TreeNode temp = root.left;
           root.left =  invertTree(root.right);
           root.right = invertTree(temp);
        }
        return root;
    }


    public TreeNode invertTree01(TreeNode root){
        if(root!=null){
            TreeNode temp = root.left;
            root.left=   root.right;
            root.right = temp;
            //翻转左子树
            invertTree01(root.left);
            // 翻转右子树
            invertTree01(root.right);
        }
       return root;
    }



    public TreeNode invertTree00(TreeNode root) {
        if (root == null) {
            return root;
        }
        if (root != null) {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;

            invertTree00(root.left);
            invertTree00(root.right);
        }

       return root;

    }
}
