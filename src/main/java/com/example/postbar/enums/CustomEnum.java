package com.example.postbar.enums;

import lombok.Getter;

/**
 * @author zhangxu
 * @title
 * @date 2019/8/28 9:18
 */
@Getter
public enum CustomEnum {

    PAGE_NOT_FOUND(1,"页面不存在"),
    QUESTION_NOT_FOUND(2,"问题不存在或者已被删除"),
    NO_LOGIN(3,"当前用户未登录，请登录后重试"),
    QUESTION_NOT_SELECT(4,"未选中问题进行评论"),
    SYS_ERROR(5,"服务器出差，请稍后再试"),
    TYPE_ERROR(6,"评论类型错误"),
    COMMENT_NOT_FOUND(7,"评论不存在或已删除"),
    COMMENT_IS_EMPTY(8,"回复不能为空");



    private Integer code;
    private String msg;

    CustomEnum(Integer code,String msg) {
        this.code=code;
        this.msg = msg;
    }
}
