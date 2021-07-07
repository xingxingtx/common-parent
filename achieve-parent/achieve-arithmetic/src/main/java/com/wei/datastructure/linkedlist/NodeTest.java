package com.wei.datastructure.linkedlist;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年06月11日
 */
public class NodeTest {
    public static class Node<E>{
        public E data;
        private Node next;
        public Node(E data) {
            this.data = data;
        }
        public boolean add(E data){
            Node node = this;
            while (node != null){
                if(node.next == null){
                    node.next = new Node(data);
                    break;
                }
                node = node.next;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Node node = new Node(8);
        node.add(9);
        node.add(1);
        node.add(2);
        node.add(3);
        Node node1 = new Node(3);
        node1.add(2);
        node1.add(1);
        node1.add(4);
        node1.add(7);
        node1.add(9);
        Node node2 = merge(node1, node);
        Node node3 = merge(node1, node);
        while (node2 != null){
            System.out.print(node2.data + "->");
            node2 = node2.next;
        }
        Node node4 = reverseList(node3);
        System.out.println();
        while (node4 != null){
            System.out.print(node4.data + "->");
            node4 = node4.next;
        }
    }
    /**
     *
     * @param l1 8-9-1-2-3
     * @param l2 3-2-1-4-7
     * @return   1-2-3-6-0-1  32189+74123 =106321
     */
    public static Node merge(Node<Integer> l1, Node<Integer> l2){
        if(l1== null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }
        Node head = new Node(-1);
        Node next = head;
        int carry = 0;
        while (l1 != null || l2 != null){
            int sum = (l1 == null?0: l1.data) + (l2==null?0: l2.data) + carry;
            carry = sum / 10 ;
            int val = sum % 10;
            next.next = new Node(val);
            next = next.next;
            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }
        }
        if(carry > 0){
            next.next = new Node(1);
        }
        return head.next;
    }

    /**
     * outPut:1-2-3-6-0-1
     * input:1-0-6-3-2-1
     * @param head
     * @return
     *
     */
    public static Node reverseList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node next = head.next;
        Node new_head = reverseList(next);
        next.next = head;
        head.next = null;
        return new_head;
    }

}
