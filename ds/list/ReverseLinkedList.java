package ds.list;

import ds.list.LinkedList.ListNode;

/**
 * Implementation of reversing a linked list
 */
public class ReverseLinkedList {

    /**
     * Reverse a linked list
     * @param list
     */
    public static void reverseLinkedList(LinkedList<Object> list) {
        if (list.front == null)
            return;
        if (list.front.next == null)
            return;

        ListNode<Object> listPtr = list.front.next;
        ListNode<Object> prevPtr = list.front;

        list.rear = listPtr;
        prevPtr.next = null;

        while (listPtr != null) {
            ListNode<Object> temp = listPtr.next;
            listPtr.next = prevPtr;
            prevPtr = listPtr;
            listPtr = temp;
        }

        list.front = prevPtr;
    }

    public static void main(String[] args) {
        LinkedList<Object> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(10);
        ReverseLinkedList.reverseLinkedList(list);
        System.out.println(list);
    }
}
