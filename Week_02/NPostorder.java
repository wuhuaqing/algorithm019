package leetcode.week02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 590. N叉树的后序遍历
 * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
 */
public class NPostorder {


    // ------------ 第三遍 2020/11/13 --------------

    /**
     * 使用栈先入后出的特性 + linkedlist 头插法  配合得到N叉树后序遍历值
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {

        LinkedList<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node pop = stack.pop();
            //头插法，后面的元素排第一位
            list.addFirst(pop.val);
            if(pop.children!=null){
                for (Node item :   pop.children) {
                    if(item!=null){
                        stack.push(item);
                    }
                }
            }
        }
        return  list;

    }


    /**
     * 递归完成N叉树 的后序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> postorder02(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        for (int index = 0; index < root.children.size(); index++) {
            reverNode(root.children.get(index), list);
        }
        list.add(root.val);

        return list;
    }


    private void reverNode(Node node, List<Integer> list) {
        if (node != null) {
            for (int index = 0; index < node.children.size(); index++) {
                reverNode(node.children.get(index), list);
            }
            list.add(node.val);
        }

    }


// ------------ 第一二遍 2020/11/12 --------------

    /**
     * 使用栈先入后出的特性 + linkedlist 头插法  配合得到N叉树后序遍历值
     *
     * @param root
     * @return
     */
    public List<Integer> postorder01(Node root) {
        if (root == null) {
            return new ArrayList();
        }
        Stack<Node> stack = new Stack<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        //将根节点压入栈
        stack.push(root);
        while (!stack.isEmpty()) {
            //弹出栈顶元素
            Node pop = stack.pop();
            //将元素值插入链表列表第一位
            linkedList.addFirst(pop.val);
            if (pop.children != null) {
                for (int index = 0; index < pop.children.size(); index++) {
                    Node node = pop.children.get(index);
                    if (node != null) {
                        stack.push(node);
                    }
                }
            }

        }
        return linkedList;
    }

    /**
     * 使用递归完成N叉树的后序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> postorder00(Node root) {
        if (root == null) {
            return new ArrayList();
        }
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            for (int i = 0; i < root.children.size(); i++) {
                reverNode00(root.children.get(i), list);
            }
            list.add(root.val);
        }
        return list;

    }


    public void reverNode00(Node root, List<Integer> list) {
        if (root != null) {
            for (int i = 0; i < root.children.size(); i++) {
                reverNode00(root.children.get(i), list);
            }
            list.add(root.val);

        }
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}