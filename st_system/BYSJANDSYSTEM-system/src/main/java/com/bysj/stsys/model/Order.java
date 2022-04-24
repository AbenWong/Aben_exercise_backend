package com.bysj.stsys.model;


public class Order {

  private String orderKey;
  private String orderTollkey;
  private String orderPaypeoplekey;
  private String orderStatue;
  private java.sql.Timestamp orderPaytime;
  private String orderPaymoney;
  public Toll toll;
  public User user;

  @Override
  public String toString() {
    return "Order{" +
            "orderKey='" + orderKey + '\'' +
            ", orderTollkey='" + orderTollkey + '\'' +
            ", orderPaypeoplekey='" + orderPaypeoplekey + '\'' +
            ", orderStatue='" + orderStatue + '\'' +
            ", orderPaytime=" + orderPaytime +
            ", orderPaymoney='" + orderPaymoney + '\'' +
            ", toll=" + toll +
            ", user=" + user +
            '}';
  }

  public Toll getToll() {
    return toll;
  }

  public void setToll(Toll toll) {
    this.toll = toll;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getOrderKey() {
    return orderKey;
  }

  public void setOrderKey(String orderKey) {
    this.orderKey = orderKey;
  }


  public String getOrderTollkey() {
    return orderTollkey;
  }

  public void setOrderTollkey(String orderTollkey) {
    this.orderTollkey = orderTollkey;
  }


  public String getOrderPaypeoplekey() {
    return orderPaypeoplekey;
  }

  public void setOrderPaypeoplekey(String orderPaypeoplekey) {
    this.orderPaypeoplekey = orderPaypeoplekey;
  }


  public String getOrderStatue() {
    return orderStatue;
  }

  public void setOrderStatue(String orderStatue) {
    this.orderStatue = orderStatue;
  }


  public java.sql.Timestamp getOrderPaytime() {
    return orderPaytime;
  }

  public void setOrderPaytime(java.sql.Timestamp orderPaytime) {
    this.orderPaytime = orderPaytime;
  }


  public String getOrderPaymoney() {
    return orderPaymoney;
  }

  public void setOrderPaymoney(String orderPaymoney) {
    this.orderPaymoney = orderPaymoney;
  }

}
