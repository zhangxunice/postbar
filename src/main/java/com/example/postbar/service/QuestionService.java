package com.example.postbar.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.postbar.enums.CustomEnum;
import com.example.postbar.Exception.CustomException;
import com.example.postbar.dto.PageDto;
import com.example.postbar.dto.QuestionDto;
import com.example.postbar.entity.Question;
import com.example.postbar.entity.User;
import com.example.postbar.mapper.QuestionMapper;
import com.example.postbar.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author zhangxu
 * @title
 * @date 2019/8/20 10:25
 */
@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;



    public PageDto questionlist(Integer page,Integer pagesize){
        //Integer offsize=pagesize*(page-1);
        Page<Question> questionPage=new Page<>(page,pagesize);
        QueryWrapper<Question> questionQueryWrapper=new QueryWrapper<>();
        questionQueryWrapper.orderByDesc("gmt_create");
        IPage<Question> goodsIPage=questionMapper.selectPage(questionPage,questionQueryWrapper);
        List<Question> questionList=goodsIPage.getRecords();
        List<QuestionDto> questionDtos=new ArrayList<>();
         PageDto pageDto=new PageDto();
        for (Question question:questionList){
            QuestionDto questionDto=new QuestionDto();
            User user=userMapper.selectById(question.getCreator());
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtos.add(questionDto);
        }
        pageDto.setQuestionDtoList(questionDtos);
        pageDto.setpages(goodsIPage.getPages(),goodsIPage.getTotal(),page,pagesize);
        return pageDto;
    }


    public  PageDto selectbyid(String userid, Integer page, Integer pagesize) {
        Page<Question> questionPage=new Page<>(page,pagesize);
        QueryWrapper<Question> questionQueryWrapper=new QueryWrapper<>();
        questionQueryWrapper.eq("creator",userid).orderByDesc("gmt_create");
        IPage<Question> goodsIPage=questionMapper.selectPage(questionPage,questionQueryWrapper);
        List<Question> questionList=goodsIPage.getRecords();
        List<QuestionDto> questionDtos=new ArrayList<>();
        PageDto pageDto=new PageDto();
        for (Question question:questionList){
            QuestionDto questionDto=new QuestionDto();
            User user=userMapper.selectById(question.getCreator());
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtos.add(questionDto);
        }
        pageDto.setQuestionDtoList(questionDtos);
        pageDto.setpages(goodsIPage.getPages(),goodsIPage.getTotal(),page,pagesize);
        return pageDto;
    }

    public QuestionDto getlistByid(String id) {
        Question question=questionMapper.selectById(id);
        if (StringUtils.isEmpty(question)){
            throw new  CustomException(CustomEnum.QUESTION_NOT_FOUND);
        }
        question.setViewCount(question.getViewCount()+1);
        questionMapper.updateById(question);
        QuestionDto questionDto=new QuestionDto();
        BeanUtils.copyProperties(question,questionDto);
        User user=userMapper.selectById(question.getCreator());
        questionDto.setUser(user);
        return questionDto;
    }


    public void publishquestion(Question question){
        System.out.println(question.getId());
        if (org.apache.commons.lang3.StringUtils.isBlank(question.getId())){
            question.setId(String.valueOf(UUID.randomUUID()));
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setCommentCount(0);
            question.setViewCount(0);
            question.setLikeCount(0);
            questionMapper.insert(question);
        }else {
            question.setGmtModified(question.getGmtCreate());
            questionMapper.updateById(question);
        }
    }

        public List<QuestionDto> getlistBytags(QuestionDto queryDto) {
        if (org.apache.commons.lang3.StringUtils.isBlank(queryDto.getTag())){
            return new ArrayList<>();
        }
        String[] tags= org.apache.commons.lang3.StringUtils.split(queryDto.getTag(),",");
        String regexpTag= Arrays.stream(tags).collect(Collectors.joining("|"));
        Question question=new Question();
        question.setId(queryDto.getId());
        question.setTag(regexpTag);
        List<Question> questionList=questionMapper.getlistBytags(question);
        List<QuestionDto> questionDtoList=questionList.stream().map(q->{
            QuestionDto questionDto=new QuestionDto();
            BeanUtils.copyProperties(q,questionDto);
            return questionDto;
        }).collect(Collectors.toList());
        return questionDtoList;
    }
}
