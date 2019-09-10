package com.example.postbar.dto;

import lombok.Data;


/**
 * @author zhangxu
 * @title
 * @date 2019/9/3 10:41
 */
@Data
public class CommentDto {
    private String parentId;
    private String comment;
    private Integer type;
}
