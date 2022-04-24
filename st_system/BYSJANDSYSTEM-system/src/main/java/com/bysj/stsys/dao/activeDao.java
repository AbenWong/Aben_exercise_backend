package com.bysj.stsys.dao;

import com.bysj.stsys.model.Active;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface activeDao {

    /**
     * 查看社团活动，需要传入参数，自己没加入的社团活动
     */
    public List<Active> findStActive(@Param(value = "StactiveList") List<String> StactiveList);
    /**
     * 查看社团活动详细信息 传入活动id
     */
    public Active findStactiveInf(String acKey);
    /**
     * 新增活动
     */
    public Integer addStactive(String ackey,String actitle,String accontent,String acmaxpeoplenum,
                                   String acplace,String actime,String acdate,String acotherinf,String accreatest,String userKey);
    /**
     * 查看我的活动申请
     */
    public List<Active> activeAppliaction(String userKey);
    /**
     * 查看所有的活动
     */
    public List<Active> findactive();
    /**
     * 修改活动状态
     */
    public Integer updateAcStatue(String statue,String ackey);
}
