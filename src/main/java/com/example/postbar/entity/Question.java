package com.example.postbar.entity;

import lombok.Data;

/**
 * @author zhangxu
 * @title
 * @date 2019/8/19 18:55
 */
@Data
public class Question {
    private String id;
    private String title;
    private String description;
    private String creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private long gmtCreate;
    private long gmtModified;

}
