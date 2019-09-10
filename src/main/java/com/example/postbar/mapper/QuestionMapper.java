package com.example.postbar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.postbar.entity.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zhangxu
 * @title
 * @date 2019/8/19 19:19
 */

public interface QuestionMapper extends BaseMapper<Question> {
    List<Question> getlistBytags(Question question);
}
