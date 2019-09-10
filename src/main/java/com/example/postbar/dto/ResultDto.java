package com.example.postbar.dto;

import com.example.postbar.Exception.CustomException;
import com.example.postbar.enums.CustomEnum;
import com.sun.org.apache.regexp.internal.RE;
import lombok.Data;

/**
 * @author zhangxu
 * @title
 * @date 2019/9/3 16:15
 */
@Data
public class ResultDto <T>{
    Integer code;
    String message;
    private T data;

    public static ResultDto errof(Integer code,String message){
        ResultDto resultDto=new ResultDto();
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto;
    }

    public static ResultDto errof(CustomEnum c){
        return errof(c.getCode(),c.getMsg());
    }

    public static ResultDto ok(Integer code,String message){
        ResultDto resultDto=new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("请求成功");
        return resultDto;
    }

    public static ResultDto errof(CustomException e){
        return errof(e.getCode(),e.getMsg());
    }
    public static <T> ResultDto ok(T t){
        ResultDto resultDto=new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("请求成功");
        resultDto.setData(t);
        return resultDto;
    }
}
