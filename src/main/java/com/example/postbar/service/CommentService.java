package com.example.postbar.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.postbar.Exception.CustomException;
import com.example.postbar.dto.CommentVO;
import com.example.postbar.entity.Comment;
import com.example.postbar.entity.Question;
import com.example.postbar.entity.User;
import com.example.postbar.enums.CustomEnum;
import com.example.postbar.enums.TypeEnum;
import com.example.postbar.mapper.CommentMapper;
import com.example.postbar.mapper.QuestionMapper;
import com.example.postbar.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhangxu
 * @title
 * @date 2019/9/3 20:38
 */
@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void insertcomment(Comment comment) {
        if (comment.getParentId() == null) {
            throw new CustomException(CustomEnum.QUESTION_NOT_SELECT);
        }
        if (comment.getType() == null || !TypeEnum.isExist(comment.getType())) {
            throw new CustomException(CustomEnum.TYPE_ERROR);
        }
        if (comment.getType() == TypeEnum.COMMENT.getType()) {
            //回复评论
            Comment comment1 = commentMapper.selectById(comment.getParentId());
            if (comment1 == null) {
                throw new CustomException(CustomEnum.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
            comment1.setCommentCount(comment1.getCommentCount() + 1);
            commentMapper.updateById(comment1);
        } else {
            //回复问题
            Question question = questionMapper.selectById(comment.getParentId());
            if (question == null) {
                throw new CustomException(CustomEnum.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(question.getCommentCount() + 1);
            questionMapper.updateById(question);
        }

    }

    public List<CommentVO> getlistById(String id, Integer type) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id", id).eq("type", type).orderByDesc("gmt_create");
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        if (comments.size() == 0) {
            return new ArrayList<>();
        }
        Set<String> commentors = comments.stream().map(comment -> comment.getCommentId()).collect(Collectors.toSet());
        List<String> userido = new ArrayList<>();
        userido.addAll(commentors);
        QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.in("id", userido);

        List<User> users = userMapper.selectList(queryWrapper1);
        Map<String, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        List<CommentVO> commentVOS = comments.stream().map(comment -> {
            CommentVO commentVO = new CommentVO();
            BeanUtils.copyProperties(comment, commentVO);
            commentVO.setUser(userMap.get(comment.getCommentId()));
            return commentVO;
        }).collect(Collectors.toList());
        return commentVOS;
    }
}
