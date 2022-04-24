package com.bysj.stsys.dao;

import com.bysj.stsys.model.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface orderDao {
    /**
     * 社长发布收款
     */
   public Integer addorder(String orderkey,String ordertollkey,String paypeoplekey,String money);
    /**
     * 查询自己未支付订单
     */
    public List<Order> findMyorder(String userKey,String orderstatue,String tollstatue);
    /**
     * 查询和我有关的订单
     */
    public List<Order> findorderaboutme(String userKey);
    /**
     * 支付完成后修改我的订单状态
     */
    public Integer payupdatestatue(String orderKey);
    /**
     * 查询首款订单
     */
    public List<Order> StmanagerFindorder(String tollkey);
}
