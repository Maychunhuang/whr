package com.example.whr.dao;

import com.example.whr.bean.Hr;
import com.example.whr.bean.MsgContent;
import com.example.whr.bean.SysMsg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统消息
 *
 * @author huangchunmei
 * @create 2019/9/10 17:53
 */
public interface SysMsgMapper {
    /**
     * 发送消息
     *
     * @param msg
     * @return
     */
    int sendMsg(MsgContent msg);

    /**
     * 给所有的hr用户添加系统消息
     *
     * @param hrs
     * @param mid
     * @return
     */
    int addMsg2AllHR(@Param("hrs") List<Hr> hrs, @Param("mid") Long mid);

    /**
     * 获取系统消息
     *
     * @param start
     * @param size
     * @param hrid
     * @return
     */
    List<SysMsg> getSysMsg(@Param("start") int start, @Param("size") Integer size, @Param("hrid") Long hrid);

    /**
     * 标记已读,要么一次只能标记一个为已读，要么一次性全都标记为已读
     *
     * @param flag
     * @param hrid
     * @return
     */
    int markRead(@Param("flag") Long flag, @Param("hrid") Long hrid);
}
