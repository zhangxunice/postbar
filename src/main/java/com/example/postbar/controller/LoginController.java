package com.example.postbar.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.postbar.entity.User;
import com.example.postbar.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author zhangxu
 * @title
 * @date 2019/8/18 17:44
 */
@Controller
public class LoginController {

    @Autowired
    private UserMapper userMapper;


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user/login")
    public String userlogin(String username, String password,
                            HttpServletResponse response) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", username);
        User user = userMapper.selectOne(queryWrapper);
        if (username.equals(user.getName())) {
            if (password.equals(user.getPassword())) {
                String token = user.getToken();
                Cookie cookie = new Cookie("token", token);
                cookie.setDomain("localhost");
                cookie.setPath("/");
                response.addCookie(cookie);
//                request.getSession().setAttribute("user",user);
                return "redirect:/";
            } else {
                return "redirect:/login";
            }
        } else {
            return "redirect:/login";
        }

    }

    @GetMapping("/loginout")
    public String loginout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        Cookie cookieName = new Cookie("token", null);
        cookieName.setMaxAge(0);
        cookieName.setPath("/");
        response.addCookie(cookieName);
        return "redirect:/login";
    }
}
