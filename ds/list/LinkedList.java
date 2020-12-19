package ds.list;

public class LinkedList<T> {
    public static class ListNode<T> {
        T val;
        ListNode<T> next;
        ListNode<T> prev;

        public ListNode(T val) {
            this.val = val;
        }
    }

    ListNode<T> front = null;
    ListNode<T> rear = null;
    int size = 0;

    public void add(T val) {
        ListNode<T> newNode = new ListNode<>(val);
        size++;

        if (front == null) {
            front = rear = newNode;
            return;
        }

        rear.next = newNode;
        newNode.prev = rear;
        rear = rear.next;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        ListNode<T> listPtr = front;

        while (listPtr != null) {
            if (index == 0)
                break;
            index--;
            listPtr = listPtr.next;
        }

        return listPtr.val;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            return;
        }

        // User requests to remove last list element
        if (index == size - 1) {
            removeLast();
            return;
        }

        size--;

        // User requests to remove the only list element
        if (size == 0) {
            rear = front = null;
            return;
        }

        // Remove the front element
        if (index == 0) {
            front = front.next;
            front.prev = null;
            return;
        }

        ListNode<T> listPtr = front;
        ListNode<T> prevPtr = listPtr;

        while (listPtr != null) {
            if (index == 0)
                break;
            index--;
            prevPtr = listPtr;
            listPtr = listPtr.next;
        }

        prevPtr.next = listPtr.next;
        listPtr.next.prev = prevPtr;
    }

    public void removeLast() {
        if (rear == null) {
            return;
        }

        size--;

        if (front.next == null) {
            rear = front = null;
            return;
        }

        rear = rear.prev;
        rear.next = null;
    }

    public int size() {
        return size;
    }

    public String toString() {
        if (size == 0)
            return "[]";

        ListNode<T> listPtr = front;
        StringBuilder str = new StringBuilder();
        str.append('[');

        while (listPtr != null) {
            str.append(listPtr.val + ", ");
            listPtr = listPtr.next;
        }

        str.deleteCharAt(str.length() - 1);
        str.deleteCharAt(str.length() - 1);
        str.append(']');

        return str.toString();
    }

    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (ListNode<T> x = front; x != null; x = x.next)
            result[i++] = x.val;
        return result;
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
    }
}
