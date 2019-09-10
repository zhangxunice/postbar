package com.example.postbar.controller;

import com.example.postbar.dto.PageDto;
import com.example.postbar.dto.QuestionDto;
import com.example.postbar.entity.User;
import com.example.postbar.mapper.QuestionMapper;
import com.example.postbar.mapper.UserMapper;
import com.example.postbar.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author zhangxu
 * @title
 * @date 2019/8/10 12:41
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionMapper questionMapper;

       @GetMapping("/")
       public String index(Model model,HttpServletRequest request,
                           @RequestParam(value = "page",defaultValue = "1")Integer page,
                           @RequestParam(value = "pagesize",defaultValue = "5")Integer pagesize){


        PageDto pageDto=questionService.questionlist(page,pagesize);
        model.addAttribute("pageDto",pageDto);
//           User user= (User) request.getSession().getAttribute("user");
//           System.out.println(user);
        return "index";
    }

//    @GetMapping("/test")
//    @ResponseBody
//    public List<QuestionDto> test(HttpServletRequest request, Model model,
//                                  @RequestParam(value = "page",defaultValue = "1")Integer page,
//                                  @RequestParam(value = "pagesize",defaultValue = "7")Integer pagesize){
//
//
//        PageDto pageDto=questionService.questionlist(page,pagesize);
//
//        return pageDto.getQuestionDtoList();
//    }

}
