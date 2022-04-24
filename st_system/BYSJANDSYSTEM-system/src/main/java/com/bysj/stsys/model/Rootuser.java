package com.bysj.stsys.model;


public class Rootuser {

  @Override
  public String toString() {
    return "Rootuser{" +
            "userkey='" + userkey + '\'' +
            ", usernumber='" + usernumber + '\'' +
            ", userpassword='" + userpassword + '\'' +
            ", userflage='" + userflage + '\'' +
            ", userphoto='" + userphoto + '\'' +
            ", delflag='" + delflag + '\'' +
            '}';
  }

  private String userkey;
  private String usernumber;
  private String userpassword;
  private String userflage;
  private String userphoto;
  private String delflag;


  public String getUserkey() {
    return userkey;
  }

  public void setUserkey(String userkey) {
    this.userkey = userkey;
  }


  public String getUsernumber() {
    return usernumber;
  }

  public void setUsernumber(String usernumber) {
    this.usernumber = usernumber;
  }


  public String getUserpassword() {
    return userpassword;
  }

  public void setUserpassword(String userpassword) {
    this.userpassword = userpassword;
  }


  public String getUserflage() {
    return userflage;
  }

  public void setUserflage(String userflage) {
    this.userflage = userflage;
  }


  public String getUserphoto() {
    return userphoto;
  }

  public void setUserphoto(String userphoto) {
    this.userphoto = userphoto;
  }


  public String getDelflag() {
    return delflag;
  }

  public void setDelflag(String delflag) {
    this.delflag = delflag;
  }

}
