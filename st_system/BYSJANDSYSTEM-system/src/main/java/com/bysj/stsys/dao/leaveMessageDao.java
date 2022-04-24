package com.bysj.stsys.dao;

import com.bysj.stsys.model.Leavemessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface leaveMessageDao {
    /**
     * 添加留言
     */
    public Integer addLeaveMessage(Leavemessage leavemessage);
    /**
     * 查看留言
     */
    public List<Leavemessage> findmymessageshowMyleaveessage(String userKey);
    /**
     * 修改留言状态
     */
    public Integer updateleaveStatue(String leavemessageKey);
}
