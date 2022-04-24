package com.bysj.stsys.dao;

import com.bysj.stsys.model.Stpersoninf;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface stPersonDao {
//    public void adddate(String key,Integer pid,String sid);

    /**
     * 统计社团人数
     * @param Stkey
     * @return
     */
    public Integer StAllNumber(String Stkey);
    /**
     * 查询自己所在哪些社团
     */
    public List<Stpersoninf> getStlist(String userkey);
    /**
     * 查询自己社团的人员
     */
    public List<Stpersoninf> searchStperson(String Stkey,String Userkey);
    /**
     * 查询自己加入了那些社团返回List
     */
    public List<String> searchMyjoinSt(String userKey);
    /**
     * 查询我加入的社团编号 返回String
     */
    public List<String> myJoinStkey(String userKey);
    /**
     * 查询此人是否管理社团
     */
    public List<Stpersoninf> searchIsStmanager(String userKey);
    /**
     * 同意申请后加入到社团表内
     */
    public Integer addinSt(String stpeKey,String stpePersonKey,String stPestid);
    /**
     *查找社团有哪些人
     */
    public List<Stpersoninf> searchstPerson(String stkey);
    /**
     * 退出社团，真的删除
     */
    public Integer tuichuSt(String key);
    /**
     * 取消社长
     */
    public Integer updateFuezeren(String oldStudentId,String Stkey);
    public Integer updateFuezeren2(String newStudentId,String Stkey);
    /**
     * 把社长加入到社团
     */
    public void createinSt(String stpeKey,String stpePersonKey,String stPestid);
}
