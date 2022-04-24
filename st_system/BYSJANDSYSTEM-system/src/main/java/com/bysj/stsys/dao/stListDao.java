package com.bysj.stsys.dao;

import com.bysj.stsys.model.Stinf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface stListDao {
    /**
     * 查询所有的社团信息
     * @return
     */
    public List<Stinf> findStlist();
    /**
     * 管理员查询社团信息 不带条件的查询
     *
     */
    public List<Stinf> rootfindStlist();
    /**
     * 查询单个社团的信息
     */
    public Stinf findStinf(String Stkey);
    /**
     * 修改社团名字
     */
    public Integer updateStName(String newStName,String stKey);
    /**
     * 修改社团所属类别
     */
    public Integer updateStkind(String newStKind,String stKey);
    /**
     * 修改社团备注
     */
    public Integer updateStintf(String newStintf,String stKey);
    /**
     * 根据社团key获取下面的所属的社团
     */
    public List<Stinf> selectStinfByStkey(String stKindKey);
    /**
     * 查询出来要加入的社团的下拉列表的值
     * 传入参数是我加入的社团的列表
     */
    public List<Stinf> findNotJoinStList(@Param(value = "myJoinStList") List<String> myjoinStlist);
    /**
     * 上传头像
     */
    public Integer uploadStlogo(String stkey,String logo);
    /**
     * 修改社团负责人
     */
    public Integer updateFuzeren(String Stkey,String newStudent);
    /**
     * 修改社团状态
     */
    public Integer updateStstatue(String Stkey,String statue);
    /**
     * 添加社团
     */
    public Integer addSt(String stKey,String stname,String studentpersonId,String stKind,String stinf);
}
