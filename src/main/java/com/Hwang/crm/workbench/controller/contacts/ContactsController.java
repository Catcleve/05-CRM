package com.Hwang.crm.workbench.controller.contacts;

import com.Hwang.crm.workbench.bean.contacts.Contacts;
import com.Hwang.crm.workbench.service.contacts.ContactsService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ContactsController {

    @Autowired
    private ContactsService contactsService;

    @RequestMapping("/workbench/contacts/list")
    @ResponseBody
    public PageInfo<Contacts> list(PageInfo<Contacts> pageInfo, Contacts contacts) {

        return contactsService.list(pageInfo, contacts);
    }
}
