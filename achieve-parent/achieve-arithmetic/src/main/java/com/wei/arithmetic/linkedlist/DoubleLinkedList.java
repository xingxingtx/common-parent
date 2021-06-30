package com.wei.arithmetic.linkedlist;

import java.util.NoSuchElementException;

/**
 * @Describe 双向链表
 * @Author wei.peng
 * @Date 2021年06月10日
 */
public class DoubleLinkedList<E> {

    private int size = 0;
    private Node first;
    private Node last;
   
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    public E remove() {
        Node<E> first = this.first;
        if(first == null){
            throw new NoSuchElementException();
        }
        return unlinkElement(first);
    }

    private E unlinkElement(Node<E> node) {
        E element = node.data;
        if(node == first){}
        size--;
        return element;
    }

    public int getSize() {
        return size;
    }

    private void linkLast(E e) {
        Node lastNode = this.last;
        Node newNode = new Node(e);
        //未初始化first节点
        if(lastNode == null){
            first = newNode;
            first.next = newNode;
            last = newNode;
            last.pre = first;
        }else {//已经初始化first节点
            lastNode.next = newNode;
            newNode.pre = lastNode;
            last = newNode;
        }
        size++;
    }

    private class Node<E> {
         E data;
         Node<E> pre;
         Node<E> next;

        public Node(E data) {
            this.data = data;
        }
        public Node(E data, Node<E> pre,Node<E> next) {
            this.data = data;
            this.pre = pre;
            this.next = next;
        }

    }
}
