package com.wei.arithmetic.linkedlist;

/**
 * @Describe 单向链表
 * @Author wei.peng
 * @Date 2021年06月10日
 */
public class SinglyLinkedList<E> {
    private E data;
    private SinglyLinkedList next;
    private int size = 0;
    private SinglyLinkedList first;
    private SinglyLinkedList last;

    public SinglyLinkedList(E data) {
        this.data = data;
    }

    public boolean add(E data){
        SinglyLinkedList node = this;
        while (node != null){
            if(node.next == null){
                node.next = new SinglyLinkedList(data);
                break;
            }
            node = node.next;
        }
        return true;
    }

    public boolean remove(E e){
        return true;
    }

    public boolean remove(){
        return true;
    }

    private class Node<E>{
          E data;
          Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }
}
