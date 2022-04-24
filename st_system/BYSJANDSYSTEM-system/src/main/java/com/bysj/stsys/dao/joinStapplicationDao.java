package com.bysj.stsys.dao;

import com.bysj.stsys.model.Joinstapplication;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface joinStapplicationDao {
    /**
     * 发起申请
     */
    public Integer addJoinapplication(String apkey,String userkey,String stkey,String myinf,String myreason);
    /**
     * 根据用户id查看申请列表
     */
    public List<Joinstapplication> showMyapplicate(String userKey);
    /**
     * 用户查看自己申请的详细信息
     */
    public Joinstapplication showMyapplicationInf(String Ank);
    /**
     * 查看要审核的申请
     */
    public List<Joinstapplication> dealWithJoinApplication(String stKey);
    /**
     * 查看申请完成的列表
     */
    public List<Joinstapplication> dealWithJoinApplicationresoult(String stKey);
    /**
     * 拒绝加入社团
     */
    public Integer refuseapplication(String applicationKey,String reason);
    /**
     * 同意加入社团
     */
    public Integer agreeApplication(String applicationKey);


}
