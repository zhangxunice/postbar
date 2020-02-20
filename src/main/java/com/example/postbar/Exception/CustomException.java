package com.example.postbar.Exception;

import com.example.postbar.enums.CustomEnum;
import lombok.Getter;

/**
 * @author zhangxu
 * @title
 * @date 2019/8/26 10:39
 */
@Getter
public class CustomException extends RuntimeException {
    private String msg;
    private Integer code;

    public CustomException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CustomException(CustomEnum customEnum) {
        this.code = customEnum.getCode();
        this.msg = customEnum.getMsg();
    }


}
