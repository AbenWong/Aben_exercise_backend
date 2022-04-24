package com.bysj.stsys.model;


public class Active {

  @Override
  public String toString() {
    return "Active{" +
            "acKEy='" + acKEy + '\'' +
            ", acTitle='" + acTitle + '\'' +
            ", acContent='" + acContent + '\'' +
            ", acMaxpeoplenum=" + acMaxpeoplenum +
            ", acPlace='" + acPlace + '\'' +
            ", acTime='" + acTime + '\'' +
            ", acDate='" + acDate + '\'' +
            ", acOtherinf='" + acOtherinf + '\'' +
            ", acStatue='" + acStatue + '\'' +
            ", delFlag='" + delFlag + '\'' +
            ", acCreatest='" + acCreatest + '\'' +
            ", stinf=" + stinf +
            '}';
  }

  private String acKEy;
  private String acTitle;
  private String acContent;
  private long acMaxpeoplenum;
  private String acPlace;
  private String acTime;
  private String acDate;
  private String acOtherinf;
  private String acStatue;
  private String delFlag;
  private String acCreatest;
  public Stinf stinf;


  public String getAcKEy() {
    return acKEy;
  }

  public void setAcKEy(String acKEy) {
    this.acKEy = acKEy;
  }


  public String getAcTitle() {
    return acTitle;
  }

  public void setAcTitle(String acTitle) {
    this.acTitle = acTitle;
  }


  public String getAcContent() {
    return acContent;
  }

  public void setAcContent(String acContent) {
    this.acContent = acContent;
  }


  public long getAcMaxpeoplenum() {
    return acMaxpeoplenum;
  }

  public void setAcMaxpeoplenum(long acMaxpeoplenum) {
    this.acMaxpeoplenum = acMaxpeoplenum;
  }


  public String getAcPlace() {
    return acPlace;
  }

  public void setAcPlace(String acPlace) {
    this.acPlace = acPlace;
  }


  public String getAcTime() {
    return acTime;
  }

  public void setAcTime(String acTime) {
    this.acTime = acTime;
  }


  public String getAcDate() {
    return acDate;
  }

  public void setAcDate(String acDate) {
    this.acDate = acDate;
  }


  public String getAcOtherinf() {
    return acOtherinf;
  }

  public void setAcOtherinf(String acOtherinf) {
    this.acOtherinf = acOtherinf;
  }


  public String getAcStatue() {
    return acStatue;
  }

  public void setAcStatue(String acStatue) {
    this.acStatue = acStatue;
  }


  public String getDelFlag() {
    return delFlag;
  }

  public void setDelFlag(String delFlag) {
    this.delFlag = delFlag;
  }


  public String getAcCreatest() {
    return acCreatest;
  }

  public void setAcCreatest(String acCreatest) {
    this.acCreatest = acCreatest;
  }

}
