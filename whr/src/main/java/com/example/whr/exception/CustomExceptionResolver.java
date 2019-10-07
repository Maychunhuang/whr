package com.example.whr.exception;

import com.google.common.collect.Maps;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 统一异常处理
 * 自定义异常处理类
 * @author huangchunmei
 * @create 2019/8/27 16:05
 */
@Component
public class CustomExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());
        Map<String,String> map = Maps.newHashMap();
        map.put("status","error");
        if(e instanceof DataIntegrityViolationException){
            map.put("message","该角色尚有关联的资源或用户，删除失败!");
        }
        mv.addAllObjects(map);
        return mv;
    }
}
