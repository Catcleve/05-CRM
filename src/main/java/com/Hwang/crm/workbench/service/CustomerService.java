package com.Hwang.crm.workbench.service;

import com.Hwang.crm.settings.bean.User;
import com.Hwang.crm.workbench.bean.Customer;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CustomerService {

    PageInfo<Customer> list(PageInfo<Customer> pageInfo, Customer customer);
    

    void saveCustomer(Customer customer);

    void updateCustomerById(Customer customer);

    void deleteCustomer(List<String> ids);
}
