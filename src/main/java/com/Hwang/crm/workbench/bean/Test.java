package com.Hwang.crm.workbench.bean;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Table;

@NameStyle(Style.normal)
@Table(name = "test")
public class Test {

  private long id;
  private String money;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getMoney() {
    return money;
  }

  public void setMoney(String money) {
    this.money = money;
  }

}
