package com.Hwang.crm.workbench.controller;

import com.Hwang.crm.base.bean.ResultVo;
import com.Hwang.crm.base.exception.CrmException;
import com.Hwang.crm.settings.bean.User;
import com.Hwang.crm.workbench.bean.Customer;
import com.Hwang.crm.workbench.service.CustomerService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;


@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //    分页多条件模糊查询
    @RequestMapping("/workbench/customer/list")
    public PageInfo<Customer> list(PageInfo<Customer> pageInfo, Customer customer) {

        return customerService.list(pageInfo,customer);
    }


    //    添加客户
    @RequestMapping("/workbench/customer/saveCustomer")
    public ResultVo<Object> saveCustomer(Customer customer,HttpSession session) {
        ResultVo<Object> resultVo = new ResultVo<>();
        User user = (User) session.getAttribute("user");
        try {
            customer.setCreateBy(user.getName());
            customerService.saveCustomer(customer);
            resultVo.setOk(true);

        } catch (CrmException c) {
            resultVo.setMessage(c.getMessage());
        }
        return resultVo;
    }

    //  编辑客户
    @RequestMapping("/workbench/customer/updateCustomer")
    public ResultVo<Object> updateCustomer(Customer customer , HttpSession session) {

        customer.setEditBy(((User)session.getAttribute("user")).getName());
        ResultVo<Object> resultVo = new ResultVo<>();

        try {
            customerService.updateCustomerById(customer);
            resultVo.setOk(true);
        } catch (CrmException c) {
            resultVo.setMessage(c.getMessage());
        }
        return resultVo;
    }

    //    删除客户
    @RequestMapping("/workbench/customer/deleteCustomer")
    public ResultVo<Object> deleteCustomer(String[] id) {

        ResultVo<Object> resultVo = new ResultVo<>();
        List<String> ids = Arrays.asList(id);
        try {
            customerService.deleteCustomer(ids);
            resultVo.setOk(true);
        } catch (CrmException crmException) {
            resultVo.setMessage(crmException.getMessage());
        }
        return resultVo;
    }
}


