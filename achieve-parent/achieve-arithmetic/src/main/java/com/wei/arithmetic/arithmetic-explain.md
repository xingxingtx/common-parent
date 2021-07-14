### **一、排序查找算法**
#### **1、选择排序**

 **1)原理:** 

```
  （selectSort）遍历元素找到一个最小（或最大）的元素，把它放在第一个位置，然后再在剩余元素中找到最小（或最大）的元素，把它放在第二个位置，依次下去，完成排序。
```
![selectionSort](D:\image\gif\selectionSort.gif)
 **2)直接选择排序步骤：**

```
 n个记录的文件的直接选择排序可经过n-1趟直接选择排序得到有序结果：
      ①初始状态：无序区为R[1..n]，有序区为空。
      ②第1趟排序
        　  在无序区R[1..n]中选出关键字最小的记录R[k]，将它与无序区的第1个记录R[1]交换，使R[1..1]和R[2..n]分别变为记录个数增加1个的新有序
        区和记录个数减少1个的新无序区。
    　　……
      ③第i趟排序
    　　    第i趟排序开始时，当前有序区和无序区分别为R[1..i-1]和R[i..n](1≤i≤n-1)。该趟排序从当前无序区中选出关键字最小的记录R[k]，将它与无
        序区的第1个记录R[i]交换，使R[1..i]和R[i+1..n]分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区。这样，n个记录的文件的直接
        选择排序可经过n-1趟直接选择排序得到有序结果。
```

 **3）算法分析：**
  （1）关键字比较次数

```
   无论文件初始状态如何，在第i趟排序中选出最小(或者最大值)关键字的记录，需做n-i次比较，因此，总的比较次数为： n(n-1)/2=o(n^2)
```

  （2）记录的移动次数

```
    当初始文件为正序时，移动次数为0
    无论文件初始状态如何，在第i趟排序中选出最小(或者最大值)关键字的记录，需做n-i次比较，因此，总的比较次数为： n(n-1)/2=o(n^2)
```

  （3）直接选择排序是一个就地排序
  （4）稳定性分析
      　直接选择排序是稳定的

  **4）Java实现**

排序算法抽象类，后排排序均实现AbstractSort类

```java
package com.wei.arithmetic.base.sort;

/**
 * @Describe
 * @Author wei.peng
 */
public interface Sort<T> {

    /**
     * 排序int数组
     *
     * @param array 排序数组
     * @param order -1 降序，其余升序
     */
    int[] sort(int[] array, int order);

    /**
     * 排序对象数组
     *不支持数组中含有null元素，null元素会抛出空指针
     * @param array 排序数组
     * @param order -1 降序，其余升序
     */
    T[] sort(T[] array, int order);

}

```

```java
package com.wei.arithmetic.base.sort.abs;
import com.wei.arithmetic.base.sort.Sort;
import java.util.Comparator;

/**
 * @Describe
 * @Author wei.peng
 */
public abstract class AbstractSort<T> implements Sort<T> {

    protected final Comparator<T> comparator;

    protected AbstractSort(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    protected AbstractSort() {
        this.comparator = null;
    }

    protected void swap(int[] arr, int i, int j) {
        if (arr == null || arr.length == 0 || i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    protected <T> void swap(T[] arr, int i, int j) {
        if (arr == null || arr.length == 0 || i == j) {
            return;
        }
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}

```

排序测试类

```java
package com.wei.arithmetic.test;

import com.alibaba.fastjson.JSON;
import com.wei.arithmetic.base.MergeSort;
import com.wei.arithmetic.base.ShellSort;
import com.wei.arithmetic.base.sort.Sort;
import com.wei.utils.ArithmeticUtils;

import java.util.Comparator;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月05日
 */
public class SortTest {
    public static void main(String[] args) {
        Integer[] array1 = ArithmeticUtils.getRandomIntegerArray(20, 1, 1000);
        int[] array2 = ArithmeticUtils.getRandomIntArrays(11, 1, 1000);
        System.out.println(JSON.toJSON(array2));
        //测试选择排序
        Sort sort;
        long start = System.currentTimeMillis();
        //1.选择排序
        //sort = new SelectSort(Comparator.comparingInt(o -> (int) o));

        //2.冒泡排序
        //sort = new BubbleSort(Comparator.comparingInt(o -> (int) o));

        //3.插入排序
        //sort = new InsertSort(Comparator.comparingInt(o -> (int) o));

        //4.快速排序
        //sort = new QuickSort(Comparator.comparingInt(o -> (int) o));

        //5.归并排序
        //sort = new MergeSort(Comparator.comparingInt(o -> (int) o));

        //6.希尔排序
        sort = new ShellSort(Comparator.comparingInt(o -> (int) o));
        sort.sort(array2, 1);

        //7.桶排序
        long end = System.currentTimeMillis();
        //用于比较各种算法花费时间长短
        System.out.println("-------waste time:" + (end - start));
        System.out.println(JSON.toJSON(array2));
    }


}

```



