package com.bysj.stsys.dao;

import com.bysj.stsys.model.Topup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface topupDao {
    /**
     * 用户新增用户充值记录
     */
    public Integer addmoney(String topupKey,String topupmoney,String topupperson);
    /**
     * 查看充值记录
     */
    public List<Topup> findtopupList(String userKey);
}
