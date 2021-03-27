package com.wei.utils.excel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author a_pen
 */
public class ReturnResult implements Serializable {

    private static final long serialVersionUID = 6816265515135254415L;

    private Map<String, Object> data;
    protected int code = 0;
    protected String msg = "";
    public ReturnResult() {

    }

    public ReturnResult(int c, String m) {
        this.code = c;
        this.msg = m;
    }

    public ReturnResult(int c, String m, Map<String, Object> dataMap) {
        this(c, m);
        this.data = dataMap;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void putAll(Map<String, Object> data) {
        if (this.data == null) {
            this.data = new HashMap<>();
        }
        this.data.putAll(data);
    }

    public void put(String key, Object value) {
        if (this.data == null) {
            this.data = new HashMap<>();
        }
        this.data.put(key, value);
    }

    public static void main(String[] args) {
        BigDecimal bl = new BigDecimal(2000.10);
        BigDecimal re = new BigDecimal(2000);
        System.out.println(bl.compareTo(re) );
    }
}