```java
package com.wei.arithmetic.base;

import com.alibaba.fastjson.JSON;
import com.wei.arithmetic.base.sort.abs.AbstractSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Comparator;
public class SelectSort<T> extends AbstractSort<T> {

    private static Logger log = LoggerFactory.getLogger(SelectSort.class);

    public SelectSort(Comparator<T> comparator) {
        super(comparator);
    }

    public SelectSort() {
    }
    
    @Override
    public int[] sort(int[] array, int order) {
        if (array == null || array.length == 0) {
            return array;
        }
        //比较次数
        int count = 0;
        int length = array.length - 1;
        //需要比较的次数为array.length - 1次
        for (int i = 0; i < length; i++) {
            //假设每一趟第一个元素都是最小值或者最大值，记录当前索引
            int temp = i;
            //从剩余未排序序列中找到最小值下标
            for (int j = i + 1; j < array.length; j++) {
                count++;
                //降序排序
                if (order == -1) {
                    if (array[temp] < array[j]) {
                        temp = j;
                    }
                } else {//升序排序
                    if (array[temp] > array[j]) {
                        temp = j;
                    }
                }
                log.debug("比较次数为:{}, array:{}", count, JSON.toJSON(array));
            }
            //如果本身就是最小值或者最大值下标，则不需要交换
            if (temp != i) {
                swap(array, i, temp);
            }
        }
        return array;
    }

    @Override
    public T[] sort(T[] array, int order) {
        if (array == null || array.length == 0) {
            return array;
        }
        //存放每次最大或者最小值的数组下标
        int temp;
        //比较次数
        int count = 0;
        for (int i = 0; i < array.length - 1; i++) {
            temp = i;
            for (int j = i + 1; j < array.length; j++) {
                count++;
                //降序排序
                boolean bl = (comparator != null && comparator.compare(array[temp], 						array[j]) < 0)
                        || ((Comparable & Serializable) array[temp]).compareTo(array[j]) 					< 0;
                if (order > 0) {
                    if (bl) {
                        temp = j;
                    }
                } else {
                    //升序排序
                    if (!bl) {
                        temp = j;
                    }
                }
                log.debug("比较次数为:{}, array:{}", count, JSON.toJSON(array));
            }
            if (temp != i) {
                swap(array, i, temp);
            }
        }
        return array;
    }
}

```

**5）应用**



#### **2、冒泡排序**

 **1)原理：**

```
（bubbleSort）依次比较相邻的两个数,将小/大数放在前面,大/小数数放在后面。
```
![bubbleSort](D:\image\gif\bubbleSort.gif)
 **2)比较步骤：**

```
 设数组的长度为N：
  (1)比较前后相邻的二个数据，如果前面数据大于后面的数据，就将这二个数据交换。
  (2)这样对数组的第0个数据到N-1个数据进行一次遍历后，最大的一个数据就“沉”到数组第N-1个位置。
  (3)N=N-1，如果N不为0就重复前面二步，否则排序完成。
```

**3）算法分析：**
  （1）关键字比较次数

```
  如果我们的数据正序，只需要走一趟即可完成排序。所需的比较次数C和记录移动次数M均达到最小值，即：Cmin=n-1;Mmin=0;所以，冒泡排序最好的时间复杂度为O(n)。
  如果很不幸我们的数据是反序的，则需要进行n-1趟排序。每趟排序要进行n-i次比较(1≤i≤n-1)，且每次比较都必须移动记录三次来达到交换记录位置。在这种情况下，比较和移动次数均达到最大值,冒泡排序总的平均时间复杂度为：
```

（2）稳定性分析
      　稳定的

  **4）Java实现**

