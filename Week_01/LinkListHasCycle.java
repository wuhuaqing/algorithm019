package leetcode.week01;

import java.util.HashSet;
import java.util.List;

/**
 * 141 环形列表
 */
public class LinkListHasCycle {


    public static void main(String[] args) {

    }

    //------------第一遍 2020/11/7------------------------

    /**
     * 使用hashset做容器，如果容器中存在此node，代表是环形链表
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        HashSet<ListNode> sets = new HashSet<>();
        while (head != null) {
            if (sets.contains(head)) {
                return true;
            }
            sets.add(head);
            head = head.next;
        }

        return false;
    }

    /**
     * 快慢指针法
     * @param head
     * @return
     */
    public boolean hasCycle00(ListNode head){
        if(head == null || head.next == null){
            return false;
        }
        ListNode slowNode =  head;
        ListNode fastNode =  head.next;
        //当循环条件是等于，快指针追上慢指针，代表链表有环
        while (slowNode!=fastNode){
            //如果快指针为空 或者 快指针的下一个节点为空，则代表没有环
            if(fastNode == null || fastNode.next == null){
                return false;
            }
            slowNode =  slowNode.next;
            fastNode =  fastNode.next.next;
        }
        return true;

    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}