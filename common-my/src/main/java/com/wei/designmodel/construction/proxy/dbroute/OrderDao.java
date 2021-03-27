package com.wei.designmodel.construction.proxy.dbroute;

/**
 * @Author wei.peng.
 */
public class OrderDao {
    public int insert(Order order){
        System.out.println("OrderDao创建Order成功!");
        return 1;
    }
}
