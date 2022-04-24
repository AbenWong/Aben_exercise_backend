package com.bysj.stsys.dao;

import com.bysj.stsys.model.Collegeinf;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface collegeDao {
    /**
     * 查找学院列表
     */
    public List<Collegeinf> findcollegeList();
}
