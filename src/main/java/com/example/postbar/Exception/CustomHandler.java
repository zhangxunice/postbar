package com.example.postbar.Exception;

import com.alibaba.fastjson.JSON;
import com.example.postbar.dto.ResultDto;
import com.example.postbar.enums.CustomEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zhangxu
 * @title
 * @date 2019/8/27 20:21
 */
@ControllerAdvice
public class CustomHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response) {
        ResultDto resultDto = null;
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            //返回JSON
            if (e instanceof CustomException) {
                resultDto = resultDto.errof((CustomException) e);
            } else {
                resultDto = resultDto.errof(CustomEnum.SYS_ERROR);
            }

            try {
                response.setCharacterEncoding("utf-8");
                response.setStatus(200);
                response.setContentType("application/json");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDto));
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return null;
        } else {
            //返回页面
            if (e instanceof CustomException) {
                model.addAttribute("msg", ((CustomException) e).getMsg());
            } else {
                model.addAttribute("msg", "服务器出差，请稍后再试");
            }
            return new ModelAndView("error.html");
        }


    }


}
