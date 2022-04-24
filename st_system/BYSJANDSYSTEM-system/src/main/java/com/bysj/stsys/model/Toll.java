package com.bysj.stsys.model;

public class Toll {

  private String tollKey;
  private String tollSt;
  private String tollTitle;
  private String tollMessage;
  private java.sql.Date totllCreatetime;
  private java.sql.Date totllLastpaytime;
  private String totllPaymmoney;
  private String tollFlag;
  public Stinf stinf;

  @Override
  public String toString() {
    return "Toll{" +
            "tollKey='" + tollKey + '\'' +
            ", tollSt='" + tollSt + '\'' +
            ", tollTitle='" + tollTitle + '\'' +
            ", tollMessage='" + tollMessage + '\'' +
            ", totllCreatetime=" + totllCreatetime +
            ", totllLastpaytime=" + totllLastpaytime +
            ", totllPaymmoney='" + totllPaymmoney + '\'' +
            ", tollFlag='" + tollFlag + '\'' +
            ", stinf=" + stinf +
            '}';
  }

  public String getTollFlag() {
    return tollFlag;
  }

  public void setTollFlag(String tollFlag) {
    this.tollFlag = tollFlag;
  }

  public String getTollKey() {
    return tollKey;
  }

  public void setTollKey(String tollKey) {
    this.tollKey = tollKey;
  }


  public String getTollSt() {
    return tollSt;
  }

  public void setTollSt(String tollSt) {
    this.tollSt = tollSt;
  }


  public String getTollTitle() {
    return tollTitle;
  }

  public void setTollTitle(String tollTitle) {
    this.tollTitle = tollTitle;
  }


  public String getTollMessage() {
    return tollMessage;
  }

  public void setTollMessage(String tollMessage) {
    this.tollMessage = tollMessage;
  }


  public java.sql.Date getTotllCreatetime() {
    return totllCreatetime;
  }

  public void setTotllCreatetime(java.sql.Date totllCreatetime) {
    this.totllCreatetime = totllCreatetime;
  }


  public java.sql.Date getTotllLastpaytime() {
    return totllLastpaytime;
  }

  public void setTotllLastpaytime(java.sql.Date totllLastpaytime) {
    this.totllLastpaytime = totllLastpaytime;
  }


  public String getTotllPaymmoney() {
    return totllPaymmoney;
  }

  public void setTotllPaymmoney(String totllPaymmoney) {
    this.totllPaymmoney = totllPaymmoney;
  }

}