```java
 package com.wei.arithmetic.base;

import com.wei.arithmetic.base.sort.abs.AbstractSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Comparator;

public class BubbleSort<T> extends AbstractSort<T> {

    private static Logger log = LoggerFactory.getLogger(BubbleSort.class);

    public BubbleSort(Comparator<T> comparator) {
        super(comparator);
    }

    public BubbleSort() {
    }

    /**
     * @param array 排序数组
     * @param order -1 降序，其余升序
     */
    @Override
    public int[] sort(int[] array, int order) {
        if (array == null || array.length == 0) {
            return array;
        }
        boolean isSwap;
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            isSwap = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                count++;
                //降序排序
                if (order == -1) {
                    if (array[j] - array[j + 1] < 0) {
                        isSwap = true;
                        swap(array, j, j + 1);
                    }
                } else {
                    //升序排序
                    if (array[j] - array[j + 1] > 0) {
                        isSwap = true;
                        swap(array, j, j + 1);
                    }
                }
                //log.info("第 {} 次排序后array:{}", count, JSON.toJSON(array));
            }
            //未交换位置，说明已经是有序数组
            if (!isSwap) {
                break;
            }
        }
        return array;
    }


    @Override
    public T[] sort(T[] array, int order) {
        if (array == null || array.length == 0) {
            return array;
        }
        boolean isSwap;
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            isSwap = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                count++;
                boolean bl = (comparator != null && comparator.compare(array[j], 								array[j]) < 0)
                        || ((Comparable & Serializable) array[j]).compareTo(array[j + 1]) 							< 0;
                //降序排序
                if (order == -1) {
                    if (bl) {
                        isSwap = true;
                        swap(array, j, j + 1);
                    }
                } else {
                    //升序排序
                    if (!bl) {
                        isSwap = true;
                        swap(array, j, j + 1);
                    }
                }
                //log.info("第 {} 次排序后array:{}", count, JSON.toJSON(array));
            }
            //未交换位置，说明已经是有序数组
            if (!isSwap) {
                break;
            }
        }
        return array;
    }
}

```

**5）应用**



#### **3、插入排序**

**1)原理：**

```
   （insertSort）利用插入法对无序数组排序时，我们其实是将数组R划分成两个子区间R[1．．i-1]（已排好序的有序区）和R[i．．n]（当前未排序的部分，
   可称无序区）。插入排序的基本操作是将当前无序区的第1个记录R[i]插人到有序区R[1．．i-1]中适当的位置上，使R[1．．i]变为新的有序区。因为
这种方法每次使有序区增加1个记录，通常称增量法。
```
![insertSort](D:\image\gif\insertSort.gif)
  **2)步骤:**

```
1、以数组的某一位作为分隔位，比如index=1，假设左面的都是有序的
 2、将index位的数据拿出来，放到临时变量里，这时index位置就空出来了.
 3、从leftindex=index-1开始将左面的数据与当前index位的数据（即temp）进行比较，如果array[leftindex]>temp,
 则将array[leftindex]后移一位，即array[leftindex+1]=array[leftindex],此时leftindex就空出来了
 4、再用index-2(即leftindex=leftindex-1)位的数据和temp比，重复步骤3，
    直到找到<=temp的数据或者比到了最左面（说明temp最小），停止比较，将temp放在当前空的位置上
 5、index向后挪1，即index=index+1，temp=array[index],重复步骤2-4，直到index=array.length,排序结束，
    此时数组中的数据即为从小到大的顺序.
3)算法分析：
稳定 
空间复杂度O(1) 
时间复杂度O(n2) 
最差情况：反序，需要移动n*(n-1)/2个元素 
最好情况：正序，不需要移动元素
```

**3）算法分析：**

（1）稳定性分析
      　稳定的

  **4）Java实现**

