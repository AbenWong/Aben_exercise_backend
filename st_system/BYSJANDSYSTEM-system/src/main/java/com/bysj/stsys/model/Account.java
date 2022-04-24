package com.bysj.stsys.model;


public class Account {

  private String accountKey;
  private String accountUserid;
  private String accountPassword;
  private long accountMoney;


  public String getAccountKey() {
    return accountKey;
  }

  public void setAccountKey(String accountKey) {
    this.accountKey = accountKey;
  }


  public String getAccountUserid() {
    return accountUserid;
  }

  public void setAccountUserid(String accountUserid) {
    this.accountUserid = accountUserid;
  }


  public String getAccountPassword() {
    return accountPassword;
  }

  public void setAccountPassword(String accountPassword) {
    this.accountPassword = accountPassword;
  }


  public long getAccountMoney() {
    return accountMoney;
  }

  public void setAccountMoney(long accountMoney) {
    this.accountMoney = accountMoney;
  }

}
