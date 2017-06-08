package com.webgis.enums;

/**
 * Created by CCMEOW on 2017/6/8.
 */
public enum RoleEnum {
    SUPER_ADMIN("0", "SUPER_ADMIN"),
    ADMIN("1", "ADMIN"),
    USER("2","USER");

    private String code;
    private String value;

    RoleEnum(String code, String value) {
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