```java
package com.wei.arithmetic.base;

import com.alibaba.fastjson.JSON;
import com.wei.arithmetic.base.sort.Sort;
import com.wei.arithmetic.base.sort.abs.AbstractSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Comparator;

public class InsertSort<T> extends AbstractSort<T> {

    private static Logger log = LoggerFactory.getLogger(InsertSort.class);

    public InsertSort() {

    }
    public InsertSort(Comparator<T> comparator) {
        super(comparator);
    }

    /**
     * 插入排序
     *
     * @param array 排序数组
     * @param order -1 降序，其余升序
     */
    @Override
    public int[] sort(int[] array, int order) {
        if (array == null || array.length == 0) {
            return array;
        }
        //比较的次数
        int count = 0;
        //记录需要插入的值
        int indexValue;
        for (int i = 0; i < array.length - 1; i++) {
            int index = i + 1;
            indexValue = array[index];
            for (int j = i; j >= 0; j--) {
                count++;
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
                log.debug("第{}次比较，array:{}", count, JSON.toJSON(array));
            }
            //将临时变量保存的临时值插入
            array[index] = indexValue;
        }
        return array;
    }

    @Override
    public T[] sort(T[] array, int order) {
        if (array == null || array.length == 0) {
            return array;
        }
        int index;
        T temp;
        int count = 0;
        for (int i = 0; i < array.length - 1; i++) {
            index = i + 1;
            temp = array[index];
            for (int j = i; j >= 0; j--) {
                count++;
                boolean isChange = (comparator != null && comparator.compare(array[j], 								temp) < 0)
                        || (((Comparable<T> & Serializable) array[j]).compareTo(temp) < 							0);
                if (order == -1) {
                    if (isChange) {
                        array[j + 1] = array[j];
                        index = j;
                    }
                } else {
                    if (!isChange) {
                        array[j + 1] = array[j];
                        index = j;
                    }
                }
                log.debug("第{}次比较，array:{}", count, JSON.toJSON(array));
            }
            array[index] = temp;
        }
        return array;
    }

}

```

**5）应用**



#### **4、快速排序**

  **1)原理：**

```
   (QuickSort)选择一个关键值作为基准值。比基准值小的都在左边序列（无序的），比基准值大的都在右边（无序的）。 一般选择序列的第一个元素
```
![quickSort](D:\image\gif\quickSort.gif)
  **2)步骤:**

     设要排序的数组是A[0]……A[N-1]
     1、设置两个变量I、J，排序开始的时候：I=0，J=N-1； 
     2、以第一个数组元素作为关键数据，赋值给X，即 X=A[0]； 
     3、从J开始向前搜索，即由后开始向前搜索（J=J-1），找到第一个小于X的值，让该值与X交换； 
     4、从I开始向后搜索，即由前开始向后搜索（I=I+1），找到第一个大于X的值，让该值与X交换； 
     5、重复第3、4步，直到 I=J；
  **3)算法分析：**
    稳定 
    空间复杂度O(1) 
    时间复杂度O(nlogn) 
    最差情况：反序，需要移动n*(n-1)/2个元素 
    最好情况：正序，不需要移动元素

**4）Java实现**

