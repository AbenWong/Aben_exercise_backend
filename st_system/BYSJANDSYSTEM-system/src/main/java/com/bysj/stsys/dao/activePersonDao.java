package com.bysj.stsys.dao;

import com.bysj.stsys.model.Activeperson;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface activePersonDao {
    /**
     * 查询自己加入了哪些活动，然后返回list
     */
    public List<String> findMyjionactive(String UserKey);
    /**
     * 查询当前活动已经有多少人参加了
     */
    public Integer findHowManyPeopleJoinActive(String acKey);
    /**
     * 报名
     */
    public Integer singInUp(String apKey,String userKey,String acKey);
    /**
     * 查看我已经报名的活动
     */
    public List<Activeperson> findMyjoinactiveList(String userKey);
    /**
     * 取消报名
     */
    public Integer delSinginUp(String userKey,String acKey);
    /**
     * 查询谁报了名
     */
    public List<Activeperson> searchActivePeopleInf(String acKey);
}
