package com.Hwang.crm.workbench.service.Impl.customer;

import cn.hutool.core.util.StrUtil;
import com.Hwang.crm.base.exception.CrmEnum;
import com.Hwang.crm.base.exception.CrmException;
import com.Hwang.crm.base.util.DateTimeUtil;
import com.Hwang.crm.base.util.UUIDUtil;
import com.test.User;
import com.Hwang.crm.settings.mapper.UserMapper;
import com.Hwang.crm.workbench.bean.customer.Customer;
import com.Hwang.crm.workbench.bean.customer.CustomerRemark;
import com.Hwang.crm.workbench.mapper.CustomerMapper;
import com.Hwang.crm.workbench.mapper.CustomerRemarkMapper;
import com.Hwang.crm.workbench.service.customer.CustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Resource
    private UserMapper userMapper;

    @Autowired
    private CustomerRemarkMapper customerRemarkMapper;


//    多条件模糊查询
    @Override
    public PageInfo<Customer> list(PageInfo<Customer> pageInfo, Customer customer) {

//        创建example对象
        Example example = new Example(Customer.class);
//        添加查询条件
        Example.Criteria criteria = example.createCriteria();


//        判断，当名称不为空时，加入模糊查询
        if (StrUtil.isNotEmpty(customer.getName())) {
            criteria.andLike("name", "%" + customer.getName() + "%");
        }

//         当owner不为空时，通过owner模糊查询出符合条件的user对象，拿到id，然后进行customer查询
        if (StrUtil.isNotEmpty(customer.getOwner())) {
            Example example1 = new Example(User.class);
            example1.createCriteria().andLike("name", "%" + customer.getOwner() + "%");
            List<User> users = userMapper.selectByExample(example1);
            ArrayList<String> owners = new ArrayList<>();
            for (User user : users) {
                owners.add(user.getId());
            }
            criteria.andIn("owner", owners);
        }


//        公司座机查询
        if (StrUtil.isNotEmpty(customer.getPhone())) {
            criteria.andLike("phone", "%" + customer.getPhone() + "%");
        }

//         公司网站查询
        if (StrUtil.isNotEmpty(customer.getWebsite())) {
            criteria.andLike("website", "%" + customer.getWebsite() + "%");
        }


//        分页助手需要写在这里，如果写在controller，会导致给上面的user查询分页
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<Customer> activities = customerMapper.selectByExample(example);
        pageInfo = new PageInfo<>(activities);
        for (Customer custom : pageInfo.getList()) {
            User user = userMapper.selectByPrimaryKey(custom.getOwner());
            getCustomerRemark(custom);
            custom.setOwner(user.getName());
        }
        return pageInfo;
    }



    //    通过customer获取对应的customerRemark
    public void getCustomerRemark(Customer custom){
        Example remarkExample = new Example(CustomerRemark.class);
        remarkExample.createCriteria().andEqualTo("customerId", custom.getId());
        List<CustomerRemark> customerRemarks = customerRemarkMapper.selectByExample(remarkExample);
        customerRemarks.forEach(customerRemark -> {
            User user = userMapper.selectByPrimaryKey(customerRemark.getCreateBy());
            customerRemark.setImg(user.getImg());
            customerRemark.setCreateBy(user.getName());
        });
        custom.setCustomerRemarks(customerRemarks);
    }


//    添加客户
    @Override
    public void saveCustomer(Customer customer) {
        customer.setId(UUIDUtil.getUUID());
        customer.setCreateTime(DateTimeUtil.getSysTime());
        int i = customerMapper.insertSelective(customer);
        if (i == 0) {
            throw new CrmException(CrmEnum.CUSTOMER_ADD);
        }
    }

//    修改客户
    @Override
    public void updateCustomerById(Customer customer ) {
        customer.setEditTime(DateTimeUtil.getSysTime());
        int i = customerMapper.updateByPrimaryKeySelective(customer);
        if (i == 0) {
            throw new CrmException(CrmEnum.CUSTOMER_EDIT);
        }
    }

//    删除市场活动，一个或者多个
    @Override
    public void deleteCustomer(List<String> ids) {
        Example example = new Example(Customer.class);
        example.createCriteria().andIn("id",ids);
        int i = customerMapper.deleteByExample(example);
        if (i <= 0) {
            throw new CrmException(CrmEnum.ACTIVITY_DELETE);
        }
    }
}