```java
package com.wei.arithmetic.base;

import com.wei.arithmetic.base.sort.abs.AbstractSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Comparator;

public class QuickSort<T> extends AbstractSort<T> {

    private static final Logger log = LoggerFactory.getLogger(QuickSort.class);


    public QuickSort(Comparator<T> comparator) {
        super(comparator);
    }

    public QuickSort() {
    }

    /**
     * @param array 排序数组
     * @param order -1 降序，其余升序
     * @return
     */
    @Override
    public int[] sort(int[] array, int order) {
        if (array == null || array.length <= 1) {
            return array;
        }
        quickSort(array, 0, array.length - 1, order);
        return array;
    }

    private void quickSort(int[] array, int left, int right, int order) {
        if (array.length <= 1 || left >= right) {
            return;
        }
        int mid = partition(array, left, right, order);
        quickSort(array, left, mid, order);
        quickSort(array, mid + 1, right, order);
        //log.info("array:{}", JSON.toJSON(array));
    }

    private int partition(int[] array, int left, int right, int order) {
        int temp = array[left];
        boolean desc = (order == -1);
        while (left < right) {
            if (desc) {
                // 先从最右边依次和基数做比较
                while (left < right && temp >= array[right]) {
                    right--;
                }
                // 当基准数小于了 arr[right]，则填坑
                if (left < right) {
                    array[left] = array[right];
                    left++;
                }
                //在从左边依次和基数做比较
                while (left < right && temp <= array[left]) {
                    left++;
                }
                // 当基准数大于了 arr[left]，则填坑
                if (left < right) {
                    array[right] = array[left];
                    right--;
                }
            } else {
                // 先从最右边依次和基数做比较
                while (left < right && temp <= array[right]) {
                    right--;
                }
                // 当基准数大于了 arr[right]，则填坑
                if (left < right) {
                    array[left] = array[right];
                    left++;
                }
                //在从左边依次和基数做比较
                while (left < right && temp >= array[left]) {
                    left++;
                }
                // 当基准数大于了 arr[left]，则填坑
                if (left < right) {
                    array[right] = array[left];
                    right--;
                }
            }
        }
        array[left] = temp;
        return left;
    }

    @Override
    public T[] sort(T[] array, int order) {
        if (array == null || array.length <= 1) {
            return array;
        }
        quickSort(array, 0, array.length - 1, order);
        return array;
    }

    private void quickSort(T[] array, int left, int right, int order){
        if (array.length <= 1 || left >= right) {
            return;
        }
        int mid = partition(array, left, right, order);
        quickSort(array, left, mid, order);
        quickSort(array, mid + 1, right, order);
        //log.info("array:{}", JSON.toJSON(array));
    }

    private int partition(T[] array, int left, int right, int order) {
        T temp = array[left];
        boolean desc = (order == -1);
        while (left < right) {
            if (desc) {
                // 先从最右边依次和基数做比较
                while (left < right && ((comparator != null && comparator.compare(temp, 							array[right]) >= 0) ||
                        ((Comparable<T> & Serializable)temp).compareTo(array[right]) >= 							0)) {
                    right--;
                }
                // 当基准数小于了 arr[right]，则填坑
                if (left < right) {
                    array[left] = array[right];
                    left++;
                }
                //在从左边依次和基数做比较
                while (left < right && ((comparator != null && comparator.compare(temp, 							array[left]) <= 0) ||
                        ((Comparable<T> & Serializable)temp).compareTo(array[left]) <= 								0)) {
                    left++;
                }
                // 当基准数大于了 arr[left]，则填坑
                if (left < right) {
                    array[right] = array[left];
                    right--;
                }
            } else {
                // 先从最右边依次和基数做比较
                while (left < right && ((comparator != null && comparator.compare(temp, 							array[right]) <= 0) ||
                        ((Comparable<T> & Serializable)temp).compareTo(array[right]) <= 							0)) {
                    right--;
                }
                // 当基准数大于了 arr[right]，则填坑
                if (left < right) {
                    array[left] = array[right];
                    left++;
                }
                //在从左边依次和基数做比较
                while (left < right && ((comparator != null && comparator.compare(temp, 							array[left]) >= 0) ||
                        ((Comparable<T> & Serializable)temp).compareTo(array[left]) >= 								0)) {
                    left++;
                }
                // 当基准数大于了 arr[left]，则填坑
                if (left < right) {
                    array[right] = array[left];
                    right--;
                }
            }
        }
        array[left] = temp;
        return left;
    }
}

```

**5）应用**



#### **5、归并排序**

  **1)原理：**

```
 归并排序(Merge Sort)就是利用归并思想对数列进行排序。根据具体的实现，归并排序包括"从上往下"和"从下往上"2种方式
```


  **2)步骤:**

     1. 从下往上的归并排序：将待排序的数列分成若干个长度为1的子数列，然后将这些数列两两合并；得到若干个长度为2的有序数列，再将这些数列两两合并；得到若干个长度为4的有序数列，再将它们两两合并；直接合并成一个数列为止。这样就得到了我们想要的排序结果。
    
    2. 从上往下的归并排序：它与"从下往上"在排序上是反方向的。它基本包括3步：
    ① 分解 -- 将当前区间一分为二，即求分裂点 mid = (low + high)/2;
    ② 求解 -- 递归地对两个子区间a[low...mid] 和 a[mid+1...high]进行归并排序。递归的终结条件是子区间长度为1。
    ③ 合并 -- 将已排序的两个子区间a[low...mid]和 a[mid+1...high]归并为一个有序的区间a[low...high]。

![mergeSort](D:\image\gif\mergeSort.gif)

  **3)算法分析：**
（1）稳定性
   　归并排序是一种稳定的排序。
（2）存储结构要求
  　可用顺序存储结构。也易于在链表上实现。
（3）时间复杂度
  　对长度为n的文件，需进行趟二路归并，每趟归并的时间为O(n)，故其时间复杂度无论是在最好情况下还是在最坏情况下均是O(nlgn)。
（4）空间复杂度
   需要一个辅助向量来暂存两有序子文件归并的结果，故其辅助空间复杂度为O(n)，显然它不是就地排序。
 注意：
  　若用单链表做存储结构，很容易给出就地的归并排序

