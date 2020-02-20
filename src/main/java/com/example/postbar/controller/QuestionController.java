package com.example.postbar.controller;


import com.example.postbar.dto.CommentVO;
import com.example.postbar.dto.QuestionDto;
import com.example.postbar.dto.ResultDto;
import com.example.postbar.enums.TypeEnum;
import com.example.postbar.service.CommentService;
import com.example.postbar.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zhangxu
 * @title
 * @date 2019/8/24 16:44
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String questionid(@PathVariable(name = "id") String id, Model model) {
        QuestionDto questionDto = questionService.getlistByid(id);
        List<QuestionDto> tagsquestion = questionService.getlistBytags(questionDto);
        List<CommentVO> commentDtoList = commentService.getlistById(id, TypeEnum.QUESTION.getType());
        model.addAttribute("comments", commentDtoList);
        model.addAttribute("question", questionDto);
        model.addAttribute("tagsquestion", tagsquestion);
        return "question";
    }

//    @GetMapping("/question/test")
//    @ResponseBody
//    public List<CommentVO>  questionids( Model model){
//        List<CommentVO> commentDtoList=commentService.getlistById(String.valueOf(12442));
//
//
//        return commentDtoList;
//    }

}

