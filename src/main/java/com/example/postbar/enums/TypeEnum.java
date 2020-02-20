package com.example.postbar.enums;


import lombok.Getter;

/**
 * @author zhangxu
 * @title
 * @date 2019/9/3 20:33
 */
@Getter
public enum TypeEnum {
    QUESTION(1),
    COMMENT(2);
    private Integer type;

    TypeEnum(Integer type) {
        this.type = type;
    }

    public static boolean isExist(Integer type) {
        for (TypeEnum typeEnum : TypeEnum.values()) {
            if (typeEnum.getType() == type) {
                return true;
            }
        }
        return false;
    }
}