**4）Java实现**

```java
package com.wei.arithmetic.base;

import com.wei.arithmetic.base.sort.abs.AbstractSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Comparator;

public class MergeSort<T> extends AbstractSort<T> {
    private static final Logger log = LoggerFactory.getLogger(MergeSort.class);

    public MergeSort(Comparator<T> comparator) {
        super(comparator);
    }
    public MergeSort() {
    }


    /**
     * @param array 排序数组
     * @param order -1 降序，其余升序
     * @return
     */
    @Override
    public int[] sort(int[] array, int order) {
        if (array == null || array.length <= 1) {
            return array;
        }
        mergeSort(array, 0, array.length - 1, order);
        return array;
    }

    private void mergeSort(int[] array, int left, int right, int order) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) >> 1;
        mergeSort(array, left, mid, order);
        mergeSort(array, mid + 1, right, order);
        merge(array, left, mid, right, order);
        //log.info("array:{}", JSON.toJSON(array));
    }

    private  void merge(int[] array, int left, int mid, int right, int order) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            //降序排序
            if (order == -1) {
                if (array[i] > array[j]) {
                    temp[k++] = array[i++];
                } else {
                    temp[k++] = array[j++];
                }
            } else {
                if (array[i] < array[j]) {
                    temp[k++] = array[i++];
                } else {
                    temp[k++] = array[j++];
                }
            }
        }
        //
        while (i <= mid) {
            temp[k++] = array[i++];
        }
        while (j <= right) {
            temp[k++] = array[j++];
        }
        System.arraycopy(temp, 0, array, left, temp.length);
        temp = null;
    }

    @Override
    public T[] sort(T[] array, int order) {
        if (array == null || array.length <= 1) {
            return array;
        }
        mergeSort(array, 0, array.length - 1, order);
        return array;
    }

    private void mergeSort(T[] array, int left, int right, int order) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) >> 1;
        mergeSort(array, left, mid, order);
        mergeSort(array, mid + 1, right, order);
        merge(array, left, mid, right, order);
        //log.info("array:{}", JSON.toJSON(array));
    }

    private  void merge(T[] array, int left, int mid, int right, int order) {
        Object[] temp = new Object[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            boolean bl = (comparator != null && comparator.compare(array[i], array[j]) > 						0)
                    || (((Comparable<T> & Serializable)array[i]).compareTo(array[j]) > 						   0);
            //降序排序
            if (order == -1) {
                if (bl) {
                    temp[k++] = array[i++];
                } else {
                    temp[k++] = array[j++];
                }
            } else {
                if (!bl) {
                    temp[k++] = array[i++];
                } else {
                    temp[k++] = array[j++];
                }
            }
        }
        //
        while (i <= mid) {
            temp[k++] = array[i++];
        }
        while (j <= right) {
            temp[k++] = array[j++];
        }
        System.arraycopy(temp, 0, array, left, temp.length);
        temp = null;
    }
}
```

**5）应用**





#### **6、希尔排序**

**1)原理：**

```
    (shellSort)现将待排序的数组元素分成多个子序列，使得每个子序列的元素个数相对较少，然后对各个子序列分别进行直接插入排序，待整个待排序列“基本有序”后，最后在对所有元素进行一次直接插入排序。因此，我们要采用跳跃分割的策略：将相距某个“增量”的记录组成一个子序列，这样才能保证在子序列内分别进行直接插入排序后得到的结果是基本有序而不是局部有序。希尔排序是对直接插入排序算法的优化和升级
```

 ![shellSort](D:\image\gif\shellSort.gif)

**2)步骤:**

```
1.先取一个正整数 d1(d1 < n)，把全部记录分成 d1 个组，所有距离为 d1 的倍数的记录看成一组，然后在各组内进行插入排序
2.然后取 d2(d2 < d1)
重复上述分组和排序操作；直到取 di = 1(i >= 1) 位置，即所有记录成为一个组，
3.最后对这个组进行插入排序。一般选 d1 约为 n/2，d2 为 d1 /2， d3 为 d2/2 ，…， di = 1。
列如：我们有一个数组[ 13 14 94 33 82 25 59 94 65 23 45 27 73]如果我们以步长为6开始进行排序，我们可以通过将这列表放在有6列
```

 **3)算法分析：**

