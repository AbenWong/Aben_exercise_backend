package com.bysj.stsys.model;

public class Staccounnt {
  @Override
  public String toString() {
    return "Staccounnt{" +
            "statKey='" + statKey + '\'' +
            ", statMoney='" + statMoney + '\'' +
            ", statPassword='" + statPassword + '\'' +
            '}';
  }

  private String statKey;
  private String statMoney;
  private String statPassword;


  public String getStatKey() {
    return statKey;
  }

  public void setStatKey(String statKey) {
    this.statKey = statKey;
  }


  public String getStatMoney() {
    return statMoney;
  }

  public void setStatMoney(String statMoney) {
    this.statMoney = statMoney;
  }


  public String getStatPassword() {
    return statPassword;
  }

  public void setStatPassword(String statPassword) {
    this.statPassword = statPassword;
  }

}
