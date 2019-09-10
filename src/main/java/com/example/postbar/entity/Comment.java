package com.example.postbar.entity;

import lombok.Data;



/**
 * @author zhangxu
 * @title
 * @date 2019/9/3 10:31
 */
@Data
public class Comment {
    private String id;
    private String parentId;
    private Integer type;
    private String commentId;
    private String comment;
    private long likeCount;
    private long gmtCreate;
    private long gmtModified;
    private Integer commentCount;
}