```
   希尔排序的关键并不是随便分组后各自排序，而是将相隔某个“增量”的记录组成一个子序列，实现跳跃式移动，使得排序的效率提高。需要注意的是，增量序列的最后一个增量值必须等于1才行。另外，由于记录是跳跃式的移动，希尔排序并不是一种稳定的排序算法。

希尔排序时间复杂度
   希尔排序的时间复杂度与增量(即，步长gap)的选取有关。例如，当增量为1时，希尔排序退化成了直接插入排序，此时的时间复杂度为O(N²)，而Hibbard增量的希尔排序的时间复杂度为O(N3/2)。
```

 **4)Java实现：**

```
package com.wei.arithmetic.base;

import com.wei.arithmetic.base.sort.abs.AbstractSort;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @Describe 希尔排序
 * @Author wei.peng
 * @Date 2021年05月17日
 */
public class ShellSort<T> extends AbstractSort<T> {

    public ShellSort(Comparator<T> comparator) {
        super(comparator);
    }

    public ShellSort() {
    }


    @Override
    public int[] sort(int[] array, int order) {
        if (array == null || array.length <= 1) {
            return array;
        }
        //每次取步长为array.length/2
        for (int step = array.length >> 1; step > 0; step = step >> 1) {
            //根据同步长数据分成step组进行直接插入排序
            for (int j = step; j < array.length; j += step) {
                int temp = array[j];
                int k = j - step;
                int index = -1;
                //降序排序
                if (order == -1) {
                    //
                    while (k >= 0) {
                        if (temp > array[k]) {
                            //后移一位
                            array[k + step] = array[k];
                            index = k;
                        }
                        k -= step;
                    }
                } else {//升序排序
                    while (k >= 0) {
                        if (temp < array[k]) {
                            //后移一位
                            array[k + step] = array[k];
                            index = k;
                        }
                        k -= step;
                    }
                }
                //将array[j]填入index位置
                if (index != -1) {
                    array[index] = temp;
                }
            }
        }
        return array;
    }

    @Override
    public T[] sort(T[] array, int order) {
        if (array == null || array.length <= 1) {
            return array;
        }
        //每次取步长为array.length/2
        for (int step = array.length >> 1; step > 0; step = step >> 1) {
            //根据同步长数据分成step组进行直接插入排序
            for (int j = step; j < array.length; j += step) {
            	//保存需要插入排序的数据
                T temp = array[j];
                int k = j - step;
              	//保存tmep最终需要插入的位置
                int index = -1;
                while (k >= 0) {
                    boolean bl = (comparator != null && comparator.compare(temp, 										array[k]) > 0)
                            || ((Comparable & Serializable) temp).compareTo(array[k]) > 								0;
                    //1、(order == -1 && bl) 降序排序；2、(order != -1 && !bl) 升序排序
                    if ((order == -1 && bl) || (order != -1 && !bl)) {
                        //后移一位
                        array[k + step] = array[k];
                        index = k;
                    }
                    k -= step;
                }
                //将array[j]填入index位置
                if (index != -1) {
                    array[index] = temp;
                }
            }
        }

        return array;
    }

}

```

 **5)应用**



#### **7、堆排序**

**1)原理：**

```
 堆通常是一个可以被看做一棵树，它满足下列性质：
  [性质一] 堆中任意节点的值总是不大于(不小于)其子节点的值；
  [性质二] 堆总是一棵完全树。
  将任意节点不大于其子节点的堆叫做最小堆或小根堆，而将任意节点不小于其子节点的堆叫做最大堆或大根堆。常见的堆有二叉堆、左倾堆、斜堆、二项堆、斐波那契堆等等。
 二叉堆的定义
 二叉堆是完全二元树或者是近似完全二元树，它分为两种：最大堆和最小堆。
 最大堆：父结点的键值总是大于或等于任何一个子节点的键值；最小堆：父结点的键值总是小于或等于任何一个子节点的键值。
 假设"第一个元素"在数组中的索引为 0 的话，则父节点和子节点的位置关系如下：
 (01) 索引为i的左孩子的索引是 (2*i+1);
 (02) 索引为i的右孩子的索引是 (2*i+2);
 (03) 索引为i的父结点的索引是 floor((i-1)/2);
```

**2)步骤:**

