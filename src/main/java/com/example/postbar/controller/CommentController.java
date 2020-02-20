package com.example.postbar.controller;

import com.example.postbar.Exception.CustomException;
import com.example.postbar.dto.CommentDto;
import com.example.postbar.dto.CommentVO;
import com.example.postbar.dto.ResultDto;
import com.example.postbar.entity.Comment;
import com.example.postbar.entity.User;
import com.example.postbar.enums.CustomEnum;
import com.example.postbar.enums.TypeEnum;
import com.example.postbar.mapper.CommentMapper;
import com.example.postbar.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;


/**
 * @author zhangxu
 * @title
 * @date 2019/9/3 10:38
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    @ResponseBody
    public Object comment(@RequestBody CommentDto commentDto, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomException(CustomEnum.NO_LOGIN);
        }
        if (commentDto == null || StringUtils.isBlank(commentDto.getComment())) {
            throw new CustomException(CustomEnum.COMMENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setId(String.valueOf(UUID.randomUUID()));
        comment.setParentId(commentDto.getParentId());
        comment.setCommentId(String.valueOf(1));
        comment.setComment(commentDto.getComment());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setType(commentDto.getType());
        comment.setCommentCount(0);
        commentService.insertcomment(comment);
        return ResultDto.ok(200, "请求成功");
    }


    @RequestMapping("/comment/{id}")
    @ResponseBody
    public ResultDto twocomment(@PathVariable(name = "id") String id) {
        List<CommentVO> commentVOList = commentService.getlistById(id, TypeEnum.COMMENT.getType());
        return ResultDto.ok(commentVOList);
    }
}
