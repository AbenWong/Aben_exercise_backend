package com.bysj.stsys.dao;

import com.bysj.stsys.model.Staccounnt;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface staccountDao {
    public Integer addmoney(String stkey,String money);
    /**
     * 查询社团账户
     */
    public Staccounnt lookstaccount(String stkey);
}