```
1、构建初始堆，将待排序列构成一个大顶堆(或者小顶堆)，升序大顶堆，降序小顶堆；
2、将堆顶元素与堆尾元素交换，并断开(从待排序列中移除)堆尾元素。
3、重新构建堆。
4重复2~3，直到待排序列中只剩下一个元素(堆顶元素)。
```

 **3)算法分析：**

```

```

 **4)应用：**

```

```

#### **8、计数排序**

**1)原理：**

```

```

**2)步骤:**

```

```

 **3)算法分析：**

```

```

 **4)应用：**

```

```

#### **9、桶排序**

**1)原理：**

```

```

**2)步骤:**

```

```

 **3)算法分析：**

```

```

 **4)应用：**

```

```

#### **10、桶基数排序**

**1)原理：**

```

```

**2)步骤:**

```

```

 **3)算法分析：**

```

```

 **4)应用：**

```

```

#### **11、二分查找**

**1)原理：**

```

```

**2)步骤:**

```

```

 **3)算法分析：**

```

```

 **4)应用：**

```

```

#### **12、布隆过滤器**

**1)原理：**

```

```

**2)步骤:**

```

```

 **3)算法分析：**

```

```

 **4)应用：**

```

```

#### **13 深度优先、广度优先**

**1)原理：**

```

```

**2)步骤:**

```

```

 **3)算法分析：**

```

```

 **4)应用：**

```

```

#### **14、贪心算法**

**1)原理：**

```

```

**2)步骤:**

```

```

 **3)算法分析：**

```

```

 **4)应用：**

```

```

#### **15、回溯算法**

**1)原理：**

```

```

**2)步骤:**

```

```

 **3)算法分析：**

```

```

 **4)应用：**

```

```

#### **16、剪枝算法**

#### **17、朴素贝叶**

#### **18、推荐算法**

#### **19、最小生成树算法**

#### **20、最短路径算法**

  **1)原理：**

```
 
```


  **2)步骤:**

​     

  **3)算法分析：**
    

**4）Java实现**

```java
 
```

**5）应用**





___

### ***二、提升类算法总结***
#### 1、搜索
##### 	1、BFS

##### 	2、DFS

##### 	3、强联通划分

##### 	4、割点、桥

##### 	5、双联通

#### 2、图论
##### 	1、最小生成树

##### 	2、二分图染色

##### 	3、二分图匹配

##### 	4、拓扑排序

##### 	5、最短路

##### 	6、Floyd

##### 	7、Dijkstra

##### 	8、SPFA

##### 	9、网络流&Dinic算法

##### 	10、最小费用流

##### 	11、上下限网络流

##### 	12、差分约束系统

#### 3、树
##### 	1、树的直径

##### 	2、树的重心

##### 	3、点分治

##### 	4、倍增LCA

##### 	5、虚树

##### 	6、DFS序

##### 	7、树链剖分

#### 4、数据结构
##### 	1、堆

##### 	2、单调队列、单调栈

##### 	3、ST表

##### 	4、Splay

##### 	5、CT

##### 	6、并查集

##### 	7、带权并查集

##### 	8、CDQ分治

##### 	9、线段树

##### 	10、树状数组

##### 	11、主席树

##### 	12、线段树合并

#### 5、字符串
##### 	1、KMP

##### 	2、拓展KMP

##### 	3、AC自动机

##### 	4、字符串hash

##### 	5、Manacher

##### 	6、后缀自动机

##### 	7、后缀数组

##### 	8、字典树

#### 6、动态规划
##### 	1、树形DP

##### 	2、状压DP

##### 	3、数位DP

##### 	4、斜率DP

##### 	5、区间DP

##### 	6、概率DP

##### 	7、插头DP

#### 7、数学
##### 	1、拓展欧几里得

##### 	2、乘法逆元

##### 	3、中国剩余定理

##### 	4、快速幂

##### 	5、矩阵快速幂

##### 	6、容斥原理

##### 	7、Polya

##### 	8、莫比乌斯反演

##### 	9、FFT、NTT

##### 	10、高斯消元

##### 	11、线性基

#### 8、博弈
##### 	1、Nim博弈

#### 9、几何
##### 	1、线段交、点积、差积

##### 	2、凸包

#### 10、其他
##### 	1、分块

##### 	2、莫队

##### 	3、KD树

___

### 三、加解密算法
##### 	1、RSA 

##### 	2、AES

##### 	3、ECDSA

##### 	4、SM2 

##### 	5、SM4

##### 	6、ECC