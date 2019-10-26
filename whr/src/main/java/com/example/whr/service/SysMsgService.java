package com.example.whr.service;

import com.example.whr.common.HrUtils;
import com.example.whr.dao.SysMsgMapper;
import com.example.whr.bean.Hr;
import com.example.whr.bean.MsgContent;
import com.example.whr.bean.SysMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author huangchunmei
 * @create 2019/9/10 17:52
 */
@Service
@Transactional
public class SysMsgService {
    @Autowired
    private HrService hrService;
    @Autowired
    private SysMsgMapper sysMsgMapper;

    /**
     * 只有管理员可以发送系统消息
     * @param msg
     * @return
     */
    @PreAuthorize("hasRole('ROLE_admin')")
    public boolean sendMsg(MsgContent msg){
        //消息内容表里新增一条记录
        int result = sysMsgMapper.sendMsg(msg);
        //获取所有hr用户
        List<Hr> allHr = hrService.getAllHr();
        //给所有用户发送系统消息
        int result2 = sysMsgMapper.addMsg2AllHR(allHr,msg.getId());
        return result2 == allHr.size();
    }

    /**
     * 分页获取系统消息
     * @param page
     * @param size
     * @return
     */
    public List<SysMsg> getSysMsgByPage(Integer page,Integer size){
        int start = (page-1)*size;
        return sysMsgMapper.getSysMsg(start,size, HrUtils.getCurrentHr().getId());
    }

    /**
     *  标记已读
     * @param flag
     * @return
     */
    public boolean markRead(Long flag){
        if(flag != -1){
            return sysMsgMapper.markRead(flag,HrUtils.getCurrentHr().getId()) == 1;
        }
        sysMsgMapper.markRead(flag,HrUtils.getCurrentHr().getId());
        return true;
    }
}
