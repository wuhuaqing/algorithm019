package leetcode.week02;

import java.util.*;

/**
 * 589. N叉树的前序遍历
 * https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
 */
public class NPreorder {

    // ------------ 第三遍 2020/11/12 --------------
    /**
     * 栈 + 链表列表 配合使用
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {

        LinkedList<Integer> linkedList = new LinkedList<>();
        if(root == null){
             return linkedList ;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node pop = stack.pop();
            linkedList.add(pop.val);
            if(pop.children!=null){
                //从后往前遍历
                for (int j = pop.children.size()-1;j>=0;j--) {
                    Node item =  pop.children.get(j);
                    if(item!=null){
                        stack.push(item);
                    }
                }

            }
        }

        return linkedList;

    }


    // ------------ 第一二遍 2020/11/12 --------------

    /**
     * 栈 + 链表列表 配合使用
     * @param root
     * @return
     */
    public List<Integer> preorder02(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Stack<Node> stack = new Stack<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            //每弹出一个节点，将子节点列表反转顺序
            Collections.reverse(pop.children);
            for (int index = 0; index < pop.children.size(); index++) {
                Node node = pop.children.get(index);
                if (node != null) {
                    stack.push(node);
                }
            }
            //将数据一个一个往后加
            linkedList.add(pop.val);
        }
        return linkedList;
    }

    /**
     * 递归求解N叉树
     *
     * @param root
     * @return
     */
    public List<Integer> preorder00(Node root) {

        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        for (int index = 0; index < root.children.size(); index++) {
            reverNode(root.children.get(index), list);
        }
        return list;

    }

    private void reverNode(Node root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            for (int i = 0; i < root.children.size(); i++) {
                reverNode(root.children.get(i), list);
            }
        }
    }


}
