package com.wei.datastructure.head;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @Describe 堆
 * 堆通常是一个可以被看做一棵树，它满足下列性质：
 *             78                         78
 *           /    \                     /    \
 * *        66     67                  79     90
 *         /  \    /  \              /  \    /  \
 *        34  45  35  22           88  87   91  99
 *     / \  / \   /\    / \
 *   23 21 40 43 33 31 20 19
 *             最大堆                    最小堆
 * [性质一] 堆中任意节点的值总是不大于(不小于)其子节点的值；
 * [性质二] 堆总是一棵完全树。
 * 将任意节点不大于其子节点的堆叫做最小堆或小根堆，而将任意节点不小于其子节点的堆叫做最大堆或大根堆。常见的堆有二叉堆、左倾堆、斜堆、二项堆、斐波那契堆等等。
 * 二叉堆的定义
 * 二叉堆是完全二元树或者是近似完全二元树，它分为两种：最大堆和最小堆。
 * 最大堆：父结点的键值总是大于或等于任何一个子节点的键值；最小堆：父结点的键值总是小于或等于任何一个子节点的键值。
 * 假设"第一个元素"在数组中的索引为 0 的话，则父节点和子节点的位置关系如下：
 * (01) 索引为i的左孩子的索引是 (2*i+1);
 * (02) 索引为i的右孩子的索引是 (2*i+2);
 * (03) 索引为i的父结点的索引是 floor((i-1)/2);
 * @Author wei.peng
 * @Date 2021年07月13日
 */
public class HeadDataStructure<T> implements Serializable {

    /**
     * 数组存储数据
     */
    private Object[] elementData;

    /**
     * 元素总数
     */
    private int size = 0;

    /**
     * 是否是最大堆
     */
    private final boolean isMaxHead;

    /**
     * 默认初始数组大小
     */
    private static final int DEFAULT_CAPACITY = 16;

    /**
     * 比较器
     */
    private final Comparator<T> comparator;


    public HeadDataStructure(Comparator<T> comparator) {
        elementData = new Object[DEFAULT_CAPACITY];
        this.comparator = comparator;
        this.isMaxHead = true;
    }

    public HeadDataStructure() {
        elementData = new Object[DEFAULT_CAPACITY];
        this.comparator = null;
        this.isMaxHead = true;
    }

    public HeadDataStructure(Comparator<T> comparator, int size) {
        elementData = new Object[size];
        this.comparator = comparator;
        this.isMaxHead = true;
    }

    public HeadDataStructure(int size) {
        elementData = new Object[size];
        this.comparator = null;
        this.isMaxHead = true;
    }

    public HeadDataStructure(Comparator<T> comparator, boolean isMaxHead) {
        elementData = new Object[DEFAULT_CAPACITY];
        this.comparator = comparator;
        this.isMaxHead = isMaxHead;
    }

    public HeadDataStructure(boolean isMaxHead) {
        elementData = new Object[DEFAULT_CAPACITY];
        this.comparator = null;
        this.isMaxHead = isMaxHead;

    }

    public HeadDataStructure(Comparator<T> comparator, int size, boolean isMaxHead) {
        elementData = new Object[size];
        this.comparator = comparator;
        this.isMaxHead = isMaxHead;
    }

    public HeadDataStructure(int size, boolean isMaxHead) {
        elementData = new Object[size];
        this.comparator = null;
        this.isMaxHead = isMaxHead;
    }

    /**
     * 添加元素
     * @param element
     */
    public void add(T element) {
        if (element == null) {
            throw new NullPointerException("not allow add null element");
        }
        T t = get(0);
        if (t == null) {
            //初始化初始节点
            elementData[0] = element;
        } else {
            if (size() + 1 > elementData.length) {
                //扩容
                dilatationArray();
            }
            if(isContains(element)){
                set(element);
                return;
            }
            elementData[size] = element;
            fixAfterInsertion(element);
        }
        size++;
    }

    public T set(T element) {

        return null;
    }

    public T set(int index, T element) {
        if(index < 0 || index > size()){
            throw new IndexOutOfBoundsException("set index out of zhe data length");
        }

        return null;
    }

    public boolean addAll(T[] element) {
        if (element == null || element.length == 0) {
            return false;
        }

        return true;
    }


    /**
     * 移除指定元素
     * @param element
     * @return
     */
    public boolean remove(T element) {
        int index = getElementIndex(element);
        if(index == -1){
            return false;
        }
        return remove(index);
    }


    /**
     * 移除指定位置上的元素
     * @param index
     * @return
     */
    public boolean remove(int index) {
        if(index < 0 || index > size()){
            throw new IndexOutOfBoundsException("remove index out of zhe data length");
        }

        return true;
    }

