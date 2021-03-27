package com.wei.statemachine;

/**
 * @author wei.peng
 * @descripe
 * @Date 2019/7/1 0001.
 */
public enum EnumStateMachineType {

    StateMachineType1,
    StateMachineType2,
    StateMachineType3
    ;
    private String url;
    private String key;
    EnumStateMachineType() {
    }
    EnumStateMachineType(String key, String url) {
        this.key = key;
        this.url = url;
    }

    public static EnumStateMachineType getInstance(String key) throws Exception {
        EnumStateMachineType[] values = EnumStateMachineType.values();
        for (EnumStateMachineType value : values) {
            if(value.getKey() != null && value.getKey().equals(key)){
                return value;
            }
        }
        throw new Exception("状态机不存在");
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
