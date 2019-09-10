package com.example.postbar.dto;

import com.example.postbar.entity.User;
import lombok.Data;

/**
 * @author zhangxu
 * @title
 * @date 2019/8/20 10:24
 */
@Data
public class QuestionDto {
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
    private User user;
//    private long totalpages;
//    private long currentpage;

}
