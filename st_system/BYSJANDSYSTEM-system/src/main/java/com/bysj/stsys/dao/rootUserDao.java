package com.bysj.stsys.dao;

import com.bysj.stsys.model.Rootuser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface rootUserDao {
    public Rootuser rootLogin(String username,String password);
    public Rootuser rootfaceLogin(String username);
    public Integer updatepassword(String newpassword,String userkey);
}
