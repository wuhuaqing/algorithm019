package leetcode.week01;

import java.util.HashSet;

/**
 * 142 环形链表2
 * <p>
 * 快慢指针好的题解
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/142-huan-xing-lian-biao-ii-jian-hua-gong-shi-jia-2/
 */
public class LinkListDetectCycle {

  //------------第一遍 2020/11/7------------------------
    /**
     * 快慢指针，
     * 公式推导的结论：https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/142-huan-xing-lian-biao-ii-jian-hua-gong-shi-jia-2/
     * 当快慢指针相遇，就在环内，此时慢指针到入环口的距离就是起点到入环口的距离，新开一个真正和满指针一样的步伐，两个相遇点就是入环口
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slowNode = head.next;
        ListNode fastNode = head.next.next;

        while (fastNode != null && fastNode.next != null) {

            slowNode = slowNode.next;
            fastNode = fastNode.next.next;

            //如果快指针为空 或者 快指针的下一个节点为空，则代表没有环
            if (slowNode == fastNode) {
                ListNode preNode = head;
                while (preNode != slowNode) {
                    preNode = preNode.next;
                    slowNode = slowNode.next;
                }
                return preNode;
            }
        }
        return null;

    }

    /**
     * 使用hashset做容器，如果容器中存在此node，代表是环形链表
     *
     * @param head
     * @return
     */
    public ListNode detectCycle00(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        HashSet<ListNode> sets = new HashSet<>();

        while (head != null) {
            if (sets.contains(head)) {
                return head;
            }
            sets.add(head);
            head = head.next;
        }
        return null;

    }
}
