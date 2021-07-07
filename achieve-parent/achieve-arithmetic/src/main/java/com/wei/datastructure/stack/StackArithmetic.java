package com.wei.datastructure.stack;



import com.wei.utils.ArithmeticUtils;

import java.util.Stack;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年06月14日
 */
public class StackArithmetic {

    public static void main(String[] args) {
        //question one test --start
        int[] array = new int[]{6, 5, 5, 7, 2};
        System.out.println(rangeSumMax(array));
        //question one test --end

        //question two test --start
        StackQueue<Integer> stackQueque = new StackQueue<>();
        stackQueque.push(1);
        stackQueque.push(2);
        stackQueque.push(3);
        stackQueque.push(4);
        stackQueque.push(5);
        while (!stackQueque.isEmpty()){
            System.out.println(stackQueque.pop());
        }
        //question two test --end
    }


    /**
     * question 0ne
     * @param array
     * @return
     * @Describe 求一序列任意的区间组合的最大值(即为 ： 区间最小值乘于区间和)
     * 例如：6，3，7，2
     * [6]=6*6=36
     * [3]=3*3=9 ...
     * [6,3] = 3*(6+3)=27 ...
     * [6,3,7] = 3*(6+3+7)=48
     */
    public static int rangeSumMax(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        //获取该数组的区间和
        int[] intervalsSum = ArithmeticUtils.prefixIntervalsSum(array);
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            while (!stack.isEmpty() && array[i] < array[stack.peek()]) {
                int left = i;
                int right = i;
                int index = stack.pop();
                if (stack.isEmpty()) {
                    left = 0;
                } else {
                    left = index;
                }
                max = Math.max(max, array[index] * (intervalsSum[right] - intervalsSum[left]));
                System.out.println(max);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int left;
            int right = array.length;
            int index = stack.pop();
            if (stack.isEmpty()) {
                left = 0;
            } else {
                left = index;
            }
            max = Math.max(max, array[index] * (intervalsSum[right] - intervalsSum[left]));
            System.out.println(max);
        }
        return max;
    }

    /**
     * question two
     * 用栈实现队列的功能
     * @param <T>
     */
    public static class StackQueue<T> {
        private Stack<T> stackIn = new Stack();
        private Stack<T> stackOut = new Stack<>();

        public T push(T t) {
            return stackIn.push(t);
        }

        public T peek() {
            transfer();
            return stackOut.peek();
        }

        public T pop() {
            transfer();
            return stackOut.pop();
        }

        public boolean isEmpty() {
            if (stackIn.isEmpty() && stackOut.isEmpty()) {
                return true;
            }
            return false;
        }

        private void transfer(){
            if (isEmpty()) {
                return;
            }
            if (stackOut.isEmpty()) {
                while (!stackIn.isEmpty()) {
                    stackOut.push(stackIn.pop());
                }
            }
        }
    }
}
