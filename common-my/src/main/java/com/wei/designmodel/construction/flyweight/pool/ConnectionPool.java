package com.wei.designmodel.construction.flyweight.pool;

import java.sql.Connection;
import java.util.Vector;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年03月27日
 */
public class ConnectionPool {

    private Vector<Connection> pool;
    private int poolSize = 100;
    private String url = "jdbc:mysql://localhost:3306/test";
    private String userName = "root";
    private String password = "root";

}
