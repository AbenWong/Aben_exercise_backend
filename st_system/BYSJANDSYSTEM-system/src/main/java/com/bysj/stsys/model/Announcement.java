package com.bysj.stsys.model;


public class Announcement {

  private String atKey;
  private String atTitle;
  private String atMessage;
  private java.sql.Timestamp atCreatetime;
  private String atCreateperson;
  private String atLookperson;
  private String atStatue;
  private String delFlag;
  public Stinf stinf;

  public Stinf getStinf() {
    return stinf;
  }

  public void setStinf(Stinf stinf) {
    this.stinf = stinf;
  }

  public String getAtKey() {
    return atKey;
  }

  public void setAtKey(String atKey) {
    this.atKey = atKey;
  }


  public String getAtTitle() {
    return atTitle;
  }

  public void setAtTitle(String atTitle) {
    this.atTitle = atTitle;
  }


  public String getAtMessage() {
    return atMessage;
  }

  public void setAtMessage(String atMessage) {
    this.atMessage = atMessage;
  }


  public java.sql.Timestamp getAtCreatetime() {
    return atCreatetime;
  }

  public void setAtCreatetime(java.sql.Timestamp atCreatetime) {
    this.atCreatetime = atCreatetime;
  }


  public String getAtCreateperson() {
    return atCreateperson;
  }

  public void setAtCreateperson(String atCreateperson) {
    this.atCreateperson = atCreateperson;
  }


  public String getAtLookperson() {
    return atLookperson;
  }

  public void setAtLookperson(String atLookperson) {
    this.atLookperson = atLookperson;
  }


  public String getAtStatue() {
    return atStatue;
  }

  public void setAtStatue(String atStatue) {
    this.atStatue = atStatue;
  }


  public String getDelFlag() {
    return delFlag;
  }

  public void setDelFlag(String delFlag) {
    this.delFlag = delFlag;
  }

  @Override
  public String toString() {
    return "Announcement{" +
            "atKey='" + atKey + '\'' +
            ", atTitle='" + atTitle + '\'' +
            ", atMessage='" + atMessage + '\'' +
            ", atCreatetime=" + atCreatetime +
            ", atCreateperson='" + atCreateperson + '\'' +
            ", atLookperson='" + atLookperson + '\'' +
            ", atStatue='" + atStatue + '\'' +
            ", delFlag='" + delFlag + '\'' +
            ", stinf=" + stinf +
            '}';
  }
}
