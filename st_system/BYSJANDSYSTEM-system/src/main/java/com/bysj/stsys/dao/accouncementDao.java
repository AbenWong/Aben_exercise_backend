package com.bysj.stsys.dao;

import com.bysj.stsys.model.Announcement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface accouncementDao {
    /**
     * 查询系统公告
     */
    public List<Announcement> findRootAccounceMent();
    /**
     * 利用ajax查看完整的系统公告
     */
    public String findAllMessage(String Atkey);
    /**
     * 查询社团公告
     */
    public List<Announcement> findStAccounceMent(@Param(value = "myjoinStList") List<String> myJoinStList);
    /**
     * 新增公告
     */
    public Integer addannounce(String atKey,String atTitle,String atMessage,String userKey,String stKey);
    /**
     * 查看我的社团公告
     */
    public List<Announcement> showMyannounce(String userKey);
    /**
     * 修改一下状态
     */
    public Integer updateAnnounceStatue(String anKey);
    /**
     * 查看系统管理员公告
     */
    public List<Announcement> findRootAccounceMentall();
    /**
     * 新增
     */
    public Integer rootaddannounce(String atKey,String atTitle,String atMessage);





}
