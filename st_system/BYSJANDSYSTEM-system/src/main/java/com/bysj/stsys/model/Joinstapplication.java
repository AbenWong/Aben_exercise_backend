package com.bysj.stsys.model;


public class Joinstapplication {

  private String applicationKey;
  private String applicationPerson;
  private String applicationStkey;
  private String applicationIntroduction;
  private String applicationMessage;
  private String applicationStatue;
  private java.sql.Date applicationTime;
  private String applicationFlag;
  public Stinf stinf;
  public User user;
  public String getApplicationFlag() {
    return applicationFlag;
  }

  public void setApplicationFlag(String applicationFlag) {
    this.applicationFlag = applicationFlag;
  }

  @Override
  public String toString() {
    return "Joinstapplication{" +
            "applicationKey='" + applicationKey + '\'' +
            ", applicationPerson='" + applicationPerson + '\'' +
            ", applicationStkey='" + applicationStkey + '\'' +
            ", applicationIntroduction='" + applicationIntroduction + '\'' +
            ", applicationMessage='" + applicationMessage + '\'' +
            ", applicationStatue='" + applicationStatue + '\'' +
            ", applicationTime=" + applicationTime +
            ", applicationFlag='" + applicationFlag + '\'' +
            ", stinf=" + stinf +
            ", user=" + user +
            '}';
  }

  public String getApplicationKey() {
    return applicationKey;
  }

  public void setApplicationKey(String applicationKey) {
    this.applicationKey = applicationKey;
  }


  public String getApplicationPerson() {
    return applicationPerson;
  }

  public void setApplicationPerson(String applicationPerson) {
    this.applicationPerson = applicationPerson;
  }


  public String getApplicationStkey() {
    return applicationStkey;
  }

  public void setApplicationStkey(String applicationStkey) {
    this.applicationStkey = applicationStkey;
  }


  public String getApplicationIntroduction() {
    return applicationIntroduction;
  }

  public void setApplicationIntroduction(String applicationIntroduction) {
    this.applicationIntroduction = applicationIntroduction;
  }


  public String getApplicationMessage() {
    return applicationMessage;
  }

  public void setApplicationMessage(String applicationMessage) {
    this.applicationMessage = applicationMessage;
  }


  public String getApplicationStatue() {
    return applicationStatue;
  }

  public void setApplicationStatue(String applicationStatue) {
    this.applicationStatue = applicationStatue;
  }


  public java.sql.Date getApplicationTime() {
    return applicationTime;
  }

  public void setApplicationTime(java.sql.Date applicationTime) {
    this.applicationTime = applicationTime;
  }

}
