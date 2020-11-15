package com.example.whr.controller;

import com.example.whr.config.VerifyCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码生成接口
 */
@RestController
public class VerifyCodeController {

    /**
     * 用输出流向前端输出验证码图片,同时要将验证码文本放在session中，用于登录比较
     */
    @GetMapping("/vercode")
    public void code(HttpSession session, HttpServletResponse resp) throws IOException {
        VerifyCode code = new VerifyCode();
        //获取验证码图
        BufferedImage image = code.getImage();
        //获取验证码文本
        String text = code.getText();
        //将验证码文本存放在Session中
        session.setAttribute("index_code",text);
        //将验证码图输出到前端
        VerifyCode.output(image,resp.getOutputStream());
    }
}
