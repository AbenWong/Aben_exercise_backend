package com.bysj.stsys.dao;

import com.bysj.stsys.model.Profession;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface proferceDao {
    public List<Profession> findprofession(String collegeKey);
}
