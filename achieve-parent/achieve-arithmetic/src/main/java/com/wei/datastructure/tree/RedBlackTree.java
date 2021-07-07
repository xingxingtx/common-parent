package com.wei.datastructure.tree;

import java.util.Comparator;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年07月01日
 */
public class RedBlackTree<E> {

    private TreeNode<E> parent;
    private int size;
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private final Comparator<? super E> comparator;

    public RedBlackTree(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    public RedBlackTree() {
        comparator = null;
    }

    public E add(E e) {
        TreeNode<E> t = this.parent;
        //first add element
        if (t == null) {
            
        }
        return null;
    }

    static class TreeNode<E> {
        private E element;
        private TreeNode<E> right;
        private TreeNode<E> left;
        private TreeNode<E> parent;
        boolean color = BLACK;

        public TreeNode(E element) {
            this.element = element;
        }

        public TreeNode(E element, TreeNode<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public E setElement(E element) {
            E oldElement = this.element;
            this.element = element;
            return oldElement;
        }
    }
}
