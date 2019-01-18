package com.github.tomdican.program.linkedlist;

public class Node {

    public static void main(String[] args) {
        Node head = new Node(50);
        head.next = new Node(20);
        head.next.next = new Node(15);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(10);
        head.next.next.next.next.next = head.next.next;


      //  detectAndRemoveLoop(head);
        detectAndRemoveLoop2(head);

        printList(head);
    }

    static void printList(Node node) {
        while (node != null) {
            System.out.print(node.key + ",");
            node = node.next;
        }
        System.out.println("");
    }

    /**
     * So if we start moving both pointers again at same speed such that one pointer (say slow) begins
     * from head node of linked list and other pointer (say fast) begins from meeting point.
     *
     *
     Distance traveled by fast pointer = 2 * (Distance traveled
     by slow pointer)

     (m + n*x + k) = 2*(m + n*y + k)

     Note that before meeting the point shown above, fast
     was moving at twice speed.

     x -->  Number of complete cyclic rounds made by
     fast pointer before they meet first time

     y -->  Number of complete cyclic rounds made by
     slow pointer before they meet first time


     m + k = (x-2y)*n
     Which means m+k is a multiple of n.
     *
     * @param node
     */
    private static void detectAndRemoveLoop2(Node node) {
        if (node == null || node.next == null)
            return;

        Node slow = node, fast = node;

        slow = slow.next;
        fast = fast.next.next;

        while (fast != null && fast.next != null) {
            if (slow == fast)
                break;

            slow = slow.next;
            fast = fast.next.next;
        }

        if (slow == fast) {
            slow = node;
            while (slow.next != fast.next) {
                slow = slow.next;
                fast = fast.next;
            }

            fast.next = null; /* remove loop */
        }
    }

    /**
     *  Detect Loop using Floyd’s Cycle detection algo
     *  remove loop
     *  method 1
     *
     *  source: https://www.geeksforgeeks.org/detect-and-remove-loop-in-a-linked-list/
     *
     * @param node
     */
    private static void detectAndRemoveLoop(Node node) {
        Node slow = node, fast = node;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                removeLoop(slow, node);
                return ;
            }
        }
    }

    private static void removeLoop(Node loop, Node head) {
        Node ptr1 = loop;
        Node ptr2 = loop;

        int k = 1, i;
        while (ptr1.next != ptr2) {
            ptr1 = ptr1.next;
            k++;
        }

        ptr1 = head;
        ptr2 = head;
        for (i = 0; i < k; i++) {
            ptr2 = ptr2.next;
        }

        while (ptr2 != ptr1) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        ptr2 = ptr2.next;
        while (ptr2.next != ptr1) {
            ptr2 = ptr2.next;
        }

        ptr2.next = null;
    }

    int key;
    Node next;
    Node(int key) {
        this.key = key;
    }
}
