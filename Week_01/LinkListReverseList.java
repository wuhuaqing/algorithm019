package leetcode.week01;

import java.util.ArrayList;
import java.util.List;

/**
 * 206 反转链表
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * <p>
 * 题解：https://leetcode-cn.com/problems/reverse-linked-list/solution/dong-hua-yan-shi-206-fan-zhuan-lian-biao-by-user74/
 */
public class LinkListReverseList {
    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        ListNode node4 = new ListNode(4);
        node3.next = node4;
        ListNode node5 = new ListNode(5);
        node4.next = node5;

        LinkListReverseList linkListReverseList = new LinkListReverseList();
        linkListReverseList.reverseList(node);
    }

    //————————————————————2020/11/7--------------------------

    //有一种遍历解法，还不太理解

    /**
     * 使用双指针（一一翻转）+ 临时链 实现链表翻转
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        //当前节点
        ListNode current = head.next;
        //当前节点的下一个节点的临时链
        ListNode tempNode = null;
        //首节点给前节点变量
        ListNode pre = head;
        //切断第一个节点后面的链接
        head.next = null;

        while (current.next != null) {
            //将下一个节点地址给临时节点
            tempNode = current.next;
            //将当前节点指向前一节点
            current.next = pre;
            //将前一节点的位置往后挪
            pre = current;
            //将当前节点往后挪一位
            current = tempNode;
        }
        //将最后一个节点接着反转过的链表前面
        current.next = pre;
        return current;



             /* 上述思路精炼写法

              ListNode prev = null;
              ListNode curr = head;
              while (curr != null) {
                    ListNode nextTemp = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = nextTemp;
                }
                return prev;

             */


    }

    /***
     * 耗时太长
     * @param head
     * @return
     */
    public ListNode reverseList00(ListNode head) {
        if (head == null) {
            return null;
        }
        //使用容器装载节点，然后反转遍历复值
        ArrayList<ListNode> listNodes = new ArrayList<>();
        while (head != null) {
            ListNode node = new ListNode(head.val);
            listNodes.add(node);
            head = head.next;
        }

        //此种做法，listNodes 0～size-2 的元素都是链表不全的废数据 debug查看便知
        for (int index = listNodes.size() - 1; index >= 0; index--) {
            if (index > 0) {
                listNodes.get(index).next = listNodes.get(index - 1);
            }
        }

        return listNodes.get(listNodes.size() - 1);

    }
}
