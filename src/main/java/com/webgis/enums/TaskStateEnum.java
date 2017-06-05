package com.webgis.enums;

/**
 * Created by CCMEOW on 2017/6/5.
 */
public enum TaskStateEnum {

    WAITING("0","等待接受"),
    ACCEPTED("1","已接受"),
    FINISHED("2","已完成"),
    PAID("3","已付款"),
    GIVENUP("4","已放弃"),
    CANCELED("5","已取消");

    private String code;
    private String value;

    TaskStateEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
