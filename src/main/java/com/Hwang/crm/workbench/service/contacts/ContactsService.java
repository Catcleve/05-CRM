package com.Hwang.crm.workbench.service.contacts;

import com.Hwang.crm.workbench.bean.contacts.Contacts;
import com.github.pagehelper.PageInfo;

public interface ContactsService {
    //    多条件模糊查询
    PageInfo<Contacts> list(PageInfo<Contacts> pageInfo, Contacts contacts);
}
