package com.Hwang.crm.workbench.bean.contacts;

import lombok.Data;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tbl_contacts")
@NameStyle(Style.normal)
public class Contacts {

  @Id
  private String id;
  private String owner;
  private String source;
  private String customerId;
  private String fullName;
  private String appellation;
  private String email;
  private String mPhone;
  private String job;
  private String birth;
  private String createBy;
  private String createTime;
  private String editBy;
  private String editTime;
  private String description;
  private String contactSummary;
  private String nextContactTime;
  private String address;


}
