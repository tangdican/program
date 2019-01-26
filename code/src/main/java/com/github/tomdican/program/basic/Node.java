package com.github.tomdican.program.basic;

import java.util.Stack;

public class Node {
    int value;
    Node next;
    Node(int value) {
        this.value = value;
    }

    public static void main(String[] args) {
        Node head = new Node(0);
        Node curr = head;
        for (int i = 1; i < 6; i++) {
            if (curr.next == null) {
                curr.next = new Node(i);
            }
            curr = curr.next;
        }

     //   reverseNode(head);
     //   reverseNode2(head);
//        reverseNode3(head);
        reverseNode4(head);
    }

    private static void reverseNode4(Node head) {
        if (head == null) {
            return;
        }

        Node pre = head.next;
        Node node = null;
        Node curr = head;
        while (curr != null) {
            node = curr.next;
            node.next = pre;
            pre = node;
            curr = curr.next;
        }


        while (node != null){
            System.out.print(","+node.value);
            node = node.next ;
        }
        System.out.println("");

    }

    private static void reverseNode3(Node head) {
        if (head == null) {
            return ;
        }

        Node pre = head;
        Node cur = head.next;
        Node next ;
        while(cur != null){
            next = cur.next;

            //链表的头插法
            cur.next = pre;
            pre = cur;

            cur = next;
        }
        head.next = null;

        Node node = pre;
        while (node != null){
            System.out.print(","+node.value);
            node = node.next ;
        }
        System.out.println("");

    }

    private static void reverseNode2(Node node) {
        if (node == null){
            return ;
        }

        if (node.next != null){
            reverseNode2(node.next) ;
        }
        System.out.print(node.value+"===>");
    }

    private static void reverseNode(Node node) {
        System.out.println("====翻转之前====");

        Stack<Node> stack = new Stack<>() ;
        while (node != null){

            System.out.print(node.value + "===>");

            stack.push(node) ;
            node = node.next ;
        }

        System.out.println("");

        System.out.println("====翻转之后====");
        while (!stack.isEmpty()){
            System.out.print(stack.pop().value + "===>");
        }
    }
}
