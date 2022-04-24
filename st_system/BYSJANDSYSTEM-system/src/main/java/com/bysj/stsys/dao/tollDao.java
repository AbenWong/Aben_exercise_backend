package com.bysj.stsys.dao;

import com.bysj.stsys.model.Toll;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface tollDao {
    /**
     * 社长发布收款内容
     */
    public Integer addtoll(String tollkey,String stkey,String tolltitle,String tollmessage,String tolllastime,String money);
    /**
     * 查看收款信息
     */
    public List<Toll> findtoll(String stkey) ;
    /**
     * 关闭收款
     */
    public Integer closetoll(String tollkey);
}
