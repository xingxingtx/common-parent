package com.wei.arithmetic.base;

import com.wei.arithmetic.sort.Sort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Describe 插入排序
 * @Author wei.peng
 * @Date 2021年05月05日
 */
public class InsertSort implements Sort {

    private static Logger log = LoggerFactory.getLogger(InsertSort.class);

    /**
     * 插入排序
     *
     * @param array 排序数组
     * @param order -1 降序，其余升序
     * 1)原理：利用插入法对无序数组排序时，我们其实是将数组R划分成两个子区间R[1．．i-1]（已排好序的有序区）和R[i．．n]（当前未排序的部分，
     *可称无序区）。插入排序的基本操作是将当前无序区的第1个记录R[i]插人到有序区R[1．．i-1]中适当的位置上，使R[1．．i]变为新的有序区。
     * 因为这种方法每次使有序区增加1个记录，通常称增量法。
     * 2)步骤:
     * 1、以数组的某一位作为分隔位，比如index=1，假设左面的都是有序的
     * 2、将index位的数据拿出来，放到临时变量里，这时index位置就空出来了.
     * 3、从leftindex=index-1开始将左面的数据与当前index位的数据（即temp）进行比较，如果array[leftindex]>temp,
     * 则将array[leftindex]后移一位，即array[leftindex+1]=array[leftindex],此时leftindex就空出来了
     * 4、再用index-2(即leftindex=leftindex-1)位的数据和temp比，重复步骤3，
     * 直到找到<=temp的数据或者比到了最左面（说明temp最小），停止比较，将temp放在当前空的位置上
     * 5、index向后挪1，即index=index+1，temp=array[index],重复步骤2-4，直到index=array.length,排序结束，
     * 此时数组中的数据即为从小到大的顺序.
     */
    @Override
    public int[] sort(int[] array, int order) {
        if (array == null || array.length == 0) {
            return array;
        }
        //比较的次数
        int count = 0;
        //记录需要插入的值
        int indexValue = 0;
        for (int i = 0; i < array.length - 1; i++) {
            int index = i + 1;
            indexValue = array[index];
            for (int j = i; j >= 0; j--) {
                count++;
                log.debug("比较次数为:{}", count);
                //降序排序
                if (order == -1) {
                    if (array[j] < indexValue) {
                        //后移一位
                        array[j + 1] = array[j];
                        //记录下后移完成后,空缺后需要填充的位置
                        index = j;
                    }
                } else {//升序排序
                    if (array[j] > indexValue) {
                        //后移一位
                        array[j + 1] = array[j];
                        //记录下后移完成后,空缺后需要填充的位置
                        index = j;
                    }
                }
            }
            //将临时变量保存的临时值插入
            array[index] = indexValue;
            log.debug(Arrays.toString(array));
        }
        return array;
    }

    @Override
    public <T extends Comparable> T[] sort(T[] array, int order) {
        return null;
    }

    @Override
    public <T> T[] sort(T[] array, int order, Comparator<T> comparator) {
        return null;
    }
}
