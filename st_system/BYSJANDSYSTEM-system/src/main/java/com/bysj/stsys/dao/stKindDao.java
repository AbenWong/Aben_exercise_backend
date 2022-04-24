package com.bysj.stsys.dao;

import com.bysj.stsys.model.Stkind;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface stKindDao {
    public List<Stkind> getStKindList();
    /**
     * 修改社团类型名
     */
    public Integer updateStKindName(String stKindKey,String newStKindName);
    /**
     * 删除社团分类
     */
    public Integer delStkind(String stKindKey);
    /**
     * 新增社团
     */
    public Integer addStkind(String stKindKey,String stKindName);
    /**
     * 查看新增的社团是否存在
     */
    public List<Stkind> checkStkindNameIsPresence(String stKindName);
}
