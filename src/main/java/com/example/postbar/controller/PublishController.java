package com.example.postbar.controller;



import com.example.postbar.cache.TagCache;
import com.example.postbar.entity.Question;
import com.example.postbar.entity.User;
import com.example.postbar.mapper.QuestionMapper;
import com.example.postbar.mapper.UserMapper;
import com.example.postbar.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author zhangxu
 * @title
 * @date 2019/8/18 20:30
 */
@Controller
public class PublishController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish(Model model){
        model.addAttribute("alltags", TagCache.get());
        return "publish";
    }

    @PostMapping("/publish")
    public String postpublish(@RequestParam(value = "title",required = false) String title,
                              String description,
                              String tag,
                              @RequestParam(value = "id",required = false) String id,
                              Model model, HttpServletRequest request){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        model.addAttribute("alltags", TagCache.get());
        User user= (User) request.getSession().getAttribute("user");

        if (user==null){
            model.addAttribute("erros","用户未登录");
            return "publish";
        }
        if (title==null||title==""){
            model.addAttribute("erros","标题不能为空");
            return  "publish";
        }
        if (description==null||description==""){
            model.addAttribute("erros","内容不能为空");
            return  "publish";
        }
        if (tag==null||tag==""){
            model.addAttribute("erros","标签不能为空");
            return  "publish";
        }
        String invatag=TagCache.isExistTag(tag);
        if (StringUtils.isNotBlank(invatag)){
            model.addAttribute("erros","不存在此标签"+invatag);
            return  "publish";
        }

        Question question=new Question();
        question.setId(id);
        question.setCreator(user.getId());
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
       questionService.publishquestion(question);
        return "redirect:/";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id")String id,Model model){
        Question question=questionMapper.selectById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        model.addAttribute("alltags", TagCache.get());
        return "publish";
    }
}
