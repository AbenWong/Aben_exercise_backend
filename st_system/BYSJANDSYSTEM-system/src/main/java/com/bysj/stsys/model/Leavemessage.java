package com.bysj.stsys.model;

public class Leavemessage {

  private String lmKey;
  private String lmSendperson;
  private String lmReceiverperson;
  private String lmTitle;
  private String lmMessage;
  private java.sql.Timestamp lmCreatetime;
  private String delFlag;
  private String lmStatue;
  public User user;

  @Override
  public String toString() {
    return "Leavemessage{" +
            "lmKey='" + lmKey + '\'' +
            ", lmSendperson='" + lmSendperson + '\'' +
            ", lmReceiverperson='" + lmReceiverperson + '\'' +
            ", lmTitle='" + lmTitle + '\'' +
            ", lmMessage='" + lmMessage + '\'' +
            ", lmCreatetime=" + lmCreatetime +
            ", delFlag='" + delFlag + '\'' +
            ", lmStatue='" + lmStatue + '\'' +
            ", user=" + user +
            '}';
  }

  public String getLmKey() {
    return lmKey;
  }

  public void setLmKey(String lmKey) {
    this.lmKey = lmKey;
  }


  public String getLmSendperson() {
    return lmSendperson;
  }

  public void setLmSendperson(String lmSendperson) {
    this.lmSendperson = lmSendperson;
  }


  public String getLmReceiverperson() {
    return lmReceiverperson;
  }

  public void setLmReceiverperson(String lmReceiverperson) {
    this.lmReceiverperson = lmReceiverperson;
  }


  public String getLmTitle() {
    return lmTitle;
  }

  public void setLmTitle(String lmTitle) {
    this.lmTitle = lmTitle;
  }


  public String getLmMessage() {
    return lmMessage;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setLmMessage(String lmMessage) {
    this.lmMessage = lmMessage;
  }


  public java.sql.Timestamp getLmCreatetime() {
    return lmCreatetime;
  }

  public void setLmCreatetime(java.sql.Timestamp lmCreatetime) {
    this.lmCreatetime = lmCreatetime;
  }


  public String getDelFlag() {
    return delFlag;
  }

  public void setDelFlag(String delFlag) {
    this.delFlag = delFlag;
  }


  public String getLmStatue() {
    return lmStatue;
  }

  public void setLmStatue(String lmStatue) {
    this.lmStatue = lmStatue;
  }

}
