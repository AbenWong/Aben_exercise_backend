package com.bysj.stsys.model;

public class Topup {

  private String topupKey;
  private String topupMoney;
  private java.sql.Timestamp topupTime;
  private String topupPerson;

  @Override
  public String toString() {
    return "Topup{" +
            "topupKey='" + topupKey + '\'' +
            ", topupMoney='" + topupMoney + '\'' +
            ", topupTime=" + topupTime +
            ", topupPerson='" + topupPerson + '\'' +
            '}';
  }

  public String getTopupKey() {
    return topupKey;
  }

  public void setTopupKey(String topupKey) {
    this.topupKey = topupKey;
  }


  public String getTopupMoney() {
    return topupMoney;
  }

  public void setTopupMoney(String topupMoney) {
    this.topupMoney = topupMoney;
  }


  public java.sql.Timestamp getTopupTime() {
    return topupTime;
  }

  public void setTopupTime(java.sql.Timestamp topupTime) {
    this.topupTime = topupTime;
  }


  public String getTopupPerson() {
    return topupPerson;
  }

  public void setTopupPerson(String topupPerson) {
    this.topupPerson = topupPerson;
  }

}
