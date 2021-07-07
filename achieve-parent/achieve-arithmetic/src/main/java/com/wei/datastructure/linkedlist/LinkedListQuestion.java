package com.wei.datastructure.linkedlist;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年06月10日
 */
public class LinkedListQuestion {

    /*public static void main(String[] args) {
        //questionOne -- test--start
        SinglyLinkedList wNode = new SinglyLinkedList(5);
        SinglyLinkedList<Integer> node = new SinglyLinkedList(3);
        node.add(new SinglyLinkedList(7));
        node.add(wNode);
        node.add(new SinglyLinkedList(8));
        node.add(new SinglyLinkedList(5));
        node.remove(wNode);
        SinglyLinkedList<Integer> node2 = new SinglyLinkedList(3);
        node2.add(new SinglyLinkedList(4));
        node2.add(new SinglyLinkedList(5));
        node2.add(new SinglyLinkedList(7));
        node2.add(new SinglyLinkedList(7));
        SinglyLinkedList wayNode = questionOne(node, node2);
        while (wayNode != null){
            System.out.println(wayNode.data);
            wayNode = wayNode.next;
        }
        //questionOne -- test--end
    }

    *//**
     * 给定两个int类型链表，进行先加
     * example: intPut:3-7-8-5 + 3-4-5-7
     * outPut:6-1-4-3-1
     *//*
    public static SinglyLinkedList questionOne(SinglyLinkedList<Integer> node1, SinglyLinkedList<Integer> node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }
        SinglyLinkedList<Integer> head = new SinglyLinkedList<>(null);
        SinglyLinkedList pre = head;
        int carry = 0;
        while (node1 != null || node2 != null) {
            //是否相加进 1
            int sum = (node1 == null ? 0: node1.data == null ? 0 : node1.data) +
                    (node2 == null ? 0 : node2.data == null ? 0 : node2.data) + carry;
            carry = sum / 10;
            int var = sum % 10;
            pre.next = new SinglyLinkedList(var);
            pre = pre.next;
            if (node1 != null) {
                node1 = node1.next;
            }
            if(node2 != null){
                node2 = node2.next;
            }
        }
        if(carry > 0){
            pre.next = new SinglyLinkedList(1);
        }
        return head.next;
    }*/
}
