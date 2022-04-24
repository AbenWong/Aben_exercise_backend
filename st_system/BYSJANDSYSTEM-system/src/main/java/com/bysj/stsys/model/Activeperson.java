package com.bysj.stsys.model;

public class Activeperson {

  public Active getActive() {
    return active;
  }

  public void setActive(Active active) {
    this.active = active;
  }

  @Override
  public String toString() {
    return "Activeperson{" +
            "apKey='" + apKey + '\'' +
            ", apPerson='" + apPerson + '\'' +
            ", apActivekey='" + apActivekey + '\'' +
            ", apTime=" + apTime +
            ", delFlag='" + delFlag + '\'' +
            ", active=" + active +
            ", user=" + user +
            '}';
  }

  private String apKey;
  private String apPerson;
  private String apActivekey;
  private java.sql.Timestamp apTime;
  private String delFlag;
  public Active active;
  public User user;


  public String getApKey() {
    return apKey;
  }

  public void setApKey(String apKey) {
    this.apKey = apKey;
  }


  public String getApPerson() {
    return apPerson;
  }

  public void setApPerson(String apPerson) {
    this.apPerson = apPerson;
  }


  public String getApActivekey() {
    return apActivekey;
  }

  public void setApActivekey(String apActivekey) {
    this.apActivekey = apActivekey;
  }


  public java.sql.Timestamp getApTime() {
    return apTime;
  }

  public void setApTime(java.sql.Timestamp apTime) {
    this.apTime = apTime;
  }


  public String getDelFlag() {
    return delFlag;
  }

  public void setDelFlag(String delFlag) {
    this.delFlag = delFlag;
  }

}
