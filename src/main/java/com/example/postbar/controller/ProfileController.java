package com.example.postbar.controller;

import com.example.postbar.dto.PageDto;
import com.example.postbar.dto.QuestionDto;
import com.example.postbar.entity.User;
import com.example.postbar.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zhangxu
 * @title
 * @date 2019/8/22 9:25
 */
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action, Model model,
                          @RequestParam(value = "page",defaultValue = "1")Integer page,
                          @RequestParam(value = "pagesize",defaultValue = "5")Integer pagesize,
                          HttpServletRequest request){
        if ("question".equals(action)){
            model.addAttribute("section","question");
            model.addAttribute("sectionName","我的提问");
        }
        if ("replise".equals(action)){
            model.addAttribute("section","replise");
            model.addAttribute("sectionName","最新回复");
        }
        User user= (User) request.getSession().getAttribute("user");
        PageDto pageDto=questionService.selectbyid( user.getId(),page,pagesize);
        model.addAttribute("pagedto",pageDto);
        return "profile";
    }

//        @GetMapping("/test")
//    @ResponseBody
//    public PageDto test(HttpServletRequest request, Model model,
//                                  Integer userid,
//                                  @RequestParam(value = "page",defaultValue = "1")Integer page,
//                                  @RequestParam(value = "pagesize",defaultValue = "7")Integer pagesize){
//
//
//
//            PageDto pageDto=questionService.selectbyid( userid,page,pagesize);
//            return pageDto;
//    }
}
