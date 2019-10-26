package com.example.whr.controller;

import com.example.whr.bean.RespBean;
import com.example.whr.bean.Hr;
import com.example.whr.bean.MsgContent;
import com.example.whr.bean.SysMsg;
import com.example.whr.service.HrService;
import com.example.whr.service.SysMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 处理通知消息的Controller
 * 登录即可访问
 *
 * @author huangchunmei
 * @create 2019/9/10 14:00
 */
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private HrService hrService;
    @Autowired
    private SysMsgService sysMsgService;

    /**
     * 获取除系统管理员外的所有Hr用户信息
     *
     * @return
     */
    @GetMapping("/hrs")
    public List<Hr> getAllHr() {
        return hrService.getAllHrExceptAdmin();
    }

    /**
     * 系统管理员发送消息
     *
     * @param msg
     * @return
     */
    @RequestMapping(value = "/nf", method = RequestMethod.POST)
    public RespBean sendNF(MsgContent msg) {
        //发送消息
        if (sysMsgService.sendMsg(msg)) {
            return RespBean.ok("发送成功！");
        }
        return RespBean.error("发送失败！");

    }

    /**
     * 获取系统消息
     *
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/sysmsgs")
    public List<SysMsg> getSysMsg(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return sysMsgService.getSysMsgByPage(page,size);
    }

    @RequestMapping(value = "/markread", method = RequestMethod.PUT)
    public RespBean markRead(Long flag){
        //先设置为已读，再判断是多个还是单个
        if(sysMsgService.markRead(flag)){
            if(flag == -1){
                return RespBean.ok("multiple");
            }else{
                return RespBean.ok("single");
            }
        }else{
            if(flag == -1){
                return RespBean.error("multiple");
            }else{
                return RespBean.error("single");
            }
        }
    }

}
