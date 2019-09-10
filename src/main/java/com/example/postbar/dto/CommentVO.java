package com.example.postbar.dto;

import com.example.postbar.entity.User;
import lombok.Data;

/**
 * @author zhangxu
 * @title
 * @date 2019/9/6 18:14
 */
@Data
public class CommentVO {
    private String id;
    private String parentId;
    private Integer type;
    private String commentId;
    private String comment;
    private long likeCount;
    private long gmtCreate;
    private long gmtModified;
    private User user;
    private Integer commentCount;
}
