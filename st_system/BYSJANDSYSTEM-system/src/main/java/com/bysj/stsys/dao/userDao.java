package com.bysj.stsys.dao;

import com.bysj.stsys.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface userDao {
    /**
     * 普通用户进行登录
     */
    public User userlogin(String username,String password);
    /**
     * 普通用户使用手机号登录
     */
    public User userloginBytel(String telNum);
    /**
     * 联合student表查询用户信息
     */
    public User findUserinf(String userKey);
    /**
     * 修改手机号
     */
    public Integer changeTel(String newTel,String userKey);
    /**
     * 修改密码
     */
    public Integer changePassword(String userKey,String newPassword);
    /**
     * 上传头像
     */
    public Integer updatetouxiang(String userKey,String path);
    /**
     * 社团管理员查看社员的详细信息
     */
    public User searchUserInf(String userKey);
    /**
     * 根据专业和学年找人
     */
    public List<User> findpersonList(String profecessKey,String xuenian);

}
