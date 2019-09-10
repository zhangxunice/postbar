package com.example.postbar.entity;

import lombok.Data;

/**
 * @author zhangxu
 * @title
 * @date 2019/8/18 10:21
 */
@Data
public class User {
    private String id;
    private String accountId;
    private String name;
    private String password;
    private String token;
    private long gmtCreate;
    private long gmtModified;
    private String avatarurl;

}
