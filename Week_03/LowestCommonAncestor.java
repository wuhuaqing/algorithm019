package leetcode.week03;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 236. 二叉树的最近公共祖先  https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {
        TreeNode node = BuildTree.getBuildTree();
        LowestCommonAncestor lowestCommonAncestor = new LowestCommonAncestor();
        TreeNode lcp = lowestCommonAncestor.lowestCommonAncestor(node, new TreeNode(9), new TreeNode(4));
        if (lcp != null) {
            System.out.println(lcp.val);
        }
    }


    HashMap<Integer, TreeNode> parentMap = new HashMap<>();
    HashSet<Integer> parentSet = new HashSet<>();

    /**
     * 通过存储父节点一级一级往上找
     * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/er-cha-shu-de-zui-jin-gong-gong-zu-xian-by-leetc-2/
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return root;
        }
        //存储所有节点的父节点
        saveNodeParent(root);
        return getLCP(p, q);
    }

    public TreeNode getLCP(TreeNode pNode, TreeNode q) {
        while (pNode != null) {
            //将父节点元素值放入集合
            parentSet.add(pNode.val);
            //通过当前节点元素取它的父节点
            TreeNode treeNode = parentMap.get(pNode.val);
            //将父节点赋给当前节点，接着往上获取父节点
            pNode = treeNode;
        }
        while (q != null) {
            // set中包含 q, q为父节点，则返回q
            if (parentSet.contains(q.val)) {
                return q;
            }
            //如果当前节点不在set中，从父集合中取q的父集合 接着遍历
            q = parentMap.get(q.val);
        }
        return null;
    }


    public void saveNodeParent(TreeNode node) {
        if (node.left != null) {
            parentMap.put(node.left.val, node);
            saveNodeParent(node.left);
        }
        if (node.right != null) {
            parentMap.put(node.right.val, node);
            saveNodeParent(node.right);
        }
    }
}
