package com.example.postbar.dto;

import lombok.Data;

import java.util.List;

/**
 * @author zhangxu
 * @title
 * @date 2019/9/9 13:03
 */
@Data
public class TagDto {
    private String className;
    private List<String> tags;
}