    /**
     * 是否包含 element元素
     *
     * @param element
     * @return
     */
    public boolean isContains(T element) {
        if (element == null) {
            return false;
        }
        int index = findElement(element, 0);
        return index >= 0 ? true : false;
    }

    /**
     * 获取指定元素下表位置
     *
     * @param element
     * @return
     */
    public int getElementIndex(T element) {
        if (element == null) {
            return -1;
        }
        return findElement(element, 0);
    }


    /**
     * 将新插入的数据调整位置，使其满足堆特性
     */
    private void fixAfterInsertion(T element) {
        int addIndex = size;
        //与父节点比较大小
        int index = (size - 1) >> 1;
        while ((isMaxHead && compareTwoElement(element, get(index)) == 1)
                || (!isMaxHead && compareTwoElement(element, get(index)) == -1)) {
            elementData[addIndex] = elementData[index];
            elementData[index] = element;
            addIndex = index;
            if (index == 0) {
                break;
            }
            index = (index - 1) >> 1;
        }
    }

    private int findElement(T element, int index) {
        if (index > size - 1) {
            return -1;
        }
        //比较父节点
        int isParent = compareTwoElement(element, get(index));
        if (isParent == 0) {
            return index;
        }
        //比较左节点
        if (2 * index + 1 > size - 1) {
            return -1;
        }
        int isEqLeft = compareTwoElement(element, get(2 * index + 1));
        if (isEqLeft == 0) {
            return 2 * index + 1;
        }
        //比较右节点
        if (2 * index + 2 > size - 1) {
            return -1;
        }
        int isEqRight = compareTwoElement(element, get(2 * index + 2));
        //System.out.println("index = " + index + ";parent=" + get(index) + ";left=" + get(2 * index + 1) + "right=" + get(2 * index + 2));
        if (isEqRight == 0) {
            return 2 * index + 2;
        }
        int lf = -1;
        int rh = -1;
        //最大堆
        if (isMaxHead) {
            if (isParent == 1) {
                return -1;
            }
            if (isEqLeft == -1) {
                lf = findElement(element, 2 * index + 1);
            }
            if (isEqRight == -1) {
                rh = findElement(element, 2 * index + 2);
            }
        } else {
            //最小堆
            if (isParent == -1) {
                return -1;
            }
            if (isEqLeft == 1) {
                lf = findElement(element, 2 * index + 1);
            }
            if (isEqRight == 1) {
                rh = findElement(element, 2 * index + 2);
            }
        }
        if (lf != -1) {
            return lf;
        } else if (rh != -1) {
            return rh;
        }
        return -1;
    }


    /**
     * 拿到指定位置上的元素
     *
     * @param index
     * @return
     */
    public T get(int index) {
        if (index < 0 || index >= elementData.length) {
            throw new IndexOutOfBoundsException("index out of zhe data length");
        }
        return (T) elementData[index];
    }


    /**
     * 获取数据长度
     *
     * @return
     */
    public int size() {
        return size;
    }


    /**
     * 动态扩容数组大小
     */
    private void dilatationArray() {
        Object[] newData = new Object[elementData.length << 1];
        System.arraycopy(elementData, 0, newData, 0, elementData.length);
        elementData = newData;
    }


    /**
     * 比较两个元素大小
     *
     * @param e1
     * @param e2
     * @return -1:e1<e2  0:e1=e2  1:e1>e2
     */
    private int compareTwoElement(T e1, T e2) {
        int result;
        if (comparator != null) {
            result = comparator.compare(e1, e2);
        } else {
            result = ((Comparable<T> & Serializable) e1).compareTo(e2);
        }
        if (result > 0 && result != 1) {
            result = 1;
        }
        if (result < 0 && result != -1) {
            result = -1;
        }
        return result;
    }

    @Override
    public String toString() {
        return "HeadDataStructure{" +
                "data=" + Arrays.toString(elementData) +
                '}';
    }

    public static void main(String[] args) {
        HeadDataStructure<Integer> dataStructure = new HeadDataStructure<>(false);
        Integer[] re = new Integer[]{66, 33, 13, 34, 19, 14, 88, 22, 44, 77, 90, 31, 43, 10, 2, 12, 29, 55};
        for (int i = 0; i < re.length; i++) {
            dataStructure.add(re[i]);
        }
        System.out.println(dataStructure.toString());
        System.out.println("size == " + dataStructure.size);
        for (Integer integer : re) {
            System.out.print("   元素："+ integer + "--->"+ dataStructure.getElementIndex(integer));
        }
    }
}
