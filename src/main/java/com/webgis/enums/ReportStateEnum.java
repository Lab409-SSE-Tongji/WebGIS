package com.webgis.enums;


public enum ReportStateEnum {

    DAMAGE("0","DAMAGE"),
    LOST("1","LOST"),
    BLOCK("2","BLOCK"),
    FINISH("3","FINISH"),
    DELETE("4", "DELETE"),
    BAD("5", "BAD");

    private String code;
    private String value;

    ReportStateEnum(String code, String value) {
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

    public static ReportStateEnum getEnum(String type) {
        for (ReportStateEnum stateEnum : ReportStateEnum.values()) {
            if (stateEnum.name().equals(type)) {
                return stateEnum;
            }
        }
        return null;
    }
}
