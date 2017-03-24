package com.webgis.enums;

/**
 * Created by Justin on 2017/3/13.
 *
 * 公共设施状态类型
 *
 */

public enum  StatusEnum {
    GOOD("001", "Good"),
    BAD("002", "Bad");

    private String code;
    private String value;

    StatusEnum(String code, String value) {
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

    public static StatusEnum getEnum(String status) {
        for (StatusEnum statusEnum : StatusEnum.values()) {
            if (statusEnum.name().equals(status)) {
                return statusEnum;
            }
        }
        return null;
    }
}
