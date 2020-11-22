package leetcode.week03;

public class TreeNode {

    public int val ;
    public TreeNode left;
    public TreeNode right;

    public TreeNode( ) {
        this.left = null;
        this.right = null;
    }
    public TreeNode(int value) {
        this.val = value;
    }

    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.val = value;
        this.left = left;
        this.right = right;
    }
}
