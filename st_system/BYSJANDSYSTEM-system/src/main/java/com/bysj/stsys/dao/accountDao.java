package com.bysj.stsys.dao;

import com.bysj.stsys.model.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface accountDao {
    /**
     * 查询账户信息
     */
    public Account findMyaccountInf(String userKey);
    /**
     * 新增账户
     *
     */
    public Integer addAccount(String userKey,String password,String key);
    /**
     * 充值
     */
    public  Integer topUp(String accountKey,String saveMoney);
    /**
     * 修改支付密码
     */
    public Integer updatePaypassword(String password,String accountKey);
    /**
     * 个人账户减少钱
     */
    public Integer myaccountpay(String userKey,String money);
}
