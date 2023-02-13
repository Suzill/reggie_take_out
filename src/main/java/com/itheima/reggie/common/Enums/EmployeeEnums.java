package com.itheima.reggie.common.Enums;

import lombok.Getter;

@Getter
public enum EmployeeEnums {
    DISABLE(0, "禁用"),
    ENABLE(1,"启用"),
    ;

    private Integer type;
    private String value;

    EmployeeEnums(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

    public static String getInitCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (EmployeeEnums c : EmployeeEnums.values()) {
            if (code.equals(c.getType())) {
                return c.getValue();
            }
        }
        return null;
    }
}
